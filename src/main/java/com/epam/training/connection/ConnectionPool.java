package main.java.com.epam.training.connection;

import main.java.com.epam.training.exception.ConnectionException;

import java.sql.Connection;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final String THREAD_ERROR= "Thread operation error, cause ";
    private static final int POOL_SIZE = 10;
    private Semaphore semaphore = new Semaphore(POOL_SIZE);

    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionInUse;
    private static AtomicReference<ConnectionPool> instance = new AtomicReference<>();

    private static final Lock INSTANCE_LOCK = new ReentrantLock();
    private final Lock connectionLock = new ReentrantLock();

    private ConnectionPool() {
        availableConnections = new ArrayDeque<>();
        connectionInUse = new ArrayDeque<>();
        createPool();
    }

    public static ConnectionPool getInstance() {
        if (instance.get() == null) {
            INSTANCE_LOCK.lock();
            try {
                instance.compareAndSet(null, new ConnectionPool());
            } finally {
                INSTANCE_LOCK.unlock();
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
            throw new ConnectionException(THREAD_ERROR, e);
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
        ConnectionFactory factory = new ConnectionFactory();
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = factory.create();
            ProxyConnection proxyConnection = new ProxyConnection(connection, this);
            availableConnections.add(proxyConnection);
        }
    }
}
