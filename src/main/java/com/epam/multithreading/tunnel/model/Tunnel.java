package com.epam.multithreading.tunnel.model;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Tunnel {
    private static Tunnel instance = null;
    private static final ReentrantLock LOCK = new ReentrantLock();

    //think about it
    private final Semaphore trainsInTunnel = new Semaphore(2);

    private Tunnel() {
    }

    public static Tunnel getInstance() {
        LOCK.lock();
        try {
            if (instance == null) {
                instance = new Tunnel();
            }
        } finally {
            LOCK.unlock();
        }
        return instance;
    }
}