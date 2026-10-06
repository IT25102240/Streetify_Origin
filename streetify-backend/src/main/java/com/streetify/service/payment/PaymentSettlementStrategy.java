//IT25102225

package com.streetify.service.payment;

public interface PaymentSettlementStrategy {


    void settle(Long passengerId,Long driverId,double grossAmount, double commission, double driverNet);

    String getMethodName();


}
