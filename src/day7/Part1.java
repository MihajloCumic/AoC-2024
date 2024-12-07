package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<Equation> equations = executePart1();
        long sum = 0;
        for(Equation equation: equations){
            if(equation.isSolvable){
                sum += equation.value;
            }
        }
        System.out.println(sum);

    }
    public static List<Equation> executePart1() throws IOException {
        List<Equation> equations = initializeEquations("resources/input-day-7.txt");
        for(Equation equation: equations){
            int spots = equation.numbers.length - 1;
            traverseAllPermutations("", spots, equation);
        }

        return equations;


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
    }

    private static boolean isEquationCorrect(String operations, Equation equation){
        char[] operationsArr = operations.toCharArray();
        long acc = equation.numbers[0];
        for(int i = 0; i < operationsArr.length; i++){
            if(acc > equation.value) return false;
            if(operationsArr[i] == '+'){
                acc += equation.numbers[i + 1];
                continue;
            }
            acc *= equation.numbers[i + 1];
        }
        return acc == equation.value;
    }

    private static List<Equation> initializeEquations(String path) throws IOException {
        List<Equation> equations = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            reader.lines().forEach(line -> {
                String[] values = line.split(":");

                if(values.length != 2) throw new RuntimeException("Invalid input");

                long value = Long.parseLong(values[0]);

                String[] numbersStr = values[1].trim().split(" ");
                long[] numbers = new long[numbersStr.length];
                for(int i = 0; i < numbersStr.length; i++){
                    numbers[i] = Long.parseLong(numbersStr[i]);
               }
                equations.add(new Equation(value, numbers));
            });
            return equations;
        }

    }
}
