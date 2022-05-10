import time


def genSachovnicu(n):
	sachovnica = [[" " for i in range(n) ] for j in range(n)]
	stred = n//2
	houseRange = 4 + (n-9)//2
	for i in range(n):
		sachovnica[stred-1][i]="*";sachovnica[stred+1][i]="*"
		sachovnica[i][stred-1]="*";sachovnica[i][stred+1]="*"
	sachovnica[stred][0]="*";sachovnica[stred][n-1]="*"
	sachovnica[0][stred]="*";sachovnica[n-1][stred]="*"
	for i in range(1,houseRange):
		sachovnica[stred][i]="b";sachovnica[stred][n-i-1]="d"
		sachovnica[i][stred]="a";sachovnica[n-i-1][stred]="c"
	sachovnica[stred][stred]="X"

	return sachovnica

##########################################################################

def tlacSachovnicu(sachovnica):
        for riadok in genSachovnicu(9):
                for item in riadok:
                        print(item, end=' ')
                print()
                
tlacSachovnicu(genSachovnicu(9))

##########################################################################
##sachovnice = genSachovnicu(9)
##
##def nextpos(sachovnica, x, y):
##	size = len(sachovnice)
##	
##	if x < size // 2:
##		if y > 0 and sachovnice[y-1][x] == '*':
##			return x, y-1
##	else:
##		if y < size-1 and sachovnice[y+1][x] == '*':
##			return x, y+1
##	if y < size // 2:
##		if x < size-1 and sachovnice[y][x+1] == '*':
##			return x+1, y
##	else:
##		if x > 0 and sachovnice[y][x-1] == '*':
##			return x-1, y
##	return x, y
##
##x, y = 5, 0
##while True:
##	sachovnice[y][x] = '*'
##	x, y = nextpos(sachovnice, x, y)
##	sachovnice[y][x] = 'A'
##	tlacSachovnicu(sachovnice)
##	time.sleep(0.5)

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


##import time
##
sachovnice = [[' ',' ',' ','*','*','*',' ',' ',' '],
              [' ',' ',' ','*','D','*',' ',' ',' '],
              [' ',' ',' ','*','D','*',' ',' ',' '],
              ['*','*','*','*','D','*','*','*','*'],
              ['*','D','D','D','X','D','D','D','*'],
              ['*','*','*','*','D','*','*','*','*'],
              [' ',' ',' ','*','D','*',' ',' ',' '],
              [' ',' ',' ','*','D','*',' ',' ',' '],
              [' ',' ',' ','*','*','*',' ',' ',' ']]

def show(sachovnice):
	for radek in sachovnice:
		print(' '.join(radek))

def nextpos(sachovnice, x, y):
	size = len(sachovnice)
	if x < size // 2:
		if y > 0 and sachovnice[y-1][x] == '*':
			return x, y-1
	else:
		if y < size-1 and sachovnice[y+1][x] == '*':
			return x, y+1
	if y < size // 2:
		if x < size-1 and sachovnice[y][x+1] == '*':
			return x+1, y
	else:
		if x > 0 and sachovnice[y][x-1] == '*':
			return x-1, y
	return x, y

x, y = 5, 0
while True:
	sachovnice[y][x] = '*'
	x, y = nextpos(sachovnice, x, y)
	sachovnice[y][x] = 'A'
	show(sachovnice)
	time.sleep(0.2)
