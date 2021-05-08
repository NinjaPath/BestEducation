package com.ninjapath.besteducation.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ninjapath.besteducation.R;
import com.ninjapath.besteducation.model.CourseRecycler;

import java.util.List;

public class CoursesRecycleViewAdapter extends RecyclerView.Adapter<CoursesRecycleViewAdapter.ViewHolder> {
    private List<CourseRecycler> accountsArrayList;

    public CoursesRecycleViewAdapter(List<CourseRecycler> accountsArrayList) {
        this.accountsArrayList = accountsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView courseName = cardView.findViewById(R.id.course_name);
        TextView courseSubject = cardView.findViewById(R.id.course_subject);
        TextView coursePrice = cardView.findViewById(R.id.course_price);
        ImageView subjectIcon = cardView.findViewById(R.id.subject_icon);
        courseName.setText(accountsArrayList.get(position).getCourseName());
        courseSubject.setText(accountsArrayList.get(position).getSubject());
        coursePrice.setText(String.format("%s%s", accountsArrayList.get(position).getPrice(), (char) 0x20BD));
        Glide.with(cardView).
                load(accountsArrayList.get(position).getIconLink())
                .into(subjectIcon);
        subjectIcon.setContentDescription(accountsArrayList.get(position).getCourseName());

    }


    @Override
    public int getItemCount() {
        return accountsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }
}
