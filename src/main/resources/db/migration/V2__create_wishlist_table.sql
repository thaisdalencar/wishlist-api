
CREATE TABLE wish_list
(
    id SERIAL PRIMARY KEY,
    created_at timestamp with time zone NOT NULL,
    modified_at timestamp with time zone NOT NULL,
    client_id int8 references client(id) NOT NULL,
    product_id int8 NOT NULL,
    UNIQUE (client_id, product_id)
);
