package RestaurantManagement.Ingredients;

public enum Ingredient {

    Tomato("Tomato",0,0),
    Potato("Potato",0,0),
    Onions("Onions",0,0),
    Batter("Batter",0,0),
    Spices("Spices",0,0),
    Milk("Milk",0,0),
    CoffeePowder("CoffeePowder",0,0),
    TeaLeaves("TeaLeaves",0,0),
    Oregano("Oregano",0,0),
    Cheese("Cheese",0,0),
    Yoghurt("Yoghurt",0,0),
    Bread("Bread",0,0),
    Capsicum("Capsicum",0,0);


    private String name;
    private double price;
    private double quantity;
    Ingredient(String name,double quantity,double price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public double getQuantity(){
        return this.quantity;
    }

    public static void setData(String value ,double quantity,double price){
        Ingredient ingredient = Ingredient.valueOf(value);
        ingredient.quantity = quantity;
        ingredient.price = price;
    }

    public double getPrice(){
        return this.price;
    }


    public void removeQuantity(double quantity){
        this.quantity -= quantity;
    }
    public void addQuantity(double quantity){
        this.quantity += quantity;
    }

    public static void viewAvailableIngredients(){
        System.out.println("\nTotal Available Ingredients\n");

        Ingredient[] ingredients = Ingredient.values();
        for(int i=0;i<ingredients.length;i++){
            if(ingredients[i].getQuantity()>0)
                System.out.println(ingredients[i].toString());
        }
    }

    public String toString(){
        return this.name+" "+ "Quantity: "+this.quantity+" Price: "+this.price;
    }

}
