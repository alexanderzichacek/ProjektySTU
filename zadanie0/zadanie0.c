#include <stdio.h>

void primeFactors(int a, int m){
	
	int j;
	int i;
	int counter = 0;
	int number = 1;
	int primeCount = 0;
	int start = number;
		
	if (a <= 1){
		printf("ERROR");
	}
	else{
		while (m > counter){
			
			for(i = start;i <= a;i++){
		
				if (a % i == 0){
					for(j = 1;j <= i;j++){
						if (i % j == 0) primeCount++;
					}
				}
				if (primeCount == 2){
					if (number > 0) number = i;
					break;	
				} 
				primeCount = 0;
			}
			start = number+1;
			printf("%d\n", number);
			if (number > a) break;
			counter++;
		
		}
	}
	
}

int main(){
	
	int a;
	int m;
	
	scanf("%d", &a);
	scanf("%d", &m);
	
	primeFactors(a, m);
	
	return 0;
}
