INSERT INTO admin(id, senha)
VALUES('d1516d33-ff6f-4dc9-aedf-9316421096cb','Lil@123') ON CONFLICT(id) DO NOTHING;

INSERT INTO public.baralho(codigo, descricao, titulo)
	VALUES ('lila1' , 'primeiro Baralho', 'baralho01') ON CONFLICT(id_codigo) DO NOTHING;

INSERT INTO public.categoria_de_carta(
	id,nome)
	VALUES ('fd7b6723-77e2-4846-bd22-88df15ca150a','visual') ON CONFLICT(id) DO NOTHING;
	
INSERT INTO public.tipo_carta(
	id, bonus, nome)
	VALUES ('fd7b6723-77e2-4846-bd22-88df15ca150a', 'true', 'pessoa') ON CONFLICT(id) DO NOTHING;