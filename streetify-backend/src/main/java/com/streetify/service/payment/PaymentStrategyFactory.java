package com.streetify.service.payment;

import com.streetify.dao.DriverDAO;
import com.streetify.dao.UserDAO;


public class PaymentStrategyFactory {


    private PaymentStrategyFactory() {}

    public static PaymentSettlementStrategy resolve(String paymentMethod,
                                                    UserDAO userDAO,
                                                    DriverDAO driverDAO) {
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("Payment method must not be null or empty.");
        }

        return switch (paymentMethod.toUpperCase().trim()) {
            case "CASH"   -> new CashSettlementStrategy(driverDAO);
            case "WALLET" -> new WalletSettlementStrategy(userDAO, driverDAO);
            case "CARD"   -> new CardSettlementStrategy(driverDAO);
            default -> throw new IllegalArgumentException(
                    "Unsupported payment method: '" + paymentMethod + "'. " +
                            "Supported methods: CASH, WALLET, CARD");
        };
    }
}

/**
-----------IT25102225---------------
 * This class resolves which PaymentSettlementStrategy to use
 * based on the payment method string from the booking request.
 *
 * The Context (PaymentService) calls this factory to get the correct
 * strategy, then delegates to it — it never contains payment-method
 * logic itself.
 *
 * Usage in PaymentService:
 *   PaymentSettlementStrategy strategy =
 *       PaymentStrategyFactory.resolve(method, userDAO, driverDAO);
 *   strategy.settle(passengerId, driverId, grossAmount, commission, driverNet);
 */
