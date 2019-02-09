package com.example.week5daily5testassignment_part2.view.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.week5daily5testassignment_part2.R;
import com.example.week5daily5testassignment_part2.model.school.SchoolResponse;
import com.example.week5daily5testassignment_part2.view.activities.schoolsatresultactivity.SchoolSATResultActivity;

import java.util.List;

public class SchoolRecyclerViewAdapter extends RecyclerView.Adapter<SchoolRecyclerViewAdapter.ViewHolder> {

    List<SchoolResponse> schoolResponses;


    public SchoolRecyclerViewAdapter(List<SchoolResponse> schoolResponses) {
        this.schoolResponses = schoolResponses;
    }

    @NonNull
    @Override
    public SchoolRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_school, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolRecyclerViewAdapter.ViewHolder viewHolder, int position) {

        SchoolResponse schoolResponse = schoolResponses.get(position);

        if(schoolResponse != null) {

            viewHolder.setItemSchoolResponse(schoolResponse);

            viewHolder.schoolNameTextView.setText(schoolResponse.getSchoolName());
            viewHolder.schoolWebsiteTextView.setText(schoolResponse.getWebsite());
            viewHolder.schoolLocationTextView.setText(schoolResponse.getLocation());

        }

    }

    @Override
    public int getItemCount() {
        return schoolResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView schoolNameTextView;
        TextView schoolWebsiteTextView;
        TextView schoolLocationTextView;

        SchoolResponse itemSchoolResponse;

        public void setItemSchoolResponse(SchoolResponse itemSchoolResponse) {
            this.itemSchoolResponse = itemSchoolResponse;
        }

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            schoolNameTextView = itemView.findViewById(R.id.schoolNameTextViewId);
            schoolWebsiteTextView = itemView.findViewById(R.id.schoolWebsiteTextViewId);
            schoolLocationTextView = itemView.findViewById(R.id.schoolLocationTextViewId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(itemView.getContext(), SchoolSATResultActivity.class);
                    intent.putExtra("schoolName", itemSchoolResponse.getSchoolName());
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }
}
