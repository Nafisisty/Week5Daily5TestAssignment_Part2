package com.example.week5daily5testassignment_part2.view.activities.mainactivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.week5daily5testassignment_part2.R;
import com.example.week5daily5testassignment_part2.model.datasource.okhttp.OkHttpHelper;
import com.example.week5daily5testassignment_part2.model.events.SchoolResponseEvent;
import com.example.week5daily5testassignment_part2.model.school.SchoolResponse;
import com.example.week5daily5testassignment_part2.view.adapters.SchoolRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

import static com.example.week5daily5testassignment_part2.model.Constants.SCHOOL_URL;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SchoolRecyclerViewAdapter schoolRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.schoolRecyclerViewId);
        OkHttpHelper.okHttpApiCall(SCHOOL_URL);

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
    public void schoolResponseEvent(@NonNull SchoolResponseEvent event) {

        List<SchoolResponse> schoolResponses = Arrays.asList(event.getSchoolResponse());

        schoolRecyclerViewAdapter = new SchoolRecyclerViewAdapter(schoolResponses);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(schoolRecyclerViewAdapter);
    }

}
