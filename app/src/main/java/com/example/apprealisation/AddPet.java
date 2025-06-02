package com.example.apprealisation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPet extends AppCompatActivity {


    EditText etDogname;
    EditText etDogWeight;
    EditText etDogage;
    Button buttonSubmit;
    static String dogname;
    static int dogage;
    Spinner spinner_for_dog_activity;
    Spinner spinner_for_dog_breed;
    Spinner spinner_for_dog_status;
    String dogweight_string;
    static float dogweight_float;
    static String selected_activitytype;
    static String selected_dogstatus;
    static String selected_breed;
    Spinner spinner_for_dog_gender;
    static String selected_gendertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button back_button = findViewById(R.id.buttonBack);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPet.this, MainActivity.class);

                startActivity(intent);
            }
        });

        etDogname = findViewById(R.id.etDogname);

        etDogWeight = findViewById(R.id.etDogWeight);

        etDogage = findViewById(R.id.etDogAge);

        spinner_for_dog_breed = findViewById(R.id.fordogbreed);
        spinner_for_dog_gender = findViewById(R.id.fordoggender);
        spinner_for_dog_status = findViewById(R.id.fordogstatus);
        spinner_for_dog_activity = findViewById(R.id.fordogactivity);


        buttonSubmit = findViewById(R.id.buttonSubmit);

        ArrayAdapter<CharSequence> adapter_for_activity_type = ArrayAdapter.createFromResource(
                this,
                R.array.activelifetime,
                R.layout.spinner_dropdown_item
        );
        adapter_for_activity_type.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_for_dog_activity.setAdapter(adapter_for_activity_type);
        spinner_for_dog_activity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_activitytype = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Действие при отсутствии выбора
            }
        });


        ArrayAdapter<CharSequence> adapter_for_dog_breed = ArrayAdapter.createFromResource(
                this,
                R.array.dogbreed,
                R.layout.spinner_dropdown_item
        );
        adapter_for_activity_type.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_for_dog_breed.setAdapter(adapter_for_dog_breed);
        spinner_for_dog_breed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_breed = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Действие при отсутствии выбора
            }
        });


        ArrayAdapter<CharSequence> adapter_for_dog_gender = ArrayAdapter.createFromResource(
                this,
                R.array.doggender,
                R.layout.spinner_dropdown_item
        );
        adapter_for_dog_gender.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_for_dog_gender.setAdapter(adapter_for_dog_gender);
        spinner_for_dog_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_gendertype = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        ArrayAdapter<CharSequence> adapter_for_dog_status = ArrayAdapter.createFromResource(
                this,
                R.array.dogstatus,
                R.layout.spinner_dropdown_item
        );
        adapter_for_dog_status.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_for_dog_status.setAdapter(adapter_for_dog_status);
        spinner_for_dog_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_dogstatus = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = true;

                // Проверка имени
                dogname = etDogname.getText().toString();
                if (dogname.isEmpty()) {
                    etDogname.setError("Введите имя собаки!");
                    isValid = false;
                }
                String dogage_string;
                dogage_string = etDogage.getText().toString();
                dogage = Integer.parseInt(dogage_string);
                if (dogage_string.isEmpty()) {
                    etDogage.setError("Введите возраст собаки!");
                    isValid = false;
                }

                // Проверка веса
                dogweight_string = etDogWeight.getText().toString();
                if (dogweight_string.isEmpty()) {
                    etDogWeight.setError("Введите вес собаки!");
                    isValid = false;
                } else {
                    try {
                        dogweight_float = Float.parseFloat(dogweight_string);
                    } catch (NumberFormatException e) {
                        etDogWeight.setError("Введите корректный вес!");
                        isValid = false;
                    }
                }
                if (dogweight_float <= 0) {
                    etDogWeight.setError("Введите корректный вес!");
                }
                // Проверка активности
                if (selected_activitytype.equals(getResources().getStringArray(R.array.activelifetime)[0])) {
                    Toast.makeText(AddPet.this,
                            "Выберите уровень активности собаки!",
                            Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                long selected;

                if (selected_breed.equals(getResources().getStringArray(R.array.dogbreed)[0])) {
                    Toast.makeText(AddPet.this,
                            "Выберите породу собаки!",
                            Toast.LENGTH_SHORT).show();
                    isValid = false;
                }


                if (selected_gendertype.equals(getResources().getStringArray(R.array.doggender)[0])) {
                    Toast.makeText(AddPet.this,
                            "Выберите пол собаки!",
                            Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                // Проверка статуса
                if (selected_dogstatus.equals(getResources().getStringArray(R.array.dogstatus)[0])) {
                    Toast.makeText(AddPet.this,
                            "Выберите положение вашей собаки!",
                            Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                // Если все данные корректны
                if (isValid) {
                    Toast.makeText(AddPet.this,
                            "Данные сохранены: " + dogname + ", " + dogweight_float + "кг, " +
                                    selected_activitytype + ", " + selected_breed + ", " +
                                    selected_gendertype + ", " + selected_dogstatus,
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddPet.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}