public class Main {

    public static void main(String[] args) {
        Gold plan = new Gold();
        String planSubscriber = plan.getSubscriber();
        String planType = plan.getPlanType();
        double planUsedDeductible = plan.getUsedDeductible();
        double planDeductible = plan.getRemainingDeductible();
        double planHSA = plan.getHsaFunds();
        double planCost = plan.getCost();
        double planCoInsurance = plan.getCoInsurance();

        double planOutOfPocket = plan.OutOfPocket(planCost,planDeductible,planCoInsurance,planHSA);

        System.out.println(plan.StringOutPut());

    }
}
