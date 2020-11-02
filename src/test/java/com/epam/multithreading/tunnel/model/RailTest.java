package com.epam.multithreading.tunnel.model;

import com.epam.multithreading.tunnel.logic.Train;
import org.junit.Assert;
import org.junit.Test;

public class RailTest {
    private static final Train FIRST_TRAIN = new Train(1, false);
    private static final Train SECOND_TRAIN = new Train(1, true);

    @Test
    public void testDriveThroughTunnelShouldWorkWhenTrainsStateIsFalse() {
        Rail rail = new Rail();
        rail.driveThroughTunnel(FIRST_TRAIN);
        Assert.assertTrue(FIRST_TRAIN.isState());
    }
    @Test
    public void testDriveThroughTunnelShouldPassMethodWhenTrainsStateIsTrue() {
        Rail rail = new Rail();
        rail.driveThroughTunnel(SECOND_TRAIN);
        Assert.assertTrue(SECOND_TRAIN.isState());
    }
}
