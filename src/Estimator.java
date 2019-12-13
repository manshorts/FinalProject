import java.util.InputMismatchException;
import java.util.Scanner;

public class Estimator {
    Scanner keyboard = new Scanner(System.in);

// This is the base class
// Gold and Silver will both extend this class

    public String name;
    double cost = 0;
    double coInsurance = 0;

    //Construct a default base object
    // no-arg constructor
    public Estimator() {
    }
    // Get name via input.
    public String getName() {
        System.out.println("Please enter your name: ");
        name = keyboard.nextLine();
        return name;
    }
    // Set a new name
    public void setName(String name) {
        this.name = name;
    }

    public double getCost(){
        System.out.println("Please enter the cost of the procedure: ");
        while(cost == 0) {
            try{
                cost = keyboard.nextDouble();
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter an amount for the procedure using only numbers.");
                keyboard.nextLine();
            }
        }
        return cost;
    }
    // Set a new cost
    public void setCost(Double cost) {
        this.cost = cost;
    }
    public double getCoInsurance() {
        System.out.println("Is the procedure in-network or out-of-network?");
        String inOrOut;
        while (coInsurance == 0) {
            inOrOut = keyboard.nextLine().trim().toLowerCase();
            if (inOrOut.equals("in")){
                coInsurance = .8;
                break;
            }
            else if (inOrOut.equals("out")) {
                coInsurance = .5;
                break;
            }
            else {
                System.out.println("Please enter \"in\" for in-network and \"out\" for out-of-network: ");
            }
        }
        return coInsurance;
    }
    //Return a string representation of the patient name that is used in both Gold and Silver
    public String StringOutPut() {
        return "\nPatient Name: " + name +
                "\nProcedure Cost: " + String.format("$%.2f", cost);
    }

}