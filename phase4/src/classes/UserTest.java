package classes;

import exceptions.BadChoice;
import exceptions.ExitException;

import java.util.ArrayList;

public class UserTest {
    private ArrayList<Integer> testItemIds;
    private LabSystem labSystem;
    private Insurance insurance = null;
    private ScheduleUnit scheduleUnit;
    private int totalPrice = 0;
    private TestCatalog testCatalog;
    private boolean isPaid;
    private Payment payment;

    UserTest() {
        testItemIds = new ArrayList<Integer>();
        isPaid = false;
    }

    public void setTestItems(ArrayList<Integer> testItemIds) {
        this.testItemIds = testItemIds;
    }

    public ArrayList<Integer> getTestItemIds() {
        return testItemIds;
    }

    public void setLabSystem(LabSystem curLabSystem) {
        this.labSystem = curLabSystem;
    }

    public void setInsurace(Insurance curInsurance) {
        this.insurance = curInsurance;
    }

    public void setSchedule(ScheduleUnit curSchedule) {
        this.scheduleUnit = curSchedule;
    }


    public int calculateTotalPrice() throws BadChoice {
        int total = 0;
        for (Integer testItemId: testItemIds) {
            total += labSystem.getTestItemPrice(testItemId);
        }
        if (insurance != null){
            total = total * (100 - insurance.getDiscountPercentage()) / 100;
        }
        totalPrice = total;
        return totalPrice;
    }

    public void createTestCatalog(User user) throws BadChoice {
        String description = "";
        description += "___________________________________________________" + "\n";
        description += "Patient's name : " + user.getName() + "\n";
        description += "Patient's address : " + user.getAddress() + "\n";
        description += "Patient's phoneNumber : " + user.getPhoneNumber() + "\n";
        description += "Patient's national Id : " + user.getSocNumber() + "\n";
        description += "Patient's email address : " + user.getEmail() + "\n";
        description += "___________________________________________________" + "\n";
        description += "Lab name : " + labSystem.getName() + "\n";
        description += "Lab address : " + labSystem.getAddress() + "\n";
        description += "Lab license number : " + labSystem.getLicenseNumber() + "\n";
        description += "Lab phoneNumber : " + labSystem.getPhoneNumber() + "\n";
        description += "___________________________________________________" + "\n";
        description += "Tests : " + "\n";
        for (Integer testItemId: testItemIds) {
            LabTest curLabtest = labSystem.getLabTest(testItemId);
            description += testItemId + ". " + curLabtest.getTestItem().getDescription() + " -> price : " + curLabtest.getPrice() + "\n";
        }
        description += "___________________________________________________" + "\n";
        description += "Time slot : " + scheduleUnit.getDay() + " " + scheduleUnit.getStartTime() + " to " + scheduleUnit.getFinishTime() + "\n";
        description += "___________________________________________________" + "\n";
        if (insurance == null)
            description += "Insurance : None \n";
        else {
            description += "Insurance :" + insurance.getName() + "-> discount : " + insurance.getDiscountPercentage() + "% \n";
        }
        description += "Total price : " + totalPrice + "\n";
        description += "___________________________________________________" + "\n";

        testCatalog = new TestCatalog(description);
//        System.out.println(description);
    }

    public void createPayment() throws ExitException {
        System.out.println("Creating new payment...");
        payment = new Payment(totalPrice);
        System.out.println("Reference to bank...");
        if (payment.getStatus() == "successful") {
            System.out.println("Payment process was successful...");
            setIsPaid();
        }
        else {
            System.out.println("Payment process failed");
            throw new ExitException();
        }
    }

    public void setIsPaid() {
        isPaid = true;
    }
}
