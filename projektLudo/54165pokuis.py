from random import randint
import sys

def genSachovnicu(n):
        playArea = [[" " for i in range(n) ] for j in range(n)]
        house = n//2
        houseRange = 4 + (n-9)//2
        for i in range(n):
                playArea[house-1][i]="*"
                playArea[house+1][i]="*"
                
                playArea[i][house-1]="*"
                playArea[i][house+1]="*"
        playArea[house][0]="*"
        playArea[house][n-1]="*"
        
        playArea[0][house]="*"
        playArea[n-1][house]="*"
        for i in range(1,houseRange):
                playArea[house][i]="d"
                playArea[house][n-i-1]="c"
                playArea[i][house]="a"
                playArea[n-i-1][house]="b"
        playArea[house][house]="X"

        return playArea

##########################################################################
    
area = 9
playArea = genSachovnicu(area)

def tlacSachovnicu(playArea):
        for line in playArea:
                for item in line:
                        print(item, end=' ')
                print()
        print()
                
##########################################################################################################################

def nextPosition(playArea, x, y):
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
yA = (area // 2) + 1 #poloha hraca A na zaciatku
houseA = area // 2

xB = 8
yB = (area // 2) - 1
houseB = area // 2

repeatA = 0
repeatB = 0

repeatCounterA = 0 #pomocna premenna pri pocitani krokov
repeatCounterB = 0

playArea[xA][yA] = 'A'
playArea[xB][yB] = 'B'
tlacSachovnicu(playArea)

trueFalse = True

while trueFalse == True:
        playArea[xA][yA] = '*'  

        if trueFalse == True:
            repeatA = int(input("Zadaj cislo A: "))
            print()
            
        while repeatCounterA < repeatA:
                if yA == houseA and xA >= 0 and xA < houseA:
                    xA = xA + 1
                    if xA > houseA - 1:
                        xA = xA - repeatA
                        trueFalse = True
                    elif xA == houseA - 1:
                        trueFalse = False
                        print("Hrac A vyhral")
                    playArea[xA-1][yA] = 'a'
                    playArea[0][houseA] = '*'
                    playArea[area-1][houseA] = '*'
                else:
                    yA, xA = nextPosition(playArea, xA, yA)
                repeatCounterA += 1
         
        repeatCounterA = 0

        if playArea[xA][yA] == 'B':
                xB = 8
                yB = (area // 2) - 1
                repeatB = 0
                playArea[xB][yB] = 'B'
                playArea[xA][yA] = 'A'
        else:
                playArea[xA][yA] = 'A'
        tlacSachovnicu(playArea)

        if trueFalse == True:
            repeatB = int(input("Zadaj cislo B: "))
            print()
        
        playArea[xB][yB] = '*'
        while repeatCounterB < repeatB:
                if yB == houseB and xB > houseB and xB <= 9:
                    xB = xB - 1
                    if xB < houseB + 1:
                        xB = xB + repeatB
                        trueFalse = True
                    elif xB == houseB + 1:
                        trueFalse = False
                        print("Hrac B vyhral")
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
##        if trueFalse == True:
##            repeat = randint(1, 6)
##            print("Hrac A hodil: ", repeat)

if playArea[houseA - 1][houseA] == 'A':
        print()
if playArea[houseB + 1][houseB] == 'B':
        print()
