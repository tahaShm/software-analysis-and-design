package classes;

import java.util.ArrayList;

public class AvailableTestListDAO {
    private ArrayList<TestItem> testItemList;

    AvailableTestListDAO() {
        testItemList = new ArrayList<>();
        ArrayList<String> tools1 = new ArrayList<>();
        tools1.add("tube");
        tools1.add("Syringe");
        String description1 = "blood test";
        TestItem newTestItem1 = new TestItem(1, description1, tools1);
        //  -----------------------------------------------------
        ArrayList<String> tools2 = new ArrayList<>();
        tools2.add("bottle");
        String description2 = "Urinalysis";
        TestItem newTestItem2 = new TestItem(2, description2, tools2);
        //  -----------------------------------------------------
        ArrayList<String> tools3 = new ArrayList<>();
        tools3.add("Syringe");
        String description3 = "addiction test";
        TestItem newTestItem3 = new TestItem(3, description3, tools3);
        //  -----------------------------------------------------
        ArrayList<String> tools4 = new ArrayList<>();
        tools4.add("thing4-1");
        tools4.add("thing4-2");
        String description4 = "test4";
        TestItem newTestItem4 = new TestItem(4, description4, tools4);
        //  -----------------------------------------------------
        ArrayList<String> tools5 = new ArrayList<>();
        tools5.add("thing5-1");
        tools5.add("thing5-2");
        String description5 = "test5";
        TestItem newTestItem5 = new TestItem(5, description5, tools5);
        //  -----------------------------------------------------
        testItemList.add(newTestItem1);
        testItemList.add(newTestItem2);
        testItemList.add(newTestItem3);
        testItemList.add(newTestItem4);
        testItemList.add(newTestItem5);
    }

    public ArrayList<TestItem> getAllTestItems() {
        return testItemList;
    }
}
