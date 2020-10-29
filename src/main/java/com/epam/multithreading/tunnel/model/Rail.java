package com.epam.multithreading.tunnel.model;

import com.epam.multithreading.tunnel.exception.TunnelAccidentException;
import com.epam.multithreading.tunnel.logic.Train;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Rail {
    private static final ReentrantLock RAILS_LOCKER = new ReentrantLock();

    public void driveThroughTunnel(Train train) throws TunnelAccidentException {

        if (!train.isState()) {
            RAILS_LOCKER.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.printf("Train with id%s entered the tunnel. Status: %s%n", train.getId(), train.isState());
                TimeUnit.SECONDS.sleep(1);
                train.setState(true);
                System.out.printf("Train with id%s rode out the tunnel. Status: %s%n", train.getId(), train.isState());
            } catch (InterruptedException e) {
                throw new TunnelAccidentException(e.getMessage());
            } finally {
                RAILS_LOCKER.unlock();
            }
        }
    }
}
