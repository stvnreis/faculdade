festa(casamento1, igreja, [alvaro, bianca, carlos]).
festa(casamento2, auditorio, [alvaro, carlos, elton]).
festa(formatura, auditorio, [daiane, elton, fabiana]).

pertence(X, [X|_]).
pertence(X, [_|Cauda]) :- pertence(X, Cauda).

participou(Pessoa, Evento, Local) :- 
    festa(Evento, Local, ListaPessoas), 
    pertence(Pessoa, ListaPessoas).