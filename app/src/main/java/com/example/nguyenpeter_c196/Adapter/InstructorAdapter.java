package com.example.nguyenpeter_c196.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenpeter_c196.Entities.InstructorEntity;
import com.example.nguyenpeter_c196.R;

import java.util.ArrayList;
import java.util.List;

public class InstructorAdapter extends RecyclerView.Adapter<InstructorAdapter.InstructorViewHolder>{
    private List<InstructorEntity> instructors = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public InstructorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_ci_list, parent, false);
        return new InstructorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructorViewHolder holder, int position) {
        final InstructorEntity instructor = instructors.get(position);
        holder.tvInstructorName.setText(instructor.getInstructorName());
    }

    @Override
    public int getItemCount() {
        return instructors.size();
    }

    public InstructorEntity getMentorAtPosition(int position){
        return instructors.get(position);
    }

    class InstructorViewHolder extends RecyclerView.ViewHolder{
        private TextView tvInstructorName;

        public InstructorViewHolder(@NonNull View itemView){
            super(itemView);
            tvInstructorName = itemView.findViewById(R.id.tv_course_instructor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(instructors.get(position));
                    }
                }
            });
        }
    }

    public void setInstructors(List<InstructorEntity> instructors){
        this.instructors = instructors;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(InstructorEntity instructor);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
