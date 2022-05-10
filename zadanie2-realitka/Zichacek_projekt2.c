#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define KAT_COUNT 53
#define MIESTO_COUNT 53
#define ULICA_COUNT 103
#define POPIS_COUNT 203

typedef struct polozka{
	char kategoria[KAT_COUNT];
	char miesto[MIESTO_COUNT];
	char ulica[ULICA_COUNT];
	int rozloha;
	int cena;
	char popis[POPIS_COUNT];
	struct polozka *next;
} POLOZKA;

void n(FILE *f, int *item, POLOZKA **first){
	char c, one_item;
 	
	POLOZKA *new_item = NULL, *last_item = NULL;
	
	int i, counter_items = 0, count_items = 0, count_dollar = 0, count_lines = 1, char_count = 0, line_item;
	
	if((f = fopen("reality.txt","r")) == NULL){
	
		*item = -1;
	
	}
	else{
	
		while((c = fgetc(f)) != EOF){
	
			if(c=='&') count_items++;
	
		}
	
		*item = count_items;
		
		fclose(f);	
	
	}
	
	*first = (POLOZKA*) malloc(sizeof (struct polozka));
	
	if(item > 0){	
		
		f = fopen("reality.txt","r");
		
		while((fscanf(f, "%c ", &one_item) != EOF)){
	
			counter_items++;
			
			new_item = malloc(sizeof(struct polozka));
			
			fgets(new_item->kategoria, KAT_COUNT, f);
			
			fgets(new_item->miesto, MIESTO_COUNT, f);
			
			fgets(new_item->ulica,ULICA_COUNT, f);
			
			fscanf(f, "%d ", &new_item->rozloha);
			
			fscanf(f, "%d ", &new_item->cena);
			
			fgets(new_item->popis, POPIS_COUNT, f);
			
			if(counter_items != 1){
	
				last_item->next = new_item;
				new_item->next = NULL;
				last_item = new_item;
	
			}
			else{
	
				*first = last_item = new_item;
	
			}
			
		}

	}
	
}

void v(POLOZKA *first){
	int counter = 0;
	
	struct polozka *actual_v = first;
	
	while(actual_v != NULL){
		counter++;
		
		printf("%d.\n", counter);
		
		printf("kategoria ponuky: %s", actual_v->kategoria);
		
		printf("miesto ponuky: %s", actual_v->miesto);
		
		printf("ulica: %s", actual_v->ulica);
		
		printf("rozloha v m2: %d\n", actual_v->rozloha);
		
		printf("cena: %d\n", actual_v->cena);
		
		printf("popis: %s", actual_v->popis);
		
		actual_v = actual_v->next;
	
	}
	
}

void h(POLOZKA *first, int *cena_nacitana){
	struct polozka *actual_h = first;
	
	int cena_struct = 0, tf = 0;
	
	while(actual_h != NULL){
	
		cena_struct = actual_h->cena;

		if((cena_struct) <= (*cena_nacitana)){
	
			tf++;

			printf("%d.\n", tf);
			
			printf("kategoria ponuky: %s", actual_h->kategoria);
			
			printf("miesto ponuky: %s", actual_h->miesto);
			
			printf("ulica: %s", actual_h->ulica);
			
			printf("rozloha v m2: %d\n", actual_h->rozloha);
			
			printf("cena: %d\n", actual_h->cena);
			
			printf("popis: %s", actual_h->popis);
	
		}

	actual_h = actual_h->next;

	}

	if(tf == 0){
	
		printf("V ponuke su len reality s vyssou cenou\n");
	
	}
	
}

void p(POLOZKA **first, int *pozicia, int *item){
	int i, roz, cen;
	
	char kat[KAT_COUNT], mies[MIESTO_COUNT], ul[ULICA_COUNT], pop[POPIS_COUNT];
	
	POLOZKA *actual_p = *first, *vloz_pom;

	POLOZKA *new_item = (POLOZKA*) malloc(sizeof(POLOZKA));
	
	if(*first == NULL) {
        *first = malloc(sizeof(POLOZKA));
        *pozicia = 1;
        *item = (*item) + 1;
    }
	
	fgets(kat, KAT_COUNT, stdin);
	strcpy(new_item->kategoria, kat);
	
	fgets(mies, MIESTO_COUNT, stdin);
	strcpy(new_item->miesto, mies);
	
	fgets(ul, ULICA_COUNT, stdin);
	strcpy(new_item->ulica, ul);
	
	scanf("%d ", &roz);
	new_item->rozloha = roz;
	
	scanf("%d ", &cen);
	new_item->cena = cen;
	
	fgets(pop, POPIS_COUNT, stdin);
	strcpy(new_item->popis, pop);
		
	if(*pozicia > *item){
	
		for(i = 1; i < *item;i++)
	
			actual_p = actual_p->next;
			actual_p->next = new_item;
	
	*item = (*item) + 1;
	
		}
		
	if(*pozicia == 1){
	
		new_item->next = *first;										
		*first = new_item;
	
	*item = (*item) + 1;
	
	}
	
	if(*pozicia > 1 && *pozicia <= *item){
	
		for(i = 1; i < *pozicia-1;i++){
		
			actual_p = actual_p->next;
		
		}
	
		vloz_pom = actual_p->next;
		actual_p->next = new_item;
		new_item->next = vloz_pom;
	
	*item = (*item) + 1;
	
	}
		
}

char *toLowerCase(char *string){
	char *vloz_pom = malloc(MIESTO_COUNT);
	
	int i;
	
	for (i = 0; i < MIESTO_COUNT;i++){

		vloz_pom[i] = tolower(string[i]);

	}
	
	vloz_pom[strlen(vloz_pom)] = '\0';	
	
	return vloz_pom;

}

void z(POLOZKA **first, int *item){ //funkcia na zmazavanie
	char *str, *vlozPom;
		
	str = (char*)malloc(MIESTO_COUNT);
	vlozPom = (char*)malloc(MIESTO_COUNT);
		
	POLOZKA *actual_z = *first, *last_item = *first;
	
	int count = 0, i, n = 0, j;
			
	scanf("%s", str);

	for (i = 0; i < strlen(str);i++){
		
		str[i] = tolower(str[i]);
	
	}
		
	str[strlen(str)] = '\0';
		
	count = 0;
		
	for(i = 0; i < *item;i++){	
		
		vlozPom = realloc(vlozPom,(strlen(actual_z->miesto)));
			
		vlozPom = toLowerCase(actual_z->miesto);
	
		if((strstr(vlozPom,str)) != NULL){
		
			if(actual_z == *first){ //ak zmazavam prvy
		
				*first = actual_z->next;
				free((void*)actual_z);
				actual_z = *first;	
		
				count++;
		
			}
			else{	
		
				while (last_item->next != actual_z){
		
					last_item = last_item->next; 
		
				}
		
				if(actual_z->next != NULL){ //ak zmazavam medzi prvym a poslednym
	
					last_item->next = actual_z->next;
					free((void*)actual_z);
					actual_z = last_item->next;
		
				}
				else{ 	//ak zmzavam posledny	
		
				last_item->next = NULL;	
				free((void*)actual_z);
		
				}
		
				count++;
		
			}	
		
		}
		else{
		
			actual_z = actual_z->next;
		
		}
		
	}
		
	printf("Vymazalo sa %d zaznamov\n", count);
		
	*item = *item - count;
		
}

void a(POLOZKA **first){
	
	char vlozPom[MIESTO_COUNT];
	
	int count = 0;
	
	scanf("%s ", vlozPom);
	
	POLOZKA *actual_a = *first;
	POLOZKA *new_item = malloc(sizeof(POLOZKA));
	
	fgets(new_item->kategoria, KAT_COUNT, stdin);
	
	fgets(new_item->miesto, MIESTO_COUNT, stdin);
	
	fgets(new_item->ulica,ULICA_COUNT, stdin);
	
	fscanf(stdin, "%d ", &new_item->rozloha);
	
	fscanf(stdin, "%d ", &new_item->cena);
	
	fgets(new_item->popis, POPIS_COUNT, stdin);

	
	char *vloz_pom = toLowerCase(vlozPom);
	
	while(1){
	
	char *actual_aL = toLowerCase(actual_a->miesto);
	
	if(strstr(actual_aL, vloz_pom)){
		
		strcpy(actual_a->kategoria, new_item->kategoria);
	
		strcpy(actual_a->miesto, new_item->miesto);
	
		strcpy(actual_a->ulica, new_item->ulica);
	
		actual_a->rozloha = new_item->rozloha;
	
		actual_a->cena = new_item->cena;
	
		strcpy(actual_a->popis, new_item->popis);

		count++;    

	}
	
	if(actual_a->next == NULL){
		
		break;
		
	}
	
	actual_a = actual_a->next;
	
	}
	
	printf("Aktualizovalo sa %d zaznamov\n", count);
	
}

void k(POLOZKA **first){
	
	POLOZKA *actual_k = *first;
	POLOZKA *next_item;
	
	while(actual_k != NULL){
		
		next_item = actual_k->next;
		free(actual_k);
		actual_k = next_item;
		
	}
	
	*first = NULL;
	
	exit(0);		
	
}

int main(){	
	FILE *file;
	
	char nacitaj;

	POLOZKA *first = NULL;

	int item = 0, cena_nacitana = 0, exit = 0, pozicia = 0, boolean = 0;
	
	while(nacitaj!='k'){

		nacitaj = getchar();

		if(nacitaj == 'n'){
	
			n(file, &item, &first);

				if(item == -1){
		
					printf("Zaznamy neboli nacitane\n");
		
				} 
				else {	

					boolean = 1;
		
					printf("Nacitalo sa %d zaznamov\n",item);
		
				}				
		
		}
		if(nacitaj == 'v'){
		
			v(first);
		
		}
		if(nacitaj == 'h'){
		
			scanf("%d", &cena_nacitana);
		
			h(first, &cena_nacitana);
		
		}
		if(nacitaj == 'k'){
		
			k(&first);
		
		}
		if(nacitaj == 'p'){
		
			scanf("%d ", &pozicia);
		
			p(&first, &pozicia, &item);
		
		}
		if(nacitaj == 'z'){
		
			z(&first, &item);
		
		}
		
		if(nacitaj == 'a'){
			
			a(&first);
			
		}
		
	}
	
	return 0;
	
}
