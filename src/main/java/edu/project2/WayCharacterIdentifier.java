package edu.project2;

public class WayCharacterIdentifier {
    private int[][] characterTable;
    private Point centerPoint;

    public WayCharacterIdentifier(Point point) {
        this.characterTable = new int[3][3];
        centerPoint = point;
    }

    public void setNeighbourPoint(Point point) {
        characterTable[point.getY() - centerPoint.getY() + 1][point.getX() - centerPoint.getX() + 1] = 1;
    }

    public char getCharacter() {
        char symbol = ' ';

        if (characterTable[0][1] == 1 && characterTable[2][1] == 1) {
            symbol = '│';
        } else if (characterTable[1][0] == 1 && characterTable[1][2] == 1) {
            symbol = '─';
        } else if (characterTable[0][1] == 1 && characterTable[1][2] == 1) {
            symbol = '└';
        } else if (characterTable[1][0] == 1 && characterTable[2][1] == 1) {
            symbol = '┐';
        } else if (characterTable[0][1] == 1 && characterTable[1][0] == 1) {
            symbol = '┘';
        }  else if (characterTable[1][2] == 1 && characterTable[2][1] == 1) {
            symbol = '┌';
        }

        return symbol;
    }

}
