package com.example.nguyenpeter_c196.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nguyenpeter_c196.Entities.AssessmentEntity;
import com.example.nguyenpeter_c196.R;
import java.util.ArrayList;
import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
    private List<AssessmentEntity> assessments = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_assessments_list, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        final AssessmentEntity assessment = assessments.get(position);
        holder.textViewAssessmentName.setText(assessment.getAssessmentName());

    }

    @Override
    public int getItemCount() {
        return assessments.size();
    }

    public AssessmentEntity getAssessmentAtPosition(int position) {
        return assessments.get(position);
    }

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewAssessmentName;


        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAssessmentName = itemView.findViewById(R.id.tv_assessment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(assessments.get(position));
                    }
                }
            });
        }
    }

    public void setAssessments(List<AssessmentEntity> assessments) {
        this.assessments = assessments;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void onItemClick(AssessmentEntity assessment);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
