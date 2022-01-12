<<<<<<< HEAD
INSERT INTO admin(id, senha)
VALUES('d1516d33-ff6f-4dc9-aedf-9316421096cb','Lil@123') ON CONFLICT(id) DO NOTHING;
=======
<<<<<<< HEAD
INSERT INTO admin(senha) VALUES('123')ON CONFLICT(id) DO NOTHING;
INSERT INTO admin(senha) VALUES('456')ON CONFLICT(id) DO NOTHING;
>>>>>>> origin/US056-Carregarosdados

INSERT INTO public.baralho(id_codigo, descricao, titulo)
	VALUES ('lila1' , 'primeiro Baralho', 'baralho01') ON CONFLICT(id_codigo) DO NOTHING;

INSERT INTO public.categoria_de_carta(
	id,nome)
	VALUES ('fd7b6723-77e2-4846-bd22-88df15ca150a','visual') ON CONFLICT(id) DO NOTHING;
	
<<<<<<< HEAD
INSERT INTO public.tipo_carta(
	id, bonus, nome)
	VALUES ('fd7b6723-77e2-4846-bd22-88df15ca150a', 'true', 'pessoa') ON CONFLICT(id) DO NOTHING;
=======
	INSERT INTO public.tipo_carta(
	id, bonus, nome)
	VALUES ('fd7b6723-77e2-4846-bd22-88df15ca150a', 'true', 'pessoa');
=======
INSERT INTO tb_admin(senha) VALUES('123');
INSERT INTO tb_admin(senha) VALUES('456');
>>>>>>> origin/US004modeldobaralho
>>>>>>> origin/US056-Carregarosdados
