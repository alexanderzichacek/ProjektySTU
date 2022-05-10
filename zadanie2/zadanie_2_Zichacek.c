#include <stdio.h>

#define R_MAX 2147483647 // vsetky generovane cisla su mensie ako R_MAX
static long long unsigned int SEED = 0x1; // seed generatora

void srnd(int seed) {
  SEED = seed;
}

int rnd(int from, int to) {
    SEED = SEED * 16807 % R_MAX;
    return from + (int) SEED % (to - from + 1);
}

int main(){
	
	int position = -1, help, seed, n, m1, m2, i, rndBlock, rndBoost, blockCount = 0, boostCount = 0 , firstDice, secDice, player = 1, round = 1, move, posBefore = -1, posAfter = -1, opoPosBefore = -1, opoPosAfter = -1, boostBefore = 0, boostAfter = 0, opoBoostBefore = 0, opoBoostAfter = 0;
	
	scanf("%d", &seed);
	scanf("%d", &n);
	scanf("%d", &m1);
	scanf("%d", &m2);

	int pole[n];
	
	for(i = 0;i < n;i++){
		pole[i] = 0;
	}
	
	if (seed <= 0) return 1;
	else if (n < 10 || n > 100) return 1;
	else if ((m1 + m2) > (n/2)) return 1;
	else{
			
		srnd(seed);
		
		while (blockCount < m1){
			rndBlock = rnd(1, n-1);
			if (pole[rndBlock] == 0){
				pole[rndBlock] = 1;
				blockCount++;
			}
		}
		while (boostCount < m2){
			rndBoost = rnd(1, n-1);
			if (pole[rndBoost] == 0){
				pole[rndBoost] = 2;
				boostCount++;
			}
		}
		////////////////////////////////////// Generovanie blockerov a boosterov
		
		printf("BLOCK:");
		for(i = 0;i < n;i++){
			if (pole[i] == 1) printf("%d ", i);
		}
		
		printf("\nBOOST:");
		for(i = 0;i < n;i++){
			if (pole[i] == 2) printf("%d ", i);
		}		
		printf("\n");
		////////////////////////////////////// Vypis blockerov a boosterov
				
		while (n > position){
			
			if (player == 1){
				firstDice = rnd(1, 6);
				secDice = rnd(1, 6);
				////////////////////////////////////// hod kockou
				if (posBefore == -1){	// ked nie je na ploche
					if ((firstDice + secDice) > 7){
						if (boostBefore > 0) move = firstDice + secDice - 7 + boostBefore;
				////////////////////////////////////// posun ked ma boost
						else move = firstDice + secDice - 7;
				////////////////////////////////////// posun ked nema boost
						if (pole[move] == 0) posAfter = move;
				////////////////////////////////////// posun
						if (pole[move] == 1 && boostBefore == 0){
							posAfter = -1;
							pole[move] = 0;
						}
				////////////////////////////////////// posun na blocker bez boostu
						if (pole[move] == 1 && boostBefore > 1){
							posAfter = move;
							pole[move] = 0;
							boostAfter = 0;
						}
				////////////////////////////////////// posun na blocker s boostom
						if (pole[move] == 2){
							posAfter = move + boostBefore;
							pole[move] = 0;
							boostAfter++;
						}
				////////////////////////////////////// posun na boost
					}
				}
				
				if (posBefore >= 0){	// ked je na ploche
					if (firstDice > secDice) move = firstDice + boostBefore;
					////////////////////////////////////// ak je 1 kocka viac ako 2
					if (secDice > firstDice) move = secDice + boostBefore;
					////////////////////////////////////// ak je 2 viac ako 1
					if (firstDice == secDice){
						if ((firstDice == 1) && (opoPosBefore < posBefore) && (opoPosBefore >= 0)){
							help = opoPosBefore;
							opoPosBefore = posBefore;
							posBefore = help;
							move = 0;
						}
					////////////////////////////////////// ak su rovnake a su 1
						if ((firstDice == 6) && (opoPosBefore > posBefore)){
							help = opoPosBefore;
							opoPosBefore = posBefore;
							posBefore = help;
							move = 0;
						}
					////////////////////////////////////// ak su rovnake a su 6
						else move = firstDice + boostBefore;
					}
					if (pole[posBefore + move + boostBefore] == 0) posAfter = posBefore + move + boostBefore;
					////////////////////////////////////// posun
					if (pole[posBefore + move] == 1 && boostBefore == 0){
						pole[posBefore + move] = 0;
						posAfter = -1;
					}
					////////////////////////////////////// posun na blocker bez boostu
					if (pole[posBefore + move + boostBefore] == 1 && boostBefore > 1){
						posAfter = posBefore + move + boostBefore;
						pole[posAfter] = 0;
						boostAfter = 0;
					}
					////////////////////////////////////// posun na blocker s boostom
					if (pole[posBefore + move + boostBefore] == 2){
						posAfter = posBefore + move + boostBefore;
						pole[posAfter] = 0;
						boostAfter++;
					}
					////////////////////////////////////// posun na boost
					if (posAfter > position) position = posAfter;
				}
				if (posAfter == opoPosBefore) opoPosBefore = -1;
				printf("[%d,%d] [%d,%d] [%d,%d] [%d,%d]", round, player, posBefore, boostBefore, firstDice, secDice, posAfter, boostAfter);
				posBefore = posAfter;
				boostBefore = boostAfter; 
				printf("\n"); 
				player = 2;
			}
			////////////////////////////////////// hrac 1
			
			else{
				firstDice = rnd(1, 6);
				secDice = rnd(1, 6);
				////////////////////////////////////// hod kockou
				if (opoPosBefore == -1){	// ked nie je na ploche
					if ((firstDice + secDice) > 7){
						if (opoBoostBefore > 0) move = firstDice + secDice - 7 + opoBoostBefore;
				////////////////////////////////////// posun ked ma boost
						else move = firstDice + secDice - 7;
				////////////////////////////////////// posun ked nema boost
						if (pole[move] == 0) opoPosAfter = move;
				////////////////////////////////////// posun
						if (pole[move] == 1 && opoBoostBefore == 0){
							opoPosAfter = -1;
							pole[move] = 0;
						}
				////////////////////////////////////// posun na blocker bez boostu
						if (pole[move] == 1 && opoBoostBefore > 1){
							opoPosAfter = move;
							pole[move] = 0;
							opoBoostAfter = 0;
						}
				////////////////////////////////////// posun na blocker s boostom
						if (pole[move] == 2){
							opoPosAfter = move + opoBoostBefore;
							pole[move] = 0;
							opoBoostAfter++;
						}
				////////////////////////////////////// posun na boost
					}
				}
				
				if (opoPosBefore >= 0){	// ked je na ploche
					if (firstDice > secDice) move = firstDice + opoBoostBefore;
					////////////////////////////////////// ak je 1 kocka viac ako 2
					if (secDice > firstDice) move = secDice + opoBoostBefore;
					////////////////////////////////////// ak je 2 viac ako 1
					if (firstDice == secDice){
						if ((firstDice == 1) && (posBefore < opoPosBefore) && (posBefore >= 0)){
							help = posBefore;
							posBefore = opoPosBefore;
							opoPosBefore = help;
							move = 0;
						}
					////////////////////////////////////// ak su rovnake a su 1
						if ((firstDice == 6) && (posBefore > opoPosBefore)){
							help = posBefore;
							posBefore = opoPosBefore;
							opoPosBefore = help;
							move = 0;
						}
					////////////////////////////////////// ak su rovnake a su 6
						else move = firstDice + opoBoostBefore;
					}
					if (pole[opoPosBefore + move + opoBoostBefore] == 0) opoPosAfter = opoPosBefore + move + opoBoostBefore;
					////////////////////////////////////// posun
					if (pole[opoPosBefore + move] == 1 && opoBoostBefore == 0){
						pole[opoPosBefore + move] = 0;
						opoPosAfter = -1;
					}
					////////////////////////////////////// posun na blocker bez boostu
					if (pole[opoPosBefore + move + opoBoostBefore] == 1 && opoBoostBefore > 1){
						opoPosAfter = opoPosBefore + move + opoBoostBefore;
						pole[opoPosAfter] = 0;
						opoBoostAfter = 0;
					}
					////////////////////////////////////// posun na blocker s boostom
					if (pole[opoPosBefore + move + opoBoostBefore] == 2){
						opoPosAfter = opoPosBefore + move + opoBoostBefore;
						pole[opoPosAfter] = 0;
						opoBoostAfter++;
					}
					////////////////////////////////////// posun na boost
					if (opoPosAfter > position) position = opoPosAfter;
				}
				if (opoPosAfter == posBefore) posBefore = -1;
				printf("[%d,%d] [%d,%d] [%d,%d] [%d,%d]", round, player, opoPosBefore, opoBoostBefore, firstDice, secDice, opoPosAfter, opoBoostAfter);
				opoPosBefore = opoPosAfter;
				opoBoostBefore = opoBoostAfter; 
				printf("\n"); 
				player = 1;
			}
			////////////////////////////////////// hrac 2
			
			round++;	
		}
		if (player == 1) printf("WINNER:2\n");
		else printf("WINNER:1\n");
		printf("HOTSPOT:");
		return 0;	
		
	}
}
