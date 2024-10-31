import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");
            String choice = reader.readLine();
            
            // 1か2が選択された場合
            switch (choice) {
                case "1":
                    CSVDataHandler csvDataHandler = new CSVDataHandler();
                    new RecipeUI(csvDataHandler).displayMenu();
                    break;

                case "2":
                    JSONDataHandler jsonDataHandler = new JSONDataHandler();
                    new RecipeUI(jsonDataHandler).displayMenu();
                    
                    break;
            
                default:
                    break;
            }

            // 1、2以外が選択された場合
            if (!choice.equals("1") && !choice.equals("2")) {
                CSVDataHandler csvDataHandler = new CSVDataHandler();
                new RecipeUI(csvDataHandler).displayMenu();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}