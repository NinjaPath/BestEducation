package com.ninjapath.besteducation.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ninjapath.besteducation.R;

public class TeacherCoursesAdapter extends RecyclerView.Adapter<TeacherCoursesAdapter.ViewHolder> {

    private String[] linksToImages;
    private String[] courseSignature;
    private Context context;

    public TeacherCoursesAdapter(String[] linksToImages, String[] courseSignature, Context context) {
        this.linksToImages = linksToImages;
        this.courseSignature = courseSignature;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.courses_card, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = cardView.findViewById(R.id.image_card);
        TextView signature = cardView.findViewById(R.id.signature);
        Glide.with(context)
                .load(linksToImages[position])
                .into(imageView);
        imageView.setContentDescription(courseSignature[position]);
        signature.setText(courseSignature[position]);
    }

    @Override
    public int getItemCount() {
        return  linksToImages.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardView = itemView;
        }



    }

}
