import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Numbers first = new Numbers();
        Numbers second = new Numbers();
        Numbers answer = new Numbers();

        Scanner s = new Scanner(System.in);

        String stringInput = s.nextLine();
        String[] words = stringInput.split("\\s");

        if (words.length != 3){
            throw new Exception();
        }

        first.value = words[0];
        first.arabOrRome(first.value);

        second.value = words[2];
        second.arabOrRome(second.value);

        if(first.arabOrRome && second.arabOrRome){
            answer.value = calc(first.value, second.value, words[1], true);
            System.out.println(answer.value);
        } else if (!first.arabOrRome && !second.arabOrRome) {
            first.valueAfterConvert = first.converterToArab(first.value);
            second.valueAfterConvert = second.converterToArab(second.value);
            answer.value = calc(first.valueAfterConvert, second.valueAfterConvert, words[1], false);
            System.out.println(answer.converterToRome(answer.value));
        } else {
            throw new Exception();
        }



    }
    public static String calc(String firstInput, String secondInput, String thirdInput, boolean ArabOrRome) throws Exception {
        int answer;
        int firstCalculation = Integer.parseInt(firstInput);
        int secondCalculation = Integer.parseInt(secondInput);

        if (firstCalculation > 10 || firstCalculation <= 0){
            throw new Exception();
        }
        if(secondCalculation > 10 || secondCalculation <= 0){
            throw new Exception();
        }

        if(Objects.equals(thirdInput, "+")){
            answer = (firstCalculation + secondCalculation);
        } else if (Objects.equals(thirdInput, "-")) {
            answer = firstCalculation - secondCalculation;
        } else if (Objects.equals(thirdInput, "*")) {
            answer = firstCalculation * secondCalculation;
        } else if (Objects.equals(thirdInput, "/")) {
            answer = firstCalculation / secondCalculation;
        } else {
            throw new Exception();
        }

        if(answer <= 0 && !ArabOrRome){
            throw new Exception();
        }
        return Integer.toString(answer);
    }
}

class Numbers{
    String value;
    String valueAfterConvert;
    boolean arabOrRome; // Если арабское, то true
    String[] arabNumbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] romeNumbers = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    String[] romeLit = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};
    void arabOrRome(String input){
        for (int i = 0; i < 10; i++){
            if(Objects.equals(input, arabNumbers[i])){
                arabOrRome = true;
            }
            else if(Objects.equals(input, romeNumbers[i])){
                arabOrRome = false;
            }
        }
    }
    String converterToRome(String input){
        int arab = Integer.parseInt(input);
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++){
            while (arab >= values[i]){
                arab -= values[i];
                roman.append(romeLit[i]);
            }
        }
        return roman.toString();
    }
    String converterToArab(String input) throws Exception {
        for (int i = 0; i < 10; i++){
            if(Objects.equals(input, romeNumbers[i])){
                return arabNumbers[i];
            }
        }
        throw new Exception();
    }
}
