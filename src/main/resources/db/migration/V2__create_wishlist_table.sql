
CREATE TABLE wish_list_item
(
    id SERIAL PRIMARY KEY,
    created_at timestamp with time zone NOT NULL,
    modified_at timestamp with time zone NOT NULL,
    client_id int8 references client(id) ON DELETE CASCADE,
    product_id character varying(255) NOT NULL,
    UNIQUE (client_id, product_id)
);
