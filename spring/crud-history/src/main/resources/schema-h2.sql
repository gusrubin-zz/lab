INSERT INTO customer_address (id, street, city, country) VALUES
  (1, 'Rua Joaquim Theodoro Teixeira de Souza, 253', 'Campinas', 'Brasil'),
  (2, 'Rua Eduino Antônio Banwart, 48', 'Campinas', 'Brasil');

INSERT INTO customer (id, name, address_id) VALUES
  (1, 'Gustavo', 1),
  (2, 'Josué', 2);
  
INSERT INTO product (id, name, description, price) VALUES
  (1, 'Computador', 'Processador i7, 32GB RAM, 512GB SSD', '3000.5'),
  (2, 'Computador', 'Processador i5, 8GB RAM, 256GB SSD', '1500.2');
