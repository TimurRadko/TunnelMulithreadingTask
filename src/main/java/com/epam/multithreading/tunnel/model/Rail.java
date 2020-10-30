package com.epam.multithreading.tunnel.model;

import com.epam.multithreading.tunnel.logic.Train;

public class Rail {

    public void driveThroughTunnel(Train train) {
        if (!train.isState()) {
            System.out.printf("Train with id%s entered the tunnel. Status: %s%n", train.getId(), train.isState());
            train.setState(true);
            System.out.printf("Train with id%s rode out the tunnel. Status: %s%n", train.getId(), train.isState());
        }
    }
}
