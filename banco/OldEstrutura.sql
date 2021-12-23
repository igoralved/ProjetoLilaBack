--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2021-12-22 17:25:36

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 210 (class 1259 OID 16412)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 16405)
-- Name: tb_admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_admin (
    id integer NOT NULL,
    conf_senha character varying(255),
    senha character varying(255)
);


ALTER TABLE public.tb_admin OWNER TO postgres;

--
-- TOC entry 3305 (class 0 OID 16405)
-- Dependencies: 209
-- Data for Name: tb_admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_admin (id, conf_senha, senha) FROM stdin;
\.


--
-- TOC entry 3312 (class 0 OID 0)
-- Dependencies: 210
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- TOC entry 3165 (class 2606 OID 16411)
-- Name: tb_admin tb_admin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_admin
    ADD CONSTRAINT tb_admin_pkey PRIMARY KEY (id);


-- Completed on 2021-12-22 17:25:36

--
-- PostgreSQL database dump complete
--

