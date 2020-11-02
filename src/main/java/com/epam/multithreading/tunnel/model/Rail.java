package com.epam.multithreading.tunnel.model;

import com.epam.multithreading.tunnel.logic.Train;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Rail {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String BEFORE_TUNNEL = "Train with id%s entered the tunnel. Did it pass through the tunnel? %s";
    private static final String AFTER_TUNNEL = "Train with id%s rode out the tunnel. Did it pass through the tunnel? %s";

    public void driveThroughTunnel(Train train) {
        if (!train.isState()) {
            LOGGER.info(String.format(BEFORE_TUNNEL, train.getId(), train.isState()));
            train.setState(true);
            LOGGER.info(String.format(AFTER_TUNNEL, train.getId(), train.isState()));
        }
    }
}
