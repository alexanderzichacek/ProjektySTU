#include <stdio.h>

int prime(int number, int start){
	
	int i;
	int j;
	int primeCount = 0;
	int numb = 0;
	
	for(i = start;i <= number;i++){
		
		if (number % i == 0){
			for(j = 1;j <= i;j++){
				if (i % j == 0) primeCount++;
			}
		}
		
		if (primeCount == 2){
			numb = i;
			break;	
		}
		primeCount = 0;
		
	}

	if (numb > 0) return numb;
}

void primeFactors(int a, int m){

	int counter = 0;
	int number = 0;
		
	if (a > 1){
		while (m > counter){
		
			number = prime(a, number+1);
			if (number > a) break;
			printf("%d\n", number);
			counter++;
		
		}
	}
	else printf("ERROR");
	
}

int main(){
	
	int a;
	int m;
	
	scanf("%d", &a);
	scanf("%d", &m);
	
	primeFactors(a, m);
	
	return 0;
}
