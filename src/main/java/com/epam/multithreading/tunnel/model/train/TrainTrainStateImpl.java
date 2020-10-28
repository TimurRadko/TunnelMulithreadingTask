package com.epam.multithreading.tunnel.model.train;

public class TrainTrainStateImpl implements TrainState {
    private boolean isDriveThroughTunnel = false;

    @Override
    public void driveThroughTunnel() {
        isDriveThroughTunnel = true;
    }

    public boolean isDriveThroughTunnel() {
        return isDriveThroughTunnel;
    }
}
