import turtle

from polygon import arc

def petal(t, r, angle): ## funkcia na vykreslenie jedneho lupena pomocou funkcie arcs

    for i in range(2):
        arc(t, r, angle)
        t.lt(180-angle)

def flower(t, n, r, angle): ## funkcia na vykreslenie celeho kvetu
    ## t: korytnacka n: pocet lupenov r: radius oblukov angle: uhol medzi oblukmi

    for i in range(n):
        petal(t, r, angle)
        t.lt(360.0/n)

def move(t, length): ## funkcia na posun korytnacky

    t.pu()
    t.fd(length)
    t.pd()

def leaf(t, r, angle): ## funkcia na vykreslenie listov
    
    for i in range(2):
        petal(t, r, angle)
        t.lt(240.0/2)
        
def stem(t, r, angle): ## funkcia na vykreslenie stonky

    arc(t, r, angle)
    t.lt(180-angle)
    t.seth(0)

bob = turtle.Turtle() ## pomenovanie korytnacky5
bob.speed(0)

## Naskreslenie troch kvetov pomocou funkcii
move(bob, -200)
flower(bob, 7, 60.0, 60.0) ## PRVY

x1 = 240
a1 = 90.0
if a1 == 60.0:
    bob.seth(240)
else:
    x1 = x1 - ((a1-60)/2)
    bob.seth(x1) ## otocenie kor. podla velkosti obluka stonky tak aby isla kolmo dole

stem(bob, 120.0, a1) ## privolanie funkcie stonka
leaf(bob, 60.0, 60.0) ## privolanie funkcie listy
bob.pu()
bob.home()
bob.pd()

flower(bob, 10, 40.0, 80.0) ## DRUHY

x2 = 240
a2 = 60.0
if a2 == 60.0:
    bob.seth(240)
else:
    x2 = x2 - ((a2-60)/2)
    bob.seth(x)

stem(bob, 180.0, a2)
leaf(bob, 90.0, 60.0)
bob.pu()
bob.home()

move(bob, 200)
flower(bob, 20, 140.0, 20.0) ## TRETI

x3 = 240
a3 = 120.0
if a3 == 60.0:
    bob.seth(240)
else:
    x3 = x3 - ((a3-60)/2)
    bob.seth(x3)

stem(bob, 90.0, a3)
leaf(bob, 120.0, 60.0)

bob.hideturtle()
turtle.mainloop()
