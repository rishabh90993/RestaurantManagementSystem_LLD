package restaurantManagement.Account;

import restaurantManagement.Orders.Recipe;

import java.time.Instant;
import java.util.Date;

public class Transaction {
    Recipe recipe;
    Date date;

    public Transaction(Recipe recipe){
        this.recipe = recipe;
        date = Date.from(Instant.now());
    }

    public String toString(){
        return this.recipe.getName() + " - " + recipe.getQuantity() + " \nBill Amount: "+ recipe.getBillAmount()+" INR " + " on " + date.toString();
    }
}
