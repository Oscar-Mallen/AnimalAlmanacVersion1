package com.example.animalalmanacversion1;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
    public static List<Animal> readAnimalsFromCsv(Context context, String fileName) {
        List<Animal> animalList = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            // Skip the header line if present
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 5) {
                    String animalName = tokens[0].trim();
                    String scientificName = tokens[1].trim();
                    String dietType = tokens[2].trim();
                    String description = tokens[3].trim();
                    String imageFolderName = tokens[4].trim();

                    // Assuming imageFolderName is used to find images within folders in the assets directory
                    int imageResourceId = context.getResources().getIdentifier(imageFolderName, "drawable", context.getPackageName());

                    if (imageResourceId == 0) {
                        imageResourceId = R.drawable.img; // Ensure a default image is available
                    }


                    Animal animal = new Animal(animalName, scientificName, dietType, description, imageResourceId);
                    animalList.add(animal);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return animalList;
    }
}
