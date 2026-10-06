//IT25102225

package com.streetify.service.payment;

import com.streetify.dao.DriverDAO;


public class CashSettlementStrategy implements PaymentSettlementStrategy {

    private final DriverDAO driverDAO;

    public CashSettlementStrategy(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }


    @Override
    public void settle(Long passengerId, Long driverId,
                       double grossAmount, double commission, double driverNet) {


        driverDAO.addCommissionDebt(driverId, commission);

        System.out.println("[CashSettlementStrategy] Driver #" + driverId
                + " owes LKR " + commission + " commission to platform.");
    }

    @Override
    public String getMethodName() {
        return "CASH";
    }
}

/**
 * CASH payment logic:
 *   - The driver physically collected 100% of the fare from the passenger.
 *   - This debt is recorded via driverDAO.addCommissionDebt().
 */