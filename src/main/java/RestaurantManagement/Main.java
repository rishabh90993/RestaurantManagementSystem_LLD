package RestaurantManagement;

import RestaurantManagement.Ingredients.Ingredient;
import RestaurantManagement.Ingredients.Item;
import RestaurantManagement.Orders.Recipe;

import java.util.Scanner;

public class Main {
   static Scanner scanner =  new Scanner(System.in);
    static RestaurantManager restaurantManager = new RestaurantManager();
    public static void main(String[] args) {
        restaurantManager.readFiles();
        menuCommand();
    }

    public static void menuCommand(){
        System.out.println("\nType any command number from below.");
        System.out.println("1. View Available Ingredients\n 2. Order Ingredient \n 3. View total Sales \n 4. View Total Expenses \n 5. View Net Profit \n 6. Place Order \n 7. Exit");
        String command = scanner.next();
        int comm;
        try {
            comm = Integer.parseInt(command);
        }catch(Exception e){
            System.out.println("Invalid Command");
            return;
        }

        switch (comm){
            case 1:
                viewAvailableIngredients();
                break;
            case 2:
                orderIngredients();
                break;
            case 3:
                viewTotalSales();
                break;
            case 4:
                viewTotalExpenses();
                break;
            case 5:
                viewNetProfit();
                break;
            case 6:
                placeOrder();
                break;
            case 7:
                System.out.println("Good Bye!!");
                return;
            default:
                break;
        }
    }

    static void viewAvailableIngredients(){
        restaurantManager.viewOrderIngredients();
        menuCommand();
    }

    static void orderIngredients(){
        restaurantManager.viewOrderIngredients();
        System.out.println(" \n --- Order any ingredient with quantity ---");
        System.out.println("\n Enter Ingredient Name");

        String name = scanner.next();
        Ingredient ingredient1 ;
        try {
            ingredient1 = Ingredient.valueOf(name);
        }catch (Exception e){
            System.out.println("Ingredient not found.");
            return ;
        }

        System.out.println(" Enter Ingredient Quantity");
        double quantity = scanner.nextDouble();
        restaurantManager.orderIngredient(new Item(ingredient1,quantity));
        menuCommand();
    }

    static void viewTotalSales(){
        restaurantManager.viewTotalSales();
        menuCommand();
    }

    static void viewTotalExpenses(){
        restaurantManager.viewTotalExpenses();
        menuCommand();
    }

    static  void viewNetProfit(){
        restaurantManager.viewTotalNetProfit();
        menuCommand();
    }

    static void placeOrder(){
        restaurantManager.viewRecipeMenu();
        System.out.println(" \n --- Order any Recipe ---");
        System.out.println(" \n Enter Recipe Name");

        String name = scanner.next();

        Recipe recipe = restaurantManager.findRecipe(name);
        if(recipe ==null){
            System.out.println("Recipe not Found!!");
            return;
        }
        System.out.println(" Enter Recipe Quantity");
        int quantity = scanner.nextInt();
        recipe.setQuantity(quantity);
        restaurantManager.placeAnOrder(recipe);
        menuCommand();
    }

}
