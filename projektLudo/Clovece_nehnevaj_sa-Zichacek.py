from random import randint
import sys
import time

def genSachovnicu(n): ## funkcia na generovanie hracej plochy
        playArea = [[" " for i in range(n) ] for j in range(n)]
        house = n // 2
        houseRange = 4 + (n - 9) // 2
        
        for i in range(n):
                playArea[house-1][i] = "*"
                playArea[house+1][i] = "*"
                
                playArea[i][house-1] = "*"
                playArea[i][house+1] = "*"
                
        playArea[house][0] = "*"
        playArea[house][n-1] = "*"
        playArea[0][house] = "*"
        playArea[n-1][house] = "*"
        
        for i in range(1,houseRange):
                playArea[house][i] = "d"
                playArea[house][n-i-1] = "c"
                playArea[i][house] = "a"
                playArea[n-i-1][house] = "b"
                
        playArea[house][house] = "X"

        return playArea

##########################################################################
    
area = 13 ## rozloha hracej plochy napr area = 13 je plocha 13x13
playArea = genSachovnicu(area)

def tlacSachovnicu(playArea): ## funkcia na vypisanie hracej plochy
        for line in playArea:
                for item in line:
                        print(item, end=' ')
                print()
        print()
                
##########################################################################################################################

def nextPosition(playArea, x, y): ## funkcia na posun panacika po hracej ploche
        size = len(playArea)
        house = size // 2
        
        if y < house:
                if (x > 0) and ((playArea[x-1][y] == '*') or (playArea[x-1][y] == 'A') or (playArea[x-1][y] == 'B')):
                        return y, x-1
        else:
                if (x < size-1) and ((playArea[x+1][y] == '*') or (playArea[x+1][y] == 'A') or (playArea[x+1][y] == 'B')):
                        return y, x+1
                
        if x < house:
                if (y < size-1) and ((playArea[x][y+1] == '*') or (playArea[x][y+1] == 'A') or (playArea[x][y+1] == 'B')):
                        return y+1, x
        else:
                if (y > 0) and ((playArea[x][y-1] == '*') or (playArea[x][y-1] == 'A') or (playArea[x][y-1] == 'B')):
                        return y-1, x
                
        return x, y

##########################################################################################################################

xA = 0
yA = (area // 2) + 1 ## poloha hraca A na zaciatku
houseA = area // 2

xB = area - 1
yB = (area // 2) - 1 ## poloha hraca B na zaciatku
houseB = area // 2

repeatA = 0
repeatB = 0

repeatCounterA = 0 ## pomocna premenna pri pocitani krokov
repeatCounterB = 0

playArea[xA][yA] = 'A'
playArea[xB][yB] = 'B'
tlacSachovnicu(playArea)
time.sleep(0.9)

trueFalse = True

while trueFalse == True:
        playArea[xA][yA] = '*'  

        if trueFalse == True:
            repeatA = randint(1, 6)
            print("Hrac A hodil: ", repeatA)
            print()

##        if repeatA == 6:
##                print("Hrac A hadze znova")
##                repeatA = repeatA + randint(1, 6)
##                print("Hrac A dokopy hodil: ", repeatA)
##                if repeatA == 12:
##                        repeatA = repeatA + randint(1, 6)
##                        print("Hrac A dokopy hodil: ", repeatA)
##                        if repeatA == 18:
##                                repeatA = 12
##                                print("Hrac A hodil 3x 6 posuva sa o ", repeatA) ## ak hrac hodil 6 hadze este raz, ak hodil 6 3x po sebe pocitaju sa len dve
##                                if yA == houseA and xA >= 0 and xA < houseA:
##                                        repeatA = 6
            
        while repeatCounterA < repeatA:
                if yA == houseA and xA >= 0 and xA < houseA:
                    xA = xA + 1
                    yA = area // 2
                    if xA > houseA - 1:
                        xA = xA - repeatA
                        trueFalse = True
                    elif xA == houseA - 1:
                        trueFalse = False
                        print()
                    playArea[xA-1][yA] = 'a'
                    playArea[0][houseA] = '*'
                    playArea[area-1][houseA] = '*'
                else:
                    yA, xA = nextPosition(playArea, xA, yA)
                repeatCounterA += 1
         
        repeatCounterA = 0

        if playArea[xA][yA] == 'B':
                xB = area - 1
                yB = (area // 2) - 1
                repeatB = 0
                playArea[xB][yB] = 'B'
                playArea[xA][yA] = 'A'
        else:
                playArea[xA][yA] = 'A'
                
        tlacSachovnicu(playArea)
        time.sleep(1)

        playArea[xB][yB] = '*'
        
        if trueFalse == True:
            repeatB = randint(1, 6)
            print("Hrac B hodil: ", repeatB)
            print()

##        if repeatB == 6:
##                print("Hrac B hadze znova")
##                repeatB = repeatB + randint(1, 6)
##                print("Hrac B dokopy hodil: ", repeatB)
##                if repeatB == 12:
##                        repeatB = repeatB + randint(1, 6)
##                        print("Hrac B dokopy hodil: ", repeatB)
##                        if repeatB == 18:
##                                repeatB = 12
##                                print("Hrac B hodil 3x 6 posuva sa o ", repeatB) ## ak hrac hodil 6 hadze este raz, ak hodil 6 3x po sebe pocitaju sa len dve
##                                if yB == houseB and xB <= area and xB > houseB:
##                                        repeatB = 6
        
        while repeatCounterB < repeatB:
                if yB == houseB and xB <= area and xB > houseB:
                    xB = xB - 1
                    yB = area // 2
                    if xB < houseB - 1:
                        xB = xB - repeatB
                        trueFalse = True
                    elif xB == houseB + 1:
                        trueFalse = False
                        print()
                    playArea[xB+1][yB] = 'b'
                    playArea[0][houseB] = '*'
                    playArea[area-1][houseB] = '*'
                else:
                    yB, xB = nextPosition(playArea, xB, yB)
                repeatCounterB += 1
                
        repeatCounterB = 0
        
        if playArea[xB][yB] == 'A':
                xA = 0
                yA = (area // 2) + 1
                repeatA = 0
                playArea[xA][yA] = 'A'
                playArea[xB][yB] = 'B'
        else:
                playArea[xB][yB] = 'B'
                
        tlacSachovnicu(playArea)
        time.sleep(1)


if playArea[houseA - 1][houseA] == 'A':
        print("Hrac A vyhral")
if playArea[houseB + 1][houseB] == 'B':
        print("Hrac B vyhral")

## funkcia pre opakovane hodenie bohuzial nefunguje dobre v domceku preto som ju zakomentoval no po hracej ploche funguje
## panacikovia sa vedia navzajom aj vyhodit
