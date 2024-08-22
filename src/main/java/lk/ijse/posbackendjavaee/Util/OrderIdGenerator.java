package lk.ijse.posbackendjavaee.Util;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderIdGenerator {
    private static final AtomicInteger orderIdCounter = new AtomicInteger(1); // Start at 1

    public String generateOrderId() {
        // Generate a new numeric orderId
        int numericOrderId = orderIdCounter.getAndIncrement();

        // Format it as OR-01, OR-02, etc.
        return String.format("OR-%02d", numericOrderId);
    }
}