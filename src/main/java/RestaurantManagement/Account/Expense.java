package RestaurantManagement.Account;

import RestaurantManagement.Ingredients.Item;
import RestaurantManagement.Orders.Recipe;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Expense {
    private double amount;
    List<Item> items;
    Date date;

    public Expense(double amount, List<Item> items){
        this.amount = amount;
        this.items = items;
        date = Date.from(Instant.now());
    }

    public String toString(){
        String string = "";
        for(int i=0 ;i< items.size();i++){
            string += items.get(i)+"\n";
        }
        string+="Amount:" + amount+ "\n" + date.toString()+"\n";
        return string;
    }
}
