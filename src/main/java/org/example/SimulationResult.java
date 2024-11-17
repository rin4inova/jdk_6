package org.example;

import lombok.Data;

@Data
public class SimulationResult {
    private int step;
    private boolean won;

    public SimulationResult(int step, boolean won) {
        this.step = step;
        this.won = won;
    }
}