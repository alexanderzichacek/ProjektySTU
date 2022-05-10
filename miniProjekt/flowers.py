import turtle
bob = turtle.Turtle()
##print(bob)
##turtle.mainloop()

##bob.fd(100)
##bob.lt(90)
##bob.fd(100)
##bob.lt(90)
##bob.fd(100)
##bob.lt(90)
##bob.fd(100)

bob.speed(10)

##for i in range(4):
##    bob.fd(100)
##    bob.lt(90)

##def polygon(t, n, l):
##    for i in range(n):
##        t.fd(l)
##        t.lt(360/n)
####polygon(bob, 5, 100)
##
##import math
##
##def circle(t, r):
##    circumference = 2 * math.pi * r
##    n = 60
##    l = circumference / n
##    polygon(t, n, l)

##circle(bob,70)

##def arc(t, r, angle):
##    arc_length = 2 * math.pi * r * angle / 360
##    n = int(arc_length / 3) + 1
##    step_length = arc_length / n
##    step_angle = angle / n

##    for i in range(n):
##        t.fd(step_length)
##        t.lt(step_angle)
##    
##arc(bob, 25, 180)

def polyline(t, n, length, angle):
    for i in range(n):
        t.fd(length)
        t.lt(angle)

def polygon(t, n, length):
    angle = 360.0 / n
    polyline(b, 50, 100, 360)

def arc(t, r, angle):
    arc_length = 2 * math.pi * r * angle / 360
    n = int(arc_length / 3) + 1
    step_length = arc_length / n
    step_angle = float(angle) / n
    polyline(bob, 50, step_length, step_angle)

def circle(t, r):
    arc(bob, 50, 360)

circle(bob, 50)
