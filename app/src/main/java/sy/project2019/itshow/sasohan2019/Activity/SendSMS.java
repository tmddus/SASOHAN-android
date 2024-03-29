package sy.project2019.itshow.sasohan2019.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sy.project2019.itshow.sasohan2019.DB.DBHelper;
import sy.project2019.itshow.sasohan2019.R;

public class SendSMS extends AppCompatActivity {
    Button sendbtn;
    EditText ed_title, ed_contents;
    DBHelper db;
    CheckBox isP;
    Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String rec = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_smstest);

        sendbtn = findViewById(R.id.btn_add);
        ed_title = findViewById(R.id.edit_title);
        ed_contents = findViewById(R.id.edit_content);
        isP = findViewById(R.id.isPrivate);

        //연락처 더미데이터
        arrayList = new ArrayList<>();
        arrayList.add("엄마");
        arrayList.add("아빠");
        arrayList.add("언니");
        arrayList.add("오빠");
        arrayList.add("할머니");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

        //스피너 설정
        spinner = (Spinner)findViewById(R.id.Receiver);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),arrayList.get(i)+"가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();

                rec = arrayList.get(i);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //문자전송버튼
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //입력한 값을 가져와 변수에 담는다
//                String phoneNo = textPhoneNo.getText().toString();
//                String sms = textSMS.getText().toString();

                if(!isP.isChecked()){

                    String phone = db.getPhoneNum(rec);
                    if(!phone.equals("error")){

                    }
                    String phoneNo = "01030676865"; //번호
                    String sms = ed_contents.getText().toString(); //내용

                    try {
                        //전송
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                        Toast.makeText(getApplicationContext(), "전송 완료!", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "SMS faild, please try again later!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }

                int isprivate = 0;
                if(isP.isChecked()){
                    isprivate = 1;
                }

                //db에 글 추가
                db.DiaryInsert(ed_title.getText().toString(), ed_contents.getText().toString(), rec, 0, isprivate);

                //db삽입성공시
                Toast toast = Toast.makeText(getApplicationContext(),"일기가 등록되었습니다.",Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });

    }
}
