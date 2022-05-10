import math
import turtle
bob = turtle.Turtle() 
def polyline(t, n, length, angle): ## funkcia na vykreslenie obluku
    ## t: korytnacka n: pocet ciar v obluku length: dlzka ciar angle: uhol medzi ciarami
    
    for i in range(n):
        t.fd(length)
        t.lt(angle)

def arc(t, r, angle):
    arc_length = 2 * math.pi * r * angle / 360
    n = int(arc_length / 3) + 1
    step_length = arc_length / n
    step_angle = angle / n
    for i in range(n):
        t.fd(step_length)
        t.lt(step_angle)

arc(bob, 150, 60)
