DROP TABLE IF EXISTS jogador_cartaobjetivo CASCADE;
DROP TABLE IF EXISTS jogador_cartadojogo CASCADE;
DROP TABLE IF EXISTS baralho_cartaobjetivo CASCADE;
DROP TABLE IF EXISTS baralho_cartadojogo CASCADE;
DROP TABLE IF EXISTS baralho_cartainicio CASCADE;
DROP TABLE IF EXISTS sala_jogadores CASCADE;
DROP TABLE IF EXISTS admin CASCADE;
DROP TABLE IF EXISTS sala CASCADE; 
DROP TABLE IF EXISTS jogador CASCADE; 
DROP TABLE IF EXISTS carta_inicio CASCADE;  
DROP TABLE IF EXISTS carta_do_jogo CASCADE;
DROP TABLE IF EXISTS carta_objetivo CASCADE;
DROP TABLE IF EXISTS baralho CASCADE;



create table admin (
    id uuid not null, 
    senha varchar(8) not null,
     primary key (id));

create table baralho (
  id uuid not null,
  codigo varchar(20) not null,
  descricao varchar(255) not null, 
  titulo varchar(20) not null, 
  primary key (id)
  );

create table carta_do_jogo (
     id uuid primary key  not null,
     bonus boolean not null, 
     categoria varchar(80) not null,
     fonte varchar(80) not null, 
     pontos int4 not null,
     texto varchar(255) not null, 
     tipo varchar(50) not null, 
     valor_cor_grande int4,
     valor_cor_pequeno int4
             
     );
    
create table carta_inicio (
    id uuid not null, 
    descricao varchar(255) not null,
    nome varchar(50) not null, 
    primary key (id)
    );
    

create table carta_objetivo (
    id uuid not null, 
    categoria varchar(100) not null,
    classificacao varchar(255) not null,
    descricao varchar(255) not null, 
    pontos int4 not null,
    primary key (id)
    );
    
create table baralho_cartadojogo (
   cartadojogo_id uuid not null ,
    baralho_id uuid not null,
 CONSTRAINT FK_cartadojogo_id FOREIGN KEY (cartadojogo_id)
 REFERENCES carta_do_jogo (id),
 CONSTRAINT FK_baralho_id FOREIGN KEY (baralho_id)
 REFERENCES baralho (id));

create table baralho_cartainicio (
    baralho_id uuid not null ,
    cartainicio_id uuid not null,
     CONSTRAINT FK_cartainicio_id FOREIGN KEY (cartainicio_id)
     REFERENCES carta_inicio (id),
     CONSTRAINT FK_baralho_id FOREIGN KEY (baralho_id)
     REFERENCES baralho (id));

create table baralho_cartaobjetivo (
    baralho_id uuid not null,
    cartaobjetivo_id uuid not null,
     CONSTRAINT FK_cartaobjetivo_id FOREIGN KEY (cartaobjetivo_id)
     REFERENCES carta_objetivo (id),
     CONSTRAINT FK_baralho_id FOREIGN KEY (baralho_id)
     REFERENCES baralho (id));
    
    
create table jogador (
     id uuid not null,
     bonus_coracao_gra int4 not null,
     bonus_coracao_peq int4 not null,
     coracao_gra int4 not null, 
     coracao_peq int4 not null,
     nome varchar(30) not null,
     pontos int4 not null,
     status int4 not null,
     is_host boolean not null, 
     primary key (id)
     );

create table jogador_cartadojogo (
  jogador_id uuid not null ,
  cartadojogo_id uuid not null ,
    CONSTRAINT FK_cartadojogo_id FOREIGN KEY (cartadojogo_id)
     REFERENCES carta_do_jogo (id),
     CONSTRAINT FK_jogador_id FOREIGN KEY (jogador_id)
     REFERENCES jogador (id));

create table jogador_cartaobjetivo (
  jogador_id uuid not null ,
  cartaobjtivo_id uuid not null ,
    CONSTRAINT FK_cartaobjtivo_id FOREIGN KEY (cartaobjtivo_id)
     REFERENCES carta_objetivo (id),
     CONSTRAINT FK_jogador_id FOREIGN KEY (jogador_id)
     REFERENCES jogador (id));
    
create table sala (
    id uuid not null, 
    hash varchar(255) not null,
     status int4 not null,
     baralho_id uuid, 
     dado int4 not null,
     primary key (id));

create table sala_jogadores (
    sala_id uuid not null,
    jogadores_id uuid not null);
    





