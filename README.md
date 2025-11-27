# Menu_java
Menu aplicando Polimorfismo, Abstração, Herança e Encapsulamento em Java

## Instruções para funcionar:
- Obs: Ignore as pastas teste/java e target para explicação
- MySQL instalado ( versão 5.7+ )

- Adicione o comando a seguir nele ( criar banco de dados)
 -- Criar banco de dados
CREATE DATABASE IF NOT EXISTS restaurante_db;
USE restaurante_db;

-- Tabela itens_cardapio (pratos e bebidas)
CREATE TABLE itens_cardapio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(20) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL
);

-- Tabela pratos (dados específicos de Prato)
CREATE TABLE pratos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    item_id INT NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    tamanho VARCHAR(2) NOT NULL,
    tempo_preparo INT NOT NULL,
    FOREIGN KEY (item_id) REFERENCES itens_cardapio(id) ON DELETE CASCADE
);

-- Tabela bebidas (dados específicos de Bebida)
CREATE TABLE bebidas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    item_id INT NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    volume INT NOT NULL,
    gelada BOOLEAN NOT NULL,
    FOREIGN KEY (item_id) REFERENCES itens_cardapio(id) ON DELETE CASCADE
);

-- Tabela mesas
CREATE TABLE mesas (
    numero INT PRIMARY KEY,
    capacidade INT NOT NULL,
    localizacao VARCHAR(50)
);

-- Tabela reservas
CREATE TABLE reservas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    data_hora DATETIME NOT NULL,
    nome_cliente VARCHAR(100) NOT NULL,
    quantidade_pessoas INT NOT NULL,
    numero_mesa INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    tipo_reserva VARCHAR(20) DEFAULT 'normal',
    FOREIGN KEY (numero_mesa) REFERENCES mesas(numero)
);

-- Tabela reservas_especiais (dados adicionais de ReservaEspecial)
CREATE TABLE reservas_especiais (
    id INT PRIMARY KEY AUTO_INCREMENT,
    reserva_id INT NOT NULL,
    tipo_evento VARCHAR(50) NOT NULL,
    decoracao_especial BOOLEAN NOT NULL,
    FOREIGN KEY (reserva_id) REFERENCES reservas(id) ON DELETE CASCADE
);