import time

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
                playArea[house][i]="b"
                playArea[house][n-i-1]="d"
                playArea[i][house]="a"
                playArea[n-i-1][house]="c"
        playArea[house][house]="X"

        return playArea

##########################################################################
area = 9
playArea = genSachovnicu(area)

def tlacSachovnicu(playArea):
##        for line in playArea:
##                print(' '.join(riadok))
        for line in playArea:
                for item in line:
                        print(item, end=' ')
                print()
        print()
                
tlacSachovnicu(playArea)

##########################################################################

def nextpos(playArea, x, y, cislo):
        size = len(playArea)
        house = size // 2
        if y < house:
                if x > 0 and playArea[x-1][y] == '*':
                        return y, x-1
        else:
                if x < size-1 and playArea[x+1][y] == '*':
                        return y, x+1
        if x < house:
                if y < size-1 and playArea[x][y+1] == '*':
                        return y+1, x
        else:
                if y > 0 and playArea[x][y-1] == '*':
                        return y-1, x              
        return x, y

xA = 0
yA = area // 2 #poloha hraca A na zaciatku
houseA = ((area // 2) - 1)
repeat = 1
repeatCounter = 0 #pomocna premenna pri pocitani krokov
while True:
        playArea[xA][yA] = '*'
        while repeatCounter < repeat:
                yA, xA = nextpos(playArea, xA, yA, repeat)
                repeatCounter += 1
        repeatCounter = 0
        playArea[xA][yA] = 'A'
        tlacSachovnicu(playArea)
##      time.sleep(0.5)
        repeat = int(input("Zadaj cislo: "))
























##n = 9
##sachovnice = []
##for i in range(n):
##    radek = []
##    for j in range(n):
##        radek.append("*")
##    sachovnice.append(radek)
##sachovnice[0][0] = "!"
##
##for riadok in sachovnice:
##        for item in riadok:
##                print(item, end=' ')
##        print()
##print(sachovnice)
####
##
##import time
##
