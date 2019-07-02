package sy.project2019.itshow.sasohan2019.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import sy.project2019.itshow.sasohan2019.DB.DBHelper;
import sy.project2019.itshow.sasohan2019.Fragment.FragDefault;
import sy.project2019.itshow.sasohan2019.Fragment.Fragment3;
import sy.project2019.itshow.sasohan2019.Fragment.GallayFragment;
import sy.project2019.itshow.sasohan2019.Fragment.ListFragment;
import sy.project2019.itshow.sasohan2019.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DBHelper helper;
    SQLiteDatabase database;

    private final int FRAGMENT = 0;
    private final int FRAGMENT1 = 1;
    private final int FRAGMENT2 = 2;
    private final int FRAGMENT3 = 3;

    private  TextView btn_Home;

    BottomNavigationView bottomView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCustomActionBar();


        bottomView = findViewById(R.id.navigation);


        btn_Home = findViewById(R.id.btnHome);
        btn_Home.setOnClickListener(this);



        // 임의로 액티비티 호출 시점에 어느 프레그먼트를 프레임레이아웃에 띄울 것인지를 정함
        callFragment(FRAGMENT);

        helper = new DBHelper(MainActivity.this, DBHelper.tableName, null, 1);
        database = helper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name ='family'" , null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            Log.e("데이터베이스", "있음");

        }


        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.navigation_sarang:
                        callFragment(FRAGMENT1);
                        return true;

                    case R.id.navigation_giuk:
                        callFragment(FRAGMENT3);
                        return true;

                    case R.id.navigation_sojung:
                        callFragment(FRAGMENT2);
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnHome:
                // '홈' 클릭 시 '프래그먼트' 호출
                callFragment(FRAGMENT);
                break;
        }
    }

    private void callFragment(int frament_no){

        // 프래그먼트 사용을 위해
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (frament_no){
            case FRAGMENT:
                // '프래그먼트1' 호출
                FragDefault dfragment = new FragDefault();
                transaction.replace(R.id.fragment_container, dfragment);
                transaction.commit();
                break;
            case FRAGMENT1:
                // '프래그먼트1' 호출
                ListFragment fragment1 = new ListFragment();
                transaction.replace(R.id.fragment_container, fragment1);
                transaction.commit();
                break;

            case FRAGMENT2:
                // '프래그먼트2' 호출
                GallayFragment fragment2 = new GallayFragment();
                transaction.replace(R.id.fragment_container, fragment2);
                transaction.commit();
                break;

            case FRAGMENT3:
                // '프래그먼트2' 호출
                Fragment3 fragment3 = new Fragment3();
                transaction.replace(R.id.fragment_container, fragment3);
                transaction.commit();
                break;
        }

    }


    public void setCustomActionBar(){
        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.custom_title, null);

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);



    }

}
