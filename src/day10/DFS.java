package day10;

import java.util.ArrayList;
import java.util.List;

public class DFS {
    private static final int[] ROW_MOVE = {-1, 1, 0, 0};
    private static final int[] COL_MOVE = {0, 0, -1, 1};

    static int part1(int[][] trails, boolean[][] visited, int row, int col, int trailsCnt){
        visited[row][col] = true;
        int prevValue = trails[row][col];
        if(prevValue == 9){
            return ++trailsCnt;
        }
        List<Pair> nextSteps = new ArrayList<>();
        for(int i = 0; i < ROW_MOVE.length; i++){
            int nextRow = row + ROW_MOVE[i];
            int nextCol = col + COL_MOVE[i];
            if(isInBounds(nextRow, nextCol, trails) && trails[nextRow][nextCol] - 1 == prevValue){
                nextSteps.add(new Pair(nextRow, nextCol));
            }
        }

        for(Pair nextStep: nextSteps){
            if(visited[nextStep.row][nextStep.col]) continue;
            trailsCnt = part1(trails,visited, nextStep.row, nextStep.col, trailsCnt);
        }
        return trailsCnt;
    }

    static int part2(int[][] trails, int row, int col, int trailsCnt){

        int prevValue = trails[row][col];
        if(prevValue == 9){
            return ++trailsCnt;
        }
        List<Pair> nextSteps = new ArrayList<>();
        for(int i = 0; i < ROW_MOVE.length; i++){
            int nextRow = row + ROW_MOVE[i];
            int nextCol = col + COL_MOVE[i];
            if(isInBounds(nextRow, nextCol, trails) && trails[nextRow][nextCol] - 1 == prevValue){
                nextSteps.add(new Pair(nextRow, nextCol));
            }
        }

        for(Pair nextStep: nextSteps){
            trailsCnt = part2(trails, nextStep.row, nextStep.col, trailsCnt);
        }
        return trailsCnt;
    }

    private static boolean isInBounds(int row, int col, int[][] arr){
        return row >= 0 && row < arr.length && col >= 0 && col < arr[0].length;
    }

    private static class Pair{
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
