package com.streetify.service.payment;

import com.streetify.dao.DriverDAO;

/**  IT25102225
 * CardSettlementStrategy

    Passenger pays via card through payment gateway then Platform collects the full grossAmount after that
     Platform retains 15% commission.then drivers digital wallet is credited with 85%.
 */

public class CardSettlementStrategy implements PaymentSettlementStrategy {

    private final DriverDAO driverDAO;

    public CardSettlementStrategy(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }


    @Override
    public void settle(Long passengerId, Long driverId,
                       double grossAmount, double commission, double driverNet) {
        // Platform collected grossAmount via card gateway

        driverDAO.creditWallet(driverId, driverNet);

        System.out.println("[CardSettlementStrategy] Driver #" + driverId
                + " credited LKR " + driverNet + " (platform commission: LKR " + commission + ")");
    }

    @Override
    public String getMethodName() {
        return "CARD";
    }
}
