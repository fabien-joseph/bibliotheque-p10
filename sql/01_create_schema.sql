--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5
-- Dumped by pg_dump version 10.5

-- Started on 2019-10-03 12:23:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2818 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 196 (class 1259 OID 173083)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 181217)
-- Name: livre; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.livre (
    id bigint NOT NULL,
    annee integer NOT NULL,
    auteur character varying(255) NOT NULL,
    genre integer,
    img_url character varying(2000) NOT NULL,
    nom character varying(255) NOT NULL,
    quantite integer NOT NULL,
    resume character varying(2000) NOT NULL
);


--
-- TOC entry 199 (class 1259 OID 197851)
-- Name: reservation; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.reservation (
    id bigint NOT NULL,
    date_debut timestamp without time zone NOT NULL,
    livre_id bigint,
    utilisateur_id bigint,
    rendu boolean DEFAULT false NOT NULL,
    renouvelable boolean DEFAULT true NOT NULL
);


--
-- TOC entry 198 (class 1259 OID 197833)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.utilisateur (
    id bigint NOT NULL,
    date_creation timestamp without time zone NOT NULL,
    is_bibliothecaire boolean NOT NULL,
    mail character varying(255) NOT NULL,
    mot_de_passe character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    prenom character varying(255) NOT NULL
);


--
-- TOC entry 2683 (class 2606 OID 181224)
-- Name: livre livre_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.livre
    ADD CONSTRAINT livre_pkey PRIMARY KEY (id);


--
-- TOC entry 2687 (class 2606 OID 197855)
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);


--
-- TOC entry 2685 (class 2606 OID 197840)
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);


--
-- TOC entry 2689 (class 2606 OID 197861)
-- Name: reservation fk7age5yb4rno7mnt26auu4403c; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fk7age5yb4rno7mnt26auu4403c FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


--
-- TOC entry 2688 (class 2606 OID 197856)
-- Name: reservation fkbtwk47ayb3rwdyr6uqp7dsgr2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fkbtwk47ayb3rwdyr6uqp7dsgr2 FOREIGN KEY (livre_id) REFERENCES public.livre(id);


-- Completed on 2019-10-03 12:23:40

--
-- PostgreSQL database dump complete
--

