package com.epam.multithreading.tunnel.logic;

import com.epam.multithreading.tunnel.model.Rail;
import com.epam.multithreading.tunnel.model.Tunnel;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@JsonAutoDetect
public class Train implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger();
    private int id;
    private boolean state;
    private final Tunnel tunnel;

    public Train() {
        this.tunnel = Tunnel.getInstance();
    }

    public Train(int id, boolean state) {
        this.id = id;
        this.state = state;
        this.tunnel = Tunnel.getInstance();
    }

    public int getId() {
        return id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Tunnel getTunnel() {
        return tunnel;
    }

    @Override
    public void run() {
        Rail rail = tunnel.getRail();
        rail.driveThroughTunnel(this);
    }

    @Override
    public String toString() {
        return String.format("%nTrain %s drive through the tunnel: %s", getId(), isState());
    }
}
