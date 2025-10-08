-- users
MERGE INTO users u
USING (SELECT 'Alice' name, 'alice@example.com' email, 'senha_hash' password, 'N' tfa FROM dual) s
ON (u.email = s.email)
WHEN NOT MATCHED THEN
  INSERT (name, email, password, two_factor_auth)
  VALUES (s.name, s.email, s.password, s.tfa);

MERGE INTO users u
USING (SELECT 'Bob' name, 'bob@example.com' email, 'senha_hash' password, 'Y' tfa FROM dual) s
ON (u.email = s.email)
WHEN NOT MATCHED THEN
  INSERT (name, email, password, two_factor_auth)
  VALUES (s.name, s.email, s.password, s.tfa);

-- companies
MERGE INTO companies c
USING (SELECT 'Acme Invest' name, '00.000.000/0001-00' cnpj, 'Broker' type FROM dual) s
ON (c.cnpj = s.cnpj)
WHEN NOT MATCHED THEN
  INSERT (name, cnpj, type) VALUES (s.name, s.cnpj, s.type);

MERGE INTO companies c
USING (SELECT 'Nimbus Bank' name, '11.111.111/0001-11' cnpj, 'Bank' type FROM dual) s
ON (c.cnpj = s.cnpj)
WHEN NOT MATCHED THEN
  INSERT (name, cnpj, type) VALUES (s.name, s.cnpj, s.type);

-- cryptos
MERGE INTO cryptocurrencies cr
USING (SELECT 'Bitcoin' name, 'BTC' symbol, 340000.00 prevp, 350000.00 p FROM dual) s
ON (cr.symbol = s.symbol)
WHEN NOT MATCHED THEN
  INSERT (name, symbol, previous_price, price) VALUES (s.name, s.symbol, s.prevp, s.p);

MERGE INTO cryptocurrencies cr
USING (SELECT 'Ethereum' name, 'ETH' symbol, 17500.00 prevp, 18000.00 p FROM dual) s
ON (cr.symbol = s.symbol)
WHEN NOT MATCHED THEN
  INSERT (name, symbol, previous_price, price) VALUES (s.name, s.symbol, s.prevp, s.p);

COMMIT;

-- investments
MERGE INTO investments i
USING (
  SELECT
    (SELECT id FROM users WHERE email = 'alice@example.com')      AS user_id,
    (SELECT id FROM companies WHERE cnpj = '00.000.000/0001-00')  AS company_id,
    (SELECT id FROM cryptocurrencies WHERE symbol = 'BTC')        AS crypto_id,
    0.50000000 AS amount,
    300000.00  AS price
  FROM dual
) s
ON (i.user_id = s.user_id AND i.company_id = s.company_id AND i.crypto_id = s.crypto_id
    AND i.amount = s.amount AND i.price = s.price)
WHEN NOT MATCHED THEN
  INSERT (user_id, company_id, crypto_id, amount, price)
  VALUES (s.user_id, s.company_id, s.crypto_id, s.amount, s.price);

MERGE INTO investments i
USING (
  SELECT
    (SELECT id FROM users WHERE email = 'bob@example.com')         AS user_id,
    (SELECT id FROM companies WHERE cnpj = '11.111.111/0001-11')   AS company_id,
    (SELECT id FROM cryptocurrencies WHERE symbol = 'ETH')         AS crypto_id,
    10.00000000 AS amount,
    17000.00    AS price
  FROM dual
) s
ON (i.user_id = s.user_id AND i.company_id = s.company_id AND i.crypto_id = s.crypto_id
    AND i.amount = s.amount AND i.price = s.price)
WHEN NOT MATCHED THEN
  INSERT (user_id, company_id, crypto_id, amount, price)
  VALUES (s.user_id, s.company_id, s.crypto_id, s.amount, s.price);

COMMIT;

-- selects
SELECT * FROM users;
SELECT * FROM companies;
SELECT * FROM cryptocurrencies;
SELECT * FROM investments;

-- joins
SELECT
  i.id,
  u.name        AS user_name,
  c.name        AS company_name,
  cr.symbol     AS crypto,
  i.amount,
  i.price       AS buy_price,
  cr.price      AS current_price,
  (i.amount * cr.price) AS current_value,
  ROUND(((cr.price - i.price) / i.price) * 100, 2) AS pl_percent
FROM investments i
JOIN users u             ON u.id = i.user_id
JOIN companies c         ON c.id = i.company_id
JOIN cryptocurrencies cr ON cr.id = i.crypto_id
ORDER BY i.id;

-- update
UPDATE cryptocurrencies
   SET price = 19000.00, updated_at = SYSTIMESTAMP
 WHERE symbol = 'ETH';
COMMIT;

-- delete
DELETE FROM investments
 WHERE id = 1;  -- ou outro id de teste
COMMIT;
