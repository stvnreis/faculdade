gerou(abraham, herb).
gerou(abraham, homer).
gerou(mona, homer).
gerou(clancy, marge).
gerou(jackie, marge).
gerou(clancy, patty).
gerou(jackie, patty).
gerou(clancy, selma).
gerou(jackie, selma).
gerou(homer, bart).
gerou(marge, bart).
gerou(homer, lisa).
gerou(marge, lisa).
gerou(homer, maggie).
gerou(marge, maggie).
gerou(selma, ling).

mulher(mona).
mulher(jackie).
mulher(marge).
mulher(patty).
mulher(selma).
mulher(ling).

homem(X) :- \+ mulher(X).

pai(Pai, Filho) :- homem(Pai), gerou(Pai, Filho).
mãe(Mãe, Filho) :- mulher(Mãe), gerou(Mãe, Filho).
                               
sogrosDoMarido(Marido, Mulher,Sogro) :- pai(Marido, Filho), mãe(Mulher, Filho), gerou(Sogro,Mulher).