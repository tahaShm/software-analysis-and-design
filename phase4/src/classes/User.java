package classes;

import java.util.ArrayList;

public class User {
    private String name;
    private String socNumber;
    private String phoneNumber;
    private String email;
    private String address;
    private ArrayList<Insurance> insuranceList;

    public User(String name, String socNumber, String phoneNumber, String email, String address, ArrayList<Insurance> insuranceList ) {
        this.name =  name;
        this.socNumber = socNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.insuranceList = insuranceList;
    }

    public String getName() {
        return name;
    }

    public String getSocNumber() {
        return socNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Insurance> getInsuranceList() {
        return insuranceList;
    }

    public void addNewInsurance(Insurance newIns) {
        insuranceList.add(newIns);
    }
}
