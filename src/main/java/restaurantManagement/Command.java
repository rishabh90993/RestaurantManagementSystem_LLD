package restaurantManagement;

public enum Command {

    ViewAvailableIngredients(1),
    OrderIngredients(2),
    ViewTotalSales(3),
    ViewTotalExpenses(4),
    ViewNetProfit(5),
    PlaceOrder(6),
    Exit(7);

    int value;
    Command(int command){
        this.value = command;
    }

    public static Command findCommand(int command) {
        Command[] commands = Command.values();
        for(int i=0;i<commands.length; i++){
            if(commands[i].value == command){
                return commands[i];
            }
        }
        return Command.Exit;
    }
}
