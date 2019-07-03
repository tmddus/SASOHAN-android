package sy.project2019.itshow.sasohan2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import sy.project2019.itshow.sasohan2019.R;

public class ShowDiary extends AppCompatActivity {
    String title, date, content, send;
    Intent intent;
    TextView tv_title, tv_date, tv_content, tv_send ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diary);
        init();


        tv_title.setText(title);
        tv_date.setText(date);
        tv_content.setText(content);
        tv_send.setText(send);


    }

    public void init(){
        tv_title = findViewById(R.id.sd_title);
        tv_date = findViewById(R.id.sd_date);
        tv_content = findViewById(R.id.sd_cont);
        tv_send = findViewById(R.id.sd_send);
        intent = getIntent();
        title = intent.getStringExtra("title");
        date = intent.getStringExtra("date");
        content = intent.getStringExtra("content");
        send = intent.getStringExtra("rec");
    }
}
