package classes;

import exceptions.BadChoice;
import exceptions.ExitException;

import java.util.ArrayList;

public class TestRegistration {
    private UserTest userTest;
    private InsuranceSystem insuranceSystem;

    public void startRegistration() {
        UserTest userTestTemp = new UserTest();
        userTest = userTestTemp;
    }

    public void setTestItems(ArrayList<Integer> testItemIds) {
        if (userTest != null)
            userTest.setTestItems(testItemIds);
    }

    public void setLabSystem(LabSystem curLabSystem) {
        if (userTest != null) {
            userTest.setLabSystem(curLabSystem);
        }
    }

    public void setSchedule(ScheduleUnit curSchedule) {
        if (userTest != null) {
            userTest.setSchedule(curSchedule);
        }
    }

    public void setInsurance(Insurance curInsurance) {
        if (userTest != null) {
            userTest.setInsurace(curInsurance);
        }
    }

    public int calculateTotalPrice() throws BadChoice {
        int totalPrice = 0;
        if (userTest != null) {
            totalPrice += userTest.calculateTotalPrice();
        }
        return totalPrice;
    }

    public void createTestCatalog(User user) throws BadChoice {
        if (userTest != null) {
            userTest.createTestCatalog(user);
        }
    }

    public void createPayment() throws ExitException {
        if (userTest != null) {
            userTest.createPayment();
        }
    }

}
