package classes;

public class Payment {
    private int totalPrice;
    private PaymentSystem paymentSystem;
    private String status;

    Payment(int totalPrice) {
        this.totalPrice = totalPrice;
        paymentSystem = new PaymentSystem();
        status = paymentSystem.referenceToApi();
    }

    public String getStatus() {
        return status;
    }
}
