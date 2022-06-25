package restaurantManagement.Orders;

import restaurantManagement.Ingredients.Item;

public interface OrderManager {

    public void orderIngredient(Item item);

    void placeAnOrder(Recipe recipe);
}
