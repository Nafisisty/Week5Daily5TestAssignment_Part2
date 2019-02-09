package com.example.week5daily5testassignment_part2.model.datasource.okhttp;

import android.util.Log;

import com.example.week5daily5testassignment_part2.model.events.SATResponseEvent;
import com.example.week5daily5testassignment_part2.model.events.SchoolResponseEvent;
import com.example.week5daily5testassignment_part2.model.school.SATResponse;
import com.example.week5daily5testassignment_part2.model.school.SchoolResponse;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.week5daily5testassignment_part2.model.Constants.SCHOOL_URL;

public class OkHttpHelper {

    public static void okHttpApiCall(final String url) {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {

            String jsonResponse;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG", "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                jsonResponse = response.body().string();
                Log.d("TAG", "onResponse: " + jsonResponse);

                Gson gson = new Gson();
                if(url == SCHOOL_URL) {

                    SchoolResponse[] schoolResponse = gson.fromJson(jsonResponse, SchoolResponse[].class);
                    EventBus.getDefault().post(new SchoolResponseEvent(schoolResponse));

                } else {

                    SATResponse[] satResponse = gson.fromJson(jsonResponse, SATResponse[].class);
                    EventBus.getDefault().post(new SATResponseEvent(satResponse));

                }
            }
        });
    }

}
