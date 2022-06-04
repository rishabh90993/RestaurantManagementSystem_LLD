package RestaurantManagement.Orders;


import java.util.Date;

public class Order {

    Recipe recipe;
    Date date;

    public Order(Recipe recipe, Date date){
        this.recipe = recipe;
        this.date = date;
    }

    @Override
    public String toString() {
        return recipe.toString() + " " + date.toString();
    }
}
