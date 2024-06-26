chefia(rogerio, [sandro, roberto, micheli]).
chefia(sandro, [mafalda, rebeca]).
chefia(roberto, [osmar]).
chefia(micheli, [wesley, sara]).

chefe_imediato(X, Y) :- chefia(X, Lista), member(Y, Lista).
