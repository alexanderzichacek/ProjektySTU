import math
import turtle

def arc(t, r, angle): ## funkcia na vykreslenie obluka
    ## t: korytnacka r: polomer angle: uhol 
    
    arc_length = 2 * math.pi * r * angle / 360 ## obvod 2pi r
    n = int(arc_length / 3) + 1 ## pocet ciar v kruhu
    step_length = arc_length / n ## dlzka jednej ciary kruhu
    step_angle = angle / n ## uhol medzi ciarami

    t.lt(step_angle/2) ## maly krok dolava pred zacatim kreslenia ciary kvoli vacsej presnosti
    for i in range(n):
        t.fd(step_length)
        t.lt(step_angle) ## vykreslenie obluka po ciarach od 0 po n
    t.rt(step_angle/2) ## rovnaky krok s5 kvoli presnosti

def petal(t, r, angle): ## funkcia na vykreslenie jedneho lupena pomocou funkcie arcs

    for i in range(2):
        arc(t, r, angle) ## zavolanie funkcie arc na vykreslenie casti kruhu
        t.lt(180-angle)

def flower(t, n, r, angle): ## funkcia na vykreslenie celeho kvetu
    ## n: pocet lupenov

    for i in range(n):
        petal(t, r, angle)
        t.lt(360.0/n) ## vykreslenie kvetu pomocou for cyklu od 0 po pocet lupenov

def move(t, length): ## funkcia na posun korytnacky

    t.pu() ## pero hore aby nekreslil pri presuvani
    t.fd(length)
    t.pd()

def leaf(t, r, angle): ## funkcia na vykreslenie listov
    
    for i in range(2):
        petal(t, r, angle)
        t.lt((360-(angle * 2))/2) ## vykreslenie dvoch listov pomocou foru od 0 po 2 pomocou funkcie na kreslenie lupenov / akoby kvet z dvomi lupenmi
        
def stem(t, r, angle): ## funkcia na vykreslenie stonky

    arc(t, r, angle)
    t.lt(180-angle)
    t.seth(0)

bob = turtle.Turtle() ## pomenovanie korytnacky
bob.speed(0) ## rychlost korytnacky

## NAKRESLENIE TROCH KVETOV POMOCOU FUNKCII VYSSIE

## PRVY kvet pomocou funkcie flower (kvet)

move(bob, -200)
flower(bob, 7, 60.0, 60.0) ## 7 lupenov, 60 velkost lupena(pocet jeho casti), 60 uhol medzi castami z ktorych je obluk tvoreny

x1 = 240
a1 = 90.0
if a1 == 60.0:
    bob.seth(240)
else:
    x1 = x1 - ((a1-60)/2)
    bob.seth(x1) ## otocenie kor. podla velkosti obluka stonky tak aby isla kolmo dole

stem(bob, 120.0, a1) ## privolanie funkcie stem (stonka); 120 dlzka stonky(pocet jej casti) a1 uhol medzi castami z ktorych je obluk tvoreny
leaf(bob, 60.0, 90.0) ## privolanie funkcie leaf (list); 60 dlzka -||- 90 uhol -||-
bob.pu()
bob.home()
bob.pd()

## DRUHY

flower(bob, 10, 40.0, 80.0)

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

## TRETI

move(bob, 200)
flower(bob, 20, 140.0, 20.0)

x3 = 240
a3 = 120.0
if a3 == 60.0:
    bob.seth(240)
else:
    x3 = x3 - ((a3-60)/2)
    bob.seth(x3)

stem(bob, 90.0, a3)
leaf(bob, 120.0, 30.0)

bob.hideturtle() ## skrytie korytnacky (sipky) ked dokresli aby ju nebolo vidno
##turtle.mainloop() ## funkcia ktora pocka na pouzivatela aby nieco spravil
