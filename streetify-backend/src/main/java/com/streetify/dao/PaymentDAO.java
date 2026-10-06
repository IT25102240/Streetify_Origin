package com.streetify.dao;

import com.streetify.entity.Payment;
import com.streetify.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**        Data Access Layer for Payment entities
 * Explicit query methods for payment processing,retry logic,and financial reporting.
 */

@Repository
public interface PaymentDAO extends JpaRepository<Payment, Long> {


    Optional<Payment> findByTripId(Long tripId);

    //Find all payments made by a specific passenger.

    List<Payment> findByPassengerIdOrderByCreatedAtDesc(Long passengerId);


    List<Payment> findByDriverIdOrderByCreatedAtDesc(Long driverId);

    List<Payment> findByStatus(PaymentStatus status);

// Find failed payments eligible for retry (retryCount < 3).

    @Query("SELECT p FROM Payment p WHERE p.status = 'FAILED' AND p.retryCount < 3")
    List<Payment> findRetryableFailedPayments();

    @Query("""
           SELECT COALESCE(SUM(p.driverNet), 0.0)
           FROM Payment p
           WHERE p.driver.id = :driverId
             AND p.status = 'SUCCESS'
             AND p.createdAt >= :since
           """)
    Double sumDriverEarningsSince(@Param("driverId") Long driverId, @Param("since") LocalDateTime since);

//update payment status and processed timestamp.

    @Modifying
    @Transactional
    @Query("""
           UPDATE Payment p
           SET p.status = :status,
               p.processedAt = :processedAt
           WHERE p.id = :paymentId
           """)
    void updatePaymentStatus(
            @Param("paymentId") Long paymentId,
            @Param("status") PaymentStatus status,
            @Param("processedAt") LocalDateTime processedAt
    );

  //increment the retry count for a failed payment.

    @Modifying
    @Transactional
    @Query("UPDATE Payment p SET p.retryCount = p.retryCount + 1, p.failureReason = :reason WHERE p.id = :paymentId")
    void incrementRetryCount(@Param("paymentId") Long paymentId, @Param("reason") String reason);
}
