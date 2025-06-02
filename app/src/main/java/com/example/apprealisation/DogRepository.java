package com.example.apprealisation;

import static java.util.Collections.max;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class DogRepository {

    public static ContentValues traits;

    public static ContentValues breeds;

    public static ContentValues dogfoods;

    public static ContentValues dogfoodsbyvalue;

    public static ContentValues doggenders;

    private final DatabaseManager dbManager;

    public static List<Dog> dogs;

    public DogRepository(Context context) {
        dbManager = new DatabaseManager(context);
        breeds = new ContentValues();
        traits = new ContentValues();
        dogfoods = new ContentValues();
        dogfoodsbyvalue = new ContentValues();

        LoadTraits();
        LoadBreeds();
        LoadFood();
        dogs = getAllDogs();
    }

    // Добавление собаки
    public long addDog(Dog dog) {
        dbManager.open();

        ContentValues values = new ContentValues();

        values.put(DogContract.COLUMN_NAME, dog.getName());
        values.put(DogContract.COLUMN_AGE, dog.getAge());
        values.put(DogContract.COLUMN_BREED, dog.getBreed());
        values.put(DogContract.COLUMN_BREED_KIND, dog.getBreedKind());
        values.put(DogContract.COLUMN_KG, dog.getKg());

        long id = dbManager.db.insert(DogContract.TABLE_DOG, null, values);
        dog.setId(id);

        dbManager.close();
        return id;
    }

    // Получение собаки по ID
    public Dog getDog(long id) {
        dbManager.open();

        Dog dog = null;
        Cursor cursor = dbManager.db.query(
                DogContract.TABLE_DOG,
                null,
                DogContract.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            dog = cursorToDog(cursor);
            cursor.close();
        }

        dbManager.close();
        return dog;
    }

    // Получение всех собак
    public List<Dog> getAllDogs() {
        dbManager.open();

        List<Dog> dogs = new ArrayList<>();
        Cursor cursor = dbManager.db.query(
                DogContract.TABLE_DOG,
                null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                dogs.add(cursorToDog(cursor));
            } while (cursor.moveToNext());

            cursor.close();
        }

        dbManager.close();
        return dogs;
    }

    // Обновление данных собаки
    public int updateDog(Dog dog) {
        dbManager.open();

        ContentValues values = new ContentValues();
        values.put(DogContract.COLUMN_NAME, dog.getName());
        values.put(DogContract.COLUMN_BREED, dog.getBreed());
        values.put(DogContract.COLUMN_AGE, dog.getAge());
        values.put(DogContract.COLUMN_KG, dog.getKg());
        values.put(DogContract.COLUMN_BREED_KIND, dog.getBreedKind());

        int count = dbManager.db.update(
                DogContract.TABLE_DOG,
                values,
                DogContract.COLUMN_ID + " = ?",
                new String[]{String.valueOf(dog.getId())});
        /*Удаляем  особенности*/
        dbManager.db.delete(DogContract.TABLE_DOG_TRAIT, DogContract.COLUMN_DOG + "= ?", new String[]{String.valueOf(dog.getId())});

        List<Integer> traits = dog.getTraits();

        /*Вставляем особенноти особенности*/
        for (int trait : traits) {
            ContentValues TraitValues = new ContentValues();
            TraitValues.put(DogContract.COLUMN_DOG, dog.getId());
            TraitValues.put(DogContract.COLUMN_TRAIT, trait);
            dbManager.db.insert(DogContract.TABLE_DOG_TRAIT, null, TraitValues);
        }
        /*Удаляем диету*/

        dbManager.db.delete(DogContract.TABLE_DOG_DIET, DogContract.COLUMN_DOG + "= ?", new String[]{String.valueOf(dog.getId())});
        /*Вставляем диету*/

        double dogfood_g;
        /*Вставляем особенноти особенности*/
        for (String dogfood : dog.diet.keySet()) {
            dogfood_g = dog.diet.getAsDouble(dogfood);
            ContentValues DietValues = new ContentValues();
            DietValues.put(DogContract.COLUMN_DOG, dog.getId());
            DietValues.put(DogContract.COLUMN_DOGFOOD, Integer.parseInt(dogfood));
            DietValues.put(DogContract.COLUMN_MASS_G, dogfood_g);
            dbManager.db.insert(DogContract.TABLE_DOG_DIET, null, DietValues);
        }

        dbManager.close();
        return count;
    }

    // Удаление данных собаки
    public int deleteDog(Dog dog) {
        dbManager.open();

        int count = dbManager.db.delete(
                DogContract.TABLE_DOG,
                DogContract.COLUMN_ID + " = ?",
                new String[]{String.valueOf(dog.getId())});

        dbManager.close();
        return count;
    }

    // Преобразование Cursor в Dog
    private Dog cursorToDog(Cursor cursor) {

        long id = cursor.getLong(cursor.getColumnIndex(DogContract.COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndex(DogContract.COLUMN_NAME));
        int age = cursor.getInt(cursor.getColumnIndex(DogContract.COLUMN_AGE));
        int breed = cursor.getInt(cursor.getColumnIndex(DogContract.COLUMN_BREED));
        int breedkind = cursor.getInt(cursor.getColumnIndex(DogContract.COLUMN_BREED_KIND));
        double kg = cursor.getInt(cursor.getColumnIndex(DogContract.COLUMN_KG));

        Dog dog = new Dog(id, name, breed, breedkind, age, kg);

        Cursor traits = dbManager.db.query(DogContract.TABLE_DOG_TRAIT,
                null,
                DogContract.COLUMN_DOG + " = ?",
                new String[]{String.valueOf(dog.getId())},
                null, null, null);
        int trait;

        if (traits != null && traits.moveToFirst()) {
            do {
                trait = traits.getInt(traits.getColumnIndex(DogContract.COLUMN_TRAIT));
                dog.AddTrait(trait);
            } while (traits.moveToNext());

            traits.close();
        }

        int dogfood;
        double dogfood_g;

        Cursor diet = dbManager.db.query(DogContract.TABLE_DOG_DIET,
                null,
                DogContract.COLUMN_DOG + " = ?",
                new String[]{String.valueOf(dog.getId())},
                null, null, null);

        if (diet != null && diet.moveToFirst()) {
            do {
                dogfood = diet.getInt(diet.getColumnIndex(DogContract.COLUMN_DOGFOOD));
                dogfood_g = diet.getDouble(diet.getColumnIndex(DogContract.COLUMN_MASS_G));
                dog.AddDogFood(dogfood,dogfood_g);
            } while (diet.moveToNext());

            diet.close();
        }

        return dog;
    }

    public double EstimateFood(Dog dog) {

        dbManager.open();

        String query = String.format("select t.id, t.mass_factor,t.mult_factor from dog_trait dt\n" +
                "join trait t on dt.id_trait = t.id\n" +
                "join dog d on d.id = dt.id_dog\n" +
                "where dt.id_dog = %d;",dog.getId());

        Cursor cursor = dbManager.db.rawQuery(query, null);
        double mass_factor = 0;
        double mult_factor = 1;

        if (cursor != null && cursor.moveToFirst()) {
            do {
                mass_factor = Math.max(mass_factor, cursor.getDouble(cursor.getColumnIndex(DogContract.COLUMN_DOG_DIET_MASS_FACTOR)));
                mult_factor = mult_factor * cursor.getDouble(cursor.getColumnIndex(DogContract.COLUMN_DOG_DIET_MULT_FACTOR));
            } while (cursor.moveToNext());

            cursor.close();
        }

        dog.setMass_factor(mass_factor);
        dog.setMult_factor(mult_factor);

        dbManager.close();

        double ans = dog.EstimateFoodAmmount();
        return ans;
    };

   public double [] EstimateNutrientsNorm (Dog dog) {

       dbManager.open();


       /*Вычисляем нормы питания без особенностей собаки*/
       String query = String.format("select bkn.id_nutrient,\n" +
               "       n.name,\n" +
               "       n.caption,\n" +
               "       round(bkn.norm_value_min_g_per_kg +bkn.norm_value_max_g_per_kg,2)/2 nutrient_norm\n" +
               "       from breed_kind_nutrient bkn\n" +
               "join nutrient n on bkn.id_nutrient = n.id\n" +
               "where bkn.id_breed_kind = %d;", dog.getBreedKind());

       double[] norm = new double[4];

       Cursor cursor = dbManager.db.rawQuery(query, null);

       int nutrient;
       int nutrient_norm;

       if (cursor != null && cursor.moveToFirst()) {
           do {
               nutrient = cursor.getInt(cursor.getColumnIndex(DogContract.COLUMN_NUTRIENT));
               norm[nutrient - 1] = cursor.getDouble(cursor.getColumnIndex(DogContract.COLUMN_NUTRIENT_NORM));
           } while (cursor.moveToNext());

           cursor.close();
       }

       /*учитываем особенности собаки, аддитивные факторы*/

       double[] add_factor = new double[]{0, 0, 0, 0};

       query = String.format("select dt.id_trait, taf.id_nutrient, (min_add_factor+max_add_factor)/2 trait_add_nutrient from dog_trait dt\n" +
               "join trait_nutrient_add_factor taf on taf.id_trait = dt.id_trait\n" +
               "join nutrient n on n.id = taf.id_nutrient\n" +
               "where dt.id_dog = %d;", dog.getId());

       cursor = dbManager.db.rawQuery(query, null);

       int nutrient_add;

       if (cursor != null && cursor.moveToFirst()) {
           do {
               nutrient = cursor.getInt(cursor.getColumnIndex(DogContract.COLUMN_NUTRIENT));
               add_factor[nutrient - 1] = add_factor[nutrient - 1] + cursor.getDouble(cursor.getColumnIndex(DogContract.COLUMN_NUTRIENT_TRAIT_ADD));
           } while (cursor.moveToNext());

           cursor.close();
       }

       /*учитываем особенности собаки, мультипликативные факторы*/
       double[] mult_factor = new double[]{1, 1, 1, 1};

       query = String.format("select dt.id_trait, taf.id_nutrient, (min_mult_factor+max_mult_factor)/2 trait_mult_nutrient from dog_trait dt\n" +
               "join trait_nutrient_mult_factor taf on taf.id_trait = dt.id_trait\n" +
               "join nutrient n on n.id = taf.id_nutrient\n" +
               "where dt.id_dog = %d;\n;", dog.getId());


       cursor = dbManager.db.rawQuery(query, null);


       if (cursor != null && cursor.moveToFirst()) {
           do {
               nutrient = cursor.getInt(cursor.getColumnIndex(DogContract.COLUMN_NUTRIENT));
               mult_factor[nutrient - 1] = mult_factor[nutrient - 1] * cursor.getDouble(cursor.getColumnIndex(DogContract.COLUMN_NUTRIENT_TRAIT_MULT));
           } while (cursor.moveToNext());

           cursor.close();
       }
       /*финальный расчет потребности в корме*/

       double[] nutrients_need = new double[4];

       double dog_mass_kg = dog.getKg();

       for (int i = 0; i < 4; i++) {
           nutrients_need[i] = dog_mass_kg * (norm[i] + add_factor[i]) * mult_factor[i];
       }
       ;

       dog.setNutrients_need(nutrients_need);

       dbManager.close();

       return nutrients_need;
   }

    public   void LoadTraits (){
        dbManager.open();


        Cursor cursor = dbManager.db.query(
                DogContract.TABLE_TRAIT,
                null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                traits.put(
                        Integer.toString(cursor.getInt(cursor.getColumnIndex(DogContract.COLUMN_ID))),
                        cursor.getString(cursor.getColumnIndex(DogContract.COLUMN_CAPTION))
                );
            } while (cursor.moveToNext());

            cursor.close();

        }


        dbManager.close();

    };


    public void EstimateNutrientsConsumption(Dog dog) {

        dbManager.open();

        String query = String.format("select dfn.id_nutrient, sum(dfn.g_per_100g*dd.mass_g/100) nutrient_g from dog_diet dd\n" +
                "join dogfood_nutrient dfn on dd.id_dogfood = dfn.id_dogfood\n" +
                "where dd.id_dog = %d\n" +
                "group by dfn.id_nutrient;",dog.getId());

        Cursor cursor = dbManager.db.rawQuery(query, null);

        int nutrient;
        double nutrient_g;

        double [] consumption  = new double[4];

        if (cursor != null && cursor.moveToFirst()) {
            do {
                nutrient = cursor.getInt(cursor.getColumnIndex(DogContract.COLUMN_NUTRIENT));
                nutrient_g = cursor.getDouble(cursor.getColumnIndex(DogContract.COLUMN_NUTRIENT_G));
                consumption [nutrient-1] = nutrient_g;
            } while (cursor.moveToNext());
            dog.setNutrientConsumption(consumption);
            cursor.close();
        }

        dbManager.close();
    };

    public   void LoadBreeds (){
        dbManager.open();


        Cursor cursor = dbManager.db.query(
                DogContract.TABLE_BREED,
                null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                breeds.put(
                        Integer.toString(cursor.getInt(cursor.getColumnIndex(DogContract.COLUMN_ID))),
                        cursor.getString(cursor.getColumnIndex(DogContract.COLUMN_CAPTION))
                );
            } while (cursor.moveToNext());

            cursor.close();

        }


        dbManager.close();

    };




    public   void LoadFood (){
        dbManager.open();


        Cursor cursor = dbManager.db.query(
                DogContract.TABLE_DOGFOOD,
                null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                dogfoods.put(
                        Integer.toString(cursor.getInt(cursor.getColumnIndex(DogContract.COLUMN_ID))),
                        cursor.getString(cursor.getColumnIndex(DogContract.COLUMN_CAPTION))
                );
                dogfoodsbyvalue.put(cursor.getString(cursor.getColumnIndex(DogContract.COLUMN_CAPTION)),
                        cursor.getInt(cursor.getColumnIndex(DogContract.COLUMN_ID)));
            } while (cursor.moveToNext());

            cursor.close();

        }


        dbManager.close();

    };


    public void SetDogDiet(Dog dog) {
        dbManager.open();

        ContentValues values = new ContentValues();

        values.put(DogContract.COLUMN_NAME, dog.getName());
        values.put(DogContract.COLUMN_AGE, dog.getAge());
        values.put(DogContract.COLUMN_BREED, dog.getBreed());
        values.put(DogContract.COLUMN_BREED_KIND, dog.getBreedKind());
        values.put(DogContract.COLUMN_KG, dog.getKg());

        for (int i=0;i<dog.nutrients_consumption.length;i++) {
            dbManager.db.insert(DogContract.TABLE_DOG_DIET, null, values);
        }

        dbManager.close();
    }

    public void ExportFoods(List<String> List) {
        List.clear();
        for (String key : dogfoods.keySet()) {
            List.add(dogfoods.getAsString(key));
        }
    }

    public void ExportDogs(List<String> List) {
        dogs = getAllDogs();
        List.clear();
        for (Dog aDog : dogs) {
            List.add(aDog.name);
        }

    }

    public String DecodeDiet(Dog dog) {
        String res = "";
        ;
        for (String food_key : dog.diet.keySet()) {
              String food = dogfoods.getAsString(food_key);
              res = res + String.format("%s(%d г)  ",food, Math.round(dog.diet.getAsDouble(food_key)));
        }

        return res;
    }

};


