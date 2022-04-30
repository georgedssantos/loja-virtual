-- -----------------------------------------------------
-- Table lojaVirtualdb.CLIENTE
-- -----------------------------------------------------
DROP TABLE IF EXISTS lojaVirtualdb.CLIENTE;

CREATE TABLE IF NOT EXISTS lojaVirtualdb.CLIENTE (
  ID_CLIENTE INT NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(45) NOT NULL,
  EMAIL VARCHAR(45) NOT NULL,
  ENDERECO VARCHAR(60) NOT NULL,
  PRIMARY KEY (ID_CLIENTE))
ENGINE = InnoDB default charset=utf8;

-- -----------------------------------------------------
-- Table lojaVirtualdb.PEDIDO
-- -----------------------------------------------------
DROP TABLE IF EXISTS lojaVirtualdb.PEDIDO;

CREATE TABLE IF NOT EXISTS lojaVirtualdb.PEDIDO (
  ID_PEDIDO INT NOT NULL AUTO_INCREMENT,
  ID_CLIENTE INT NOT NULL,
  DATA DATETIME NOT NULL,
  STATUS VARCHAR(45) NOT NULL,
  VALOR_TOTAL DECIMAL(10,2) NULL,
  PRIMARY KEY (ID_PEDIDO),
  INDEX idCliente_idx (ID_CLIENTE ASC) VISIBLE,
  CONSTRAINT ID_CLIENTE
    FOREIGN KEY (ID_CLIENTE)
    REFERENCES lojaVirtualdb.CLIENTE (ID_CLIENTE)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB default charset=utf8;

-- -----------------------------------------------------
-- Table lojaVirtualdb.PRODUTO
-- -----------------------------------------------------
DROP TABLE IF EXISTS lojaVirtualdb.PRODUTO;

CREATE TABLE IF NOT EXISTS lojaVirtualdb.PRODUTO (
  ID_PRODUTO INT NOT NULL AUTO_INCREMENT,
  NOME VARCHAR(45) NOT NULL,
  DESCRICAO VARCHAR(45) NOT NULL,
  PRECO DECIMAL(10,2) NOT NULL,
  QUANTIDADE INT NOT NULL,
  FOTO BLOB NULL,
  SIZE VARCHAR(10) NULL,
  PRIMARY KEY (ID_PRODUTO))
ENGINE = InnoDB default charset=utf8;

-- -----------------------------------------------------
-- Table lojaVirtualdb.PEDIDO_ITEM
-- -----------------------------------------------------
DROP TABLE IF EXISTS lojaVirtualdb.PEDIDO_ITEM;

CREATE TABLE IF NOT EXISTS lojaVirtualdb.PEDIDO_ITEM (
  ID_PEDIDO_ITEM INT NOT NULL AUTO_INCREMENT,
  ID_PRODUTO INT NOT NULL,
  ID_PEDIDO INT NOT NULL,
  QUANTIDADE INT NOT NULL,
  PRIMARY KEY (ID_PEDIDO_ITEM),
  INDEX idProduto_idx (ID_PRODUTO ASC) VISIBLE,
  INDEX idPedido_idx (ID_PEDIDO ASC) VISIBLE,
  CONSTRAINT idProduto
    FOREIGN KEY (ID_PRODUTO)
    REFERENCES lojaVirtualdb.PRODUTO (ID_PRODUTO)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT idPedido
    FOREIGN KEY (ID_PEDIDO)
    REFERENCES lojaVirtualdb.PEDIDO (ID_PEDIDO)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB default charset=utf8;
