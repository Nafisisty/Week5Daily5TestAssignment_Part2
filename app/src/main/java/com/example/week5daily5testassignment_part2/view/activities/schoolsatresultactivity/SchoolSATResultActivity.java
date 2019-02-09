package com.example.week5daily5testassignment_part2.view.activities.schoolsatresultactivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.week5daily5testassignment_part2.R;
import com.example.week5daily5testassignment_part2.model.datasource.okhttp.OkHttpHelper;
import com.example.week5daily5testassignment_part2.model.events.SATResponseEvent;
import com.example.week5daily5testassignment_part2.model.school.SATResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;

import static com.example.week5daily5testassignment_part2.model.Constants.SAT_URL;

public class SchoolSATResultActivity extends AppCompatActivity {

    String name;
    TextView mathScoreTextView;
    TextView readingScoreTextView;
    TextView writingScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_satresult);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            name = bundle.getString("schoolName");

        }

        mathScoreTextView = findViewById(R.id.mathScoreTextViewId);
        readingScoreTextView = findViewById(R.id.readingScoreTextViewId);
        writingScoreTextView = findViewById(R.id.writingScoreTextViewId);

        OkHttpHelper.okHttpApiCall(SAT_URL);
    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void satResponseEvent(@NonNull SATResponseEvent event) {

        List<SATResponse> satResponses = Arrays.asList(event.getSatResponse());

        ArrayList<SATResponse> satResponseArrayList = new ArrayList<>();

        for (SATResponse satResponse :
                satResponses) {

            Log.d("TAG", "satResponseEvent: " + satResponse.getSchoolName().toLowerCase() + " " + name.toLowerCase());


            if(satResponse.getSchoolName().toLowerCase().equals(name.toLowerCase())) {
                satResponseArrayList.add(satResponse);
            }

        }


        mathScoreTextView.setText("Math Score: "+ satResponseArrayList.get(0).getSatMathAvgScore());
        readingScoreTextView.setText("Reading Score: " + satResponseArrayList.get(0).getSatCriticalReadingAvgScore());
        writingScoreTextView.setText("Writing Score: " + satResponseArrayList.get(0).getSatWritingAvgScore());


    }

}
