package com.epam.multithreading.tunnel.model;

import com.epam.multithreading.tunnel.exception.TunnelAccidentException;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Tunnel {
    private static Tunnel instance = null;
    private static final ReentrantLock TUNNEL_LOCKER = new ReentrantLock();
    private static final int MAX_COUNT_ALLOWED_RAILS = 2;
    private final Deque<Rail> rails = new LinkedList<>();
    private final Semaphore semaphore = new Semaphore(MAX_COUNT_ALLOWED_RAILS, true);
    private final ReentrantLock reentrantLock = new ReentrantLock();


    private Tunnel() {
        for (int i = 0; i < MAX_COUNT_ALLOWED_RAILS; i++) {
            Rail rail = new Rail();
            rails.add(rail);
        }
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

    public Rail getRail() throws TunnelAccidentException {
        Rail localRail;
        try {
            semaphore.acquire();
            reentrantLock.lock();
            localRail = rails.poll();
        } catch (InterruptedException e) {
            throw new TunnelAccidentException(String.format("An accident in the tunnel: %s", e.getMessage()));
        } finally {
            reentrantLock.unlock();
        }
        return localRail;
    }

    public void releaseRail(Rail rail) {
        reentrantLock.lock();
        try {
            rails.offer(rail);
        } finally {
            reentrantLock.unlock();
        }
        semaphore.release();
    }
}
