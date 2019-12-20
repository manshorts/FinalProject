import java.util.InputMismatchException;
import java.io.*;
import java.util.Scanner;

//The Silver class inherits all properties of the parent class Estimator
//And is one of the two options of insurance plans the user might have
public class Silver extends Estimator {

    private String subscriber, planType;
    private double usedDeductible = 0, deductible = 0, remainingDeductible = 0;
    private double patientOOP = 0;

    //no-arg default constructor
    public Silver() {}

    /*
    Ask if the user is the subscriber, if yes the name inputted in the Estimator class will be used
    If no ask the user to enter their name
    */
    public String getSubscriber() {
        System.out.println("Are you the subscriber? ");
        String response;
        while (true) {
            response = keyboard.nextLine().trim().toLowerCase();
            if (response.equals("y")) {
                subscriber = name;
                break;
            }
            else if (response.equals("n")) {
                System.out.println("Please enter the subscriber's first and last name: ");
                subscriber = keyboard.nextLine();
                break;
            }
            else {
                System.out.println("Please enter y/n for your answer: ");
            }
        }

        return subscriber;
    }

    /*
    Ask the user if the have a family or individual plan to determine what their plan deductible is and return the value
    Family Plans have a $4000 deductible on the Silver plan
    Individual Plans have a $2500 deductible
    */
    public String getPlanType() {
        System.out.println("Is this a family plan or individual plan? Enter \"family\" or \"individual\": ");
        String response;
        while (true) {
            response = keyboard.nextLine().trim().toLowerCase();
            if (response.equals("family")) {
                planType = "Family";
                deductible = 4000;
                break;
            }
            else if (response.equals("individual")) {
                planType = "Individual";
                deductible = 2500;
                break;
            }
            else {
                System.out.println("Error. Please enter \"family\" or \"individual\": ");
            }
        }
        return planType;
    }

    /*
    Ask the user the amount of their deductible they have used year to date, and return the value
    Use try and catch if a number is not entered
    This will be used to calculate how much deductible the user have left to meet before co-insurance starts to work
    */
    public double getUsedDeductible() {

        while (usedDeductible == 0) {
            try {
                System.out.println("Please enter the deductible amount used YTD: ");
                usedDeductible = keyboard.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an amount using only numbers.");
                keyboard.nextLine();
            }
        }
        return usedDeductible;
    }

    //Calculate the remaining deductible using the plan deductible - used deductible
    public double getRemainingDeductible() {
        remainingDeductible = deductible - getUsedDeductible();
        if (remainingDeductible < 0) {remainingDeductible = 0;}
        return remainingDeductible;
    }

    /*Calculate the out of pocket cost
    This will be cost - deductible remaining multiplied by the co-insurance + deductible remaining if less than cost
    If deductible remaining is greater than cost of the procedure than out of pocket expense is equal to cost of procedure
    There will not be an HSA in the Silver plan
    */
    public double OutOfPocket(double cost, double coInsurance) {
        cost = super.getCost();
        coInsurance = super.getCoInsurance();
        patientOOP = cost - remainingDeductible;
        if (cost < remainingDeductible) { patientOOP = cost;}
        patientOOP = patientOOP - (patientOOP * coInsurance);
        if (patientOOP < 0) {
            patientOOP = 0;
        }
        return patientOOP;
    }

    //Return the information for the user from the Silver class and from the Estimator class using StringOutPut()
    //Need to use super.StringOutPut()
    public String StringOutPut() {
        String s = "\nSubscriber: " + subscriber
                + "\nPlan type: " + planType
                + "\nRemaining Deductible: " + String.format("$%.2f", remainingDeductible)
                + "\nPatient Out-of-pocket: " + String.format("$%.2f", patientOOP);
        s += super.StringOutPut();
        return s;
    }

    //Save the users cost of procdure and out of pocket expense information to a file, and confirm with the user
    public String writeFile() throws FileNotFoundException {
        File data_file = new File("data.txt");
        double i = cost;
        double j = patientOOP;
        String savedFile = null;
        try (PrintWriter output = new PrintWriter(data_file)) {
            output.println(i);
            output.println(j);
        } catch (FileNotFoundException e) {
            System.out.println("Can't Find the data.txt file");
            System.exit(1);
        }
        Scanner input = new Scanner(data_file);
        while (input.hasNext()) {
            double saveCost = input.nextDouble();
            double saveOP = input.nextDouble();
            savedFile = "Your procedure cost of " + String.format("$%.2f", saveCost) + " and out of pocket cost of " + String.format("$%.2f", saveOP) + " have been saved to the file.";
        }
        return savedFile;
    }
}