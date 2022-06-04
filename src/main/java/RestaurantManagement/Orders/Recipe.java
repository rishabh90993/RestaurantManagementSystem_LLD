package RestaurantManagement.Orders;

import RestaurantManagement.Ingredients.Item;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private final String name;
    private double amount;

    private int quantity;

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

    public double getBillAmount() {
        return amount * quantity;
    }

    public List<Item> getItems(){
        return itemList;
    }

    public double removeItemQuantities(){

        for(int i=0;i<itemList.size();i++){
            itemList.get(i).removeQuantity(this.getQuantity());
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

    public int getQuantity(){return quantity;}

    public void setQuantity(int quantity){ this.quantity = quantity; }

   public boolean isQuantityAvailable(int recipeQuantity){

        for(int i=0;i<itemList.size();i++){
            if(!itemList.get(i).isQuantityExist(recipeQuantity))
                return  false;
        }
        return  true;
    }

    @Override
    public String toString() {
        return name + " " + ((quantity==1) ? "" : "Quantity : "+quantity+" " ) + amount+"INR";
    }
}
