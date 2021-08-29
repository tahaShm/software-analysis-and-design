package classes;

import exceptions.BadChoice;
import exceptions.EmptyChoice;

import java.util.ArrayList;

public class AvailableLabListDAO {
    private ArrayList<LabSystem> labSystems;

    AvailableLabListDAO(ArrayList<TestItem> testItems, ArrayList<Insurance> insuranceList) {
        labSystems = new ArrayList<>();
        int id1 = 1;
        String name1 = "Taha lab";
        String address1 = "Tehran, somewhere";
        String phoneNumber1 = "09371863994";
        String licenseNumber1 = "6491";
        //***
        LabTest labTest1_1 = new LabTest(testItems.get(0), 50000);
        LabTest labTest1_2 = new LabTest(testItems.get(1), 25000);
        LabTest labTest1_3 = new LabTest(testItems.get(2), 64000);
        ArrayList<LabTest> labTests1 = new ArrayList<>();
        labTests1.add(labTest1_1);
        labTests1.add(labTest1_2);
        labTests1.add(labTest1_3);
        //***
        ArrayList<Insurance> insuranceList1 = new ArrayList<>();
        insuranceList1.add(insuranceList.get(1));
        insuranceList1.add(insuranceList.get(3));
        //***
        ScheduleUnit scheduleUnit1_1 = new ScheduleUnit(1, "Monday", 9, 20, 10, 20);
        ScheduleUnit scheduleUnit1_2 = new ScheduleUnit(2, "Monday", 10, 20, 11, 20);
        ScheduleUnit scheduleUnit1_3 = new ScheduleUnit(3, "Monday", 11, 20, 12, 20);
        ScheduleUnit scheduleUnit1_4 = new ScheduleUnit(4, "Monday", 12, 20, 13, 20);
        ArrayList<ScheduleUnit> scheduleUnits1 = new ArrayList<>();
        scheduleUnits1.add(scheduleUnit1_1);
        scheduleUnits1.add(scheduleUnit1_2);
        scheduleUnits1.add(scheduleUnit1_3);
        scheduleUnits1.add(scheduleUnit1_4);
        LabSystem labSystem1 = new LabSystem(id1, name1, address1, phoneNumber1, licenseNumber1,  labTests1, insuranceList1, scheduleUnits1);
        //  -----------------------------------------------------

        int id2 = 2;
        String name2 = "Parsa lab";
        String address2 = "Tehran, nowhere";
        String phoneNumber2 = "09128396271";
        String licenseNumber2 = "6604";
        //***
        LabTest labTest2_1 = new LabTest(testItems.get(3), 28000);
        LabTest labTest2_2 = new LabTest(testItems.get(2), 42000);
        ArrayList<LabTest> labTests2 = new ArrayList<>();
        labTests2.add(labTest2_1);
        labTests2.add(labTest2_2);
        //***
        ArrayList<Insurance> insuranceList2 = new ArrayList<>();
        insuranceList2.add(insuranceList.get(1));
        insuranceList2.add(insuranceList.get(2));
        insuranceList2.add(insuranceList.get(4));
        //***
        ScheduleUnit scheduleUnit2_1 = new ScheduleUnit(1, "Monday", 14, 0, 15, 0);
        ScheduleUnit scheduleUnit2_2 = new ScheduleUnit(2, "Monday", 15, 0, 16, 0);
        ScheduleUnit scheduleUnit2_3 = new ScheduleUnit(3, "Monday", 16, 0, 17, 0);
        ScheduleUnit scheduleUnit2_4 = new ScheduleUnit(4, "Monday", 17, 0, 18, 0);
        ArrayList<ScheduleUnit> scheduleUnits2 = new ArrayList<>();
        scheduleUnits2.add(scheduleUnit2_1);
        scheduleUnits2.add(scheduleUnit2_2);
        scheduleUnits2.add(scheduleUnit2_3);
        scheduleUnits2.add(scheduleUnit2_4);
        LabSystem labSystem2 = new LabSystem(id2, name2, address2, phoneNumber2, licenseNumber2,  labTests2, insuranceList2, scheduleUnits2);
        //  -----------------------------------------------------

        int id3 = 3;
        String name3 = "Houman lab";
        String address3 = "Tehran, anywhere";
        String phoneNumber3 = "09100681723";
        String licenseNumber3 = "6985";
        LabTest labTest3_1 = new LabTest(testItems.get(4), 50000);
        LabTest labTest3_2 = new LabTest(testItems.get(1), 35000);
        LabTest labTest3_3 = new LabTest(testItems.get(0), 15000);
        ArrayList<LabTest> labTests3 = new ArrayList<>();
        labTests3.add(labTest3_1);
        labTests3.add(labTest3_2);
        labTests3.add(labTest3_3);
        //***
        ArrayList<Insurance> insuranceList3 = new ArrayList<>();
        insuranceList3.add(insuranceList.get(0));
        insuranceList3.add(insuranceList.get(3));
        //***
        ScheduleUnit scheduleUnit3_1 = new ScheduleUnit(1, "Friday", 7, 30, 8, 30);
        ScheduleUnit scheduleUnit3_2 = new ScheduleUnit(2, "Friday", 8, 30, 9, 30);
        ScheduleUnit scheduleUnit3_3 = new ScheduleUnit(3, "Friday", 9, 30, 10, 30);
        ScheduleUnit scheduleUnit3_4 = new ScheduleUnit(4, "Friday", 10, 30, 11, 30);
        ArrayList<ScheduleUnit> scheduleUnits3 = new ArrayList<>();
        scheduleUnits3.add(scheduleUnit3_1);
        scheduleUnits3.add(scheduleUnit3_2);
        scheduleUnits3.add(scheduleUnit3_3);
        scheduleUnits3.add(scheduleUnit3_4);
        LabSystem labSystem3 = new LabSystem(id3, name3, address3, phoneNumber3, licenseNumber3,  labTests3, insuranceList3, scheduleUnits3);
        //  -----------------------------------------------------

        labSystems.add(labSystem1);
        labSystems.add(labSystem2);
        labSystems.add(labSystem3);

    }

    public ArrayList<LabSystem> getAllLabSystems() {
        return labSystems;
    }

    public ArrayList<LabSystem> getLabSystemsOnTestItemIds(ArrayList<Integer> chosenTestItems) {
        ArrayList<LabSystem> validLabSystems = new ArrayList<>();
        int testItemSize = chosenTestItems.size();
        for (LabSystem labSystem: labSystems) {
            int validTestItems = 0;
            for (int id: chosenTestItems) {
                if (labSystem.findTestItemId(id))
                    validTestItems++;
            }
            if (validTestItems == testItemSize)
                validLabSystems.add(labSystem);
        }
        return validLabSystems;
    }

    public LabSystem getLabSystemOnId(int chosenLabId) {
        for (LabSystem labSystem: labSystems) {
            if (labSystem.getId() == chosenLabId)
                return labSystem;
        }
        return null;
    }

    public ArrayList<ScheduleUnit> getValidScheduleUnits(int chosenLabId) throws EmptyChoice {
        for (LabSystem labSystem: labSystems) {
            if (chosenLabId == labSystem.getId()){
                ArrayList<ScheduleUnit> validScheduleUnits = labSystem.getValidScheduleUnits();
                if (validScheduleUnits.size() == 0)
                    throw new EmptyChoice();
                else
                    return validScheduleUnits;
            }
        }
        throw new EmptyChoice();

    }

    public ScheduleUnit setAndReturnScheduleUnit(int chosenLabId, int chosenScheduleId) throws BadChoice {
        for (LabSystem labSystem: labSystems) {
            if (chosenLabId == labSystem.getId()) {
                for (ScheduleUnit scheduleUnit: labSystem.getValidScheduleUnits()) {
                    if (scheduleUnit.getId() == chosenScheduleId) {
                        scheduleUnit.setOccupied(true);
                        return scheduleUnit;
                    }
                }
            }
        }
        throw new BadChoice();
    }
}
