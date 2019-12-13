import java.util.InputMismatchException;
import java.util.Scanner;

public class Gold extends Estimator {
    Scanner keyboard = new Scanner(System.in);

    private String subscriber, planType;
    private double usedDeductible = 0, deductible = 0, remainingDeductible = 0, hsaFunds = 0;
    private double patientOOP = 0;

    public Gold() {}

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

    public String getPlanType() {
        System.out.println("Is this a family plan or individual plan? Enter \"family\" or \"individual\": ");
        String response;
        while (true) {
            response = keyboard.nextLine().trim().toLowerCase();
            if (response.equals("family")) {
                planType = "Family";
                deductible = 1000;
                break;
            }
            else if (response.equals("individual")) {
                planType = "Individual";
                deductible = 500;
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

    public double getHsaFunds() {
        String response;
        while (true) {
            System.out.println("Do you have an HSA (y/n): ");
            response = keyboard.nextLine().trim().toLowerCase();
            if (response.equals("y")) {
                System.out.println("Please enter the available amount of HSA funds: ");
                while (hsaFunds == 0){
                    try{
                        hsaFunds = keyboard.nextDouble();
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Please enter the available amount using only numbers.");
                        keyboard.nextLine();
                    }
                }
                break;
            }
            else if (response.equals("n")) {
                break;
            }
            else {
                System.out.println("Please enter y/n for your answer.");
            }
        }
        return hsaFunds;
    }

    public double OutOfPocket(double cost, double coInsurance, double hsa) {
        cost = super.getCost();
        coInsurance = super.getCoInsurance();
        hsa = hsaFunds;
        patientOOP = cost - remainingDeductible;
        patientOOP = patientOOP - (patientOOP * coInsurance) - hsa;
        if (patientOOP < 0) {
            patientOOP = 0;
        }
        return patientOOP;
    }

    public String StringOutPut() {
        String s = "\nSubscriber: " + subscriber
                + "\nPlan type: " + planType
                + "\nRemaining Deductible: " + String.format("$%.2f", remainingDeductible)
                + "\nHSA amount remaining: " + String.format("$%.2f", hsaFunds)
                + "\nPatient Out-of-pocket: " + String.format("$%.2f", patientOOP);
        s += super.StringOutPut();
        return s;
    }
}
