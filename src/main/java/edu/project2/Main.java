package edu.project2;

import java.util.LinkedList;

public class Main {

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        MazeHandler mazeHandler = new MazeHandler();

        while (true) {
            mazeHandler.setWayToExit(new LinkedList<>());

            if (mazeHandler.getMaze() == null) {
                mazeHandler.getMenu().printMenuBeforeMazeGeneration();
            } else {
                mazeHandler.getMenu().printMenuAfterMazeGeneration();
            }

            mazeHandler.getMenu().askForInput();
            mazeHandler.setActionChoice(mazeHandler.getInputHandler().makeIntChoice());
            if (mazeHandler.getMaze() == null) {
                mazeHandler.getMenu().userMenuChoiceBeforeMazeGen(mazeHandler.getActionChoice());
            } else {
                mazeHandler.getMenu().userMenuChoiceAfterMazeGeneration(mazeHandler.getActionChoice());
            }

            if (mazeHandler.getMaze() == null && mazeHandler.getActionChoice() == 3) {
                mazeHandler.setActionChoice(-1);
            }

            if (mazeHandler.getActionChoice() > 0 && mazeHandler.getActionChoice() != 2) {
                mazeHandler.getMenu().askForInput();
                mazeHandler.setOptionChoice(mazeHandler.getInputHandler().makeIntChoice());
            }

            switch (mazeHandler.getActionChoice()) {
                case 1 -> mazeHandler.chooseMazeGeneration();

                case 2 -> mazeHandler.readFromFileChoice();

                case 3 -> mazeHandler.chooseMazeSearch();

                default -> {
                }
            }
        }
    }

    private Main() {
    }
}
