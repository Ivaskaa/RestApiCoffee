package com.example.RestApiCoffee.others;

import com.example.RestApiCoffee.entities.ingredients.alcohol.Alcohol;
import com.example.RestApiCoffee.entities.ingredients.milk.Milk;
import com.example.RestApiCoffee.entities.ingredients.sauce.Sauce;
import com.example.RestApiCoffee.entities.ingredients.supplement.Supplement;
import com.example.RestApiCoffee.entities.ingredients.syrup.Syrup;

public class IngredientsFactory {
    public Alcohol getAlcohol(){
        Alcohol ingredient = new Alcohol();
        ingredient.setName("name");
        ingredient.setPrice(1d);
        ingredient.setActive(true);
        return ingredient;
    }
    public Milk getMilk(){
        Milk ingredient = new Milk();
        ingredient.setName("name");
        ingredient.setPrice(1d);
        ingredient.setActive(true);
        return ingredient;
    }
    public Sauce getSauce(){
        Sauce ingredient = new Sauce();
        ingredient.setName("name");
        ingredient.setPrice(1d);
        ingredient.setActive(true);
        return ingredient;
    }
    public Supplement getSupplement(){
        Supplement ingredient = new Supplement();
        ingredient.setName("name");
        ingredient.setPrice(1d);
        ingredient.setActive(true);
        return ingredient;
    }
    public Syrup getSyrup(){
        Syrup ingredient = new Syrup();
        ingredient.setName("name");
        ingredient.setPrice(1d);
        ingredient.setActive(true);
        return ingredient;
    }
}
