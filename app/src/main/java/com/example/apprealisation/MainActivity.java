package com.example.apprealisation;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static DogRepository dogRepo;
    public static Dog dog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        dogRepo = new DogRepository(this);

        dog = dogRepo.getDog(1);



        double food = dogRepo.EstimateFood(dog);
        dogRepo.EstimateNutrientsNorm(dog);
        dogRepo.EstimateNutrientsConsumption(dog);

        Log.d("Diet",dogRepo.DecodeDiet(dog));

        Log.d("Foods",dogRepo.dogfoods.toString());

        Log.d("DogInfo",dog.toString());


        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ConstraintLayout layout = findViewById(R.id.main);
        layout.setBackgroundResource(R.drawable.mainactivitywallpaper);


        // Примеры использования
        /*Dog newDog = new Dog(0,"Шарик",2,2,6,18);


        long dogId = dogRepo.addDog(newDog);

        Dog savedDog = dogRepo.getDog(dogId);
        Log.d("NewDog", savedDog.toString());

        savedDog.setAge(13);
        savedDog.AddTrait(3);
        savedDog.AddTrait(4);
        savedDog.AddTrait(5);
        dogRepo.updateDog(savedDog);*/

        List<Dog> allDogs = dogRepo.getAllDogs();
        for (Dog d : allDogs) {
            Log.d("AllDogs", d.toString());
        }
        Button button_calculate = findViewById(R.id.buttonGoToDataInput);
        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataInput.class);

                startActivity(intent);
            }
        });



        Button button_add_pet = findViewById(R.id.buttonAddYourPet);
        button_add_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPet.class);

                startActivity(intent);
            }
        });


    }

}