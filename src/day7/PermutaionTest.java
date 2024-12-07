package day7;

import java.util.ArrayList;
import java.util.List;

public class PermutaionTest {
    public static void main(String[] args) {
        List<String> permutations = new ArrayList<>();
        generatePermutations("", 3, permutations);
        for(String permutation: permutations){
            System.out.println(permutation);
        }

    }

    private static void generatePermutations(String current, int spots, List<String> permutations){
        if(current.length() == spots){
            permutations.add(current);
            return;
        }

        generatePermutations(current + '+', spots, permutations);
        generatePermutations(current + '*', spots, permutations);
    }

}