/*This class represents a category of limited edition drinks offered by the Boba Cafe.
It extends the Beverage class and includes methods to choose the specific limited edition 
option, size, and quantity. It calculates the cost of the limited edition drink based on the user's selections. */


import javax.swing.JOptionPane;

public class LimitedEditionPicks extends Beverage {
    private String[] options = {"Honeydew Melon Tea $6.00", "Strawberry Bliss $6.00"};
    private double[] prices = {6.00, 6.00};
    private int selectedOptionIndex = -1;
    private String[] sizes = {"Small", "Medium", "Large"};
    private int selectedSize;

    public LimitedEditionPicks() {
        super("Limited Edition Picks", 0);
    }

    public void chooseOption() {
        selectedOptionIndex = JOptionPane.showOptionDialog(null, "Select one of the Limited Edition Picks:",
                "Limited Edition Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (selectedOptionIndex >= 0) {
            this.name = options[selectedOptionIndex].split(" ")[0];
        }
    }

    public void chooseSize() {
        selectedSize = JOptionPane.showOptionDialog(null, "Select the size of your drink:",
                "Drink Size", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, sizes, sizes[0]);
    }

    public void chooseQuantity() {
        String input = JOptionPane.showInputDialog("Enter the quantity for Limited Edition Picks (1-15):");
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
        double basePrice = prices[selectedOptionIndex];
        double sizeModifier = 1.0;
        switch (selectedSize) {
            case 1:  // Medium
                sizeModifier = 1.2;
                break;
            case 2:  // Large
                sizeModifier = 1.5;
                break;
        }
        return basePrice * sizeModifier * getQuantity();
    }

    @Override
    public String getName() {
        return options[selectedOptionIndex].split("\\$")[0].trim();
    }
}
