CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS public.admin (
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    senha character varying(255)
);



CREATE TABLE IF NOT EXISTS public.baralho
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    codigo character varying(255) COLLATE pg_catalog."default",
    descricao character varying(255) COLLATE pg_catalog."default",
    titulo character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT baralho_pkey PRIMARY KEY (id),
    CONSTRAINT baralho_codigo_key UNIQUE (codigo)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.baralho
    OWNER to lila;

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

CREATE TABLE IF NOT EXISTS public.jogador
(
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    bonus_coracao_gra integer,
    bonus_coracao_peq integer,
    coracao_gra integer,
    coracao_peq integer,
    nome character varying(255) COLLATE pg_catalog."default",
    pontos integer
    
);

CREATE TABLE IF NOT EXISTS public.sala
(
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    hash character varying(255) COLLATE pg_catalog."default",
    status_enum integer,
    baralho_id uuid,
     CONSTRAINT fkc3jty7ftkxv78cwlbkx8mgw85 FOREIGN KEY (baralho_id)
        REFERENCES public.baralho (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.baralho_carta_inicio
(
    baralho_id uuid NOT NULL,
    carta_inicio_id uuid NOT NULL,
    CONSTRAINT uk_goww9pcuinggp0a0kg5j45wgq UNIQUE (carta_inicio_id),
    CONSTRAINT fki83n37ppk5njjxxa9gccr9tqk FOREIGN KEY (carta_inicio_id)
        REFERENCES public.carta_inicio (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fksmb55ndh0ay6ey3ccby6hl9og FOREIGN KEY (baralho_id)
        REFERENCES public.baralho (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.baralho_carta_inicio
    OWNER to lila;

CREATE TABLE IF NOT EXISTS public.baralho_cartas_do_jogo
(
    baralho_id uuid NOT NULL,
    cartas_do_jogo_id uuid NOT NULL,
    CONSTRAINT uk_9fid5vhq5mnt90la8kd43c3y2 UNIQUE (cartas_do_jogo_id),
    CONSTRAINT fk1bydvfmk7acvd1xmstalxuv5k FOREIGN KEY (cartas_do_jogo_id)
        REFERENCES public.carta_do_jogo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkbvd6v2rm8l615o8spp0ml53qi FOREIGN KEY (baralho_id)
        REFERENCES public.baralho (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.baralho_cartas_do_jogo
    OWNER to lila;
CREATE TABLE IF NOT EXISTS public.baralho_cartas_objetivo
(
    baralho_id uuid NOT NULL,
    cartas_objetivo_id uuid NOT NULL,
    CONSTRAINT uk_k4tyvy7nh83fh5qrno8w5fcea UNIQUE (cartas_objetivo_id),
    CONSTRAINT fkeujodwim0fxw9abeiubrie1g8 FOREIGN KEY (cartas_objetivo_id)
        REFERENCES public.carta_objetivo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkpjuyetx6lssgpdrjd2ob1yyp6 FOREIGN KEY (baralho_id)
        REFERENCES public.baralho (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.baralho_cartas_objetivo
    OWNER to lila;


