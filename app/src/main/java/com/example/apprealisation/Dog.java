package com.example.apprealisation;

import android.content.ContentValues;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dog {

    private double mass_factor;
    private double mult_factor;
    public String name;
    public long id ;
    public int breed;
    public int breedkind;
    public int age;
    public double kg;
    List<Integer> traits;
    double [] nutrients_need;
    double [] nutrients_consumption;

    ContentValues diet;

    public Dog(){
        traits = new ArrayList<>();
        this.nutrients_need = new double[4];
        this.nutrients_consumption = new double[4];
        this.diet = new ContentValues();
    };

    public Dog (long id, String name, int breed, int breedkind, int age, double kg) {
        traits = new ArrayList<>();
        this.id = id;
        this.breed = breed;
        this.breedkind = breedkind;
        this.age = age;
        this.kg = kg;
        this.name = name;
        this.nutrients_need = new double[4];
        this.nutrients_consumption = new double[4];
        this.diet = new ContentValues();
    }

    public void AddDogFood(int dogfood,double dogfood_g) {
        diet.put(Integer.toString(dogfood),dogfood_g);
    }

    public void DelDogFood(int dogfood) {
        diet.remove(Integer.toString(dogfood));
    }

    public void ClearDiet (){
        diet.clear();
        for (int i = 0;i<nutrients_consumption.length;i++) {
            nutrients_consumption[i] = 0;
        }
    }

    public void setNutrientConsumption(double [] consumption) {
        System.arraycopy(consumption,0,this.nutrients_consumption,0,consumption.length);
    }

    public void setMass_factor(double mass_factor) {
        this.mass_factor = mass_factor;
    }

    public void setMult_factor(double mult_factor) {
        this.mult_factor = mult_factor;
    }

    public double getMass_factor() {
        return mass_factor;
    }

    public double getMult_factor() {
        return mult_factor;
    }

    public void AddTrait (int trait) {
        if (!traits.contains(trait))
             traits.add(trait);
    };

    public void ClearTraits () {
        traits.clear();
    }

    public List<Integer> getTraits() {
        return traits;
    }

    public void SetTraits (List<Integer> traits) {
        this.traits = List.copyOf(traits);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBreed() {
        return breed;
    }

    public void setBreed(int breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getKg() {
        return kg;
    }

    public void setKg(double kg) {
        this.kg = kg;

        if (this.kg <=10) {
            breedkind = 1;
        }

        if ((this.kg >10) & (this.kg <=20)) {
            breedkind = 2;
        }

        if (this.kg >20) {
            breedkind = 3;
        }

    }

    public void setBreedKind(int breedkind) {
        this.breedkind = breedkind;
    }

    public int getBreedKind() {
        return breedkind;
    }

    public double EstimateFoodAmmount ()
    {
        return kg*1000*mass_factor*mult_factor;
    }

    public double[] getNutrients_need() {
        return nutrients_need;
    }

    public void setNutrients_need(double[] nutrients_need) {
        System.arraycopy(nutrients_need,0,this.nutrients_need,0,nutrients_need.length);
    }

    public String GetNutrientsNorm() {
        return String.format("Вашей собаке нужно есть %4.1f грамм еды в день. Из них белков %4.1f грамм, жиров %4.1f грамм Углеводов %4.1f грамм, клетчатки %4.1f грамм",
                EstimateFoodAmmount(),nutrients_need[0],nutrients_need[1],nutrients_need[2],nutrients_need[3]);
    }

    public String GetNutrientsConsumption() {
        return String.format("Ваша собака  потребляет белков %4.1f грамм, жиров %4.1f грамм Углеводов %4.1f грамм, клетчатки %4.1f грамм",
                nutrients_consumption[0],nutrients_consumption[1],nutrients_consumption[2],nutrients_consumption[3]);
    }
    @Override
    public String toString() {
     return String.format("{\"id\"=%d,\"name\"=%s,\"breed\"=%d,\"breedkind\"=%d,\"age\"=%d,\"kg\"=%3.1f,\"traits\"=%s,\"food_need\"=%4.1f,\"nutrient_need\"=%s,\"nutrient_consumption\"=%s,\"diet_food\"=%s}",id,name,breed,breedkind,age,kg, traits.toString(),EstimateFoodAmmount(),Arrays.toString(nutrients_need),Arrays.toString(nutrients_consumption),diet.toString());
    }
}
