package day6;

import java.io.IOException;

public class Part2 {
    public static void main(String[] args) throws IOException {
        Lab lab = Lab.initializeLab("resources/input-day-6.txt");
        int cycleCount = 0;
        Visited[][] prevObstructed = new Visited[lab.charMatrix.length][lab.charMatrix[0].length];
        int[][] placesObstructions = new int[lab.charMatrix.length][lab.charMatrix[0].length];
        while(!lab.isNextStepOut()){
            System.out.println("Cycle count: " + cycleCount);
            int prevRow = lab.securityPosRow;
            int prevCol = lab.securityPosCol;
            char prevDirection = lab.securityDirection;

            lab.takeNextStep();

            int newObstructionRow = lab.securityPosRow;
            int newObstructionCol = lab.securityPosCol;



            lab.charMatrix[newObstructionRow][newObstructionCol] = Lab.OBSTRUCTION;
            if(newObstructionRow == 9 && newObstructionCol == 7){
                System.out.println("ee");
            }

            lab.securityPosRow = prevRow;
            lab.securityPosCol = prevCol;
            lab.securityDirection = prevDirection;

            Visited[][] visits = new Visited[lab.charMatrix.length][lab.charMatrix[0].length];
            visits[newObstructionRow][newObstructionCol] = new Visited(prevDirection);
            int cnt = 0;
            int visitedNewObstruction = 1;
            while(!lab.isNextStepOut()){
                char direction = lab.securityDirection;
                lab.takeNextStep();
                if(direction != lab.securityDirection){
                    cnt++;
                }
                if(prevRow == lab.securityPosRow
                        && prevCol == lab.securityPosCol){
                    if(visitedNewObstruction == 3) {
                        placesObstructions[newObstructionRow][newObstructionCol]++;
                        break;
                    }
                    visitedNewObstruction++;
                }
                if(cnt > 10000) break;
            }

            lab.charMatrix[newObstructionRow][newObstructionCol] = '.';
            prevObstructed[newObstructionRow][newObstructionCol] = new Visited(prevDirection);

            lab.securityPosRow = prevRow;
            lab.securityPosCol = prevCol;
            lab.securityDirection = prevDirection;
            lab.takeNextStep();

       }
//        System.out.println("Final cycleCount" + cycleCount);
        for(int i = 0; i < placesObstructions.length; i++){
            for(int j = 0; j < placesObstructions[0].length; j++){
                System.out.print(placesObstructions[i][j] + " ");
                if(placesObstructions[i][j] > 0){
                    cycleCount++;
                }

            }
            System.out.println();
        }
        System.out.println(cycleCount);
    }

    private static class Visited{
        private int visitedNum = 0;
        private char visitationDirection;

        public Visited(char visitationDirection) {
            this.visitationDirection = visitationDirection;
        }
    }
}
