package com.epam.multithreading.tunnel.logic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Trains {
    private final List<Train> trains;

    @JsonCreator
    public Trains(@JsonProperty("trains")List<Train> trains) {
        this.trains = trains;
    }

    public Train getTrain(int index) {
        return trains.get(index);
    }

    public int getSize() {
        return trains.size();
    }
}
