PGDMP     :    1                w           bibliotheque    10.5    10.5                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    173058    bibliotheque    DATABASE     �   CREATE DATABASE bibliotheque WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';
    DROP DATABASE bibliotheque;
             fabien    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false                       0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    173083    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public       fabien    false    3            �            1259    181217    livre    TABLE     4  CREATE TABLE public.livre (
    id bigint NOT NULL,
    annee integer NOT NULL,
    auteur character varying(255) NOT NULL,
    genre integer,
    img_url character varying(2000) NOT NULL,
    nom character varying(255) NOT NULL,
    quantite integer NOT NULL,
    resume character varying(2000) NOT NULL
);
    DROP TABLE public.livre;
       public         fabien    false    3            �            1259    197851    reservation    TABLE     �   CREATE TABLE public.reservation (
    id bigint NOT NULL,
    date_debut timestamp without time zone NOT NULL,
    livre_id bigint,
    utilisateur_id bigint,
    rendu boolean DEFAULT false NOT NULL,
    renouvelable boolean DEFAULT true NOT NULL
);
    DROP TABLE public.reservation;
       public         fabien    false    3            �            1259    197833    utilisateur    TABLE     M  CREATE TABLE public.utilisateur (
    id bigint NOT NULL,
    date_creation timestamp without time zone NOT NULL,
    is_bibliothecaire boolean NOT NULL,
    mail character varying(255) NOT NULL,
    mot_de_passe character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    prenom character varying(255) NOT NULL
);
    DROP TABLE public.utilisateur;
       public         fabien    false    3            �
          0    181217    livre 
   TABLE DATA               Y   COPY public.livre (id, annee, auteur, genre, img_url, nom, quantite, resume) FROM stdin;
    public       fabien    false    197   �       �
          0    197851    reservation 
   TABLE DATA               d   COPY public.reservation (id, date_debut, livre_id, utilisateur_id, rendu, renouvelable) FROM stdin;
    public       fabien    false    199   �&       �
          0    197833    utilisateur 
   TABLE DATA               l   COPY public.utilisateur (id, date_creation, is_bibliothecaire, mail, mot_de_passe, nom, prenom) FROM stdin;
    public       fabien    false    198   �'                  0    0    hibernate_sequence    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hibernate_sequence', 4, true);
            public       fabien    false    196            {
           2606    181224    livre livre_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.livre
    ADD CONSTRAINT livre_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.livre DROP CONSTRAINT livre_pkey;
       public         fabien    false    197            
           2606    197855    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public         fabien    false    199            }
           2606    197840    utilisateur utilisateur_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.utilisateur DROP CONSTRAINT utilisateur_pkey;
       public         fabien    false    198            �
           2606    197861 '   reservation fk7age5yb4rno7mnt26auu4403c    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fk7age5yb4rno7mnt26auu4403c FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);
 Q   ALTER TABLE ONLY public.reservation DROP CONSTRAINT fk7age5yb4rno7mnt26auu4403c;
       public       fabien    false    198    199    2685            �
           2606    197856 '   reservation fkbtwk47ayb3rwdyr6uqp7dsgr2    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fkbtwk47ayb3rwdyr6uqp7dsgr2 FOREIGN KEY (livre_id) REFERENCES public.livre(id);
 Q   ALTER TABLE ONLY public.reservation DROP CONSTRAINT fkbtwk47ayb3rwdyr6uqp7dsgr2;
       public       fabien    false    199    2683    197            �
   �  x��YMsG�=���n�"@� �/���E�i-��3�G�� ]�ժ���?ao{Ҏ�i.��dɾ�U (��IQ]��������`|z6��~��z�[ݪ�~���`���.~��o=7q�գ�^���ѷ��7�����G������/q9���ׇ��o�'����]w�����|i�>�T�za���w�w����֨�-�|�����O�>��4ᗏ���~���|;�X�Z��l�m0�3e�Zj5���*���e����]H5��me�|k#�P��������������|jL�+C{�z��Z�V��1����Y�儅ｪwM��G҇ѯ�`[��������������2�ۭo��m�Z���d��۠������r�*ׇy���S�K+޵����QWZ�.��w���6jq9��Z�֝KQ�Z�Q�ӭ}��ڻ;{�>��v��J��tȡ�λ��%ج���,��1J'��d^L�a�T�2F	�P��Yf{��x�|�y�N$a}{�j[��z�˭���P�y�K��24%����`|vv:x1R_��W����|0��r:~Y_^��u�.%%�#�+���=�#�{���4d󢩂��`|2���fI:��T���	�[�����KH����ڌ����ʻ��k ��jtXe�h�:f�[��]���0G%�ۊ�Ko$����Ib0b��8R�y���y��C�́gF$�<Ir��'���seU��%`İP1 ��sl«��v:�#u)�H�|�����g�c�x-���C0�x2x��:.���G�l|�b.^>{zY�N]��
��/��x�I��z��s�t�AM�j�;Pn�L�f@R�]��MN@}��&�|Ts��H>�@,pwǩ���]��L;j�֍����i�3��M����:U� -ܣ�gZ��|�����B��~�\��9�h���ɂ���|,pjV�<��0IDMH)��8b��FOF_���Գ~ɤY��k�k�s��,�1�.�o��+�0's:2��_�l��;��-o�u�p ��VmJ
�����c@L��4y�:y<�E.x$W����/�a����������~�˻B�$�s4��}<xa���]�.X"AB �-���u�Q�\E���n���D�����<6��@�S��ߢ�����Ņ��)���jg{z�߸]�A�>t����ğ5m���=1 ӄ��.�m}e$a�;"ԙ���쀼`ړd�=,���W8K���|�)O�߀����!ҙ�:E$����B� �$���2�5�Y@����5qdp�7>����^��u�G�^'�t�a�-�,a,Kh~�0H�88\� =6�%KU�}�f�F..:�z!"'L%r�J����{<+���/p;BWYR����v��؆Ȯ�O__|׀>*���	i�P;\�K�yD^J2����B�yE�M� D�V�D�ŏN��  �Y�08��y4~{�������8�}���'f<O�����G�dq��$OâDM3>=��j��TY�^�/�C��}	�FJ����m[)[S��J!Cĕ�LK	%J*�Fp�X=ӡ��^� \����U�P]���P�T���d<TW��s��]�׆vL!�o����aznt����3��qh	"(]���
1q����p�����	�̣��H6�ex���Lm�h�����L���l<���{�ӣ�����L&�G�8�<::94g����SX�����xt�]U�p�L!�}�ٟ������ѣ���d,1���g'�����,l�E�F��(��e���ðv�,B��]�o��>�B ɷ��^d�.֌���G�J��T���B��B���y|vt0��DͣQ/l�"��PS(E��t���/+^�Ճ�e����ǜz����� ���*G7a����~�qB
eg���Lv&���:�p(�?K� g�&����w��������E^ri���'��o�6a]��_�Lc���z�;��"�(�Y�mß4�!�%��E�ԑ+�ν���QR�|l��NM�PXOr���a��8퓨z��bg���3 ޏ�4]K��`�w	4�%�q幥����@� ���k�qQ.w!Ҋ�cf�y�疈�j9isJ��f<j~,��	���Bܣ���0�haT��Z�@��R�v7x�X����w�b)�e��/����M��k˩~*�?d�&"��?��g��x��٩�-��z-m��s�;A�Qe��c����V�6f�+~���æ�5��;^��#sڤ�(�|]��i�������i�Lu�eT�
`I�t�Ʌ$����߹)�#O�֗��`�J�a�\6��.�}�	4|��7�v�7U�9b�g��ٔ�=1M#+��rba�����ix�H������4�T�9��̽]f� a�^������^`�KeTo� r����Q/Y^������#�՗�k�ws� L�L�}�������P�f�D�'y0�):����'����oR��c�{�휲N�n��C=�M���F�\��)�rz��������ab�)c.�)E�����a�'��d!�%�����P����ȑ:w�<v��n�qj�]z�AR̤C�r9�0*�n���o�dN��n)�nm �ЃZQ���cK�a���E�S��7Ab�6M��ba�s4���cg�H�J���~X�~5�8�E�N5J�P�z���t�� Fʀ�c �5�:k�ͨ��JW��[Y��Hֈ��G6�ҌCzHfI�A5�Sx�f�&��ivU><9K-IS�>��H�2�C�Q�sΈ�;/��HK�=Y&����Yce�A$&�7K��&G�H.���U��p�wے�Y������5$�8rD�d���!�ջ�}@ nFH�7��F	�T��:��긺��]���Q�������*F*�_��f�(����c����Q
�%<��Źى���b�w$tt]P�R�DEf��8��ی5��,(�l]D2�����D���^�P�k��n�0w��ǲh���2�e�jhKv5�+�!|Ƚt	�7isɦ�c윺�ɢ�?���+M��.��.�Bw�XG�������
8�L#u*㒡�A�p/~�WY�_��~NB,f{_
�m{-��#s�b��#�eᇤ�l��,�o���f{�F���S���0K�M&؂�H1D�g#{�B&H1+QI�Kۗp�~�FDޖ�J��D޲�./���S��N�h�6J��N0�e:�[O�PF��& γ�a#+�9���w�����E&����=�̺(!@�����@0l�Q��nt8g�"63���7�v�{���^���v�Ƌ�wחI_��������z�i�L����|V�=�w��0��٭�侅���r�=��^�\���z�T~|x�*oؽ��W�p"�=��:\=��eZ�-֖� P>�aꕼxq�2!k����h%2������XP�(04=�-��`�ԋ2�اp7�\�\<�{c3�+&�w�쬙�Y!_��.4ɒӬ�%����z�v�t ��V]�R�ؗ�p�(k31���򈑭ejds�A��u6��Vȗ�8�i���5�<Z��C^�ɤd�N�����mp�V�֜�2�e���9d�[� �`>�#�DQ�m��"����]�Z@F�o\a"�8�)�	����'��_r��9>�3�!ɶE�>���e�-�0�p���)��rC�ut���^�����?�n#?��)_�`
�^�w���D��%*͗">X�=!��*O��i����$-�����ms�|���m����7Яx�A1��&^�}�F��n/C'���擔�JF���T���d�M��F�/�#;���6��s�k!�i_KQ�$w�&A�	�����*��e�Pl�`ʼ�U�zҬ�[��V�����{��	ds��j0E+���o�;����?�ʹ����&oy�/.x��i'S�����k�N|Ò+M�� Y���b�1'�4߯@	�F���7������U�      �
   �   x�]��1�3��,2_j�9��VP��m� `�Aq�uqt�ԙ8� @�	�F�G�3'k�ê�*�Ý,�����?¿��S�r�����v?i�T.�7!3�����7az�`r�Qb^l}�yC�	f�U�ϣ��q�`���x`k���7�      �
   �   x�e�MO�0 @�����j��ڝ�NH��Z%�2`lm�p���_��D��{����D�XƄ����0G)a���ٴ��j[W�ևҴh{<����Q@��'�\O�IZ���!j:X�˜�~zN&Z�c�U�Ə��=���^)d??"�|Dx�e��B�=޴�T�*��3ީZ�fG׽V���i�w+�f�9�X�|�7Ň]&/)I�n�������A�v	W�     