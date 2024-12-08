package day8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Part1 {
    public static void main(String[] args) throws IOException {
        City city  = initializeAntennas();
        Map<Character, List<Antenna>> antennasMap = city.antennas;
        int[][] antinodes = new int[city.rowSize][city.colSize];
        for(Map.Entry<Character, List<Antenna>> entry: antennasMap.entrySet()){
            List<Antenna> antennas = entry.getValue();
            for(int i = 0; i < antennas.size(); i++){
                Antenna currAntenna = antennas.get(i);
                for(int j = i + 1; j < antennas.size(); j++){
                    Antenna nextAntenna = antennas.get(j);
                    int rowDiff = Math.abs(currAntenna.row - nextAntenna.row);
                    int colDiff = Math.abs(currAntenna.col - nextAntenna.col);

                    int upperRow = Math.min(currAntenna.row, nextAntenna.row);
                    int downRow = Math.max(currAntenna.row, nextAntenna.row);
                    int leftCol = Math.min(currAntenna.col, nextAntenna.col);
                    int rightCol = Math.max(currAntenna.col, nextAntenna.col);

                    int newRow1 = -1;
                    int newRow2 = -1;
                    int newCol1 = -1;
                    int newCol2 = -1;

                    if(rowDiff == 0){
                        newRow1 = currAntenna.row;
                        newCol1 = leftCol - colDiff;
                        newCol2 = rightCol + colDiff;
                        if(isInBoundsRow(city, newRow1)) {
                            if(isInBoundsCol(city, newCol1)){
                                antinodes[newRow1][newCol1] = 1;
                            }
                            if(isInBoundsCol(city, newCol2)){
                                antinodes[newRow1][newCol2] = 1;
                            }

                        }
                        continue;
                    }


                    if(colDiff == 0){
                        newCol1 = currAntenna.col;
                        newRow1 = upperRow - rowDiff;
                        newRow2 = downRow + rowDiff;
                        if(isInBoundsCol(city, newCol1)){
                            if(isInBoundsRow(city, newRow1)){
                                antinodes[newRow1][newCol1] = 1;
                            }
                            if(isInBoundsRow(city, newRow2)){
                                antinodes[newRow2][newCol1] = 1;
                            }
                        }
                        continue;
                    }
                    //right diagonal
                    if((currAntenna.row < nextAntenna.row && currAntenna.col < nextAntenna.col)
                                || (currAntenna.row > nextAntenna.row && currAntenna.col > nextAntenna.col)){
                        //down
                        newRow1 = downRow + rowDiff;
                        newCol1 = rightCol + colDiff;
                        if(isInBounds(city, newRow1, newCol1)){
                            antinodes[newRow1][newCol1] = 1;
                        }
                        //up
                        newRow2 = upperRow - rowDiff;
                        newCol2 = leftCol - colDiff;
                        if(isInBounds(city, newRow2, newCol2)){
                            antinodes[newRow2][newCol2] = 1;
                        }
                        continue;
                    }
                    //left diagonal
                    if((currAntenna.row < nextAntenna.row && currAntenna.col > nextAntenna.col)
                                || currAntenna.row > nextAntenna.row && currAntenna.col < nextAntenna.col){
                        //down
                        newRow1 = downRow + rowDiff;
                        newCol1 = leftCol - colDiff;
                        if(isInBounds(city, newRow1, newCol1)){
                            antinodes[newRow1][newCol1] = 1;
                        }
                        //up
                        newRow2 = upperRow - rowDiff;
                        newCol2 = rightCol + colDiff;
                        if(isInBounds(city, newRow2, newCol2)){
                            antinodes[newRow2][newCol2] = 1;
                        }
                    }
                }
            }

        }
        int cnt = 0;
        for(int[] row: antinodes){
            for(int col: row){
                if(col == 1) cnt++;
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println(cnt);
    }

    private static boolean isInBounds(City city, int row, int col){
        return  isInBoundsRow(city, row) && isInBoundsCol(city, col);
    }

    private static boolean isInBoundsRow(City city, int row){
        return row >= 0 && row < city.rowSize;
    }

    private static boolean isInBoundsCol(City city, int col){
        return col >= 0 && col < city.colSize;
    }

    private static City initializeAntennas() throws IOException {
        File inputFile = new File("resources/input-day-8.txt");
        City city = new City();
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
            reader.lines().forEach(line -> {
              city.colSize = line.length();
              char[] chars = line.toCharArray();
              for(int i = 0; i < chars.length; i++){
                  char c = chars[i];
                  if(Character.isLetter(c) || Character.isDigit(c)){
                      if(city.antennas.containsKey(c)){
                          city.antennas.get(c).add(new Antenna(c, city.rowSize, i));
                          continue;
                      }
                      city.antennas.put(c, new ArrayList<>(List.of(new Antenna(c, city.rowSize, i))));
                  }
              }
              city.rowSize++;
            });
        }
        System.out.println(city.rowSize);
        return city;
    }
}
