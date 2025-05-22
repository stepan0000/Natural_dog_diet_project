package com.example.apprealisation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DataInput extends AppCompatActivity {
    EditText etDogname;
    EditText etDogWeight;
    EditText etDogage;

    EditText etDogFoodG;

    TextView dietTextView;
    Button buttonSubmit;

    Button buttonAddFood;

    Button buttonClearDiet;
    static String dogname;
    static int dogage;

        Spinner spinner_for_dog_activity;
    Spinner spinner_for_dog_breed;
    Spinner spinner_for_dog_status;

    Spinner spinner_for_dog_food;
    String dogweight_string;
    static float dogweight_float;
    static String selected_activitytype;
    static String selected_dogstatus;
    static String selected_breed;
    Spinner spinner_for_dog_gender;
    static String selected_gendertype;
    public static String getDogName() {
        return dogname;
    }

    public  static float getDogWeight() {
        return dogweight_float;
    }
    public  static int getDogAge() {
        return dogage;
    }

    public static String getDogActivity() {
        return selected_activitytype;
    }

    public static String getDogBreed() {
        return selected_breed;
    }

    public static String getDogGender() {
        return selected_gendertype;
    }

    public  static String getDogStatus() {
        return selected_dogstatus;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_input);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        etDogFoodG = findViewById(R.id.etDogFoodG);

        dietTextView  = findViewById(R.id.dietTextView);
        dietTextView.setText(MainActivity.dogRepo.DecodeDiet(MainActivity.dog));

        etDogname = findViewById(R.id.etDogname);
        etDogname.setText(MainActivity.dog.name);


        etDogWeight = findViewById(R.id.etDogWeight);
        etDogWeight.setText(Double.toString(MainActivity.dog.kg));

        etDogage = findViewById(R.id.etDogAge);
        etDogage.setText(Integer.toString(MainActivity.dog.age));

        spinner_for_dog_breed = findViewById(R.id.fordogbreed);
        spinner_for_dog_gender = findViewById(R.id.fordoggender);
        spinner_for_dog_status = findViewById(R.id.fordogstatus);
        spinner_for_dog_activity = findViewById(R.id.fordogactivity);
        spinner_for_dog_food = findViewById(R.id.fordogfood);

        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonAddFood = findViewById(R.id.buttonAddFood);
        buttonClearDiet = findViewById(R.id.buttonClearDiet);

        // Настройка Spinner
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

       List<String> foods = new ArrayList<>();

       MainActivity.dogRepo.ExportFoods(foods);

        ArrayAdapter<String> adapter_for_dog_food = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                foods);

        adapter_for_dog_food.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_for_dog_food.setAdapter(adapter_for_dog_food);

        spinner_for_dog_food.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_food = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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
        // Обработчик кнопки
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
                    Toast.makeText(DataInput.this,
                            "Выберите уровень активности собаки!",
                            Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                long selected;

                MainActivity.dog.ClearTraits();

                /*Инициализация особенностей по активности*/
                selected =  spinner_for_dog_activity.getSelectedItemId();

                if (selected == 1) {
                    MainActivity.dog.AddTrait(8);
                }

                if (selected == 2) {
                    MainActivity.dog.AddTrait(9);
                }

                if (selected == 3) {
                    MainActivity.dog.AddTrait(10);
                }

                /*Инициализация особенностей по беременности*/

                selected =  spinner_for_dog_status.getSelectedItemId();

                if (selected == 1) {
                    MainActivity.dog.AddTrait(1);
                }

                if (selected == 2) {
                    MainActivity.dog.AddTrait(2);
                }

                /*Инициализация особенностей по беременности*/

                selected =  spinner_for_dog_gender.getSelectedItemId();

                if (selected == 1) {
                    MainActivity.dog.AddTrait(6);
                }

                if (selected == 2) {
                    MainActivity.dog.AddTrait(7);
                }

                if (dogage<=1) {
                    MainActivity.dog.AddTrait(3);
                }

                if ((dogage>1)&(dogage<=5)) {
                    MainActivity.dog.AddTrait(4);
                }

                if ((dogage>5)) {
                    MainActivity.dog.AddTrait(5);
                }



                if (selected_breed.equals(getResources().getStringArray(R.array.dogbreed)[0])) {
                    Toast.makeText(DataInput.this,
                            "Выберите породу собаки!",
                            Toast.LENGTH_SHORT).show();
                    isValid = false;
                }


                if (selected_gendertype.equals(getResources().getStringArray(R.array.doggender)[0])) {
                    Toast.makeText(DataInput.this,
                            "Выберите пол собаки!",
                            Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                // Проверка статуса
                if (selected_dogstatus.equals(getResources().getStringArray(R.array.dogstatus)[0])) {
                    Toast.makeText(DataInput.this,
                            "Выберите положение вашей собаки!",
                            Toast.LENGTH_SHORT).show();
                    isValid = false;
                }

                // Если все данные корректны
                if (isValid) {
                    Toast.makeText(DataInput.this,
                            "Данные сохранены: " + dogname + ", " + dogweight_float + "кг, " +
                                    selected_activitytype + ", " + selected_breed + ", " +
                                    selected_gendertype + ", " + selected_dogstatus,
                            Toast.LENGTH_LONG).show();

                    // Вызываем вывод данных через DataWorking
                    MainActivity.dog.setName(dogname);
                    MainActivity.dog.setKg(dogweight_float);
                    MainActivity.dog.setAge(dogage);

                    MainActivity.dogRepo.updateDog(MainActivity.dog);

                    MainActivity.dogRepo.EstimateFood(MainActivity.dog);
                    MainActivity.dogRepo.EstimateNutrientsNorm(MainActivity.dog);
                    MainActivity.dogRepo.EstimateNutrientsConsumption(MainActivity.dog);

                    String ans = MainActivity.dog.GetNutrientsNorm() + "\n" + MainActivity.dog.GetNutrientsConsumption();
                    Intent intent = new Intent(DataInput.this, Results.class);
                    intent.putExtra("answer_key", ans);
                    startActivity(intent);
                }
            }
        });

        buttonAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = true;
                String food = spinner_for_dog_food.getSelectedItem().toString();

                int food_key = MainActivity.dogRepo.dogfoodsbyvalue.getAsInteger(food);
                double food_g = Double.parseDouble(etDogFoodG.getText().toString());
                MainActivity.dog.AddDogFood(food_key,food_g);
                /*MainActivity.dog.ClearDiet();*/
                dietTextView.setText(MainActivity.dogRepo.DecodeDiet(MainActivity.dog));
        }

        });

        buttonClearDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = true;
                MainActivity.dog.ClearDiet();
                dietTextView.setText(MainActivity.dogRepo.DecodeDiet(MainActivity.dog));
            }

        });

        String val;
        for (int trait:MainActivity.dog.traits) {
            val = MainActivity.dogRepo.traits.getAsString(Integer.toString(trait));

            if (trait == 1) {
                spinner_for_dog_status.setSelection(1);
            };

            if (trait == 2) {
                spinner_for_dog_status.setSelection(2);
            };

            if (trait == 6) {
                spinner_for_dog_gender.setSelection(1);
            };

            if (trait == 7) {
                spinner_for_dog_gender.setSelection(2);
            };

            if (trait == 8) {
                spinner_for_dog_activity.setSelection(1);
            };

            if (trait == 9) {
                spinner_for_dog_activity.setSelection(2);
            };

            if (trait == 10) {
                spinner_for_dog_activity.setSelection(3);
            };

        }
        Log.d("Diet",MainActivity.dog.GetNutrientsNorm());
        Log.d("Diet",MainActivity.dog.GetNutrientsConsumption());
    }
}