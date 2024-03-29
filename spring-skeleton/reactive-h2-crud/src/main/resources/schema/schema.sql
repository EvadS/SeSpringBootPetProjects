DROP TABLE IF EXISTS CATALOGUE_ITEMS;
CREATE TABLE CATALOGUE_ITEMS (
                                 ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                 SKU_NUMBER VARCHAR(16) NOT NULL,
                                 ITEM_NAME VARCHAR(255) NOT NULL,
                                 DESCRIPTION VARCHAR(500) NOT NULL,
                                 CATEGORY VARCHAR(255) NOT NULL,
                                 PRICE DOUBLE NOT NULL,
                                 INVENTORY INT NOT NULL,
                                 CREATED_ON TIMESTAMP NOT NULL DEFAULT NOW(),
                                 UPDATED_ON TIMESTAMP
);