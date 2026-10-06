package com.streetify.service.payment;

import com.streetify.dao.DriverDAO;
import com.streetify.dao.UserDAO;


public class WalletSettlementStrategy implements PaymentSettlementStrategy {

    private final UserDAO userDAO;
    private final DriverDAO driverDAO;

    public WalletSettlementStrategy(UserDAO userDAO, DriverDAO driverDAO) {
        this.userDAO = userDAO;
        this.driverDAO = driverDAO;
    }


    @Override
    public void settle(Long passengerId, Long driverId,
                       double grossAmount, double commission, double driverNet) {
        //Debit passenger's wallet
        userDAO.debitWallet(passengerId, grossAmount);

        // Credit driver's wallet with net earnings (after commission)
        driverDAO.creditWallet(driverId, driverNet);

        System.out.println("[WalletSettlementStrategy] Passenger #" + passengerId
                + " debited LKR " + grossAmount + " | Driver #" + driverId
                + " credited LKR " + driverNet);
    }

    @Override
    public String getMethodName() {
        return "WALLET";
    }
}

/**  IT25102225
 * WALLET payment settlement logic:
 *   - Passenger's Streetify digital wallet is debited the full fare.
 *   - Platform retains 15% commission.
 *   - Driver's digital wallet is given 85% net earnings.
 */