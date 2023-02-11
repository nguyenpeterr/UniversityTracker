package com.example.nguyenpeter_c196.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenpeter_c196.CourseDetail;
import com.example.nguyenpeter_c196.Entities.CourseEntity;
import com.example.nguyenpeter_c196.R;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private List<CourseEntity> courses;

    private final Context context;

    private final LayoutInflater courseInflator;

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCourse;
        private CourseViewHolder(View itemView) {
            super(itemView);
            tvCourse = itemView.findViewById(R.id.tv_course_name);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                final CourseEntity currentCourse = courses.get(position);
                Intent i = new Intent(context, CourseDetail.class);
                i.putExtra("courseID", currentCourse.getCourseID());
                i.putExtra("courseName", currentCourse.getCourseName());
                i.putExtra("courseStart", currentCourse.getCourseStartDate());
                i.putExtra("courseEnd", currentCourse.getCourseEndDate());
                i.putExtra("courseStatus", currentCourse.getCourseStatus());
                i.putExtra("instructor", currentCourse.getInstructor());
                i.putExtra("phone", currentCourse.getPhone());
                i.putExtra("email", currentCourse.getEmail());
                i.putExtra("note", currentCourse.getNote());
                i.putExtra("termID", currentCourse.getTermID());
                context.startActivity(i);
            });
        }
    }

    public CourseAdapter(Context context) {
        courseInflator = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = courseInflator.inflate(R.layout.layout_courses_list, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (courses != null) {
            CourseEntity currentCourse = courses.get(position);
            String courseName = currentCourse.getCourseName();
            holder.tvCourse.setText(courseName);
        }
        else {
            holder.tvCourse.setText("No Course");
        }
    }

    public void setCourses(List<CourseEntity> coursesEntity) {
        courses = coursesEntity;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (courses != null) {
            return courses.size();
        }
        return 0;
    }
}

