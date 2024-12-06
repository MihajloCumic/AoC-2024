package day6;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Part2 {
    public static void main(String[] args) throws IOException {
        Lab lab = Lab.initializeLab();
        while(!lab.isNextStepOut()){
            int currRow = lab.securityPosRow;
            int currCol = lab.securityPosCol;
            lab.takeNextStep();

       }
    }
}
