package sy.project2019.itshow.sasohan2019.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

import sy.project2019.itshow.sasohan2019.R;
import sy.project2019.itshow.sasohan2019.Activity.WriteDay;

public class Fragment3 extends Fragment {
    View view;
    TextView dayText;
    ImageView writeBtn;
    MaterialCalendarView calendarView;
    Calendar currentDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment3, container, false);

        dayText = view.findViewById(R.id.today_rememberDay_frag3);
        writeBtn = view.findViewById(R.id.writeDayBtn);
        calendarView = view.findViewById(R.id.calendar);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                currentDay = date.getCalendar();
            }
        });

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), WriteDay.class);
                intent.putExtra("day", currentDay);
                startActivity(intent);

            }
        });



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment3, container, false);


    }

}