package classes;

public class InsuranceSystem {
    InsuranceSystem() {

    }

    public static boolean validate(int insInt) { //always return true
        if (insInt >= 0)
            return true;
        return false;
    }
}
