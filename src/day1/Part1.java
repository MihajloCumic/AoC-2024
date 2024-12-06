package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("resources/input-day-1");
        Scanner scanner = new Scanner(inputFile);
        List<Integer> leftColumn = new ArrayList<>();
        List<Integer> rightColumn = new ArrayList<>();
        while(scanner.hasNextLine()){
            String[] line = scanner.nextLine().split("\t");
            System.out.println(line[0] + " . " + line[1]);

        }
    }
}
