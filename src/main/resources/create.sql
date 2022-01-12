CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS public.admin (
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    senha character varying(255)
);



CREATE TABLE IF NOT EXISTS public.baralho
(
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    codigo character varying(255) UNIQUE COLLATE pg_catalog."default",
    descricao character varying(255) COLLATE pg_catalog."default",
    titulo character varying(255) COLLATE pg_catalog."default"
    
);


CREATE TABLE IF NOT EXISTS public.carta_do_jogo
(
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    bonus boolean,
    categoria character varying(255) COLLATE pg_catalog."default",
    fonte character varying(255) COLLATE pg_catalog."default",
    pontos integer,
    texto character varying(255) COLLATE pg_catalog."default",
    tipo character varying(255) COLLATE pg_catalog."default",
    valor_cor_grande integer,
    valor_cor_pequeno integer,
    id_baralho uuid,
      CONSTRAINT fkj458oqe30u97d8t13epedm482 FOREIGN KEY (id_baralho)
        REFERENCES public.baralho (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.carta_objetivo
(
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    categoria character varying(255) COLLATE pg_catalog."default",
    classificacao character varying(255) COLLATE pg_catalog."default",
    descricao character varying(255) COLLATE pg_catalog."default",
    pontos integer,
    id_baralho uuid,
      CONSTRAINT fkpg4k4u42owedcssbshysootss FOREIGN KEY (id_baralho)
        REFERENCES public.baralho (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.carta_inicio
(
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    descricao character varying(255) COLLATE pg_catalog."default",
    nome character varying(255) COLLATE pg_catalog."default",
    id_baralho uuid,
       CONSTRAINT fkcgr37kk7t3sqbasgvk4kot1ka FOREIGN KEY (id_baralho)
        REFERENCES public.baralho (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
