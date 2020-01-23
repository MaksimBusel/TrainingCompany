package com.epam.training.connection;

import java.sql.Connection;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final int POOL_SIZE = 10;
    private Semaphore semaphore = new Semaphore(POOL_SIZE);

    private Queue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> connectionInUse;
    private static AtomicReference<ConnectionPool> instance = new AtomicReference<>();

    private static Lock instanceLock = new ReentrantLock();
    private Lock connectionLock = new ReentrantLock();

    private ConnectionPool() {
        availableConnections = new ArrayDeque<>();
        createPool();
    }

    public static ConnectionPool getInstance() {
        if (instance.get() == null) { //.
            instanceLock.lock();
            try {
                instance.compareAndSet(null, new ConnectionPool());
            } finally {
                instanceLock.unlock();
            }
        }
        return instance.get();
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection;
        try {
            semaphore.acquire();
            connectionLock.lock();
            connection = availableConnections.poll();
            connectionInUse.add(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread operation error, cause ", e); //change by myException
        } finally {
            connectionLock.unlock();
        }
        return connection;
    }

    public void returnConnection(ProxyConnection connection) {
        connectionLock.lock();
        try {
            if (connectionInUse.contains(connection)) {
                availableConnections.add(connection);
                connectionInUse.poll();
            }
        } finally {
            connectionLock.unlock();
            semaphore.release();
        }
    }

    private void createPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection connection = ConnectionFactory.create(instance.get());
            availableConnections.add(connection);
        }
    }
}
