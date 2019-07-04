package sy.project2019.itshow.sasohan2019.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import sy.project2019.itshow.sasohan2019.DB.DBHelper;
import sy.project2019.itshow.sasohan2019.R;

public class addFamilyActivity extends AppCompatActivity {

    TextView name, phneNum;
    DBHelper db;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_family);
        init();
        db = new DBHelper(getApplication(), DBHelper.tableName, null, 1);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString().equals("") || phneNum.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show();
                }

                db.familyInsert(name.getText().toString(), phneNum.getText().toString());
                finish();
            }
        });



    }

    public void init(){
        name = findViewById(R.id.family_name);
        phneNum = findViewById(R.id.family_phoneNum);
        addBtn = findViewById(R.id.family_addBtn);

    }
}
