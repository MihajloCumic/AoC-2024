package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<Equation> equations = intializeEquations("resources/input-day-7.txt");
        

    }

    private static List<Equation> intializeEquations(String path) throws IOException {
        List<Equation> equations = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            reader.lines().forEach(line -> {
                String[] values = line.split(":");

                if(values.length != 2) throw new RuntimeException("Invalid input");

                long value = Long.parseLong(values[0]);

                String[] numbersStr = values[1].trim().split(" ");
                int[] numbers = new int[numbersStr.length];
                for(int i = 0; i < numbersStr.length; i++){
                    numbers[i] = Integer.parseInt(numbersStr[i]);
               }
                equations.add(new Equation(value, numbers));
            });
            return equations;
        }

    }
}
