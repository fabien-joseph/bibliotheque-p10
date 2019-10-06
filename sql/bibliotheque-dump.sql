--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5
-- Dumped by pg_dump version 10.5

-- Started on 2019-10-06 12:25:47

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
-- TOC entry 2822 (class 0 OID 0)
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
-- TOC entry 2812 (class 0 OID 181217)
-- Dependencies: 197
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.livre (id, annee, auteur, genre, img_url, nom, quantite, resume) FROM stdin;
1	1892	Arthur Conan Doyle	0	https://images-na.ssl-images-amazon.com/images/I/413FajzGBGL._SX327_BO1,204,203,200_.jpg	Les Aventures de Sherlock Holmes	20	Le roi de Bohême vient trouver Sherlock Holmes pour lui confier une affaire : il va bientôt se marier à une prude princesse mais un ancien amour, Irène Adler, menace de tout faire échouer en publiant une photo d'eux deux. Sherlock Holmes invente un stratagème pour arriver à pénétrer chez Irène pour voir où est la photo : il fait croire à un incendie en sachant qu'elle cherchera à sauver ce qu'elle a de plus cher dans la panique - la photo. Finalement, elle parvient à s'enfuir avec la photo mais assure au roi qu'elle est amoureuse d'un autre homme et qu'elle ne lui portera pas préjudice. C'est une des rares fois où Sherlock Holmes échoue.
2	1998	J. K. Rowling	1	https://images-na.ssl-images-amazon.com/images/I/81OdLM-ZyuL.jpg	Harry Potter et la Chambre des secrets	17	L'elfe Dobby a bien tenté d'empêcher Harry de retourner à l'École des Sorciers, frappée d'une terrible malédiction, mais Harry n'est pas près de laisser choir ses amis. Après une fugue et une rentrée scolaire plutôt chaotique, voici notre valeureux sorcier intégré à Poudlard. Les forces maléfiques n'ont qu'à bien se tenir.
3	2012	James Dashner	2	https://images-na.ssl-images-amazon.com/images/I/91IJ2rIOEDL.jpg	Le Labyrinthe	6	Quand Thomas reprend connaissance, il est pris au piège avec un groupe d'autres garçons dans un labyrinthe géant dont le plan est modifié chaque nuit. Il n'a plus aucun souvenir du monde extérieur, à part d'étranges rêves à propos d'une mystérieuse organisation appelée W.C.K.D. En reliant certains fragments de son passé, avec des indices qu'il découvre au sein du labyrinthe, Thomas espère trouver un moyen de s'en échapper.
4	1862	Victor Hugo	3	https://images-eu.ssl-images-amazon.com/images/I/510ypkdwIYL.jpg	Les misérables	16	Jean Valjean est un personnage aussi fantastique que Quasimodo. Mais il est fait, lui aussi, de ce beau fantastique des poètes qui part de la réalité, qui l'exalte, l'amplifie, la magnifie. Au début, Jean Valjean est un innocent qui a volé un pain et qui ne rencontre pas un président Magnaud pour le sauver du bagne.
5	1813	Jane Austen	4	http://ressources.bragelonne.fr/img/livres/2017-10/1710-Orgueiletprejugescollector_org.jpg	Orgueil et préjugés	9	Mme Bennet a cinq filles et compte bien les marier toutes, ce qui n'est pas une tâche facile dans l'Angleterre du début du XIXe siècle. Non que les demoiselles Bennett soient laides, mais elles n'ont pas de fortune.
6	1847	Emilie Brontë	5	https://images-na.ssl-images-amazon.com/images/I/51V6VR99Z0L._SX321_BO1,204,203,200_.jpg	Les Hauts de Hurle-Vent	5	Le dénouement a lieu en 1802. Les Hauts de Hurlevent sont des terres situées au sommet d'une colline et balayées par les vents du nord. La famille Earnshaw y vivait, heureuse, jusqu'à ce qu'en 1771, M. Earnshaw adopte un jeune bohémien de 6 ans, Heathcliff. Ce dernier va attirer le malheur sur cette famille.
7	1949	George Orwell	6	https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/2/2/1/005479221.jpg	1984	12	L’histoire se passe à Londres en 1984, comme l'indique le titre du roman. Le monde, depuis les grandes guerres nucléaires des années 1950, est divisé en trois grands « blocs » : l’Océania (Amériques, îles de l'Atlantique, comprenant notamment les îles Anglo-Celtes, Océanie et Afrique australe), l’Eurasia (reste de l'Europe et URSS) et l’Estasia (Chine et ses contrées méridionales, îles du Japon, et une portion importante mais variable de la Mongolie, de la Mandchourie et du Tibet) qui sont en guerre perpétuelle les uns contre les autres. Ces trois grandes puissances sont dirigées par différents régimes totalitaires revendiqués comme tels, et s'appuyant sur des idéologies nommées différemment mais fondamentalement similaires : l’Angsoc (ou « socialisme anglais ») pour l'Océania, le « néo-bolchévisme » pour l'Eurasia et le « culte de la mort » (ou « oblitération du moi ») pour l'Estasia. Tous ces partis sont présentés comme communistes avant leur montée au pouvoir, jusqu'à ce qu'ils deviennent des régimes totalitaires et relèguent les prolétaires qu'ils prétendaient défendre au bas de la pyramide sociale. Les trois régimes sont présentés comme étant socialement, économiquement et idéologiquement sensiblement les mêmes.\r\nÀ côté de ces trois blocs subsiste une sorte de « Quart-monde », dont le territoire ressemble approximativement à un parallélogramme ayant pour sommets Tanger, Brazzaville, Darwin et Hong Kong. C'est le contrôle de ce territoire, ainsi que celui de l'Antarctique, qui justifie officiellement la guerre perpétuelle entre les trois blocs.
8	1942	Albert Camus	7	https://images-na.ssl-images-amazon.com/images/I/51MYDidib7L._SX366_BO1,204,203,200_.jpg	L'Étranger	2	Le roman met en scène un personnage-narrateur nommé Meursault, vivant à Alger en Algérie française. Le roman est découpé en deux parties.\r\n\r\nAu début de la première partie, Meursault reçoit un télégramme annonçant que sa mère, qu'il a internée à l’hospice de Marengo, vient de mourir. Il se rend en autocar à l’asile de vieillards, situé près d’Alger. Veillant la morte toute la nuit, il assiste le lendemain à la mise en bière et aux funérailles, sans avoir l'attitude attendue d’un fils endeuillé ; le héros ne pleure pas, il ne veut pas simuler un chagrin qu'il ne ressent pas.\r\n\r\nLe lendemain de l'enterrement, Meursault décide d'aller nager à l'établissement de bains du port, et y rencontre Marie, une dactylo qui avait travaillé dans la même entreprise que lui. Le soir, ils sortent voir un film de Fernandel au cinéma et passent le restant de la nuit ensemble. Le lendemain matin, son voisin, Raymond Sintès, un proxénète notoire, lui demande de l'aider à écrire une lettre pour dénigrer sa maîtresse, une Maure envers laquelle il s'est montré brutal ; il craint des représailles du frère de celle-ci. La semaine suivante, Raymond frappe et injurie sa maîtresse dans son appartement. La police intervient et convoque Raymond au commissariat. Celui-ci utilise Meursault comme témoin de moralité. En sortant, il l'invite, lui et Marie, à déjeuner le dimanche suivant à un cabanon au bord de la mer, qui appartient à un de ses amis, Masson. Lors de la journée, Marie demande à Meursault s'il veut se marier avec elle. Il répond que ça n'a pas d'importance, mais qu'il le veut bien.
9	-800	Homère	7	https://images-na.ssl-images-amazon.com/images/I/41h2kjLuajL._SX328_BO1,204,203,200_.jpg	L'Odyssée	25	L’Odyssée raconte l’histoire du héros Ulysse qui, après s’être ardemment battu lors de la guerre de Troie, essaie de retourner à son île natale (Ithaque) pour retrouver sa femme Pénélope, son fils Télémaque, son père Laërte et sa patrie tout entière. Mais au cours de son voyage, il fait face à d’innombrables périls (de l’emprisonnement sur l’île de la nymphe Calypso aux attaques marines du dieu Poséidon qui lui en veut toujours d’avoir rendu aveugle son fils Polyphème). Malgré cela, Ulysse parvient à retourner chez lui et à délivrer son pays natal et son épouse des bras de multiples prétendants qui la harcèlent et qui s’étaient tous prématurément réjouis de sa mort.\r\n\r\nL’Odyssée est organisée en chants, car elle était supposément « chantée ». Les chants divers sont à leur tour subdivisés en trois catégories distinctes, chacune marquant une étape bien définie de l’histoire :\r\n\r\n-           La Télémachie (chants I à IV) : pendant cette période, Télémaque, fils d’Ulysse et de Pénélope, recherche éperdument la vérité sur son père. Il voyage à Pylos et Sparte pour rencontrer Nestor et Ménélas. Cependant, de multiples prétendants essaient de persuader Pénélope d’oublier Ulysse et d’épouser l’un d’entre eux.\r\n\r\n-           Les récits d’Ulysse chez Alcinoos (chants V à XII) : recueilli par Alcinoos après son naufrage marin (manigancé par le vindicatif...
\.


--
-- TOC entry 2814 (class 0 OID 197851)
-- Dependencies: 199
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.reservation (id, date_debut, livre_id, utilisateur_id, rendu, renouvelable) FROM stdin;
2	2019-08-29 16:47:39.011	9	3	f	t
10	2019-08-24 22:24:56	8	3	f	t
5	2019-09-02 15:52:15.862	3	3	f	t
6	2019-09-15 19:56:16.83	4	3	f	t
3	2019-09-15 21:15:18.741	2	3	f	t
4	2019-09-15 21:37:03.364	1	3	f	t
7	2019-09-15 21:54:15.216	5	3	f	t
9	2019-07-25 09:24:33.421	7	3	f	t
8	2019-10-01 15:01:04.002	6	3	f	t
\.


--
-- TOC entry 2813 (class 0 OID 197833)
-- Dependencies: 198
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.utilisateur (id, date_creation, is_bibliothecaire, mail, mot_de_passe, nom, prenom) FROM stdin;
4	2019-09-13 09:33:04.713	t	bibliothecaire@gmail.com	$2a$10$u.VrvLOiWSYFQ17PPrPnfu58UGvjjD9D2DtGx5FVUg63zeT6Njjgq	Bibliothecaire	Bibliothecaire
3	2019-08-24 14:49:43.988	f	utilisateur@gmail.com	$2a$10$trUg8Vhd2YqVUKxwsKqnT..JELVnr0U3u8h9Xj/PpjS5R7172dvJC	Utilisateur	Utilisateur
\.


--
-- TOC entry 2823 (class 0 OID 0)
-- Dependencies: 196
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.hibernate_sequence', 4, true);


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


-- Completed on 2019-10-06 12:25:48

--
-- PostgreSQL database dump complete
--

