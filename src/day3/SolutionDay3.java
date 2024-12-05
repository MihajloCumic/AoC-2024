package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolutionDay3 {

    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("resources/input-day-3.txt");
        Scanner myReader = new Scanner(myFile);

        String patternMulStr = "mul\\([0-9]{1,3},[0-9]{1,3}\\)";
        String patternDoStr = "do\\(\\)";
        String patternDontStr = "don't\\(\\)";

        String patternCombineString = "(" + patternMulStr + ")|(" + patternDoStr + ")|(" + patternDontStr + ")";
        Pattern pattern = Pattern.compile(patternCombineString);

        int sum = 0;
        boolean doFlag = true;
        while(myReader.hasNextLine()){
            String data = myReader.nextLine();
            Matcher matcher = pattern.matcher(data);
            while (matcher.find()){
                String token = matcher.group();
                if(Pattern.matches(patternMulStr, token) && doFlag){
                    sum += extractProduct(matcher.group());
                    continue;
                }
                if(Pattern.matches(patternDoStr, token)){
                    doFlag = true;
                    continue;
                }
                if(Pattern.matches(patternDontStr, token)) {
                    doFlag = false;
                }

            }
        }
        System.out.println(sum);

    }

    private static int extractProduct(String str){
        Pattern pattern = Pattern.compile("[0-9]{1,3}");
        Matcher matcher = pattern.matcher(str);
        int product = 1;
        while (matcher.find()){
           product *= Integer.parseInt(matcher.group());
        }
        return product;
    }
}
