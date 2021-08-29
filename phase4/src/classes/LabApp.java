package classes;

import exceptions.BadChoice;
import exceptions.EmptyChoice;
import exceptions.ExitException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LabApp {
    private List<TestRegistration> testRegistrationList;
    private AvailableTestListDAO availableTestListDAO;
    private AvailableLabListDAO availableLabListDAO;
    private ValidInsuranceListDAO validInsuranceListDAO;
    private User user;
    private String exitKeyword;

    public LabApp() {
        availableTestListDAO = new AvailableTestListDAO();
        validInsuranceListDAO = new ValidInsuranceListDAO();
        availableLabListDAO = new AvailableLabListDAO(availableTestListDAO.getAllTestItems(), validInsuranceListDAO.getInsuranceList());
        ArrayList<Insurance> userInsuranceList = new ArrayList<>();
        userInsuranceList.add(validInsuranceListDAO.getInsuranceList().get(0));
        user = new User("test testi", "0022118899", "09123456789", "testi@gmail.com", "test abad", userInsuranceList);
        exitKeyword = "q";
    }

    public void startRegistration() {
        try {
            // start registration
            System.out.println("Enter  " + exitKeyword + " to quit anytime you want");
            TestRegistration testRegistration = new TestRegistration();
            testRegistration.startRegistration();

            // fetching test items
            ArrayList<TestItem> testItems = availableTestListDAO.getAllTestItems();
            ArrayList<Integer> chosenTestItems = this.printAndChooseTestItems(testItems); //delete from dsd
            testRegistration.setTestItems(chosenTestItems);

            // fetching lab systems
            ArrayList<LabSystem> validLabSystems = availableLabListDAO.getLabSystemsOnTestItemIds(chosenTestItems);

            if (validLabSystems.size() == 0)
                throw new EmptyChoice();

            int chosenLabId = this.printAndChooseLabSystems(validLabSystems); //delete from dsd
            LabSystem curLabSystem = availableLabListDAO.getLabSystemOnId(chosenLabId);
            testRegistration.setLabSystem(curLabSystem);

            // handling insurances
            ArrayList<Insurance> insuranceList = curLabSystem.getInsuranceList();
            int insValidationId = validateUserInsurance(insuranceList);

            if (insValidationId == -1) {
                System.out.println("Your insurance was not valid for current lab");
                String insId = "0";
                Scanner myObj = new Scanner(System.in);
                while ((insValidationId == -1)) {
                    System.out.println("You can enter new insurance id if you want: (enter [number to validate] or [x to continue])");
                    insId = myObj.nextLine();
                    if (insId.equals("x"))
                        break;
                    int insInt = Integer.parseInt(insId);

                    insValidationId = validateUserNewInsurance(insuranceList, insInt);
                }
            }
            if (insValidationId != -1) {
                Insurance curInsurance = getInsuranceById(insuranceList, insValidationId);
                testRegistration.setInsurance(curInsurance);
                System.out.println("Your insurance was accepted by your chosen lab");
            }

            // fetching lab schedules
            ArrayList<ScheduleUnit> validScheduleUnits = availableLabListDAO.getValidScheduleUnits(chosenLabId);
            int chosenScheduleId = this.printAndChooseSchedule(validScheduleUnits); //delete from dsd
            ScheduleUnit curSchedule = availableLabListDAO.setAndReturnScheduleUnit(chosenLabId, chosenScheduleId);
            testRegistration.setSchedule(curSchedule);
            System.out.println("Time slot : " + curSchedule.getDay() + " " + curSchedule.getStartTime() + " to " + curSchedule.getFinishTime() + " has been reserved for you.");

            // payment process
            int totalPrice = testRegistration.calculateTotalPrice();
            System.out.println("Total test price : " + totalPrice);
            testRegistration.createTestCatalog(user);

            System.out.println("For payment process enter x, for quit enter q");
            Scanner myObj = new Scanner(System.in);
            String inp = myObj.nextLine();
            if (inp.equals("x"))
                testRegistration.createPayment();
            else
                throw new ExitException();



        }
        catch (Exception e) {
            if (e instanceof ExitException)
                System.out.println("User finished the process...");
            else if (e instanceof EmptyChoice)
                System.out.println("No choices were valid, so process finished...");
            else if (e instanceof BadChoice)
                System.out.println("Wrong choice, so process finished...");
        }



    }

    private int printAndChooseSchedule(ArrayList<ScheduleUnit> validScheduleUnits) throws ExitException, BadChoice {
        System.out.println("List of available time slots :");
        ArrayList<Integer> schIds = new ArrayList<>();
        for (ScheduleUnit scheduleUnit: validScheduleUnits) {
            schIds.add(scheduleUnit.getId());
            System.out.println(scheduleUnit.getId() + " :");
            System.out.println("    Day: " + scheduleUnit.getDay());
            System.out.println("    Starts at " + scheduleUnit.getStartTime());
            System.out.println("    Finishes at " + scheduleUnit.getFinishTime());
            System.out.println("_______________________________________________");
        }
        System.out.println("enter time slot number: for example: "+  validScheduleUnits.get(0).getId());

        Scanner myObj = new Scanner(System.in);
        String userInput;
        userInput = myObj.nextLine();
        if (userInput.equals(exitKeyword))
            throw new ExitException();

        int curSchId = Integer.parseInt(userInput);

        if (schIds.contains(curSchId) == false)
            throw new BadChoice();

        return curSchId;
    }

    public ArrayList<Integer> printAndChooseTestItems(ArrayList<TestItem> testItems) throws ExitException {
        System.out.println("List of available tests :");
        for (TestItem testItem: testItems) {
            System.out.println(testItem.getId() + " :");
            System.out.println("    Description : " + testItem.getDescription());
            System.out.println("    Tools : ");
            ArrayList<String> tools = testItem.getTools();
            for (String tool: tools) {
                System.out.println("        " + tool);
            }
            System.out.println("_______________________________________________");
        }
        System.out.println("enter test ids: for example: 1,2,3");

        Scanner myObj = new Scanner(System.in);
        String userInput;
        ArrayList<Integer> userInputs = new ArrayList<Integer>();
        userInput = myObj.nextLine();

        if (userInput.equals(exitKeyword))
            throw new ExitException();

        StringTokenizer tokenizer = new StringTokenizer(userInput, ",");

        while (tokenizer.hasMoreTokens()) {
            int curId = Integer.parseInt(tokenizer.nextToken());
            userInputs.add(curId);
        }
        return userInputs;
    }

    private int printAndChooseLabSystems(ArrayList<LabSystem> validLabSystems) throws ExitException, BadChoice {
        System.out.println("List of valid Labs for your tests :");
        ArrayList<Integer> labIds = new ArrayList<>();
        for (LabSystem labSystem: validLabSystems) {
            labIds.add(labSystem.getId());
            System.out.println(labSystem.getId() + " :");
            System.out.println("    Name : " + labSystem.getName());
            System.out.println("    Address : " + labSystem.getAddress());
            System.out.println("    Phone Number : " + labSystem.getPhoneNumber());
            System.out.println("    License Number : " + labSystem.getLicenseNumber());
            System.out.println("    Insurance list : ");
            ArrayList<Insurance> InsuranceList = labSystem.getInsuranceList();
            for (Insurance insurance: InsuranceList) {
                System.out.println("        " + insurance.getId() + ". " + insurance.getName());
            }
            System.out.println("_______________________________________________");
        }
        System.out.println("enter lab id: for example: " + validLabSystems.get(0).getId());

        Scanner myObj = new Scanner(System.in);
        String userInput;
        userInput = myObj.nextLine();
        if (userInput.equals(exitKeyword))
            throw new ExitException();

        int curLabId = Integer.parseInt(userInput);

        if (labIds.contains(curLabId) == false)
            throw new BadChoice();

        return curLabId;

    }

    public int validateUserInsurance(ArrayList<Insurance> insuranceList) {
        ArrayList<Integer> insuranceIds = new ArrayList<>();
        ArrayList<Integer> userInsuranceIds = new ArrayList<>();

        for (Insurance insurance: insuranceList)
            insuranceIds.add(insurance.getId());
        ArrayList<Insurance> userInsurances = user.getInsuranceList();
        for (Insurance insurance: userInsurances)
            userInsuranceIds.add(insurance.getId());

        for (Integer insId : userInsuranceIds) {
            if (insuranceIds.contains(insId))
                return insId;
        }
        return -1;
    }

    public int validateUserNewInsurance(ArrayList<Insurance> insuranceList, int insInt) throws BadChoice {
        ArrayList<Integer> insuranceIds = new ArrayList<>();
        for (Insurance insurance: insuranceList)
            insuranceIds.add(insurance.getId());
        if (insuranceIds.contains(insInt)) {
            System.out.println("here : " + insInt);
            Insurance newIns = getInsuranceById(insuranceList, insInt);
            user.addNewInsurance(newIns);
            return insInt;
        }
        return -1;

    }

    private Insurance getInsuranceById(ArrayList<Insurance> insuranceList, int insInt) throws BadChoice {
        for (Insurance insurance: insuranceList) {
            if (insurance.getId() == insInt)
                return insurance;
        }
        throw new BadChoice();
    }
}
