package RestaurantManagement;

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
        menuCommand();
    }

    static void viewAvailableIngredients(){
        restaurantManager.viewOrderIngredients();
    }

    static void orderIngredients(){
        restaurantManager.viewOrderIngredients();
        System.out.println(" \n --- Order any ingredient with quantity ---");

        String ingredient = "";
        scanner.nextLine();
        ingredient += scanner.nextLine();
        restaurantManager.orderIngredient(ingredient);
        menuCommand();
    }

    static void viewTotalSales(){
        restaurantManager.viewTotalSales();
    }

    static void viewTotalExpenses(){
        restaurantManager.viewTotalExpenses();
    }

    static  void viewNetProfit(){
        restaurantManager.viewTotalNetProfit();
    }

    static void placeOrder(){
        restaurantManager.viewRecipeMenu();
        System.out.println(" \n --- Order any Recipe ---");

        String recipe = "";
        scanner.nextLine();
        recipe += scanner.nextLine();
        Recipe recipe1 = restaurantManager.findRecipe(recipe);
        if(recipe1 ==null){
            System.out.println("Recipe not Found!!");
            return;
        }

        restaurantManager.placeAnOrder(recipe1);
    }

}
