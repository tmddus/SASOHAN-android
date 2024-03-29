package sy.project2019.itshow.sasohan2019.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import sy.project2019.itshow.sasohan2019.Model.DiaryModel;
import sy.project2019.itshow.sasohan2019.Model.FamilyModel;
import sy.project2019.itshow.sasohan2019.Model.giukDateModel;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    public static final String tableName = "tbl";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }


    // db 새로 생성시 호출
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE family (" + // 가족 테이블
                "    name TEXT PRIMARY KEY," + // 가족 이름
                "    phonenum  TEXT NOT NULL UNIQUE);" // 가족 번호
        );


        db.execSQL("create table giukDate(" + // 기념일 테이블
                "    'date' INTEGER not null," + // 날짜
                "    'name' text not null," + // 기념일 이름
                "    'token' text not null PRIMARY KEY" + // 고유값(random string)
                ");"
        );

        db.execSQL("create table diary(" + // 일기
                "    'token' TEXT not null PRIMARY KEY," + // 일기 고유값
                "    'title' TEXT not null," + // 일기 제목
                "    'content' TEXT not null," + // 일기 내용
                "    'writedate' INTEGER not null," + // 작성 날짜
                "    'receiver' TEXT not null," + // 일기 받는 이
                "     'issended' INTEGER," + // 보낼지 안보낼지
                "     'isprivate' INTEGER," + // 나만 볼지
                "    'receivedate' INTEGER" + // 보내는 날짜
                ");"
        );

        db.execSQL("create table gallary(" + // 갤러리 이미지
                "    'token' TEXT not null PRIMARY KEY," + // 사진 고유값
                "    'image' BLOB not null" + // 사진 binary 파일
                ");"
        );

        Toast.makeText(context, "table 생성 완료", Toast.LENGTH_SHORT).show();

    }

    //버전 변경
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }



    /*-------일기 관련 --------*/

    public void DiaryInsert(String title, String content, String receiver, int isSend, int isPrivate){ // 일기 저장
        SQLiteDatabase db = getWritableDatabase();
        String token = rndString(10);
        Date today = new Date();


        db.execSQL("INSERT INTO diary (token, title, content, writedate, " +
                        "receiver, issended, isprivate) values( '" + token +
                    "' , '" + title + "', '" + content + "',"+today.getTime() + ",'" + receiver + "'," + isSend + "," +  isPrivate + ");"
                );
    }

    public ArrayList<DiaryModel> getAllDiary(){ // 다이어리 전부 가져오기

        ArrayList<DiaryModel> arr = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("  SELECT * FROM diary", null);

        while (cursor.moveToNext()){
            boolean isSend = cursor.getInt(5) == 1;
            boolean isPrivate = cursor.getInt(6) == 1;
            arr.add(new DiaryModel(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getLong(3), cursor.getString(4), isSend, isPrivate)
                    );
        }
        return arr;

    }

    /*-------일기 관련 끝--------*/



    /*-------기념일 관련--------*/

    public void DateInsert(long date, String name){ // 기념일 저장하기

        SQLiteDatabase db = getWritableDatabase();
        String token = rndString(10);

        db.execSQL("INSERT INTO giukDate (token, date,  name) values('" +
                token +"'," +date + ",'" + name + "');"
        );
    }

    public ArrayList<giukDateModel> getAllGiukDate(){ // 기념일 모두 가져오기
        ArrayList<giukDateModel> arr = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("  SELECT * FROM giukDate", null);
        while (cursor.moveToNext()){
            arr.add(new giukDateModel(cursor.getLong(0), cursor.getString(1)));
        }
        return arr;
    }

    public ArrayList<String> getGiukDate(long date){ // 날짜에 해당하는 기념일 이름 가져오기
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM giukDate WHERE date = " + date, null);

        while (cursor.moveToNext()){
            arr.add(cursor.getString(0));
        }
        return arr;
    }

    /*-------기념일 관련 끝 --------*/





    /*-------연락처 관련 --------*/
    public void familyInsert(String name, String phoneNum){ // 연락처 저장
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO family (date, name) values(" +
                name + "," + phoneNum + ");"
        );
    }

    public ArrayList<FamilyModel> getAllFamily(){ // 연락처 모두 가져오기
        ArrayList<FamilyModel> arr= new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        FamilyModel model;

        Cursor cursor = db.rawQuery("SELECT * FROM family", null);

        while (cursor.moveToNext()){
            model = new FamilyModel(cursor.getString(0), cursor.getString(1));
            arr.add(model);
        }
        return arr;
    }

    public String getPhoneNum(String name){ // 이름 주면 번호 가져오기
        String result="error";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT phonenum FROM family WHERE name = " + name, null);

        while (cursor.moveToNext()){
            result = cursor.getString(0);
        }
        return result; // 행이 존재하지 않으면 error 를 반환
    }

    /*-------연락처 관련 끝 --------*/





    /*-------이미지 관련 --------*/

    public void imageInsert(byte[] img){
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement p = db.compileStatement("INSERT INTO gallary (token, image) values(?,?);");
        p.bindString(1, rndString(10));
        p.bindBlob(2, img);

        p.execute();

        Log.e("이미지 저장", "완료");

    }

    public ArrayList<byte[]> getImage(){ // 이미지 모두 가져오기
        ArrayList<byte[]> arr = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT image FROM gallary", null);

        while (cursor.moveToNext()){
            Log.e(cursor.getBlob(0).toString(), "이미지???");
            arr.add(cursor.getBlob(0));
        }

        Log.e("db에서 가져오기", "완료");


        return arr;

    }

    /*-------이미지 관련 끝--------*/




    private String rndString(int len){
        Random rnd =new Random();

        StringBuffer buf =new StringBuffer();

        for(int i=0;i<len;i++) {
            // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를,
            // false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.

            if (rnd.nextBoolean()) {
                buf.append((char) ((int) (rnd.nextInt(26)) + 97));
            } else {
                buf.append((rnd.nextInt(10)));
            }
        }

        return buf.toString();
    }


}
