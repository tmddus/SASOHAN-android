package sy.project2019.itshow.sasohan2019.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class WriteActivity extends AppCompatActivity {
    Button addbtn;
    String title, contents;
    EditText ed_title, ed_contents;
    Intent intent;
    DBHelper db;
    CheckBox isP;
    Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saranghae_wirte);

        addbtn = findViewById(R.id.btn_add);
        ed_title = findViewById(R.id.edit_title);
        ed_contents = findViewById(R.id.edit_content);
        isP = findViewById(R.id.isPrivate);


        arrayList = new ArrayList<>();
        arrayList.add("엄마");
        arrayList.add("아빠");
        arrayList.add("언니");
        arrayList.add("오빠");
        arrayList.add("할머니");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

        spinner = (Spinner)findViewById(R.id.Receiver);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(view.isPressed()){
                    Toast.makeText(getApplicationContext(),arrayList.get(i)+"가 선택되었습니다.",
                            Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getApplicationContext(),"선택안되었습니다.",
//                            Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(getApplicationContext(),arrayList.get(i)+"가 선택되었습니다.",
//                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
