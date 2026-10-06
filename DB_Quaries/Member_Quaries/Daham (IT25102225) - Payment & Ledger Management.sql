SELECT * FROM payments;

SELECT trip_id, payment_method, gross_amount, driver_net, platform_commission FROM payments WHERE status = 'SUCCESS';

-- 2. Calculate the total platform commission fees earned by Streetify
SELECT SUM(platform_commission) as TotalPlatformProfit FROM payments WHERE status = 'SUCCESS';

-- 3. Analyze transaction volume by Payment Method (Cash, Wallet, Card)
SELECT payment_method, COUNT(*) as TotalTransactions, SUM(gross_amount) as TotalVolume FROM payments WHERE status = 'SUCCESS' GROUP BY payment_method;

-- 4. Check the commission debt owed by drivers to Streetify (Cash trips)
SELECT first_name, commission_debt FROM users WHERE dtype = 'DRIVER' AND commission_debt > 0;

-- 5. View the top 5 most expensive trips ever successfully paid for on the platform
SELECT TOP 5 trip_id, payment_method, gross_amount, created_at FROM payments WHERE status = 'SUCCESS' ORDER BY gross_amount DESC;


