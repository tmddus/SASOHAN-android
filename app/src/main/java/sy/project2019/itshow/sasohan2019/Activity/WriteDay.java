package sy.project2019.itshow.sasohan2019.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import sy.project2019.itshow.sasohan2019.DB.DBHelper;
import sy.project2019.itshow.sasohan2019.R;

public class WriteDay extends AppCompatActivity {

    EditText title;
    Button writeBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_day);

        db = new DBHelper(getApplicationContext(), DBHelper.tableName, null,1);

        writeBtn = findViewById(R.id.writeDayBtn);
        title = findViewById(R.id.dayTitle);


        final Calendar day = (Calendar) getIntent().getSerializableExtra("day");

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.DateInsert(day.getTimeInMillis(), title.getText().toString());
                Toast.makeText(getApplicationContext(), "일정이 등록되었습니다", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
