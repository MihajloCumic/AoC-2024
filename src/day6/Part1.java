package day6;

import java.io.*;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws IOException {
        Lab lab = Lab.initializeLab("resources/input-day-6.txt");

        Map<Integer, Set<Integer>> visitedRowCols = new HashMap<>();
        visitedRowCols.put(lab.securityPosRow,new HashSet<>(Set.of(lab.securityPosCol)));
        while(!lab.isNextStepOut()){
            lab.takeNextStep();
            if(visitedRowCols.containsKey(lab.securityPosRow)){
                visitedRowCols.get(lab.securityPosRow).add(lab.securityPosCol);
                continue;
            }
            visitedRowCols.put(lab.securityPosRow, new HashSet<>(Set.of(lab.securityPosCol)));
        }
        int visitedFields = 0;
        for(Map.Entry<Integer, Set<Integer>> entry: visitedRowCols.entrySet()){
            visitedFields += entry.getValue().size();
        }
        System.out.println(visitedFields);
    }
}
