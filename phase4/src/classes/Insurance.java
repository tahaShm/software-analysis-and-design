package classes;

public class Insurance {
    private int id;
    private String name;
    private int discountPercentage;

    Insurance(int id, String name, int discountPercentage) {
        this.id = id;
        this.name = name;
        this.discountPercentage = discountPercentage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }
}
