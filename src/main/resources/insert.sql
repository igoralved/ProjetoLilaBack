INSERT INTO tb_admin(id, senha) 
VALUES('d1516d33-ff6f-4dc9-aedf-9316421096cb','senha secreta') ON CONFLICT(id) DO NOTHING;
