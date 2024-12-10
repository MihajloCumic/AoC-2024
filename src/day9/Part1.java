package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {

    public static void main(String[] args) throws FileNotFoundException {
        char[] discMap = initializeInput();
        int index = 0;
        List<Integer> blocks = new ArrayList<>();
        for(int i = 0; i < discMap.length; i++){
            int repeatNum = Integer.parseInt(String.valueOf(discMap[i]));
            if(i % 2 == 0){

                for(int j = 0; j < repeatNum; j++){
                    blocks.add(index);
                }
                index++;
                continue;
            }
            for(int j = 0; j < repeatNum; j++){
                blocks.add(-1);
            }
        }

        mapDisc(blocks);

        long sum = 0;
        for(int i = 0; i < blocks.size(); i++){
            if(blocks.get(i) == -1) break;
            long mul = (long) i * blocks.get(i);
            sum += mul;
        }
        System.out.println(sum);
    }

    private static void mapDisc(List<Integer> blocks){
        int left = 0;
        int right = blocks.size() - 1;
        while(left < right){
            if(blocks.get(left) == -1 && blocks.get(right) != -1){
                int tmp = blocks.get(left);
                blocks.set(left, blocks.get(right));
                blocks.set(right, tmp);
                left++;
                right--;
                continue;
            }
            if(blocks.get(left) != -1){
                left++;
            }
            if(blocks.get(right) == -1){
                right--;
            }
        }
    }


    private static char[] initializeInput() throws FileNotFoundException {
        File inputFile = new File("resources/input-day-9.txt");
        Scanner scanner = new Scanner(inputFile);
        if(scanner.hasNextLine()){
            return scanner.nextLine().toCharArray();
        }
        throw new RuntimeException("Invalid input.");
    }
}
