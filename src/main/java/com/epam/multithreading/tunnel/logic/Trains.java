package com.epam.multithreading.tunnel.logic;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect
public class Trains {
    private List<Train> trains;

    public Trains() {
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public Train getTrain(int index) {
        return trains.get(index);
    }

    public int getSize() {
        return trains.size();
    }
}
