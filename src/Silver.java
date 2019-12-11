import java.util.InputMismatchException;
import java.util.Scanner;

public class Silver {
    Scanner keyboard = new Scanner(System.in);

    private String subscriber, planType;
    private double usedDeductible = 0, deductible = 0, remainingDeductible = 0;
    private double coInsurance;
    private double cost = 0;
    private double patientOOP = 0;

    public Silver() {}

    public String getSubscriber() {
        System.out.println("Are you the subscriber (y/n): ");
        String response;
        while (true) {
            response = keyboard.nextLine().trim().toLowerCase();
            if (response.equals("y")) {
                subscriber = "Scott Donner";
                break;
            }
            else if (response.equals("n")) {
                System.out.println("Please enter the subscriber's first and last name: ");
                subscriber = keyboard.nextLine();
                break;
            }
            else {
                System.out.println("Error. Please enter y/n for your answer.  Are you the subscriber?");
            }
        }

        return subscriber;
    }

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

    public double getRemainingDeductible() {
        remainingDeductible = deductible - usedDeductible;
        return remainingDeductible;
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

    public double OutOfPocket(double cost, double deductible, double coInsurance) {
        patientOOP = cost - remainingDeductible;
        patientOOP = patientOOP - (patientOOP * coInsurance);
        if (patientOOP < 0) {
            patientOOP = 0;
        }
        return patientOOP;
    }

    public String StringOutPut() {
        String s = "\nSubscriber: " + subscriber
                + "\nPlan type: " + planType
                + "\nMaximum Deductible: " + String.format("$%.2f", deductible)
                + "\nRemaining Deductible: " + String.format("$%.2f", remainingDeductible)
                + "\nProcedure cost: " + String.format("$%.2f", cost)
                + "\nPatient Out-of-pocket: " + String.format("$%.2f", patientOOP);
        return s;
    }
}