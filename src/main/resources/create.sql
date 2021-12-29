CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS public.admin (
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    senha character varying(255)
);
CREATE TABLE IF NOT EXISTS public.baralho
(
    id_codigo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default" NOT NULL,
    titulo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    admin_id_codigo uuid,
    CONSTRAINT baralho_pkey PRIMARY KEY (id_codigo),
    CONSTRAINT fkabxy2qsduq7nst5j5me4d81wg FOREIGN KEY (admin_id_codigo)
        REFERENCES public.admin (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.categoria_de_carta
(
    id uuid NOT NULL,
    nome character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT categoria_de_carta_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS public.tipo_carta
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    bonus boolean NOT NULL,
    nome character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tipo_carta_pkey PRIMARY KEY (id)
);