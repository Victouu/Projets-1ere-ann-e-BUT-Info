drop schema if exists programme_but cascade;
create schema programme_but ;
set schema 'programme_but' ;

CREATE TABLE _competences(
  lib_competence VARCHAR(20) NOT NULL,
  CONSTRAINT competence_pk PRIMARY KEY(lib_competence)
);

CREATE TABLE _activites(
  lib_activite VARCHAR(20) NOT NULL,
  lib_competence VARCHAR(20) NOT NULL,
  CONSTRAINT activite_pk PRIMARY KEY(lib_activite),
  CONSTRAINT releve_de_fk FOREIGN KEY(lib_competence) REFERENCES _competences(lib_competence)
);

CREATE TABLE _niveau(
  numero_n VARCHAR(20) NOT NULL,
  CONSTRAINT niveau_pk PRIMARY KEY(numero_n)
);

CREATE TABLE _semestre(
  numero_sem VARCHAR(20) NOT NULL,
  numero_n VARCHAR(20) NOT NULL,
  CONSTRAINT semestre_pk PRIMARY KEY(numero_sem),
  CONSTRAINT fait_partie_fk FOREIGN KEY(numero_N ) REFERENCES _niveau(numero_N)
);

CREATE TABLE _ue(
  code_ue VARCHAR(20) NOT NULL,
  lib_activite VARCHAR(20) NOT NULL,
  numero_sem VARCHAR(20) NOT NULL,
  CONSTRAINT UE_pk PRIMARY KEY(code_ue),
  CONSTRAINT est_realisee_dans_fk FOREIGN KEY(lib_activite) REFERENCES _activites(lib_activite),
  CONSTRAINT dans_fk FOREIGN KEY(numero_sem ) REFERENCES _semestre(numero_sem )
);

CREATE TABLE _parcours(
  code_p VARCHAR(20) NOT NULL,
  libelle_parcours VARCHAR(20) NOT NULL,
  nbre_gpe_td_p NUMERIC,
  nbre_gpe_tp_p NUMERIC,
  CONSTRAINT parcours_pk PRIMARY KEY(code_p)
);

CREATE TABLE _ressources(
  code_r VARCHAR(20) NOT NULL,
  lib_r VARCHAR(20) NOT NULL,
  nb_h_cm_pn NUMERIC,
  nb_h_td_pn NUMERIC,
  nb_h_tp_pn NUMERIC,
  code_p VARCHAR(20) NOT NULL,
  numero_sem VARCHAR(20) NOT NULL,
  CONSTRAINT ressources_pk PRIMARY KEY(code_r),
  CONSTRAINT est_enseignee_fk FOREIGN KEY(code_p ) REFERENCES _parcours(code_p),
  CONSTRAINT quand_fk FOREIGN KEY(numero_sem ) REFERENCES _semestre(numero_sem )
);

CREATE TABLE _sae(
  code_sae VARCHAR(20) NOT NULL,
  lib_sae NUMERIC,
  nb_h_td_enc NUMERIC,
  nb_h_tp_projet_autonomie NUMERIC,
  CONSTRAINT sae_pk PRIMARY KEY(code_sae )
);

CREATE TABLE _comprend_r(
  code_sae VARCHAR(20) NOT NULL,
  code_r VARCHAR(20) NOT NULL,
  nb_h_td NUMERIC,
  nb_h_tp NUMERIC,
  CONSTRAINT comprend_r_pk PRIMARY KEY(code_sae, code_r),
  CONSTRAINT comprent_r_fk1 FOREIGN KEY(code_sae) REFERENCES _sae(code_sae),
  CONSTRAINT comprent_r_fk2 FOREIGN KEY(code_r) REFERENCES _ressources(code_r )
);

CREATE TABLE _correspond(
  code_p VARCHAR(20) NOT NULL,
  lib_activite VARCHAR(20) NOT NULL,
  numero_n VARCHAR(20) NOT NULL,
  CONSTRAINT correspond_pk PRIMARY KEY(code_p , lib_activite ,numero_n ),
  CONSTRAINT correspond_fk1 FOREIGN KEY(code_p) REFERENCES _parcours(code_p),
  CONSTRAINT correspond_fk2 FOREIGN KEY(lib_activite  ) REFERENCES _activites(lib_activite  ),
  CONSTRAINT correspond_fk3 FOREIGN KEY(numero_n  ) REFERENCES _niveau(numero_n )
);