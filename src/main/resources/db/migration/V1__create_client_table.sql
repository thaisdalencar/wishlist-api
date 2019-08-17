CREATE TABLE client
(
    id SERIAL PRIMARY KEY,
    created_at timestamp with time zone NOT NULL,
    modified_at timestamp with time zone NOT NULL,
    email character varying(255) UNIQUE NOT NULL,
    name character varying(255) NOT NULL
);


