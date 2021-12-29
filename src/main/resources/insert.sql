INSERT INTO admin(senha) VALUES('123')ON CONFLICT(id) DO NOTHING;
INSERT INTO admin(senha) VALUES('456')ON CONFLICT(id) DO NOTHING;

INSERT INTO public.baralho(id_codigo, descricao, titulo)
	VALUES ('lila1', 'primeiro Baralho', 'baralho01') ON CONFLICT(id_codigo) DO NOTHING;

INSERT INTO public.categoria_de_carta(nome)
	VALUES ('visual') ON CONFLICT(id) DO NOTHING;

INSERT INTO public.tipo_carta(nome , bonus)
	VALUES ('pessoa' , true) ON CONFLICT(id) DO NOTHING;