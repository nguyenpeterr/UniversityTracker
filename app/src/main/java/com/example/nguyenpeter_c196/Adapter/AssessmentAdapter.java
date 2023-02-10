package com.example.nguyenpeter_c196.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenpeter_c196.AssessmentsDetail;
import com.example.nguyenpeter_c196.Entities.AssessmentEntity;
import com.example.nguyenpeter_c196.R;
import java.util.ArrayList;
import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
    private List<AssessmentEntity> assessments;
    private final Context context;
    private final LayoutInflater assessmentInflator;

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvAssessment;
        private AssessmentViewHolder(View itemView) {
            super(itemView);
            tvAssessment = itemView.findViewById(R.id.tv_assessment);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                final AssessmentEntity current = assessments.get(position);
                Intent intent = new Intent(context, AssessmentsDetail.class);
                intent.putExtra("assessment_id", current.getAssessmentID());
                intent.putExtra("title", current.getAssessmentName());
                intent.putExtra("type", current.getAssessmentType());
                intent.putExtra("start", current.getAssessmentStart());
                intent.putExtra("end", current.getAssessmentEnd());
                intent.putExtra("course_id", current.getCourseID());
                context.startActivity(intent);
            });
        }
    }

    public AssessmentAdapter(Context context) {
        assessmentInflator = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = assessmentInflator.inflate(R.layout.layout_assessments_list, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (assessments != null) {
            AssessmentEntity current = assessments.get(position);
            String title = current.getAssessmentName();
            holder.tvAssessment.setText(title);
        }
        else {
            holder.tvAssessment.setText("No assessment title.");
        }
    }

    public void setAssessments(List<AssessmentEntity> assessmentsEntity) {
        assessments = assessmentsEntity;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (assessments != null) {
            return assessments.size();
        }
        return 0;
    }
}
