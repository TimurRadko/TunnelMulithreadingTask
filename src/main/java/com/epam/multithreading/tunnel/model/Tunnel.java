package com.epam.multithreading.tunnel.model;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Tunnel {
    private static Tunnel instance = null;
    private static final ReentrantLock TUNNEL_LOCKER = new ReentrantLock();
    private final Semaphore SEMAPHORE = new Semaphore(2, true);

//    private Queue<Rail> availableRails = new LinkedList<>();
//    private Queue<Rail> usedRails = new LinkedList<>();
//    private ReentrantLock RAILS_LOCK = new ReentrantLock();
//    private static final Rail FIRST_RAIL = new Rail();
//    private static final Rail SECOND_RAIL = new Rail();

    protected Rail[] rails = {new Rail(), new Rail()};
    protected boolean[] used = new boolean[rails.length];
    private static final int MAX_AVAILABLE = 2;

    private Tunnel() {
//        availableRails.add(FIRST_RAIL);
//        availableRails.add(SECOND_RAIL);
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
        try {
            SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getNextAvailableRail();
    }

    public void putRail(Rail rail) {
        if (markAsUnused(rail))
            SEMAPHORE.release();
    }

    protected synchronized Rail getNextAvailableRail() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return rails[i];
            } else {
                markAsUnused(rails[i]);
            }
        }
        return null; // not reached
    }

    protected synchronized boolean markAsUnused(Rail rail) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (rail == rails[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }


//    public Rail getRail() {
//        try {
//            SEMAPHORE.acquire();
//            return getNextRail();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            SEMAPHORE.release();
//        }
//        return null;
//    }
//
//    private Rail getNextRail() {
//        if (availableRails.peek() != null) {
//            Rail rail = availableRails.poll();
//            usedRails.add(rail);
//            return rail;
//        } else {
//            putRail();
//        }
//        return null;
//    }
//
//    private void putRail() {
//        if (usedRails.peek() != null) {
//            Rail rail = usedRails.poll();
//            availableRails.add(rail);
//        }
//    }
}
