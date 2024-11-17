package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int NUM_SIMULATIONS = 1000;

    public static void main(String[] args) {
        List<SimulationResult> resultsWithoutSwitching = runSimulation(false);
        List<SimulationResult> resultsWithSwitching = runSimulation(true);

        System.out.println("=== Results Without Switching ===");
        displayResults(resultsWithoutSwitching);

        System.out.println("\n=== Results With Switching ===");
        displayResults(resultsWithSwitching);
    }

    private static List<SimulationResult> runSimulation(boolean shouldSwitch) {
        List<SimulationResult> results = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= NUM_SIMULATIONS; i++) {
            int prizeDoor = random.nextInt(3);   // Дверь с призом (0, 1, или 2)
            int chosenDoor = random.nextInt(3);  // Выбор участника

            int openDoor = findOpenDoor(prizeDoor, chosenDoor, random);

            // Участник меняет выбор, если это требуется
            if (shouldSwitch) {
                chosenDoor = switchDoor(chosenDoor, openDoor);
            }

            // Проверяем, выиграл ли участник
            boolean won = (chosenDoor == prizeDoor);
            results.add(new SimulationResult(i, won));
        }

        return results;
    }

    private static int findOpenDoor(int prizeDoor, int chosenDoor, Random random) {
        int openDoor;
        do {
            openDoor = random.nextInt(3);
        } while (openDoor == prizeDoor || openDoor == chosenDoor);
        return openDoor;
    }

    private static int switchDoor(int chosenDoor, int openDoor) {
        return 3 - chosenDoor - openDoor; // Меняем выбор
    }

    private static void displayResults(List<SimulationResult> results) {
        int wins = (int) results.stream().filter(SimulationResult::isWon).count();
        int losses = NUM_SIMULATIONS - wins;

        System.out.printf("Wins: %d, Losses: %d, Win Rate: %.2f%%%n",
                wins, losses, (wins / (double) NUM_SIMULATIONS) * 100);
    }
}