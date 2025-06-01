package com.example.apprealisation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView answerTextView = findViewById(R.id.outputTextView);
        String ans = getIntent().getStringExtra("answer_key");
        if (ans != null) {
            answerTextView.setText(ans);
        }


        TextView recomTextView = findViewById(R.id.recomTextView);
        double[] nums = extractDoublesFromString(ans);
        String nums_array_str = analyzeDogDiet(nums);
        if ( nums_array_str != null ) {
            recomTextView.setText(nums_array_str);
        }



        Button back_button = findViewById(R.id.buttonBack);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Results.this, DataInput.class);

                startActivity(intent);
            }
        });




    }




    public static double[] extractDoublesFromString(String input) {
        if (input == null || input.isEmpty()) {
            return new double[0];
        }

        // Регулярное выражение для поиска чисел (включая отрицательные и с плавающей точкой)
        Pattern pattern = Pattern.compile("-?\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(input);

        List<Double> numbersList = new ArrayList<>();

        while (matcher.find()) {
            try {
                double number = Double.parseDouble(matcher.group());
                numbersList.add(number);
            } catch (NumberFormatException e) {
                // Пропускаем некорректные числа (хотя regex должен отфильтровать такие случаи)
            }
        }

        // Конвертируем List<Double> в double[]
        double[] result = new double[numbersList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = numbersList.get(i);
        }

        return result;
    }


    public static String analyzeDogDiet(double[] nutrients) {
        if (nutrients == null || nutrients.length != 9) {
            throw new IllegalArgumentException("Массив должен содержать ровно 9 элементов");
        }

        // Извлекаем данные из массива
        double requiredTotal = nutrients[0];
        double requiredProtein = nutrients[1];
        double requiredFat = nutrients[2];
        double requiredCarbs = nutrients[3];
        double requiredFiber = nutrients[4];

        double actualProtein = nutrients[5];
        double actualFat = nutrients[6];
        double actualCarbs = nutrients[7];
        double actualFiber = nutrients[8];

        // Рассчитываем разницы
        double totalDiff = requiredTotal - (actualProtein + actualFat + actualCarbs + actualFiber);
        double proteinDiff = requiredProtein - actualProtein;
        double fatDiff = requiredFat - actualFat;
        double carbsDiff = requiredCarbs - actualCarbs;
        double fiberDiff = requiredFiber - actualFiber;

        // Формируем части сообщения
        String totalMessage = formatDifferenceMessage(totalDiff, "граммов");
        String proteinMessage = formatDifferenceMessage(proteinDiff, "граммов");
        String fatMessage = formatDifferenceMessage(fatDiff, "граммов");
        String carbsMessage = formatDifferenceMessage(carbsDiff, "граммов");
        String fiberMessage = formatDifferenceMessage(fiberDiff, "граммов");

        // Собираем итоговое сообщение
        return String.format(
                "Согласно проведённому расчёту нынешней диеты вашей собаки, вашему питомцу необходимо потреблять: " +
                        "на %s полезных веществ в день, %s белков, %s жиров, %s углеводов, %s клетчатки.",
                totalMessage, proteinMessage, fatMessage, carbsMessage, fiberMessage
        );
    }

    private static String formatDifferenceMessage(double difference, String unit) {
        double absDiff = Math.abs(difference);
        String direction = difference > 0 ? "больше" : "меньше";
        return String.format("%.1f %s (%s)", absDiff, unit, direction);
    }



    public static String convertToString(double[] array) {
        if (array == null) {
            return "null";
        }

        if (array.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }

        return sb.toString();
    }



}