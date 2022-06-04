package RestaurantManagement;

import RestaurantManagement.Account.AccountManager;
import RestaurantManagement.Ingredients.Ingredient;
import RestaurantManagement.Ingredients.InventoryManager;
import RestaurantManagement.Ingredients.Item;
import RestaurantManagement.Orders.OrderManager;
import RestaurantManagement.Orders.Recipe;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RestaurantManager implements OrderManager, InventoryManager {

   private AccountManager accountManager ;

    List<Recipe> recipeList  = new ArrayList<>();

     void readFiles() {
         try {

             // Parsing account
             String account = new String(Files.readAllBytes(Paths.get("Assets/accounts.txt").toAbsolutePath()));
             accountManager = new AccountManager(Integer.parseInt(account));

             // Parsing Ingredients
             String ingredients = new String(Files.readAllBytes(Paths.get("Assets/ingredients.txt").toAbsolutePath()));
             String[] strIngredient = ingredients.split("\n");

             for(int i=0;i<strIngredient.length;i++){
                 String[] str = strIngredient[i].split(" ");
                 Ingredient.setData(str[0].replaceAll("\\s+", ""), Double.parseDouble(str[1].replaceAll("\\s+", "")), Double.parseDouble(str[2].replaceAll("\\s+", "")));
             }

             // Parsing Recipes
             String srecipe = new String(Files.readAllBytes(Paths.get("Assets/receipe.txt").toAbsolutePath()));
             String[] strRecipe = srecipe.split("\n");

             for(int i=0;i<strRecipe.length;i++){
                 String[] str = strRecipe[i].split(" ");
                 ArrayList<Item> items = new ArrayList<>();
                 for(int j=1;j<str.length-1;j+=2){
                     items.add(new Item(Ingredient.valueOf(str[j]),Double.parseDouble(str[j+1])));
                 }
                 recipeList.add( new Recipe(str[0],Double.parseDouble(str[str.length-1]),items));
                 items = null;
                 str = null;
             }

         }catch (Exception e){
             e.printStackTrace();
         }

    }

    public void viewRecipeMenu() {
        System.out.println(" === Menu ===");
        for (int i=0 ; i<recipeList.size();i++){
            System.out.println(recipeList.get(i));
        }
    }

    public Recipe findRecipe(String recipe){
         for(int i=0;i<recipeList.size();i++){
             if(recipeList.get(i).getName().compareTo(recipe)==0){
                 return  recipeList.get(i);
             }
         }
         return null;
    }

    public void viewOrderIngredients() {
         Ingredient.viewAvailableIngredients();
    }

    public void orderIngredient(String ingredient){
         String[] str = ingredient.split(" ");
         if(str.length<=1) {
             System.out.println("Ingredient not found");
             return ;
         }
         Ingredient ingredient1 ;
        try {
            ingredient1 = Ingredient.valueOf(str[0]);
        }catch (Exception e){
            System.out.println("Ingredient not found.");
            return ;
        }

        double quantity;
         try {
             quantity = Double.parseDouble(str[1]);
         }catch (Exception e){
             System.out.println("Quantity invalid");
            return ;
         }

        boolean isSuccessful =  accountManager.purchaseIngredient(new Item(ingredient1,quantity));

        if(isSuccessful){
            System.out.println("Ingredient bought Successfully");
            System.out.println(ingredient1.toString());
        }else{
            System.out.println("Insufficient Money!! Cannot Buy Ingredient");
        }
    }


    void viewTotalSales(){
        accountManager.viewTotalSales();
    }

    void viewTotalExpenses(){
        accountManager.viewTotalExpenses();
    }

    void viewTotalNetProfit(){
        accountManager.viewNetProfit();
    }

    public void placeAnOrder(String recipe1){
        String[] str = recipe1.split(" ");

        if(str.length<=1) {
            System.out.println("Recipe or quantity not found");
            return ;
        }

        Recipe recipe ;
        try {
            recipe = findRecipe(str[0]);
        }catch (Exception e){
            System.out.println("Recipe not found.");
            return ;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(str[1]);
            recipe.setQuantity(quantity);
        }catch (Exception e){
            System.out.println("Quantity invalid");
            return ;
        }

        if(recipe.isQuantityAvailable(recipe.getQuantity())){
            accountManager.addSale(recipe);
            System.out.println("\n === Bill Amount== \n" + recipe.getAmount());
            System.out.println("\nOrder Placed Successfully.");
        }else{
            if(accountManager.purchaseIngredientsForRecipe(recipe)){
                System.out.println("\n === Bill Amount== \n" + recipe.getAmount());
                accountManager.addSale(recipe);
                System.out.println("\nOrder Placed Successfully.");
            }else{
                System.out.println("Recipe cannot be made please order Something else.");
            }
        }
    }


}
