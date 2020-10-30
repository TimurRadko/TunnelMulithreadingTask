package com.epam.multithreading.tunnel.model;

import com.epam.multithreading.tunnel.exception.TunnelAccidentException;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Tunnel {
    private static Tunnel instance = null;
    private static final ReentrantLock TUNNEL_LOCKER = new ReentrantLock();
    private final Deque<Rail> rails = new LinkedList<>(
            Arrays.asList(
                    new Rail(),
                    new Rail()
            )
    );
    private final Semaphore SEMAPHORE = new Semaphore(rails.size(), true);
    private final ReentrantLock RAILS_LOCK = new ReentrantLock();


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

    public Rail getRail() throws TunnelAccidentException {
        Rail localRail;
        try {
            SEMAPHORE.acquire();
            RAILS_LOCK.lock();
            localRail = rails.poll();
        } catch (InterruptedException e) {
            throw new TunnelAccidentException(String.format("An accident in the tunnel: %s", e.getMessage()));
        } finally {
            RAILS_LOCK.unlock();
        }
        return localRail;
    }

    public void releaseRail(Rail rail) {
        RAILS_LOCK.lock();
        try {
            rails.offer(rail);
        } finally {
            RAILS_LOCK.unlock();
        }
        SEMAPHORE.release();
    }
}
