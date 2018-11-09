package exception;

import java.util.List;

public class NegativeNumberException extends RuntimeException {


    public NegativeNumberException(List<Integer> negatives) {
        super("negatives not allowed - " + negatives);
    }

}
