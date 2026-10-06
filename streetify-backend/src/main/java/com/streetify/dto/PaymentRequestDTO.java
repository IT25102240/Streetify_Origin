package com.streetify.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/** IT25102225
 * PaymentRequestDTO — Request body for POST /api/payments/process
 */
public class PaymentRequestDTO {

    @NotNull(message = "Trip ID is required")
    private Long tripId;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;


    private String cardLastFour;
    private String cardType;


    public PaymentRequestDTO() {}

    public Long getTripId() { return tripId; }
    public void setTripId(Long tripId) { this.tripId = tripId; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getCardLastFour() { return cardLastFour; }
    public void setCardLastFour(String cardLastFour) { this.cardLastFour = cardLastFour; }

    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }


}
