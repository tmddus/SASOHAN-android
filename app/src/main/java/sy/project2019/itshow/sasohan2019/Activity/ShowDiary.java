package sy.project2019.itshow.sasohan2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import sy.project2019.itshow.sasohan2019.R;

public class ShowDiary extends AppCompatActivity {
    String title;
//    String date;
//    String content;
//    String wdate;
    TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diary);

        tv_title = findViewById(R.id.sd_title);

        Intent intent = getIntent();


        title = intent.getStringExtra("title");
        tv_title.setText(title);


    }
}
