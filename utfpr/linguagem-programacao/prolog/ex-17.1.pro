antes_de(pedro, julio).
antes_de(julio, neymar).
antes_de(neymar, tulio).
antes_de(tulio, alex).
antes_de(alex, lucas).

depois_de(X, Y) :- antes_de(Y, X).

esta_entre(Anterior, Meio, Posterior) :- antes_de(Meio, Anterior), depois_de(Meio, Posterior).
