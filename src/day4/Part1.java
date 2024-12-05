package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        char[][] charMatrix = initializeMatrix();

        int count = 0;
        for(int i = 0; i < charMatrix.length; i++){
            for(int j = 0; j < charMatrix[0].length; j++){
                if(charMatrix[i][j] != 'X') continue;
                count += horizontalWordCount(charMatrix[i], j);
                count += verticalWordCount(charMatrix, i, j);
                count += rightDiagonalWordCount(charMatrix, i, j);
                count += leftDiagonalWordCount(charMatrix, i, j);
            }
        }
        System.out.println(count);
    }
    private static int horizontalWordCount(char[] row, int xIndex){
        int count = 0;
        //rightCheck
        if(xIndex + 3 < row.length){
            String word = new String(row, xIndex, 4);
            if(word.equals("XMAS")){
                count++;
            }
        }
        //leftCheck
        if(xIndex - 3 >= 0){
            String word = new String(row, xIndex - 3, 4);
            if(word.equals("SAMX")){
                count++;
            }
        }
        return count;
    }

    private static int verticalWordCount(char[][] matrix , int xIndexI, int xIndexJ){
        int count = 0;
        //downCheck
        if(xIndexI + 3 < matrix.length){
            char[] column = new char[4];
            for(int i = 0; i < 4; i++){
                column[i] = matrix[xIndexI + i][xIndexJ];
            }
            String word = new String(column);
            if(word.equals("XMAS")){
                count++;
            }
        }
        //upCheck
        if(xIndexI - 3 >= 0){
            char[] column = new char[4];
            for(int i = 0; i < 4; i++){
                column[i] = matrix[xIndexI - i][xIndexJ];
            }
            String word = new String(column);
            if(word.equals("XMAS")){
                count++;
            }
        }
        return count;
    }

    private static int rightDiagonalWordCount(char[][] matrix, int xIndexI, int xIndexJ){
        int count = 0;
        //rightDown
        if(xIndexI + 3 < matrix.length && xIndexJ + 3 < matrix[0].length){
            char[] column = new char[4];
            for(int i = 0; i < 4; i++){
                column[i] = matrix[xIndexI + i][xIndexJ + i];
            }
            String word = new String(column);
            if(word.equals("XMAS")){
                count++;
            }
        }

        if(xIndexI - 3 >= 0 && xIndexJ - 3 >= 0){
            char[] column = new char[4];
            for(int i = 0; i < 4; i++){
                column[i] = matrix[xIndexI - i][xIndexJ - i];
            }
            String word = new String(column);
            if(word.equals("XMAS")){
                count++;
            }
        }

        return count;
    }
    private static int leftDiagonalWordCount(char[][] matrix, int xIndexI, int xIndexJ){
        int count = 0;
        //rightDown
        if(xIndexI + 3 < matrix.length && xIndexJ - 3 >= 0){
            char[] column = new char[4];
            for(int i = 0; i < 4; i++){
                column[i] = matrix[xIndexI + i][xIndexJ - i];
            }
            String word = new String(column);
            if(word.equals("XMAS")){
                count++;
            }
        }

        if(xIndexI - 3 >= 0 && xIndexJ + 3 < matrix[0].length){
            char[] column = new char[4];
            for(int i = 0; i < 4; i++){
                column[i] = matrix[xIndexI - i][xIndexJ + i];
            }
            String word = new String(column);
            System.out.println(word);
            if(word.equals("XMAS")){
                count++;
            }
        }

        return count;
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
