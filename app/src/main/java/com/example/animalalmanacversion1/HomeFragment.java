package com.example.animalalmanacversion1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private AnimalAdapter animalAdapter;
    private List<Animal> animalList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Animal of the Day setup (omitted for brevity)

        // Set up RecyclerView for Encyclopedia Entries
        recyclerView = rootView.findViewById(R.id.recycler_view_entries);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager); // Set the layout manager to create 2 columns

        // Sample Data (replace with dynamic data later)
        animalList = new ArrayList<>();
        animalList.add(new Animal("Lion", "Large wild cat", R.drawable.img));
        animalList.add(new Animal("Elephant", "Largest land mammal", R.drawable.img));
        animalList.add(new Animal("Giraffe", "Tallest land animal", R.drawable.img));
        animalList.add(new Animal("Tiger", "Another big wild cat", R.drawable.img));
        animalList.add(new Animal("Lion", "Large wild cat", R.drawable.img));
        animalList.add(new Animal("Elephant", "Largest land mammal", R.drawable.img));
        animalList.add(new Animal("Giraffe", "Tallest land animal", R.drawable.img));
        animalList.add(new Animal("Tiger", "Another big wild cat", R.drawable.img));

        // Set up the adapter
        animalAdapter = new AnimalAdapter(animalList, getContext());
        recyclerView.setAdapter(animalAdapter);

        return rootView;
    }
}
