import java.io.FileNotFoundException;
import java.util.Scanner;

/*
This is the main class to execute the program
This will ask the user if they have the gold or silver plan and determine which derived class to run
Check if the user enters the correct plan and ask to re-input if not
Any future insurance plans could easily be added
*/

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Is this a gold plan or silver plan? Enter \"gold\" or \"silver\": ");
        String response;
        while (true) {
            response = keyboard.nextLine().trim().toLowerCase();
            if (response.equals("gold")) {
                Gold goldPlan = new Gold();
                String patientName = goldPlan.getName();
                double procedureCost = goldPlan.getCost();
                double coInsurance = goldPlan.getCoInsurance();
                String subscriber = goldPlan.getSubscriber();
                String planType = goldPlan.getPlanType();
                double usedDeductible = goldPlan.getUsedDeductible();
                double remainingDeductible = goldPlan.getRemainingDeductible();
                double hsaFunds = goldPlan.getHsaFunds();
                double patientOOP = goldPlan.OutOfPocket(procedureCost,coInsurance,hsaFunds);
                System.out.println(goldPlan.StringOutPut());
                System.out.println(goldPlan.writeFile());
                break;
            } else if (response.equals("silver")) {
                Silver silverPlan = new Silver();
                String patientName = silverPlan.getName();
                double procedureCost = silverPlan.getCost();
                double coInsurance = silverPlan.getCoInsurance();
                String subscriber = silverPlan.getSubscriber();
                String planType = silverPlan.getPlanType();
                double usedDeductible = silverPlan.getUsedDeductible();
                double remainingDeductible = silverPlan.getRemainingDeductible();
                double patientOOP = silverPlan.OutOfPocket(procedureCost,coInsurance);
                System.out.println(silverPlan.StringOutPut());
                System.out.println(silverPlan.writeFile());
                break;
            } else {
                System.out.println("Error. Please enter \"gold\" or \"silver\": ");

            }
        }
    }
}