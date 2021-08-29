package classes;

public class LabTest {
    private TestItem testItem;
    private int price;

    LabTest(TestItem testItem, int price) {
        this.testItem = testItem;
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setTestItem(TestItem testItem) {
        this.testItem = testItem;
    }

    public TestItem getTestItem() {
        return testItem;
    }
}
