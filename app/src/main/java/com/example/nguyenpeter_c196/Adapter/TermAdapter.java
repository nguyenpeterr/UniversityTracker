package com.example.nguyenpeter_c196.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenpeter_c196.Entities.TermEntity;
import com.example.nguyenpeter_c196.R;
import com.example.nguyenpeter_c196.TermDetail;

import java.util.ArrayList;
import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {
    private List<TermEntity> terms = new ArrayList<>();
    private final Context context;
    private final LayoutInflater termInflator;

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTerm;
        private TermViewHolder(View itemView) {
            super(itemView);
            tvTerm = itemView.findViewById(R.id.tv_term_name);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                final TermEntity current = terms.get(position);
                Intent i = new Intent(context, TermDetail.class);
                i.putExtra("termID", current.getTermID());
                i.putExtra("termName", current.getTermName());
                i.putExtra("termStartDate", current.getTermStartDate());
                i.putExtra("termEndDate", current.getTermEndDate());
                context.startActivity(i);
            });
        }
    }

    public TermAdapter(Context context) {
        termInflator = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = termInflator.inflate(R.layout.layout_terms_list, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if (terms != null) {
            TermEntity currentTerm = terms.get(position);
            String title = currentTerm.getTermName();
            holder.tvTerm.setText(title);
        }
        else {
            holder.tvTerm.setText("No Term");
        }
    }

    public void setTerms(List<TermEntity> termsEntity) {
        terms = termsEntity;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (terms != null) {
            return terms.size();
        }
        return 0;
    }
}
