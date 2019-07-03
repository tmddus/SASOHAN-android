package sy.project2019.itshow.sasohan2019.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import sy.project2019.itshow.sasohan2019.DB.DBHelper;
import sy.project2019.itshow.sasohan2019.DayDecorator;
import sy.project2019.itshow.sasohan2019.Model.giukDateModel;
import sy.project2019.itshow.sasohan2019.R;
import sy.project2019.itshow.sasohan2019.Activity.WriteDay;

public class Fragment3 extends Fragment {
    View view;
    TextView dayText;
    ImageButton writeBtn;
    MaterialCalendarView calendarView;
    Calendar currentDay=null;
    ArrayList<CalendarDay> dayArr;
    DBHelper db;
    ArrayList<giukDateModel> giukModelsArr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment3, container, false);

        init();

        giukModelsArr = db.getAllGiukDate();
        dayArr = new ArrayList<>();

        if(giukModelsArr.size() != 0){
            for(int i=0; i<giukModelsArr.size(); i++){
//                Calendar c = new GregorianCalendar(giukModelsArr.get(i).getDate());
//                Date d = new Date(giukModelsArr.get(i).getDate());
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(giukModelsArr.get(i).getDate());
                Log.e(c.get(Calendar.YEAR) + "" +  c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH) + "");
                CalendarDay Cday = new CalendarDay(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
                dayArr.add(Cday);
            }
        }

        calendarView.addDecorator(new DayDecorator(dayArr));

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                currentDay = date.getCalendar();
            }
        });

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "이씨발 온클릭 왜 안되냐구~", Toast.LENGTH_SHORT).show();
            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment3, container, false);


    }

    public void init(){
        dayText = view.findViewById(R.id.today_rememberDay_frag3);
        writeBtn = view.findViewById(R.id.writeDayBtn_frag33);
        calendarView = view.findViewById(R.id.calendar);

        db = new DBHelper(getActivity(), DBHelper.tableName, null, 1);

    }

}