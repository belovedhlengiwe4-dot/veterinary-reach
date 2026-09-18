--
-- PostgreSQL database dump
--

\restrict SggtNAhqpiSK1WT1M2vVlOt3cQdrRukCfC1yyHvamhNfcbAcf3SMAL0lqFrKlg5

-- Dumped from database version 16.15 (Ubuntu 16.15-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 16.15 (Ubuntu 16.15-0ubuntu0.24.04.1)

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
-- Data for Name: municipality; Type: TABLE DATA; Schema: public; Owner: vetreach
--

INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (1, 'Capricorn', -23.2665, 29.1169, 'Blouberg', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (2, 'Capricorn', -23.36789, 29.32768, 'Molemole', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (3, 'Capricorn', -23.912974, 29.457689, 'Polokwane (incl. Lepele-Nkumpi)', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (4, 'Mopani', -23.933, 31.117, 'Ba-Phalaborwa', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (5, 'Mopani', -23.69563, 30.14385, 'Greater Letaba (incl. Giyani)', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (6, 'Mopani', -23.8319, 30.1611, 'Greater Tzaneen', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (7, 'Mopani', -24.3534, 30.9517, 'Maruleng', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (8, 'Waterberg', -23.66607, 27.74477, 'Lephalale', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (9, 'Waterberg', -24.7, 28.4, 'Modimolle/Mookgophong', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (10, 'Waterberg', -24.18389, 29.01278, 'Mogalakwena', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (11, 'Waterberg', -24.58877, 27.41183, 'Thabazimbi', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (12, 'Waterberg', -24.88509, 28.29131, 'Bela-Bela', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (13, 'Sekhukhune', -25.16561, 29.39567, 'Elias Motsoaledi', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (14, 'Sekhukhune', -24.9674, 29.2939, 'Ephraim Mogale', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (15, 'Sekhukhune', -24.6765, 30.3251, 'Greater Tubatse/Fetakgomo', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (16, 'Vhembe', -23.0502, 29.8834, 'Makhado (incl. Collins Chabane and Thulamela)', 'Limpopo');
INSERT INTO public.municipality (municipality_id, district, latitude, longitude, name, province) VALUES (17, 'Vhembe', -22.348805, 30.040741, 'Musina', 'Limpopo');


--
-- Data for Name: health_indicator; Type: TABLE DATA; Schema: public; Owner: vetreach
--

INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (1, 'FMD', 'synthetic_outbreak_reports', 4, 1);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (2, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 2, 1);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (3, 'Brucellosis', 'synthetic_outbreak_reports', 2, 1);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (4, 'FMD', 'synthetic_outbreak_reports', 2, 2);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (5, 'Brucellosis', 'synthetic_outbreak_reports', 3, 2);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (6, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 2, 2);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (7, 'FMD', 'synthetic_outbreak_reports', 3, 3);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (8, 'Brucellosis', 'synthetic_outbreak_reports', 2, 3);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (9, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 3, 3);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (10, 'HPAI H7', 'synthetic_outbreak_reports', 1, 3);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (11, 'FMD', 'synthetic_outbreak_reports', 2, 4);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (12, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 2, 4);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (13, 'Rabies', 'synthetic_outbreak_reports', 1, 4);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (14, 'FMD', 'synthetic_outbreak_reports', 3, 5);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (15, 'Brucellosis', 'synthetic_outbreak_reports', 3, 5);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (16, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 2, 5);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (17, 'FMD', 'synthetic_outbreak_reports', 2, 6);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (18, 'Brucellosis', 'synthetic_outbreak_reports', 3, 6);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (19, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 2, 6);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (20, 'FMD', 'synthetic_outbreak_reports', 1, 7);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (21, 'Rabies', 'synthetic_outbreak_reports', 2, 7);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (22, 'Brucellosis', 'synthetic_outbreak_reports', 1, 7);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (23, 'FMD', 'synthetic_outbreak_reports', 4, 8);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (24, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 3, 8);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (25, 'Brucellosis', 'synthetic_outbreak_reports', 2, 8);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (26, 'FMD', 'synthetic_outbreak_reports', 3, 9);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (27, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 3, 9);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (28, 'HPAI H7', 'synthetic_outbreak_reports', 2, 9);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (29, 'Brucellosis', 'synthetic_outbreak_reports', 2, 9);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (30, 'FMD', 'synthetic_outbreak_reports', 4, 10);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (31, 'Brucellosis', 'synthetic_outbreak_reports', 3, 10);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (32, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 2, 10);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (33, 'HPAI H7', 'synthetic_outbreak_reports', 1, 10);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (34, 'FMD', 'synthetic_outbreak_reports', 3, 11);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (35, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 3, 11);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (36, 'Brucellosis', 'synthetic_outbreak_reports', 2, 11);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (37, 'FMD', 'synthetic_outbreak_reports', 2, 12);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (38, 'Brucellosis', 'synthetic_outbreak_reports', 2, 12);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (39, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 2, 12);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (40, 'HPAI H7', 'synthetic_outbreak_reports', 1, 12);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (41, 'FMD', 'synthetic_outbreak_reports', 2, 13);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (42, 'Brucellosis', 'synthetic_outbreak_reports', 3, 13);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (43, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 2, 13);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (44, 'FMD', 'synthetic_outbreak_reports', 3, 14);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (45, 'Brucellosis', 'synthetic_outbreak_reports', 3, 14);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (46, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 2, 14);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (47, 'Rabies', 'synthetic_outbreak_reports', 1, 14);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (48, 'FMD', 'synthetic_outbreak_reports', 3, 15);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (49, 'Brucellosis', 'synthetic_outbreak_reports', 2, 15);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (50, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 2, 15);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (51, 'FMD', 'synthetic_outbreak_reports', 4, 16);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (52, 'Brucellosis', 'synthetic_outbreak_reports', 3, 16);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (53, 'Rabies', 'synthetic_outbreak_reports', 2, 16);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (54, 'Lumpy Skin Disease', 'synthetic_outbreak_reports', 2, 16);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (55, 'FMD', 'synthetic_outbreak_reports', 3, 17);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (56, 'Rabies', 'synthetic_outbreak_reports', 3, 17);
INSERT INTO public.health_indicator (id, disease, indicator_type, indicator_value, municipality_id) VALUES (57, 'Brucellosis', 'synthetic_outbreak_reports', 1, 17);


--
-- Data for Name: livestock_indicator; Type: TABLE DATA; Schema: public; Owner: vetreach
--

INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (1, 'cattle', 4664, 1);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (2, 'cattle', 13808, 2);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (3, 'cattle', 32353, 3);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (4, 'cattle', 4613, 4);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (5, 'cattle', 4832, 5);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (6, 'cattle', 2857, 6);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (7, 'cattle', 463, 7);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (8, 'cattle', 10629, 13);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (9, 'cattle', 991, 14);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (10, 'cattle', 791, 15);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (11, 'cattle', 18179, 16);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (12, 'cattle', 528, 17);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (13, 'cattle', 9761, 12);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (14, 'cattle', 10329, 8);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (15, 'cattle', 39938, 9);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (16, 'cattle', 6800, 10);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (17, 'cattle', 8815, 11);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (18, 'sheep', 655, 1);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (19, 'sheep', 182, 2);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (20, 'sheep', 902, 3);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (21, 'sheep', 422, 6);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (22, 'sheep', 3100, 7);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (23, 'sheep', 1172, 13);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (24, 'sheep', 212, 14);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (25, 'sheep', 22, 16);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (26, 'sheep', 106, 17);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (27, 'sheep', 1486, 12);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (28, 'sheep', 514, 8);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (29, 'sheep', 2045, 9);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (30, 'sheep', 1861, 10);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (31, 'sheep', 761, 11);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (32, 'goat', 125, 2);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (33, 'goat', 14, 3);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (34, 'goat', 144, 6);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (35, 'goat', 57, 7);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (36, 'goat', 50, 13);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (37, 'goat', 206, 14);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (38, 'goat', 2315, 16);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (39, 'goat', 415, 17);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (40, 'goat', 282, 12);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (41, 'goat', 243, 8);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (42, 'goat', 1295, 9);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (43, 'goat', 75, 10);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (44, 'goat', 229, 11);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (45, 'pig', 15, 2);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (46, 'pig', 24920, 3);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (47, 'pig', 352, 17);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (48, 'pig', 114243, 12);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (49, 'pig', 53662, 9);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (50, 'pig', 4776, 10);
INSERT INTO public.livestock_indicator (id, species, value, municipality_id) VALUES (51, 'pig', 28, 11);


--
-- Data for Name: veterinary_facility; Type: TABLE DATA; Schema: public; Owner: vetreach
--

INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (3, 'Veterinary Clinic', -23.68035573855656, 27.728212706458027, true, 'ELLISRAS DIEREKLINIEK', true, 8);
INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (4, 'Consulting Room', -24.699987424780968, 28.39999915697296, false, 'LIMPOPO STUD GAME CONSULTANTS', true, 9);
INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (5, 'Consulting Room', -24.35631644301016, 30.948358659880107, true, 'LOWVELD WILDLIFE VET', true, 7);
INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (6, 'Consulting Room', -24.7028898, 28.3884125, true, 'VELD EN KRAAL HERD HEALTH PRACTICE', true, 9);
INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (7, 'Herd Health Practice — Production Animals', -23.66605445350917, 27.744772862530468, true, 'TWEERIVIER WILDLIFE SERVICES', true, 8);
INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (8, 'Veterinary Hospital', -22.353172123362263, 30.04167145242815, true, 'SAVUTI VETERINARY SERVICES', true, 17);
INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (9, 'Mixed Veterinary Practice', -22.596670124869323, 29.09672281822964, true, 'KOEDOESRAND DIEREKLINIEK', true, 10);
INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (10, 'Mixed Veterinary Practice', -24.062607792302437, 31.035927473732276, true, 'OLIFANTS VETERINARY SERVICES', true, 4);
INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (11, 'Primary Animal Health Care Facility', -24.69995447757826, 28.400002426356643, true, 'HOOF & HERD PRIMARY ANIMAL HEALTH FACILITY', true, 9);
INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (12, 'Small Animal Clinic', -24.698762, 28.4015577, true, 'KRANSKOP DIEREKLINIEK', true, 9);
INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (13, 'Small Animal Clinic', -22.35367620788509, 30.042611165603493, true, 'LIMPOPO ANIMAL CLINIC', true, 17);
INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (14, 'Small Animal Clinic', -23.6809248, 27.7417442, false, 'MATOPPI DIEREKLINIEK', true, 8);
INSERT INTO public.veterinary_facility (facility_id, facility_type, latitude, longitude, mobile_service, name, production_animal_relevant, municipality_id) VALUES (15, 'Small Animal Clinic', -24.6947428, 28.4123164, true, 'NYLSTROOM DIEREKLINIEK', true, 9);


--
-- Data for Name: travel_cost; Type: TABLE DATA; Schema: public; Owner: vetreach
--



--
-- Name: health_indicator_id_seq; Type: SEQUENCE SET; Schema: public; Owner: vetreach
--

SELECT pg_catalog.setval('public.health_indicator_id_seq', 57, true);


--
-- Name: livestock_indicator_id_seq; Type: SEQUENCE SET; Schema: public; Owner: vetreach
--

SELECT pg_catalog.setval('public.livestock_indicator_id_seq', 51, true);


--
-- Name: municipality_municipality_id_seq; Type: SEQUENCE SET; Schema: public; Owner: vetreach
--

SELECT pg_catalog.setval('public.municipality_municipality_id_seq', 17, true);


--
-- Name: travel_cost_id_seq; Type: SEQUENCE SET; Schema: public; Owner: vetreach
--

SELECT pg_catalog.setval('public.travel_cost_id_seq', 1, false);


--
-- Name: veterinary_facility_facility_id_seq; Type: SEQUENCE SET; Schema: public; Owner: vetreach
--

SELECT pg_catalog.setval('public.veterinary_facility_facility_id_seq', 15, true);


--
-- PostgreSQL database dump complete
--

\unrestrict SggtNAhqpiSK1WT1M2vVlOt3cQdrRukCfC1yyHvamhNfcbAcf3SMAL0lqFrKlg5

