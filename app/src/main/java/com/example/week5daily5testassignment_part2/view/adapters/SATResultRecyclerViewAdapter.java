package com.example.week5daily5testassignment_part2.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.week5daily5testassignment_part2.R;
import com.example.week5daily5testassignment_part2.model.school.SATResponse;

import java.util.ArrayList;

public class SATResultRecyclerViewAdapter extends RecyclerView.Adapter<SATResultRecyclerViewAdapter.ViewHolder> {

    ArrayList<SATResponse> satResponseArrayList;

    public SATResultRecyclerViewAdapter(ArrayList<SATResponse> satResponseArrayList) {
        this.satResponseArrayList = satResponseArrayList;
    }

    @NonNull
    @Override
    public SATResultRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sat_result, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SATResultRecyclerViewAdapter.ViewHolder viewHolder, int position) {

        SATResponse satResponse = satResponseArrayList.get(position);

        if(satResponse != null) {

            viewHolder.mathScoreTextView.setText(satResponse.getSatMathAvgScore());
            viewHolder.readingScoreTextView.setText(satResponse.getSatCriticalReadingAvgScore());
            viewHolder.writingScoreTextView.setText(satResponse.getSatWritingAvgScore());

        }

    }

    @Override
    public int getItemCount() {
        return satResponseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mathScoreTextView;
        TextView readingScoreTextView;
        TextView writingScoreTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mathScoreTextView = itemView.findViewById(R.id.mathScoreTextViewId);
            readingScoreTextView = itemView.findViewById(R.id.readingScoreTextViewId);
            writingScoreTextView = itemView.findViewById(R.id.writingScoreTextViewId);
        }
    }
}
