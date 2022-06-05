package RestaurantManagement.Orders;

import RestaurantManagement.Ingredients.Item;

public interface OrderManager {

    public void orderIngredient(Item item);

    void placeAnOrder(Recipe recipe);
}
