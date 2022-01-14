CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


DROP TABLE IF EXISTS jogador_lista_de_objetivos CASCADE;
DROP TABLE IF EXISTS jogador_lista_de_cartas CASCADE;
DROP TABLE IF EXISTS baralho_cartas_objetivo CASCADE;
DROP TABLE IF EXISTS baralho_cartas_do_jogo CASCADE;
DROP TABLE IF EXISTS baralho_carta_inicio CASCADE;
DROP TABLE IF EXISTS sala_jogadores CASCADE;
DROP TABLE IF EXISTS admin CASCADE;
DROP TABLE IF EXISTS sala CASCADE;
DROP TABLE IF EXISTS jogador CASCADE;
DROP TABLE IF EXISTS carta_inicio CASCADE;
DROP TABLE IF EXISTS carta_do_jogo CASCADE;
DROP TABLE IF EXISTS carta_objetivo CASCADE;
DROP TABLE IF EXISTS baralho CASCADE;


CREATE TABLE IF NOT EXISTS admin (
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    senha character varying(255)
    );

CREATE TABLE IF NOT EXISTS baralho
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    codigo character varying(255) COLLATE pg_catalog."default",
    descricao character varying(255) COLLATE pg_catalog."default",
    titulo character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT baralho_pkey PRIMARY KEY (id),
    CONSTRAINT baralho_codigo_key UNIQUE (codigo)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS baralho
    OWNER to lila;

CREATE TABLE IF NOT EXISTS carta_do_jogo
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

CREATE TABLE IF NOT EXISTS carta_objetivo
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

CREATE TABLE IF NOT EXISTS carta_inicio
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

CREATE TABLE IF NOT EXISTS jogador
(
    id uuid DEFAULT uuid_generate_v4 () PRIMARY KEY,
    bonus_coracao_gra integer,
    bonus_coracao_peq integer,
    coracao_gra integer,
    coracao_peq integer,
    nome character varying(255) COLLATE pg_catalog."default",
    pontos integer

    );

CREATE TABLE IF NOT EXISTS sala
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

CREATE TABLE IF NOT EXISTS baralho_carta_inicio
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

ALTER TABLE IF EXISTS baralho_carta_inicio
    OWNER to lila;

CREATE TABLE IF NOT EXISTS baralho_cartas_do_jogo
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

ALTER TABLE IF EXISTS baralho_cartas_do_jogo
    OWNER to lila;
CREATE TABLE IF NOT EXISTS baralho_cartas_objetivo
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

ALTER TABLE IF EXISTS baralho_cartas_objetivo
    OWNER to lila;

CREATE TABLE IF NOT EXISTS jogador_lista_de_cartas
(
    jogador_id uuid NOT NULL,
    lista_de_cartas_id uuid NOT NULL,
    CONSTRAINT jogador_lista_de_cartas_pkey PRIMARY KEY (jogador_id, lista_de_cartas_id),
    CONSTRAINT uk_gnqsqxth3nltmwxyyu7wpr664 UNIQUE (lista_de_cartas_id),
    CONSTRAINT fk1u6cwmrqp83j8gxgu59ekt4nm FOREIGN KEY (jogador_id)
    REFERENCES public.jogador (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkna4rgaoyn5hmnba4d9tg6bc4e FOREIGN KEY (lista_de_cartas_id)
    REFERENCES public.carta_do_jogo (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS jogador_lista_de_cartas
    OWNER to lila;

CREATE TABLE IF NOT EXISTS jogador_lista_de_objetivos
(
    jogador_id uuid NOT NULL,
    lista_de_objetivos_id uuid NOT NULL,
    CONSTRAINT jogador_lista_de_objetivos_pkey PRIMARY KEY (jogador_id, lista_de_objetivos_id),
    CONSTRAINT uk_on0nporhd2m2wst45g7vklwri UNIQUE (lista_de_objetivos_id),
    CONSTRAINT fkhp5sm5ceotm2scqcb4ccyeqlv FOREIGN KEY (lista_de_objetivos_id)
    REFERENCES public.carta_objetivo (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkslo440kgeappfqc8tlqcphe8p FOREIGN KEY (jogador_id)
    REFERENCES public.jogador (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS jogador_lista_de_objetivos
    OWNER to lila;


CREATE TABLE IF NOT EXISTS sala_jogadores
(
    sala_id uuid NOT NULL,
    jogadores_id uuid NOT NULL,
    CONSTRAINT uk_bk1b3pf8cwlwkfkxghisw1wi5 UNIQUE (jogadores_id),
    CONSTRAINT fkd36481lgk5aqcsv7lpfp6855r FOREIGN KEY (sala_id)
    REFERENCES public.sala (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkga0ia8yf0e58jjp50b0yrijpk FOREIGN KEY (jogadores_id)
    REFERENCES public.jogador (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS sala_jogadores
    OWNER to lila;