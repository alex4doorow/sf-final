PGDMP  #                    {            sf-final    16.1    16.1 8    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    18890    sf-final    DATABASE     �   CREATE DATABASE "sf-final" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "sf-final";
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    18891    d_currencies    TABLE     �   CREATE TABLE public.d_currencies (
    id bigint NOT NULL,
    code character varying(16) NOT NULL,
    name character varying(64) NOT NULL,
    amount_scale bigint
);
     DROP TABLE public.d_currencies;
       public         heap    postgres    false    4            �           0    0    TABLE d_currencies    COMMENT     A   COMMENT ON TABLE public.d_currencies IS 'Currencies dictionary';
          public          postgres    false    215            �           0    0    COLUMN d_currencies.id    COMMENT     ;   COMMENT ON COLUMN public.d_currencies.id IS 'Primary Key';
          public          postgres    false    215            �           0    0    COLUMN d_currencies.code    COMMENT     C   COMMENT ON COLUMN public.d_currencies.code IS 'ISO currency code';
          public          postgres    false    215            �           0    0    COLUMN d_currencies.name    COMMENT     F   COMMENT ON COLUMN public.d_currencies.name IS 'Currency description';
          public          postgres    false    215            �           0    0     COLUMN d_currencies.amount_scale    COMMENT     _   COMMENT ON COLUMN public.d_currencies.amount_scale IS 'Amount number of decimal places (ISO)';
          public          postgres    false    215            �            1259    18894    d_customers    TABLE     �   CREATE TABLE public.d_customers (
    id bigint NOT NULL,
    person_id bigint,
    status character varying(10) DEFAULT 'A'::character varying NOT NULL,
    date_added timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);
    DROP TABLE public.d_customers;
       public         heap    postgres    false    4            �           0    0    TABLE d_customers    COMMENT     4   COMMENT ON TABLE public.d_customers IS 'D_PERSONS';
          public          postgres    false    216            �           0    0    COLUMN d_customers.id    COMMENT     :   COMMENT ON COLUMN public.d_customers.id IS 'Primary Key';
          public          postgres    false    216            �            1259    19915 
   d_operates    TABLE     �   CREATE TABLE public.d_operates (
    id bigint NOT NULL,
    code character varying(16) NOT NULL,
    name character varying(64) NOT NULL
);
    DROP TABLE public.d_operates;
       public         heap    postgres    false    4            �           0    0    TABLE d_operates    COMMENT     8   COMMENT ON TABLE public.d_operates IS 'TYPES OPERATES';
          public          postgres    false    221            �           0    0    COLUMN d_operates.id    COMMENT     9   COMMENT ON COLUMN public.d_operates.id IS 'Primary Key';
          public          postgres    false    221            �            1259    18899 	   d_persons    TABLE     E  CREATE TABLE public.d_persons (
    id bigint NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255),
    middle_name character varying(255),
    country_iso_code_2 character(2) DEFAULT 'RU'::bpchar NOT NULL,
    phone_number character varying(30),
    email character varying(255)
);
    DROP TABLE public.d_persons;
       public         heap    postgres    false    4            �           0    0    TABLE d_persons    COMMENT     2   COMMENT ON TABLE public.d_persons IS 'D_PERSONS';
          public          postgres    false    217            �           0    0    COLUMN d_persons.id    COMMENT     8   COMMENT ON COLUMN public.d_persons.id IS 'Primary Key';
          public          postgres    false    217            �            1259    18905    d_sub_systems    TABLE     z  CREATE TABLE public.d_sub_systems (
    id bigint NOT NULL,
    code character varying(64) NOT NULL,
    name character varying(256) NOT NULL,
    rec_status character(1) NOT NULL,
    cr_dt timestamp without time zone NOT NULL,
    upd_dt timestamp without time zone NOT NULL,
    CONSTRAINT cc_subsys_rec_status CHECK ((rec_status = ANY (ARRAY['A'::bpchar, 'D'::bpchar])))
);
 !   DROP TABLE public.d_sub_systems;
       public         heap    postgres    false    4            �           0    0    TABLE d_sub_systems    COMMENT     8   COMMENT ON TABLE public.d_sub_systems IS 'SUB_SYSTEMS';
          public          postgres    false    218            �           0    0    COLUMN d_sub_systems.id    COMMENT     <   COMMENT ON COLUMN public.d_sub_systems.id IS 'Primary Key';
          public          postgres    false    218            �           0    0    COLUMN d_sub_systems.code    COMMENT     E   COMMENT ON COLUMN public.d_sub_systems.code IS 'Code of sub-system';
          public          postgres    false    218            �           0    0    COLUMN d_sub_systems.name    COMMENT     E   COMMENT ON COLUMN public.d_sub_systems.name IS 'Name of sub-system';
          public          postgres    false    218            �           0    0    COLUMN d_sub_systems.rec_status    COMMENT     R   COMMENT ON COLUMN public.d_sub_systems.rec_status IS 'A – Active, D - Deleted';
          public          postgres    false    218            �           0    0    COLUMN d_sub_systems.cr_dt    COMMENT     A   COMMENT ON COLUMN public.d_sub_systems.cr_dt IS 'Creation date';
          public          postgres    false    218            �           0    0    COLUMN d_sub_systems.upd_dt    COMMENT     F   COMMENT ON COLUMN public.d_sub_systems.upd_dt IS 'Modification date';
          public          postgres    false    218            �            1259    18909    sf_balances    TABLE     [  CREATE TABLE public.sf_balances (
    id bigint NOT NULL,
    customer_id bigint,
    amount numeric(25,7) NOT NULL,
    cur_id bigint NOT NULL,
    status character varying(10) DEFAULT 'A'::character varying NOT NULL,
    date_added timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified timestamp without time zone
);
    DROP TABLE public.sf_balances;
       public         heap    postgres    false    4            �            1259    19941    sf_operates    TABLE     I  CREATE TABLE public.sf_operates (
    id bigint NOT NULL,
    debt_acc_id bigint,
    cred_acc_id bigint,
    operate_id bigint NOT NULL,
    amount numeric(25,7) NOT NULL,
    cur_id bigint NOT NULL,
    date_added timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_modified timestamp without time zone
);
    DROP TABLE public.sf_operates;
       public         heap    postgres    false    4            �           0    0    TABLE sf_operates    COMMENT     ;   COMMENT ON TABLE public.sf_operates IS 'ACCOUNT OPERATES';
          public          postgres    false    222                        0    0    COLUMN sf_operates.id    COMMENT     :   COMMENT ON COLUMN public.sf_operates.id IS 'Primary Key';
          public          postgres    false    222            �            1259    18918    sf_sequence    SEQUENCE     t   CREATE SEQUENCE public.sf_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.sf_sequence;
       public          postgres    false    4            �          0    18891    d_currencies 
   TABLE DATA           D   COPY public.d_currencies (id, code, name, amount_scale) FROM stdin;
    public          postgres    false    215   k=       �          0    18894    d_customers 
   TABLE DATA           H   COPY public.d_customers (id, person_id, status, date_added) FROM stdin;
    public          postgres    false    216   �=       �          0    19915 
   d_operates 
   TABLE DATA           4   COPY public.d_operates (id, code, name) FROM stdin;
    public          postgres    false    221   �=       �          0    18899 	   d_persons 
   TABLE DATA           t   COPY public.d_persons (id, first_name, last_name, middle_name, country_iso_code_2, phone_number, email) FROM stdin;
    public          postgres    false    217   ?>       �          0    18905    d_sub_systems 
   TABLE DATA           R   COPY public.d_sub_systems (id, code, name, rec_status, cr_dt, upd_dt) FROM stdin;
    public          postgres    false    218   �>       �          0    18909    sf_balances 
   TABLE DATA           i   COPY public.sf_balances (id, customer_id, amount, cur_id, status, date_added, date_modified) FROM stdin;
    public          postgres    false    219   �>       �          0    19941    sf_operates 
   TABLE DATA           z   COPY public.sf_operates (id, debt_acc_id, cred_acc_id, operate_id, amount, cur_id, date_added, date_modified) FROM stdin;
    public          postgres    false    222   "?                  0    0    sf_sequence    SEQUENCE SET     9   SELECT pg_catalog.setval('public.sf_sequence', 6, true);
          public          postgres    false    220            :           2606    18920    d_currencies d_currencies_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.d_currencies
    ADD CONSTRAINT d_currencies_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.d_currencies DROP CONSTRAINT d_currencies_pkey;
       public            postgres    false    215            <           2606    18922    d_customers d_customers_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.d_customers
    ADD CONSTRAINT d_customers_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.d_customers DROP CONSTRAINT d_customers_pkey;
       public            postgres    false    216            F           2606    19919    d_operates d_operates_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.d_operates
    ADD CONSTRAINT d_operates_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.d_operates DROP CONSTRAINT d_operates_pkey;
       public            postgres    false    221            >           2606    18924    d_persons d_persons_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.d_persons
    ADD CONSTRAINT d_persons_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.d_persons DROP CONSTRAINT d_persons_pkey;
       public            postgres    false    217            B           2606    18926     d_sub_systems d_sub_systems_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.d_sub_systems
    ADD CONSTRAINT d_sub_systems_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.d_sub_systems DROP CONSTRAINT d_sub_systems_pkey;
       public            postgres    false    218            D           2606    18928    sf_balances sf_balances_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.sf_balances
    ADD CONSTRAINT sf_balances_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.sf_balances DROP CONSTRAINT sf_balances_pkey;
       public            postgres    false    219            H           2606    19946    sf_operates sf_operates_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.sf_operates
    ADD CONSTRAINT sf_operates_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.sf_operates DROP CONSTRAINT sf_operates_pkey;
       public            postgres    false    222            @           2606    18932    d_persons sr_person_uq 
   CONSTRAINT     t   ALTER TABLE ONLY public.d_persons
    ADD CONSTRAINT sr_person_uq UNIQUE (country_iso_code_2, phone_number, email);
 @   ALTER TABLE ONLY public.d_persons DROP CONSTRAINT sr_person_uq;
       public            postgres    false    217    217    217            I           2606    18933 $   d_customers fk_d_customers_person_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.d_customers
    ADD CONSTRAINT fk_d_customers_person_id FOREIGN KEY (person_id) REFERENCES public.d_persons(id);
 N   ALTER TABLE ONLY public.d_customers DROP CONSTRAINT fk_d_customers_person_id;
       public          postgres    false    216    217    4670            J           2606    18938     sf_balances fk_sf_balance_cur_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.sf_balances
    ADD CONSTRAINT fk_sf_balance_cur_id FOREIGN KEY (cur_id) REFERENCES public.d_currencies(id);
 J   ALTER TABLE ONLY public.sf_balances DROP CONSTRAINT fk_sf_balance_cur_id;
       public          postgres    false    219    215    4666            K           2606    19962 &   sf_operates fk_sf_operates_cred_acc_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.sf_operates
    ADD CONSTRAINT fk_sf_operates_cred_acc_id FOREIGN KEY (cred_acc_id) REFERENCES public.sf_balances(id);
 P   ALTER TABLE ONLY public.sf_operates DROP CONSTRAINT fk_sf_operates_cred_acc_id;
       public          postgres    false    219    222    4676            L           2606    19947 !   sf_operates fk_sf_operates_cur_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.sf_operates
    ADD CONSTRAINT fk_sf_operates_cur_id FOREIGN KEY (cur_id) REFERENCES public.d_currencies(id);
 K   ALTER TABLE ONLY public.sf_operates DROP CONSTRAINT fk_sf_operates_cur_id;
       public          postgres    false    222    4666    215            M           2606    19957 &   sf_operates fk_sf_operates_debt_acc_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.sf_operates
    ADD CONSTRAINT fk_sf_operates_debt_acc_id FOREIGN KEY (debt_acc_id) REFERENCES public.sf_balances(id);
 P   ALTER TABLE ONLY public.sf_operates DROP CONSTRAINT fk_sf_operates_debt_acc_id;
       public          postgres    false    222    219    4676            N           2606    19952 %   sf_operates fk_sf_operates_operate_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.sf_operates
    ADD CONSTRAINT fk_sf_operates_operate_id FOREIGN KEY (operate_id) REFERENCES public.d_operates(id);
 O   ALTER TABLE ONLY public.sf_operates DROP CONSTRAINT fk_sf_operates_operate_id;
       public          postgres    false    222    221    4678            �   /   x�3�
�*M�I-�4�2�v�t���I,*Vv���qqq �T	�      �   4   x�34�4�t�4202�54�50T04�2 "3=C3K#33.CcN#�*�b���� �]5      �   A   x�3�ttq�tLI�,���K��2�	r�v�)J�+NK-�2�u
	�.M*)JL.����� ��@      �   _   x�3�������M,��t��4T�044�T022�56�51�����:��&f��%��rq:e��p��f�s:A5YZZj* 	��$�t�4��=... ,��      �      x������ � �      �   G   x��̱� D��<E���t���H�)���#(�N�/
e�Z��X1V��Y}�L`�Q�:*�����;      �   t   x�����0Ck�����	��狋4IӁ������*:g���lS|к��.��tgLV9><'� e�mfY��U醂��6�R#�E�z�q�|D���j�~_$>��[�N�     