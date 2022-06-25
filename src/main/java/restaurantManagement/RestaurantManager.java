package restaurantManagement;

import restaurantManagement.Account.AccountManager;
import restaurantManagement.Ingredients.Ingredient;
import restaurantManagement.Ingredients.InventoryManager;
import restaurantManagement.Ingredients.Item;
import restaurantManagement.Orders.OrderManager;
import restaurantManagement.Orders.Recipe;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RestaurantManager implements OrderManager, InventoryManager {

   private AccountManager accountManager ;

    List<Recipe> recipeList  = new ArrayList<>();

     void readFiles() {
         try {
             String account = AccountManager.readFiles();
             accountManager = new AccountManager(Integer.parseInt(account));

             // Parsing Ingredients
             Ingredient.readFiles();

             // Parsing Recipes
            recipeList.addAll(Recipe.readFiles());

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

    public void orderIngredient(Item item){
        boolean isSuccessful =  accountManager.purchaseIngredient(item);

        if(isSuccessful){
            System.out.println("Ingredient bought Successfully");
            System.out.println(item.getIngredient().toString());
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

    public void placeAnOrder(Recipe recipe){

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
