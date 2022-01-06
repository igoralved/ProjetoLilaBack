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
CREATE TABLE IF NOT EXISTS public.carta_do_jogo
(
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    bonus boolean NOT NULL,
    categoria character varying(255) COLLATE pg_catalog."default" NOT NULL,
    fonte character varying(255) COLLATE pg_catalog."default" NOT NULL,
    texto character varying(255) COLLATE pg_catalog."default" NOT NULL,
    tipo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    valor_cor_grande integer ,
    valor_cor_pequeno integer,
    pontos integer,
    baralho_id_codigo character varying(255) COLLATE pg_catalog."default",
       CONSTRAINT fk83lf2a1xn7mlsrma7ht1xswn6 FOREIGN KEY (baralho_id_codigo)
        REFERENCES public.baralho (id_codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
    
);
CREATE TABLE IF NOT EXISTS public.carta_objetivo
(
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    categoria character varying(255) COLLATE pg_catalog."default" NOT NULL,
    classificacao character varying(255) COLLATE pg_catalog."default" NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default" NOT NULL,
    pontos integer NOT NULL,
    baralho_id_codigo character varying(255) COLLATE pg_catalog."default",
        CONSTRAINT fkfwk7risacj4p4ykqsqi7q7inq FOREIGN KEY (baralho_id_codigo)
        REFERENCES public.baralho (id_codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
