INSERT INTO public.baralho(codigo, descricao, titulo)
	VALUES ('Clila' ,'primeiro Baralho', 'baralho01') ON CONFLICT(codigo) DO NOTHING;

INSERT INTO public.carta_do_jogo( bonus, categoria, fonte, texto, tipo, valor_cor_grande, valor_cor_pequeno,pontos)
 Values ('false', 'visual',  'Wikipedia', 'Deficiência visual abrange pessoas cegas e pessoas com visão reduzida', 'Definição', '0', '1','1'),
 ('true', 'visual', ' www.deficienteonline.com.br', 'A deficiência visual pode ser congênita ou adquirida ao longo da vida.', 'Informação', '0','2' ,'1' ),
 ('true', 'visual', 'Wikipedia', 'Para apoio à pessoa com deficiência visual, softwares de leitura de tela  reproduzem a informação mostrada na tela do computador através de áudio.', 'Informação','0', '2', '1' ),
 ('false', 'Visual','Wikipedia', 'Andrea Bocelli, tenor, nasceu com glaucoma congênito e ficou cego aos 12 anos.', 'Pessoa', '1', '0', '1'),
 ('true', 'Visual','www.bengalalegal.com', 'Para ajudar uma pessoa cega a sentar-se, você deve guiá-la até a cadeira e colocar a mão dela sobre o encosto, informando se esta tem braço ou não.', 'Ação','1','1', '2'),
 ('true', 'Visual', 'www.passeidireto.com','Sempre que se afastar, avise a pessoa cega, pois ela pode não perceber a sua saída.', 'Ação', '0', '3', '2'),
 ('true', 'Visual','www.bengalalegal.com', 'Nunca puxe a pessoa cega ou pegue o seu braço. Caso ela queira ser guiada, dê o seu ombro ou cotovelo dobrado para apoio.', 'Ação', '2', '1', '3'),
 ('true', 'Visual', ' www.deficienteonline.com.br', 'Para facilitar a identificação do percurso para pessoa com deficiência visual, utilize faixas no piso, com textura e cor diferenciadas.', 'Ação', '1', '2', '3'),
 ('false', 'Visual', 'IMDB','Ray (2004) - A história do gênio musical Ray Charles, deficiente visual desde a infância.', 'Filme','0','1', '1'),
 ('false', 'Auditiva', ' novaescola.org.br', 'Deficiência auditiva é a perda parcial ou total da audição, causada por malformação (causa genética) ou lesões no aparelho auditivo.', 'Definição', '0', '1', '1'),
 ('true', 'Auditiva', ' Wikipedia', 'O termo correto é "Surdo" e não "Surdo-mudo". A mudez é uma outra deficiência.', 'Informação', '0', '2', '1' ),
 ('true', 'Auditiva', 'acessibilidadenapratica.blogspot.com.br', 'Existem diferentes línguas de sinais, por exemplo, LIBRAS no Brasil e ASL nos EUA. Porém, pessoas que tenham perdido a audição aos poucos, podem conhecer apenas português.', 'Informação', '0', '2', '1'),
 ('false', 'Auditiva', ' emais.estadao.com.br', 'Millie Bobby Brown, atriz de Stranger Things, é parcialmente surda.', 'Pessoa', '0', '1', '1'),
 ('true', 'Auditiva', 'www.portaldosurdo.com.br', 'Enquanto estiver conversando com a pessoa surda, mantenha sempre contato visual. Se você desviar o olhar, ela pode achar que a conversa terminou.', 'Ação', '1', '1', '2'),
 ('true', 'Auditiva', ' www.portaldosurdo.com.br', 'Sempre use um tom normal de voz com a pessoa com deficiência auditiva, a não ser que peçam para falar mais alto. Gritar nunca adianta.', 'Ação', '0', '3', '2'),
 ('true', 'Auditiva', ' www.deficienteonline.com.br', 'Identifique os sinais sonoros existentes no ambiente de trabalho (como alarmes de incêndio), para que sejam acompanhados por sinais luminosos.', 'Ação', '2','1', '3'),
 ('true', 'Auditiva', 'acessibilidadenapratica.blogspot.com.br', 'Realize capacitação em LIBRAS e utilize legendas para incluir, respectivamente, surdos falantes de língua de sinais e de português.', 'Ação', '1', '2', '3'),
 ('false', 'Auditiva', 'IMDB', 'Filhos do Silêncio (1986) - Um professor de uma escola para deficientes passa a ter em sua classe uma moça surda que vive isolada e se recusa a seguir os métodos próprios para o ensino.', 'Filme', '0', '1', '1'),
 ('false', 'Física', ' www.deficienteonline.com.br', 'Deficiência física é a alteração completa ou parcial de um ou mais segmentos do corpo humano, acarretando o comprometimento da função física.', 'Definição', '0','1', '1' ),
 ('true', 'Física', ' www.deficienteonline.com.br', 'O banheiro adaptado para pessoas com deficiência física já é uma determinação prevista em lei.', 'Informação', '0', '2', '1'),
 ('true', 'Física', ' www.deficienteonline.com.br','A possibilidade de ocorrer LER/DORT para uma pessoa com deficiência física de membro superior é a mesma que para uma pessoa sem deficiência. ', 'Informação', '0', '2', '1'),
 ('false', 'Física', ' Wikipedia', 'Jillian Mercado é uma modelo americana com distrofia muscular, que se interessou pelo mundo da moda devido à sua mãe, costureira, e seu pai, vendedor de calçados. ', 'Pessoa', '0','1', '1'),
 ('true', 'Física', 'www.bengalalegal.com','Mantenha as muletas ou bengalas sempre próximas à pessoa com deficiência física.', 'Ação', '1', '1', '2'),
 ('true', 'Física', ' www.deficienteonline.com.br', 'Nunca movimente a cadeira de rodas sem antes pedir permissão para a pessoa.', 'Ação', '0','3', '2'),
 ('true', 'Física', ' www.deficienteonline.com.br', 'Esteja atento para as barreiras de acesso dos locais que queira visitar com uma pessoa com deficiência física.', 'Ação', '2', '1', '3'),
 ('true', 'Física', ' blog.isocial.com.br', 'Instale bebedouros com uma altura adequada a cadeirantes.', 'Ação', '1', '2', '3'),
 ('false', 'Física', 'IMDB', 'Intocáveis (2012) - Um milionário tetraplégico  contrata um homem da periferia para ser o seu acompanhante, apesar de sua aparente falta de preparo.','Filme','0','2', '1'),
 ('false', 'Intelectual', ' www.deficienteonline.com.br', 'Deficiência intelectual é classificada como um  conjunto de problemas que afeta o intelecto de um indivíduo, podendo causar dificuldade de aprendizagem.', 'Definição', '0','1', '1'),
 ('true', 'Intelectual', ' www.deficienteonline.com.br', 'A deficiência intelectual pode ser conseqüência de uma doença, mas ela não é uma doença; é uma condição.', 'Informação', '0', '2', '1'),
 ('true', 'Intelectual', ' www.deficienteonline.com.br', 'A deficiência intelectual inclui limitações em diversas áreas, e cada uma delas pode ser compensada para promover a produtividade.', 'Informação','0', '2', '1'),
 ('false', 'Intelectual', ' www.movimentodown.org.br', 'Fernanda Honorato é a primeira repórter com Síndrome de Down do país.', 'Pessoa', '1', '0', '1'),
 ('true', 'Intelectual', ' www2.camara.leg.br', 'A pessoa com deficiência intelectual deve fazer sozinha tudo o que puder, e devemos ajudá-la somente se for necessário.', 'Ação', '1', '1', '2'),
 ('true', 'Intelectual', ' www2.camara.leg.br', 'Ao se dirigir a uma pessoa com deficiência intelectual, você deve agir naturalmente.', 'Ação', '0', '3', '2'),
 ('true', 'Intelectual', ' www.movimentodown.org.br', 'Simplifique linguagem de materiais e textos para incluir, por exemplo, pessoas com Síndrome de Down.', 'Ação', '2', '1', '3'),
 ('true', 'Intelectual', ' Programa Somar do Citibank', 'Os profissionais com deficiência intelectual devem ser designados a um tutor, que acompanhará e será mentor daquele colaborador.', 'Ação', '1', '2', '3'),
 ('false', 'Intelectual', 'IMDB', 'Uma lição de amor (2001) - Um pai com deficiência intelectual que toma conta de sua filha com a ajuda de um grupo de amigos.', 'Filme', '0', '1', '1'),
 ('false', 'TRANSTORNO DO ESPECTRO AUTISTA (TEA)', ' autismo.institutopensi.org.br', 'Transtorno do Espectro Autista (TEA) é uma condição do desenvolvimento neurológico, caracterizado por alteração da comunicação, interação social e presença de comportamentos repetitivos e estereotipados.', 'Definição', '0', '1', '1'),
 ('true', 'TRANSTORNO DO ESPECTRO AUTISTA (TEA)', 'gauchazh.clicrbs.com.br', 'O autismo é um espectro amplo, desde níveis mais leves até níveis mais graves, em que a fala e a motricidade também podem ser afetadas.', 'Informação', '0', '2', '1' ),
 ('true', 'TRANSTORNO DO ESPECTRO AUTISTA (TEA)', 'gauchazh.clicrbs.com.br', 'O autismo é diagnosticado por observação de conduta, por mais de um profissional, como psicólogos, psiquiatras e neurologistas.','Informação', '0', '2', '1' ),
 ('false', 'TRANSTORNO DO ESPECTRO AUTISTA (TEA)', ' YouTube', 'A atriz e ativista Daryl Hannah é autista, tendo recebido o diagnóstico ainda criança.', 'Pessoa', '1', '0', '1'),
 ('true', 'TRANSTORNO DO ESPECTRO AUTISTA (TEA)', ' www.autism.org.uk', 'Em situações de estresse, assegure a pessoa com autismo que ela está indo bem e dê exemplos concretos de como prosseguir.', 'Ação', '1', '1', '2'),
 ('true', 'TRANSTORNO DO ESPECTRO AUTISTA (TEA)', ' istoe.com.br', 'Monte rotinas bem estruturadas para a pessoa com autismo, com instruções concisas e específicas.', 'Ação', '0', '3', '2'),
 ('true', 'TRANSTORNO DO ESPECTRO AUTISTA (TEA)', 'www.psychologytoday.com', 'Priorize a comunicação escrita para pessoas com autismo, pois é mais estruturada, reduz a demanda sensorial e permite maior tempo de resposta.', 'Ação', '2', '1', '3'),
 ('true', 'TRANSTORNO DO ESPECTRO AUTISTA (TEA)','www.abc.net.au', 'Crie espaços onde a pessoa com autismo possa ficar em silêncio e com iluminação diminuída quando estiver sobrecarregada pelo ambiente.', 'Ação', '1', '2', '3'),
 ('true', 'TRANSTORNO DO ESPECTRO AUTISTA (TEA)', 'IMDB','Temple Grandin (2010) Baseado no livro “Uma menina estranha”, da própria Temple, uma mulher com autismo que se tornou uma das maiores especialistas do mundo em manejo de gado.', 'Filme', '0', '1', '1' ),
 ('false', 'TRANSTORNO DO ESPECTRO AUTISTA (TEA)', ' Wikipedia', 'Deficiência é o termo usado para definir a ausência ou a disfunção de uma estrutura psíquica, fisiológica ou anatômica.', 'Informação', '0', '2', '1'),
 ('false', 'Genérica', 'novaescola.org.br', 'Síndrome é um conjunto de sintomas ou sinais que,  juntos, evidenciam uma condição particular.', 'Informação', '0', '2', '1'),
 ('false', 'Genérica', ' www.assistiva.com.br', 'As tecnologias assistivas têm como objetivo proporcionar maior qualidade de vida, independência e inclusão social, através da ampliação da comunicação e mobilidade, por exemplo.', 'Informação', '0', '2', '1' ),
 ('false', 'Genérica', ' www.deficienteonline.com.br', 'Empresa com 100 ou mais funcionários está obrigada a preencher 2-5% dos seus cargos com pessoas com deficiência.', 'Informação', '1', '0', '1'),
 ('false', 'Genérica', ' Wikipedia', 'Capacitismo é o termo designado para definir quando há discriminação e preconceito contra pessoas com qualquer tipo de deficiência. ', 'Informação', '1', '0', '1'),
 ('false', 'Genérica', ' Programa de Inclusão DBServer', 'Realize palestras de sensibilização e treinamentos.','Ação', '0', '3', '2'),
 ('false', 'Genérica','Joyce Cristina Souza Bastos (TCC)', 'Para ter um programa de inclusão efetiva, incentive conhecimento de novos horizontes profissionais e possibilidades de progressão. ','Ação', '0', '3', '2'),
 ('false', 'Genérica','Joyce Cristina Souza Bastos (TCC)', 'Promova a interação e convivência de pessoas com e sem deficiência.', 'Ação', '1', '2', '3'),
 ('false', 'Genérica', ' Programa de Inclusão DBServer', 'Antes da contratação, percorra as dependências da empresa com a pessoa para identificar pontos de dificuldades de acesso.', 'Ação', '2', '1', '3');


 INSERT INTO public.carta_objetivo( categoria, classificacao, descricao, pontos)
	VALUES ( 'FISICA', 'Ganhe 3 pontos se você tiver a maior quantidade de cartas da categoria Física ao final da partida','Um dos seus
funcionários passou a usar cadeira de rodas.','3'),

( 'FISICA ', 'Ganhe 1 ponto por cada carta que você tiver da categoria Física ao final da partida', 'Uma das suas amigas quebrouo braço.', '1'),

( 'INTELECTUAL', 'Ganhe 3 pontos se você tiver a maior quantidade de cartas da categoria Intelectual ao final da partida', 'Você é o mentor do novo
funcionário com deficiência intelectual.', '3'),

( 'INTELECTUAL', 'Ganhe 1 ponto por cada carta que você tiver da categoria Intelectual ao final da partida', 'Sua avó está com dificuldades para se lembrar das coisas.', '1'),

( 'VISUAL', 'Ganhe 3 pontos se você tiver a maior quantidade de cartas da categoria Visual ao final da partida', 'Sua visão noturna diminuiu
recentemente.', '3'),

( 'VISUAL', 'Ganhe 1 ponto por cada carta que você tiver da categoria Visual ao final da partida', 'Seu grau de óculos aumentou muito.', '1'),

( 'TRANSTORNO DO ESPECTRO AUTISTA (TEA)', 'Ganhe 3 pontos se você tiver a maior quantidade de cartas da categoria Transtorno do Espectro Autista (TEA) 
ao final da partida
', 'Você é o mentor do novo funcionário com autismo.', '3'),

( 'TRANSTORNO DO ESPECTRO AUTISTA (TEA)', 'Ganhe 1 ponto por cada carta que você tiver da categoria Transtorno do Espectro Autista (TEA) ao final da partida', 'Seu ambiente de trabalho tem muitas distrações.', '1'),

( 'AUDITIVA', 'Ganhe 3 pontos se você tiver a maior quantidade de cartas da categoria Auditiva ao final da partida', 'Seu pai já tem certa idade e está perdendo a audição', '3'),

( 'AUDITIVA','Ganhe 1 ponto por cada carta que você tiver da categoria Auditiva ao final da partida','Seu ambiente de trabalho é muito barulhento.', '1'),

( 'CATEGORIA AO FINAL DA PARTIDA', 'Ganhe 3 pontos se você tiver a maior variedade de categorias ao final da partida', 'Seu filho pequeno está prendendo a respeitar os coleguinhas.', '3'),

( 'CATEGORIA AO FINAL DA PARTIDA','Ganhe 1 ponto por cada categoria que você tiver ao final da partida', 'Sua empresa passou a ter mais de 100 
funcionários.', '1'),
( 'CARTA DE FILME', 'Ganhe 2 pontos se você tiver alguma carta de filme ao final da partida', 'Sua sobrinha adolescente se identifica com personagens.', '2'),

( 'CARTA DE INFORMAÇÃO', 'Ganhe 2 pontos se você tiver alguma carta de informação ao final da partida', 'Você é responsável pelo newsletter da empresa', '2');

INSERT INTO public.carta_inicio(
	 descricao, nome)
	VALUES ('Inicia o jogo quem tiver usado um recurso de acessibilidade mais recentemente', 'RECURSO DE ACESSIBILIDADE'),

('Inicia o jogo quem tiver quebrado um membro mais recentemente','MEMBRO QUEBRADO'),

('Inicia o jogo quem soletrar seu nome em Libras mais rápido', 'NOME EM LIBRAS'),

('Inicia o jogo quem participou de uma palestra / capacitação
sobre inclusão mais recentemente', 'PALESTRA OU CAPACITAÇÃO'),

('Inicia o jogo quem citar uma série / filme com um personagem com
deficiência primeiro', 'FILME OU SÉRIE'),

('Inicia o jogo quem começou a usar óculos mais cedo que os demais', 'USO DE ÓCULOS'),

('Inicia o jogo quem conversou com uma pessoa com deficiência
mais recentemente', 'CONVERSA COM DEFICIENTE RECENTE'),

('Inicia o jogo quem conversou mais recentemente com uma pessoa com deficiência, sobre algo SEM relação com deficiência', 'CONVERSA COM DEFICIENTE')
;