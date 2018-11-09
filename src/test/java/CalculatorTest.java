import exception.NegativeNumberException;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

   @Test
    public void empty_string(){
       Assert.assertEquals(0, Calculator.add(""));
   }

    @Test
    public final void when_single_number_given() {
        Assert.assertEquals(100, Calculator.add("100"));
    }

    @Test
    public final void when_multiple_numbers_given() {
        Assert.assertEquals(10, Calculator.add("1,2,7"));
    }

    @Test
    public final void when_new_line_given_between_numbers() {
        Assert.assertEquals(6, Calculator.add("1\n2\n\n3\n"));
    }

    @Test
    public final void support_delimiter() {
        Assert.assertEquals(3, Calculator.add("//;\\n1;2"));
    }

    @Test
    public final void support_delimiter_with_new_lines() {
        Assert.assertEquals(3, Calculator.add("//;\\n1;2"));
    }

    @Test(expected = NegativeNumberException.class)
    public final void negative_numbers_not_supported() {
        Calculator.add("-3");
    }

    @Test(expected = NegativeNumberException.class)
    public final void negative_numbers_not_supported_with_combination_of_positives() {
        Calculator.add("-3;2;-8,8\n2");
    }

    @Test
    public final void negative_numbers_not_supported_error_message() {
        Exception exception = null;
        try {
            Calculator.add("-3;2;-8,8\n2-1");
        } catch (RuntimeException e) {
            exception = e;
        }
        Assert.assertEquals("negatives not allowed - [-3, -8, -1]", exception.getMessage());
    }

    @Test
    public final void numbers_greater_than_1000() {
        Assert.assertEquals(2, Calculator.add("1000+2"));
        Assert.assertEquals(6, Calculator.add("//;\n1;2;3;1000;5000"));
    }

    @Test
    public final void delimiter_of_any_length() {
        Assert.assertEquals(6, Calculator.add("//***\\n1***2***3"));
    }

    @Test
    public final void support_multiple_delimiter() {
        Assert.assertEquals(6, Calculator.add("//*|%\\n1*2%3"));
    }

    @Test
    public final void support_multiple_delimiter_length_longer_than_one_char() {
        Assert.assertEquals(6, Calculator.add("//**|%%\\n1**2%%3"));
    }

    @Test
    public final void support_any_special_character() {
        Assert.assertEquals(45, Calculator.add("!@1#$2#$3$%4%^5^&6^&7&*8(9)"));
    }

    @Test
    public final void support_aplhabets() {
        Assert.assertEquals(45, Calculator.add("1a2b3c4d5e6f7g8h9i"));
    }

    @Test
    public final void support_garbage() {
        Assert.assertEquals(1243, Calculator.add("534nkjkh56456456kjlkjlk5j6l4j56l4j65j4k64j56j445"));
    }

}
