import java.util.InputMismatchException;

//The Gold class inherits all properties of the parent class Estimator
//And is one of the two options of insurance plans the user might have

//Any input that does not meet the requirements needs to throw and exception ask the user to correct
public class Gold extends Estimator {

    //Declare variables, All numbers are declared as doubles to handle change and co-insurance multiplication
    private String subscriber, planType;
    private double usedDeductible = 0, deductible = 0, remainingDeductible = 0, hsaFunds = 0;
    private double patientOOP = 0;

    //no-arg default constructor
    public Gold() {}

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
        //return the subscriber name to be used in the output
        return subscriber;
    }

    /*
    Ask the user if the have a family or individual plan to determine what their plan deductible is, and return the value
    Family Plans have a $1000 deductible on the gold plan
    Individual Plans have a $500 deductible
    */
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

    /*
    Ask the user what amount of their deductible they have used year to date, and return the value
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
        remainingDeductible = deductible - usedDeductible;
        return remainingDeductible;
    }

    /*
    Ask the user if they have an HSA account, only the gold plan can have an HSA but not all gold plans will have an HSA
    HSA funds will be used before out of pocket cost
    */
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

    //Calculate the out of pocket cost
    //This will be cost - deductible remaining multiplied by the co-insurance then subtract and hsa funds
    //If deductible remaining is greater than cost of the procedure, then the out of pocket expense is equal to cost of procedure - hsa funds
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

    //Return the information for the user from the Gold class including:
    //Subscriber, plantype, remaining deductible, hsa amount and out of pocket amount
    //Also return the information from the Estimator class using StringOutPut() which will display the cost and their name
    //Need to use super.StringOutPut()
    public String StringOutPut() {
        String s = "\nSubscriber: " + subscriber
                + "\nPlan type: " + planType
                + "\nRemaining Deductible: " + String.format("$%.2f", remainingDeductible)
                + "\nHSA amount remaining: " + String.format("$%.2f", hsaFunds)
                + "\nPatient Out-of-pocket: " + String.format("$%.2f", patientOOP);
        s += super.StringOutPut();
        return s;
    }
    /*Create a method that will create an array of of the data that was displayed to the user.
    This array will include:
    Name, Plan type, cost of procedure, and out-of-pocket expense
    Then ask the user if they would like to continue with procedure request
    If Yes: Write the information that was created in the array to a data.txt file
            Ask the user their Physicians Name. Display the information from the saved data file and state the following message:
            Thank you - the following information has been sent to your physician.
     If No: Display the message: Thank you for using the cost estimator and have a good day.
     */
}
