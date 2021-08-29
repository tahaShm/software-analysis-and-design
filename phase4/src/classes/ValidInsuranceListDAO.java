package classes;

import java.util.ArrayList;

public class ValidInsuranceListDAO {
    private ArrayList<Insurance> insuranceList;

    ValidInsuranceListDAO() {
        insuranceList = new ArrayList<>();
        Insurance insurance1 = new Insurance(1, "shahrvandi", 5);
        Insurance insurance2 = new Insurance(2, "tamin ejtemai", 20);
        Insurance insurance3 = new Insurance(3, "havades", 25);
        Insurance insurance4 = new Insurance(4, "janbazan", 15);
        Insurance insurance5 = new Insurance(5, "bime5", 10);
        insuranceList.add(insurance1);
        insuranceList.add(insurance2);
        insuranceList.add(insurance3);
        insuranceList.add(insurance4);
        insuranceList.add(insurance5);
    }

    public ArrayList<Insurance> getInsuranceList() {
        return insuranceList;
    }
}
