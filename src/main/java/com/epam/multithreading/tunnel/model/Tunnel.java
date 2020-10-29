package com.epam.multithreading.tunnel.model;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Tunnel {
    private static Tunnel instance = null;
    private static final ReentrantLock TUNNEL_LOCKER = new ReentrantLock();

    private final Semaphore semaphore = new Semaphore(2);

    private Tunnel() {
    }

    public static Tunnel getInstance() {
        Tunnel localInstance = instance;
        if (localInstance == null) {
            TUNNEL_LOCKER.lock();
            try {
                localInstance = instance;
                if (localInstance == null) {
                    instance = new Tunnel();
                }
            } finally {
                TUNNEL_LOCKER.unlock();
            }
        }
        return instance;
    }

    public Rail getRail() {
//        Rail rail = null;
//        while (semaphore.availablePermits() > 0) {
//            try {
//                semaphore.acquire();
//                rail = new Rail();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                semaphore.release();
//            }
//        }
        return new Rail();
    }

}
