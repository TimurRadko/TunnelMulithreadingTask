package com.epam.multithreading.tunnel.model.train;

import com.epam.multithreading.tunnel.model.Tunnel;

public class Train implements Runnable {
    private final int trainId;
    private TrainState trainState;
    private Tunnel tunnel;

    public Train(int trainId, TrainState trainState, Tunnel tunnel) {
        this.trainId = trainId;
        this.trainState = trainState;
        this.tunnel = tunnel;
    }

    public int getTrainId() {
        return trainId;
    }

    public TrainState getTrainState() {
        return trainState;
    }

    public void setTrainState(TrainState trainState) {
        this.trainState = trainState;
    }

    public Tunnel getTunnel() {
        return tunnel;
    }

    public void setTunnel(Tunnel tunnel) {
        this.tunnel = tunnel;
    }

    @Override
    public void run() {

    }
}
