package com.example.animalalmanacversion1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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

        // Load animal list from CSV file
        animalList = CsvUtils.readAnimalsFromCsv(requireContext(), "animals.csv");

        recyclerView = rootView.findViewById(R.id.recycler_view_entries);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Set up the adapter
        animalAdapter = new AnimalAdapter(animalList, getContext());
        recyclerView.setAdapter(animalAdapter);

        // Handle item click event
        animalAdapter.setOnItemClickListener(new AnimalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Animal animal) {
                // Navigate to detail fragment
                Bundle bundle = new Bundle();
                bundle.putString("animalName", animal.getName());
                bundle.putString("animalDescription", animal.getDescription());
                bundle.putInt("animalImageResource", animal.getImageResource());

                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_homeFragment_to_animalDetailFragment, bundle);
            }
        });

        Button savedButton = rootView.findViewById(R.id.buttonNav3);

        // Set up the click listener for the Saved button
        savedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the SavedFragment when the button is clicked
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.savedFragment); // Ensure the savedFragment is in the nav graph
            }
        });

        return rootView;
    }
}

