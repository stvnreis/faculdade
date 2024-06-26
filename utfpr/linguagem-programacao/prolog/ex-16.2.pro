pessoa(andre, 33).
pessoa(cris, 26).
pessoa(juca, 57).
maisvelho(X,Y) :- pessoa(X,IdadeX), pessoa(Y,IdadeY), IdadeX > IdadeY.