chefe_de(rogerio, sandro).
chefe_de(rogerio, roberto).
chefe_de(rogerio, micheli).
chefe_de(sandro, mafalda).
chefe_de(sandro, rebeca).
chefe_de(roberto, osmar).
chefe_de(micheli, wesley).
chefe_de(micheli, sara).

chefiado_por(Funcionario, Responsavel) :- chefe_de(Responsavel, Funcionario).
chefiado_por(Funcionario, Responsavel) :- 
    chefe_de(Responsavel, FuncionarioRetorno),
    chefiado_por(Funcionario, FuncionarioRetorno).
