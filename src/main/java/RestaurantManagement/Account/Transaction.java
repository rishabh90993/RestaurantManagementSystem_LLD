package RestaurantManagement.Account;

import RestaurantManagement.Orders.Recipe;

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
        return this.recipe + " on " + date.toString();
    }
}
