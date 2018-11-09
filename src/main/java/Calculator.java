import exception.NegativeNumberException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculator {

    public static int add(String numbers){

        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(numbers);

        //extract all digits from user given input
        List<Integer> integers = new ArrayList<>();
        while (m.find()) {
            integers.add(Integer.valueOf(m.group()));
        }

        //get all negatives present in the given user input
        List<Integer> negatives = integers.stream().filter(i->i<0).collect(Collectors.toList());

        if(negatives.isEmpty()){
            return integers.stream().filter(i->i<1000).mapToInt(Integer::intValue).sum();
        }else {
            throw new NegativeNumberException(negatives);
        }
    }
}
