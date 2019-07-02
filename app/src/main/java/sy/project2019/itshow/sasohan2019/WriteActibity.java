package sy.project2019.itshow.sasohan2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import sy.project2019.itshow.sasohan2019.DB.DBHelper;

public class WriteActibity extends AppCompatActivity {
    Button addbtn;
    String title, contents;
    EditText ed_title, ed_contents;
    Intent intent;
    DBHelper db;
    CheckBox isP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saranghae_wirte);

        addbtn = findViewById(R.id.btn_add);
        ed_title = findViewById(R.id.edit_title);
        ed_contents = findViewById(R.id.edit_content);
        isP = findViewById(R.id.isPrivate);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db에 글 추가
//                db.DiaryInsert(ed_title.getText().toString(), ed_contents.getText().toString());

                //db삽입성공시
                Toast toast = Toast.makeText(getApplicationContext(),"일기가 등록되었습니다.",Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });
    }
}
