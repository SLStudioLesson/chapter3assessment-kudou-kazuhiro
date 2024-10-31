package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler() {
        filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    public String getMode() {
        return "CSV";
    }

    public ArrayList<Recipe> readData() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        // recipes.csvからデータ読み込み
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 読み込んだ一行をレシピ名と材料名に分ける
                String[] str = line.split(",");
                String recipe = str[0];
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for(int i = 1; i < str.length; i++) {
                    ingredients.add(new Ingredient(str[i]));
                }

                // 整頓したデータをArrayListに格納
                recipes.add(new Recipe(recipe, ingredients));
            }
        } catch (Exception e) {
            
        }
        return recipes;
    }

    public void writeData(Recipe recipe){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            String recipeName = recipe.getName();
            String ingredientNames ="";
            ArrayList<Ingredient> ingredients = recipe.getIngredients();
            for(int i = 0; i < ingredients.size() - 1; i++) {
                ingredientNames += ingredients.get(i).getName() + ", ";
            }
            ingredientNames += ingredients.get(ingredients.size()-1).getName();
            // for(Ingredient ingredient : recipe.getIngredients()) {
            //     ingredients += ingredient.getName() + ", ";
            // }
            writer.write(recipeName + "," + ingredientNames);
            writer.write("\n");
        } catch (Exception e) {
            
        }
    }

    public ArrayList<Recipe> searchData(String keyword) {
        return null;
    }
}
