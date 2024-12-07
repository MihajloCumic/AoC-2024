package day7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        List<Equation> equations = Part1.executePart1();
        List<Equation> incorrectEquations = new ArrayList<>();
        long sum = 0;
        for(Equation equation: equations){
            if(equation.isSolvable){
                sum += equation.value;
                continue;
            }
            incorrectEquations.add(equation);
        }
        System.out.println(sum);
        for(Equation equation: incorrectEquations){
            int spots = equation.numbers.length - 1;
            traverseAllPermutations("", spots, equation);
            if(equation.isSolvable){
                sum += equation.value;
            }
        }

        System.out.println(sum);



    }
    private static void traverseAllPermutations(String current, int spots, Equation equation){
        if(current.length() == spots){
            if(isEquationCorrect(current, equation)){
                equation.isSolvable = true;
            }
            return;
        }

        traverseAllPermutations(current + '+', spots, equation);
        traverseAllPermutations(current + '*', spots, equation);
        traverseAllPermutations(current + '|', spots, equation);
    }

    private static boolean isEquationCorrect(String operations, Equation equation){
        char[] operationsArr = operations.toCharArray();
        long acc = equation.numbers[0];

        for(int i = 0; i < operationsArr.length; i++){
            if(acc > equation.value) return false;
            char operator = operationsArr[i];
            if(operator == '+'){
                acc += equation.numbers[i + 1];
                continue;
            }
            if(operator == '*'){
                acc *= equation.numbers[i + 1];
                continue;
            }
            String num1 = String.valueOf(acc);
            String num2 = String.valueOf(equation.numbers[i + 1]);
            String concat = num1 + num2;
            acc = Long.parseLong(concat);

        }

        return acc == equation.value;
    }
}
