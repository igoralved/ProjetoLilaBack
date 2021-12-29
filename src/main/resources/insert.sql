INSERT INTO admin(senha) VALUES('123');
INSERT INTO admin(senha) VALUES('456');

INSERT INTO public.baralho(id_codigo, descricao, titulo)
	VALUES ("lila1", "primeiro Baralho", "baralho01");

INSERT INTO public.categoria_de_carta(nome)
	VALUES ("visual");

INSERT INTO public.tipo_carta(nome , bonus)
	VALUES ("pessoa" , true);