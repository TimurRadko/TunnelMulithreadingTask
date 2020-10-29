package com.epam.multithreading.tunnel.model;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Tunnel {
    private static Tunnel instance = null;
    private static final ReentrantLock LOCKER = new ReentrantLock();
    private static final Semaphore trainsInTunnel = new Semaphore(2, true);

    private Tunnel() {
    }

    public static Tunnel getInstance() {
        Tunnel localInstance = instance;
        if (localInstance == null) {
            LOCKER.lock();
            try {
                localInstance = instance;
                if (localInstance == null) {
                    instance = new Tunnel();
                }
            } finally {
                LOCKER.unlock();
            }
        }
        return instance;
    }

    public Rail getRail() {
       return null;
    }
}
