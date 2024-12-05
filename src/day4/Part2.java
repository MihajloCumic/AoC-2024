package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {

    public static void main(String[] args) throws FileNotFoundException {
        char[][] charMatrix = initializeMatrix();

        int count = 0;
        for(int i = 0; i < charMatrix.length; i++){
            for(int j = 0; j < charMatrix[0].length; j++){
                if(charMatrix[i][j] != 'A') continue;
                if(doesItMatch(charMatrix, i, j)){
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean doesItMatch(char[][] matrix, int aIndexI, int aIndexJ){
        if(aIndexI + 1 >= matrix.length || aIndexI - 1 < 0 || aIndexJ + 1 >= matrix[0].length || aIndexJ - 1 < 0) return false;

        boolean rightDiagonalMas = false;
        char charUpLeft = matrix[aIndexI - 1][aIndexJ - 1];
        char charDownRight = matrix[aIndexI + 1][aIndexJ + 1];
        if((charUpLeft == 'M' && charDownRight == 'S') || (charUpLeft == 'S' && charDownRight == 'M'))
            rightDiagonalMas = true;

        boolean leftDiagonalMas = false;
        char charUpRight = matrix[aIndexI - 1][aIndexJ + 1];
        char charDownLeft = matrix[aIndexI + 1][aIndexJ - 1];
        if((charUpRight == 'M' && charDownLeft == 'S') || (charUpRight == 'S' && charDownLeft == 'M'))
            leftDiagonalMas = true;

        return rightDiagonalMas && leftDiagonalMas;
    }
    private static char[][]  initializeMatrix() throws FileNotFoundException {
        String inputPath = "resources/input-day-4.txt";

        File inputFile  = new File(inputPath);
        Scanner scanner = new Scanner(inputFile);

        List<char[]> charMatrixList = new ArrayList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            charMatrixList.add(line.toCharArray());
        }
        return charMatrixList.toArray(new char[charMatrixList.size()][]);
    }
}
