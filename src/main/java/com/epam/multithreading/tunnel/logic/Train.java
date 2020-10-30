package com.epam.multithreading.tunnel.logic;

import com.epam.multithreading.tunnel.exception.TunnelAccidentException;
import com.epam.multithreading.tunnel.model.Rail;
import com.epam.multithreading.tunnel.model.Tunnel;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o)  {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Train train = (Train) o;

        if (id != train.id) {
            return false;
        }
        if (state != train.state) {
            return false;
        }
        return Objects.equals(tunnel, train.tunnel);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (state ? 1 : 0);
        result = 31 * result + (tunnel != null ? tunnel.hashCode() : 0);
        return result;
    }

    @Override
    public void run() {
        Rail rail;
        try {
            rail = tunnel.getRail();
            rail.driveThroughTunnel(this);
        } catch (TunnelAccidentException e) {
            LOGGER.error(String.format("An accident in the tunnel: %s", e.getMessage()));
        }
    }

    @Override
    public String toString() {
        return String.format("%nTrain %s drive through the tunnel: %s", getId(), isState());
    }
}
