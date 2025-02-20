/*This class represents another type of beverage called Iced Tea. Similar to BobaTea, 
it extends the Beverage class and contains methods to choose the flavor, size, ice level, 
and quantity of the Iced Tea. It calculates the cost of the Iced Tea based on the user's selections. */



import javax.swing.JOptionPane;

public class IcedTea extends Beverage {
    private String selectedFlavor;
    private int selectedSize;
    private int selectedIceLevel;
    private String[] sizes = {"Small", "Medium", "Large"};
    private String[] iceLevels = {"No Ice", "Regular Ice"};
    private double[] prices = {4.50, 4.50};

    public IcedTea() {
        super("Iced Tea", 0);
    }

    public void chooseFlavor() {
        String[] flavors = {"Lemon $4.50", "Raspberry $4.50"};
        int flavorIndex = JOptionPane.showOptionDialog(null, "Choose your Iced Tea flavor:",
                "Iced Tea Flavors", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, flavors, flavors[0]);
        if (flavorIndex >= 0) {
            selectedFlavor = flavors[flavorIndex];
        }
    }

    public void chooseSize() {
        selectedSize = JOptionPane.showOptionDialog(null, "Select the size of your Iced Tea:",
                "Iced Tea Size", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, sizes, sizes[0]);
    }

    public void chooseIceLevel() {
        selectedIceLevel = JOptionPane.showOptionDialog(null, "Select your preferred ice level:",
                "Ice Level", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, iceLevels, iceLevels[0]);
    }

    public void chooseQuantity() {
        boolean valid = false;
        while (!valid) {
            try {
                String input = JOptionPane.showInputDialog("Enter the quantity for Iced Tea (1-15):");
                quantity = Integer.parseInt(input);
                if (quantity < 1 || quantity > 15) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number from 1 to 15.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public double calculateCost() {
        double basePrice = prices[selectedFlavor.equals("Lemon") ? 0 : 1];
        double sizeModifier = selectedSize == 0 ? 1.0 : (selectedSize == 1 ? 1.2 : 1.5);
        return basePrice * sizeModifier * getQuantity();
    }

    @Override
    public String getName() {
        String iceDescription = (selectedIceLevel == 0 ? "No Ice" : "Regular Ice");
        return selectedFlavor + " Iced Tea - " + iceDescription;
    }
}  
