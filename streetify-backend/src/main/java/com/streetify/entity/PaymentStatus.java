package com.streetify.entity;

//IT25102225

public enum PaymentStatus {
    PENDING,    // Created but not yet processed
    SUCCESS,    // Payment completed successfully
    FAILED,     // Payment declined (retry logic applied)
    REFUNDED    // Admin approved refund



}
