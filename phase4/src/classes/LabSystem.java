package classes;

import exceptions.BadChoice;

import java.util.ArrayList;

public class LabSystem {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String licenseNumber;
    private ArrayList<LabTest> labTests;
    private ArrayList<Insurance> insuranceList;
    private ArrayList<ScheduleUnit> scheduleUnits;

    LabSystem(int id, String name, String address, String phoneNumber, String licenseNumber, ArrayList<LabTest> labTests, ArrayList<Insurance> insuranceList, ArrayList<ScheduleUnit> scheduleUnits) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.licenseNumber = licenseNumber;
        this.labTests = labTests;
        this.insuranceList = insuranceList;
        this.scheduleUnits = scheduleUnits;
    }

    public boolean findTestItemId(int id) {
        for (LabTest labTest: labTests) {
            if (labTest.getTestItem().getId() == id)
                return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Insurance> getInsuranceList() {
        return insuranceList;
    }

    public ArrayList<LabTest> getLabTests() {
        return labTests;
    }

    public ArrayList<ScheduleUnit> getValidScheduleUnits() {
        ArrayList<ScheduleUnit> validScheduleUnits = new ArrayList<>();
        for (ScheduleUnit scheduleUnit: scheduleUnits) {
            if (scheduleUnit.isOccupied() == false)
                validScheduleUnits.add(scheduleUnit);
        }
        return validScheduleUnits;
    }

    public int getTestItemPrice(Integer testItemId) throws BadChoice {
        for (LabTest labTest: labTests) {
            if (labTest.getTestItem().getId() == testItemId) {
                return labTest.getPrice();
            }
        }
        throw new BadChoice();
    }

    public LabTest getLabTest(Integer testItemId) throws BadChoice {
        for (LabTest labTest: labTests) {
            if (labTest.getTestItem().getId() == testItemId) {
                return labTest;
            }
        }
        throw new BadChoice();
    }
}
