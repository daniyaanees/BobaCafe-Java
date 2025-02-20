/*This abstract class serves as the blueprint for all beverage types in the Boba Cafe application. 
It contains common attributes such as name, price, and quantity, as well as abstract methods to 
calculate the cost and get the name of the beverage. Other beverage classes extend this class to 
implement specific functionalities for each drink type. */


public abstract class Beverage {
    String name;
    double price;
    protected int quantity;

    public Beverage(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    abstract double calculateCost();

    public abstract String getName();
}

