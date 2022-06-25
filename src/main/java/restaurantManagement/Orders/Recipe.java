package restaurantManagement.Orders;

import restaurantManagement.Ingredients.Ingredient;
import restaurantManagement.Ingredients.Item;
import restaurantManagement.reader.Reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static restaurantManagement.reader.Reader.readFile;

public class Recipe extends Reader {
    private final String name;
    private double amount;

    private int quantity;

    private List<Item> itemList = new ArrayList();

    public Recipe(String name,double amount,ArrayList<Item> items){
        this.name = name;
        this.itemList = items;
        this.amount = amount;
    }

    public static List<Recipe> readFiles() throws IOException {
        String srecipe = readFile("Assets/receipe.txt");
        String[] strRecipe = srecipe.split("\n");
        List<Recipe> recipeList = new ArrayList<>();

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
        return  recipeList;
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
        return name + " " + ((quantity<=1) ? "" : "Quantity : "+quantity+" " ) + amount+"INR";
    }
}
