package RestaurantManagement.Orders;

import RestaurantManagement.Ingredients.Item;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private final String name;
    private double amount;
    private List<Item> itemList = new ArrayList();

    public Recipe(String name,double amount,ArrayList<Item> items){
        this.name = name;
        this.itemList = items;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public List<Item> getItems(){
        return itemList;
    }

    public double removeItemQuantities(){

        for(int i=0;i<itemList.size();i++){
            itemList.get(i).removeQuantity();
        }
        return  amount;
    }

    private double calculateRecipeCost(){
        double amount = 0;
        for(int i=0;i<itemList.size();i++){
            amount = amount+itemList.get(i).getIngredientPrice();
        }
        return  amount;
    }

   public boolean isQuantityAvailable(){
        for(int i=0;i<itemList.size();i++){
            if(!itemList.get(i).isQuantityExist())
                return  false;
        }
        return  true;
    }

    @Override
    public String toString() {
        return name + ": " + amount+"INR";
    }
}
