package day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lab {
    static final char UP = '^';
    static final char DOWN = 'V';
    static final char LEFT = '<';
    static final char RIGHT = '>';
    static final char OBSTRUCTION = '#';
    final char[][] charMatrix;
    int securityPosRow;
    int securityPosCol;
    char securityDirection;

    private Lab(char[][] charMatrix, int securityPosRow, int securityPosCol, char securityDirection) {
        this.charMatrix = charMatrix;
        this.securityPosRow = securityPosRow;
        this.securityPosCol = securityPosCol;
        this.securityDirection = securityDirection;
    }

    public static Lab initializeLab() throws IOException {
        File inputFile = new File("resources/input-day-6.txt");

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        if (lines.isEmpty()) {
            return new Lab(new char[0][0], -1, -1, '#');
        }
        int numOfRows = lines.size();
        int numOfColumns = lines.get(0).length();
        char[][] charMatrix = new char[numOfRows][numOfColumns];
        char securitiesStartingDirection = '#';
        int securityStartingRow = -1;
        int securityStartingCol = -1;
        for (int i = 0; i < numOfRows; i++) {
            String line = lines.get(i);
            charMatrix[i] = line.toCharArray();

            if (securitiesStartingDirection != '#' && securityStartingRow > -1 && securityStartingCol > -1) continue;

            int securityDirectionUp = line.indexOf(Lab.UP);
            int securityDirectionDown = line.indexOf(Lab.DOWN);
            int securityDirectionLeft = line.indexOf(Lab.LEFT);
            int securityDirectionRight = line.indexOf(Lab.RIGHT);
            if (securityDirectionUp > -1) {
                securitiesStartingDirection = Lab.UP;
                securityStartingRow = i;
                securityStartingCol = securityDirectionUp;
                continue;
            }
            if (securityDirectionDown > -1) {
                securitiesStartingDirection = Lab.DOWN;
                securityStartingRow = i;
                securityStartingCol = securityDirectionDown;
                continue;
            }
            if (securityDirectionLeft > -1) {
                securitiesStartingDirection = Lab.LEFT;
                securityStartingRow = i;
                securityStartingCol = securityDirectionLeft;
                continue;
            }
            if (securityDirectionRight > -1) {
                securitiesStartingDirection = Lab.RIGHT;
                securityStartingRow = i;
                securityStartingCol = securityDirectionRight;
            }

        }

        return new Lab(charMatrix, securityStartingRow, securityStartingCol, securitiesStartingDirection);
    }

    void takeNextStep() {
        if (securityDirection == UP) {
            stepUp();
            return;
        }
        if (securityDirection == DOWN) {
            stepDown();
            return;
        }
        if (securityDirection == LEFT) {
            stepLeft();
            return;
        }
        if (securityDirection == RIGHT) {
            stepRight();
            return;
        }
        throw new RuntimeException("Invalid direction");
    }

    void stepUp() {
        if (isUpStepOut()) return;
        if (charMatrix[securityPosRow - 1][securityPosCol] == OBSTRUCTION) {
            securityPosCol += 1;
            securityDirection = RIGHT;
            return;
        }
        securityPosRow -= 1;
    }

    void stepDown() {
        if (isDownStepOut()) return;
        if (charMatrix[securityPosRow + 1][securityPosCol] == OBSTRUCTION) {
            securityPosCol -= 1;
            securityDirection = LEFT;
            return;
        }
        securityPosRow += 1;
    }

    void stepLeft() {
        if (isLeftStepOut()) return;
        if (charMatrix[securityPosRow][securityPosCol - 1] == OBSTRUCTION) {
            securityPosRow -= 1;
            securityDirection = UP;
            return;
        }
        securityPosCol -= 1;
    }

    void stepRight() {
        if (isRightStepOut()) return;
        if (charMatrix[securityPosRow][securityPosCol + 1] == OBSTRUCTION) {
            securityPosRow += 1;
            securityDirection = DOWN;
            return;
        }
        securityPosCol += 1;
    }

    boolean isNextStepOut() {
        if (securityDirection == UP) return isUpStepOut();
        if (securityDirection == DOWN) return isDownStepOut();
        if (securityDirection == LEFT) return isLeftStepOut();
        if (securityDirection == RIGHT) return isRightStepOut();
        throw new RuntimeException("Invalid direction");
    }

    boolean isUpStepOut() {
        if (securityPosRow == 0) return true;
        return charMatrix[securityPosRow - 1][securityPosCol] == OBSTRUCTION
                && securityPosCol == charMatrix[0].length - 1;
    }

    boolean isDownStepOut() {
        if (securityPosRow == charMatrix.length - 1) return true;
        return charMatrix[securityPosRow + 1][securityPosCol] == OBSTRUCTION
                && securityPosCol == 0;
    }

    boolean isLeftStepOut() {
        if (securityPosCol == 0) return true;
        return charMatrix[securityPosRow][securityPosCol - 1] == OBSTRUCTION
                && securityPosRow == 0;
    }

    boolean isRightStepOut() {
        if (securityPosCol == charMatrix[0].length - 1) return true;
        return charMatrix[securityPosRow][securityPosCol + 1] == OBSTRUCTION
                && securityPosRow == charMatrix.length - 1;
    }
}
