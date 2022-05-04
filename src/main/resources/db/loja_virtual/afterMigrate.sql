SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM LOJA_VIRTUAL_DB.CLIENTE;
DELETE FROM LOJA_VIRTUAL_DB.PRODUTO;


SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE LOJA_VIRTUAL_DB.CLIENTE AUTO_INCREMENT = 1;
ALTER TABLE LOJA_VIRTUAL_DB.PRODUTO AUTO_INCREMENT = 1;

INSERT INTO LOJA_VIRTUAL_DB.CLIENTE (ID_CLIENTE, NOME, EMAIL, ENDERECO) 
VALUES (1, 'GEORGE', 'SANTOS.S.GEORGE@GMAIL.COM', 'SÃO PAULO');

INSERT INTO LOJA_VIRTUAL_DB.PRODUTO (ID_PRODUTO, NOME, DESCRICAO, PRECO, QUANTIDADE, FOTO, SIZE) 
VALUES (1, 'CAMISA BRANCA', 'CAMISA BRANCA', '39.99', 6, NULL, 'M');
INSERT INTO LOJA_VIRTUAL_DB.PRODUTO (ID_PRODUTO, NOME, DESCRICAO, PRECO, QUANTIDADE, FOTO, SIZE) 
VALUES (2, 'CAMISA AZUL', 'CAMISA AZUL', '32.39', 6, NULL, 'M');
INSERT INTO LOJA_VIRTUAL_DB.PRODUTO (ID_PRODUTO, NOME, DESCRICAO, PRECO, QUANTIDADE, FOTO, SIZE) 
VALUES (3, 'CAMISA VERMELHA', 'CAMISA VERMELHA', '16.90', 6, NULL, 'M');
INSERT INTO LOJA_VIRTUAL_DB.PRODUTO (ID_PRODUTO, NOME, DESCRICAO, PRECO, QUANTIDADE, FOTO, SIZE) 
VALUES (4, 'CAMISA PRETA', 'CAMISA PRETA', '42.99', 6, NULL, 'M');
INSERT INTO LOJA_VIRTUAL_DB.PRODUTO (ID_PRODUTO, NOME, DESCRICAO, PRECO, QUANTIDADE, FOTO, SIZE) 
VALUES (5, 'CAMISA VERDE', 'CAMISA VERDE', '16.99', 6, NULL, 'M');