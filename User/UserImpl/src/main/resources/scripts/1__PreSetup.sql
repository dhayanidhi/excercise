--Create ddl for entity

-- create request_sequence
CREATE SEQUENCE id_sequence INCREMENT 1 START 1000;

-- create customer table
CREATE TABLE rs_customer(
     c_id bigint NOT NULL,
     c_firstname character varying(36) NOT NULL,
     c_lastname character varying(36) NOT NULL,
     c_active boolean,
     c_age int,
     c_sex character varying(10),
     c_creation_time timestamp,
     c_modified_time timestamp,
     c_version INT NOT NULL DEFAULT 1,
     PRIMARY KEY (c_id)
);
ALTER TABLE rs_customer ADD CONSTRAINT rs_customer_name UNIQUE(c_firstname, c_lastname);

-- create custom Tag table
CREATE TABLE rs_custom_tag(
     c_id bigint NOT NULL,
     c_name character varying(36) NOT NULL,
     c_validation character varying(36),
     c_validation_value character varying(36),
     c_allowed_entry int,
     c_attribute_type character varying(36),
     c_creation_time timestamp,
     c_modified_time timestamp,
     c_version INT NOT NULL DEFAULT 1,
     PRIMARY KEY (c_id)
);
ALTER TABLE rs_custom_tag ADD CONSTRAINT rs_custom_tag_name UNIQUE(c_name);

-- create customer custom attribute table
CREATE TABLE rs_customer_attribute(
     c_id bigint NOT NULL,
     fk_customer bigint NOT NULL,
     fk_custom_tag bigint NOT NULL,
     c_value character varying(36),
     c_creation_time timestamp,
     c_modified_time timestamp,
     c_version INT NOT NULL DEFAULT 1,
     PRIMARY KEY (c_id)
);
ALTER TABLE rs_customer_attribute ADD CONSTRAINT rs_customer_attribute_fk_customer FOREIGN KEY (fk_customer) REFERENCES rs_customer(c_id);
ALTER TABLE rs_customer_attribute ADD CONSTRAINT rs_customer_attribute_fk_custom_tag FOREIGN KEY (fk_custom_tag) REFERENCES rs_custom_tag(c_id);
