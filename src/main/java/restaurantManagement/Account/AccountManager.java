package restaurantManagement.Account;

import restaurantManagement.Ingredients.Item;
import restaurantManagement.Orders.Recipe;
import restaurantManagement.reader.Reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AccountManager extends Reader {
    private double initialBalance;
   protected double totalMoney;
   private List<Expense> expenses = new ArrayList<Expense>();
   private List<Transaction> sales = new ArrayList<Transaction>();

   public AccountManager(double initialBalance){
       this.initialBalance = initialBalance;
       this.totalMoney = initialBalance;
   }

   public static String readFiles() throws IOException {
       // Parsing account
       return readFile("Assets/accounts.txt");
   }

    public void viewTotalExpenses(){
        System.out.println("=== Expenses === \n");
        for(int i=0;i<expenses.size();i++){
            System.out.println(expenses.get(i).toString());
        }
    }

    public void addSale(Recipe recipe){
        recipe.removeItemQuantities();
        totalMoney += recipe.getBillAmount();
        Transaction transaction = new Transaction(recipe);
        sales.add(transaction);
    }

    public void viewTotalSales(){
        System.out.println("=== Sales === \n");
        for(int i=0;i<sales.size();i++){
            System.out.println(sales.get(i).toString());
        }
    }

    public void viewNetProfit(){
        System.out.println("\n === Account Statement ===");
        System.out.println("\nInitial Balance: " + (initialBalance));
        System.out.println("Current Balance: " + (totalMoney));
        System.out.println("Net Profit: " + (totalMoney-initialBalance));
    }

    public boolean purchaseIngredientsForRecipe(Recipe recipe){

        double amountRequired = 0;
        List<Item> items = recipe.getItems();

        //getting money required to get items
        for(int i=0; i<items.size();i++){
            double quantity = items.get(i).getQuantity() * recipe.getQuantity();
            if(quantity > items.get(i).getIngredient().getQuantity()){
                amountRequired+= items.get(i).getIngredientPrice() * (quantity - items.get(i).getIngredient().getQuantity());
            }
        }


        if(amountRequired > this.totalMoney)
            return false;

        // purchasing items
        List<Item> purchasedItems = new ArrayList<>();
        for(int i=0; i<items.size();i++){
            double recipeQuantity = items.get(i).getQuantity() * recipe.getQuantity();
            if(recipeQuantity > items.get(i).getIngredient().getQuantity()){
                double quantity = recipeQuantity - items.get(i).getIngredient().getQuantity();
                 items.get(i).addQuantity(quantity);
                purchasedItems.add(new Item(items.get(i).getIngredient(),quantity));
            }
        }

        expenses.add(new Expense(amountRequired,purchasedItems));

        totalMoney -= amountRequired;

        System.out.println("Item Purchased for amount " + amountRequired);
        System.out.println("=== Total Balance left === \n"+totalMoney);

        return true;

    }

    public boolean purchaseIngredient(Item item){

        double amountRequired = 0;

        //getting money required to get item
        amountRequired += item.getIngredientPrice() * item.getQuantity();

        if(amountRequired > this.totalMoney)
            return false;

        item.addQuantity(item.getQuantity());

        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        expenses.add(new Expense(amountRequired,items));

        totalMoney -= amountRequired;

        System.out.println("Item Purchased for amount " + amountRequired);
        System.out.println("=== Total Balance left === \n"+totalMoney);

        return true;

    }



}
