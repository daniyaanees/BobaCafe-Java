/*This class represents a specific type of beverage called Boba Tea.
 It extends the abstract class Beverage and contains methods to choose 
 the flavor,topping, size, and quantity of the Boba Tea. It also calculates
the cost of the Boba Tea based on the user's selections. */


import javax.swing.JOptionPane;

public class BobaTea extends Beverage {
    private String selectedFlavor;
    private int selectedTopping;
    private int selectedSize;
    private double[] prices = {5.99, 5.99, 5.99};
    private String[] sizes = {"Small", "Medium", "Large"};
    private String[] toppings = {"No Toppings", "Lychee Jelly", "Mango Jelly", "Tapioca Pearls"};

    public BobaTea() {
        super("Boba Tea", 0);
    }

    public void chooseFlavor() {
        String[] flavors = {"Classic Milk Tea $5.99", "Matcha Latte $5.99", "Honeydew Milk Tea $5.99"};
        int flavorIndex = JOptionPane.showOptionDialog(null, "Choose your Boba Tea flavor:",
                "Boba Tea Flavors", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, flavors, flavors[0]);
        if (flavorIndex >= 0) {
            selectedFlavor = flavors[flavorIndex];
        }
    }

    public void chooseTopping() {
        int toppingIndex = JOptionPane.showOptionDialog(null, "Select a topping! +$0.50:",
                "Boba Tea Toppings", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, toppings, toppings[0]);
        if (toppingIndex >= 0) {
            selectedTopping = toppingIndex;
        }
    }

    public void chooseSize() {
        selectedSize = JOptionPane.showOptionDialog(null, "Select the size of your Boba Tea:",
                "Boba Tea Size", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, sizes, sizes[0]);
    }

    public void chooseQuantity() {
        String input = JOptionPane.showInputDialog("Enter the quantity for Boba Tea (1-15):");
        try {
            quantity = Integer.parseInt(input);
            if (quantity < 1 || quantity > 15) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number from 1 to 15.", "Error", JOptionPane.ERROR_MESSAGE);
                chooseQuantity();  // Recursive call for valid input
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            chooseQuantity();  // Recursive call for valid input
        }
    }

    @Override
    public double calculateCost() {
        double basePrice = prices[selectedSize];
        double toppingPrice = selectedTopping == 0 ? 0 : 0.50;
        return (basePrice + toppingPrice) * getQuantity();
    }

    @Override
    public String getName() {
        return "Boba Tea: " + selectedFlavor + " with " + toppings[selectedTopping];
    }
}

