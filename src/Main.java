public class Main {

    public static void main(String[] args) {
        Silver plan = new Silver();
        String planSubscriber = plan.getSubscriber();
        String planType = plan.getPlanType();
        double planUsedDeductible = plan.getUsedDeductible();
        double planDeductible = plan.getRemainingDeductible();
        double planCost = plan.getCost();
        double planCoInsurance = plan.getCoInsurance();

        double planOutOfPocket = plan.OutOfPocket(planCost,planDeductible,planCoInsurance);

        System.out.println(plan.StringOutPut());

    }
}
