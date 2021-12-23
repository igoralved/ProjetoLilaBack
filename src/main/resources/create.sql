CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS public.tb_admin (
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    senha character varying(255)
);

CREATE TABLE IF NOT EXISTS public.baralho
(
    id uuid NOT NULL,
    codigo character varying(255) COLLATE pg_catalog."default" PRIMARY KEY,
    descricao character varying(255) COLLATE pg_catalog."default",
    senha character varying(255) COLLATE pg_catalog."default",
    titulo character varying(255) COLLATE pg_catalog."default"
   
);