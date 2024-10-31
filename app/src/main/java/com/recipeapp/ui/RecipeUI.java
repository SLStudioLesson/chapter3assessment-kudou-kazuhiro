package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() {
        try {
            // dataHandlerからル読み込んだ情報を受け取る
            ArrayList<Recipe> recipes = dataHandler.readData();
            // 受け取った配列が空の場合の分岐
            if(!recipes.isEmpty()) {
                // 繰り返し処理を使ってレシピと材料を取り出して出力
                System.out.println();
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");
                for(Recipe recipe: recipes) {
                    System.out.println("Recipe Name: " + recipe.getName());
                    System.out.print("Main Ingredients: ");
                    for(Ingredient ingredient:recipe.getIngredients()) {
                        System.out.print(ingredient.getName() + ", ");
                    }
                    System.out.println();
                    System.out.println("-----------------------------------");
                }
            } else {
                System.out.println("No recipes available.");
            }

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void addNewRecipe() {
        try {
            // レシピ名と材料名を入力
            System.out.println();
            System.err.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipe = reader.readLine();

            System.out.println("Enter ingredients (type 'done' when finished):");
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            while(true) {
                System.out.print("Ingredient: ");
                String str = reader.readLine();
                if(str.equals("done")){
                    break;
                }
                ingredients.add(new Ingredient(str));
            }

            // 入力内容をRecipe型に変換してHandlerに渡す
            dataHandler.writeData(new Recipe(recipe, ingredients));
            System.out.println("Recipe added successfully.");

        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}
