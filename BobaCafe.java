/*This class represents the main control system for a Boba Cafe application.
 It allows users to interact with the menu, select different types of drinks, remove drinks, and checkout.
The main method initializes the program and manages user interactions through a graphical user interface (GUI).*/


import javax.swing.JOptionPane;
public class BobaCafe {
    static BobaTea[] bobaTeas = new BobaTea[10];
    static IcedTea[] icedTeas = new IcedTea[10];
    static LimitedEditionPicks[] limitedEditionPicksArray = new LimitedEditionPicks[10];
    static int bobaTeaCount = 0, icedTeaCount = 0, limitedEditionCount = 0;

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to Boba Cafe!", "Boba Cafe", JOptionPane.INFORMATION_MESSAGE);

        while (true) {
            String[] options = {"Boba Tea", "Iced Tea", "Limited-Edition Picks", "Remove A Drink", "Check out", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Please make a selection:", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    if (bobaTeaCount < bobaTeas.length) {
                        BobaTea newBobaTea = new BobaTea();
                        newBobaTea.chooseFlavor();
                        newBobaTea.chooseTopping();
                        newBobaTea.chooseQuantity();
                        newBobaTea.chooseSize();
                        bobaTeas[bobaTeaCount++] = newBobaTea;
                    }
                    break;
                case 1:
                    if (icedTeaCount < icedTeas.length) {
                        IcedTea newIcedTea = new IcedTea();
                        newIcedTea.chooseFlavor();
                        newIcedTea.chooseSize();
                        newIcedTea.chooseIceLevel();
                        newIcedTea.chooseQuantity();
                        icedTeas[icedTeaCount++] = newIcedTea;
                    }
                    break;
                case 2:
                    if (limitedEditionCount < limitedEditionPicksArray.length) {
                        LimitedEditionPicks newPick = new LimitedEditionPicks();
                        newPick.chooseOption();
                        newPick.chooseQuantity();
                        newPick.chooseSize();
                        limitedEditionPicksArray[limitedEditionCount++] = newPick;
                    }
                    break;
                case 3:
                    removeDrink();
                    break;
                case 4:
                    checkout();
                    JOptionPane.showMessageDialog(null, "Thank you for your purchase!", "Thank You", JOptionPane.INFORMATION_MESSAGE);
                    return; // Exit after checkout
                case 5:
                    JOptionPane.showMessageDialog(null, "Thank you for visiting Boba Cafe! Have a great day!", "Goodbye",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                    break;
            }
        }
    }

    private static void removeDrink() {
        if (bobaTeaCount + icedTeaCount + limitedEditionCount == 0) {
            JOptionPane.showMessageDialog(null, "No drinks to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        String[] options = new String[bobaTeaCount + icedTeaCount + limitedEditionCount];
        int count = 0;
    
        for (int i = 0; i < bobaTeaCount; i++) {
            options[count++] = bobaTeas[i].getName() + " - Qty: " + bobaTeas[i].getQuantity();
        }
        for (int i = 0; i < icedTeaCount; i++) {
            options[count++] = icedTeas[i].getName() + " - Qty: " + icedTeas[i].getQuantity();
        }
        for (int i = 0; i < limitedEditionCount; i++) {
            options[count++] = limitedEditionPicksArray[i].getName() + " - Qty: " + limitedEditionPicksArray[i].getQuantity();
        }
    
        int selected = JOptionPane.showOptionDialog(null, "Select a drink to remove:", "Remove Drink",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
    
        if (selected != -1) {
            if (selected < bobaTeaCount) {
                for (int i = selected; i < bobaTeaCount - 1; i++) {
                    bobaTeas[i] = bobaTeas[i + 1];
                }
                bobaTeas[--bobaTeaCount] = null;
            } else if (selected < bobaTeaCount + icedTeaCount) {
                selected -= bobaTeaCount;
                for (int i = selected; i < icedTeaCount - 1; i++) {
                    icedTeas[i] = icedTeas[i + 1];
                }
                icedTeas[--icedTeaCount] = null;
            } else {
                selected -= (bobaTeaCount + icedTeaCount);
                for (int i = selected; i < limitedEditionCount - 1; i++) {
                    limitedEditionPicksArray[i] = limitedEditionPicksArray[i + 1];
                }
                limitedEditionPicksArray[--limitedEditionCount] = null;
            }
            JOptionPane.showMessageDialog(null, "Drink removed successfully.", "Remove Drink", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    

    public static void checkout() {
        if (bobaTeaCount + icedTeaCount + limitedEditionCount == 0) {
            JOptionPane.showMessageDialog(null, "No drinks to checkout.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double subTotal = 0.0;
        String receipt = "Receipt\n\n";

        for (int i = 0; i < bobaTeaCount; i++) {
            if (bobaTeas[i] != null) {
                subTotal += bobaTeas[i].calculateCost();
                receipt += bobaTeas[i].getQuantity() + " x " + bobaTeas[i].getName() + " - $" + String.format("%.2f", bobaTeas[i].calculateCost()) + "\n";
            }
        }
        for (int i = 0; i < icedTeaCount; i++) {
            if (icedTeas[i] != null) {
                subTotal += icedTeas[i].calculateCost();
                receipt += icedTeas[i].getQuantity() + " x " + icedTeas[i].getName() + " - $" + String.format("%.2f", icedTeas[i].calculateCost()) + "\n";
            }
        }
        for (int i = 0; i < limitedEditionCount; i++) {
            if (limitedEditionPicksArray[i] != null) {
                subTotal += limitedEditionPicksArray[i].calculateCost();
                receipt += limitedEditionPicksArray[i].getQuantity() + " x " + limitedEditionPicksArray[i].getName() + " - $" + String.format("%.2f", limitedEditionPicksArray[i].calculateCost()) + "\n";
            }
        }

        double tax = 0.05 * subTotal;
        double total = subTotal + tax;
        receipt += "\nSubtotal: $" + String.format("%.2f", subTotal) + "\n";
        receipt += "Taxes (5%): $" + String.format("%.2f", tax) + "\n";
        receipt += "Total: $" + String.format("%.2f", total);

        JOptionPane.showMessageDialog(null, receipt, "Checkout", JOptionPane.INFORMATION_MESSAGE);
    }
}
