package restaurantManagement.Ingredients;

public class Item {
   private Ingredient ingredient;
    private double quantity;

    public Item(Ingredient ingredient, double quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

   public boolean isQuantityExist(double recipeQuantity){
        return ingredient.getQuantity() >= this.quantity * recipeQuantity;
    }

   public double getQuantity(){
        return quantity;
    }

    public void addQuantity(double quantity){
        ingredient.addQuantity(quantity);
    }
    public void removeQuantity(int recipeQuantity) {
        ingredient.removeQuantity(quantity * recipeQuantity);
    }

    public double getIngredientPrice(){
        return ingredient.getPrice();
    }

    public Ingredient getIngredient(){
        return ingredient;
    }

    @Override
    public String toString() {
        return ingredient.name() + "Quantity: "+ quantity;
    }
}
