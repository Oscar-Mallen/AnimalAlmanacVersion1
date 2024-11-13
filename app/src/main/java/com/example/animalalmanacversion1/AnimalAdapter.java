package com.example.animalalmanacversion1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private List<Animal> animalList;
    private Context context;

    public AnimalAdapter(List<Animal> animalList, Context context) {
        this.animalList = animalList;
        this.context = context;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_animal, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animalList.get(position);
        holder.animalName.setText(animal.getName());
        holder.animalDescription.setText(animal.getDescription());
        holder.animalImage.setImageResource(animal.getImageResource());
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    // ViewHolder class for holding views in each row of RecyclerView
    public static class AnimalViewHolder extends RecyclerView.ViewHolder {
        ImageView animalImage;
        TextView animalName, animalDescription;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            animalImage = itemView.findViewById(R.id.animal_image);
            animalName = itemView.findViewById(R.id.animal_name);
            animalDescription = itemView.findViewById(R.id.animal_description);
        }
    }
}
