package RestaurantManagement.Ingredients;

public class Item {
   private Ingredient ingredient;
    private double quantity;

    public Item(Ingredient ingredient, double quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

   public boolean isQuantityExist(){
        return ingredient.getQuantity() >= quantity;
    }

   public double getQuantity(){
        return quantity;
    }

    public void addQuantity(double quantity){
        ingredient.addQuantity(quantity);
    }
    public void removeQuantity() {
        ingredient.removeQuantity(quantity);
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
