#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void vypis(FILE **f){ // Funkcia na vypis zaznamov zo suboru podla predlohy
	int line = 0;
	short int max_line_count = 53;
	char line_value[53];
	char *line_valuech;
	
	*f = fopen("autobazar.txt", "r");				
	
	if(f == NULL) printf("Subor sa nepodarilo otvorit");	// Otvorenie a kontrola otvorenia suboru
	
	while((fgets(line_value,max_line_count,*f)) != '\0'){
		line++;
		
		if (line == 1) {
			printf("meno priezvisko: %s", line_value);
		}
		else if (line == 2) {
			printf("SPZ: %s", line_value);
		}
		else if (line == 3) {
			printf("typ auta: %s", line_value);
		}
		else if (line == 4) {
			printf("cena: %s", line_value);
		}
		else if (line == 5) {
			printf("datum: %s \n", line_value);
		}
		
		if(line == 6) {
			line = 0;
		}
		
	}
	
}

void pocet_zaznamov(char **spz_pole, char **str, FILE **f){	//Funkcia n na zistenie poctu zaznamov a vytvorenie pola SPZ-tiek
	int line = 0;
	short int max_line_count = 53;
	char line_value[53];
	
	rewind(*f);	//prevedenie na zaciatok suboru, ked uz bol raz precitany
	
	if(*f == NULL) printf("Subor este nebol otvoreny\n");
	
	else{	
		
		if (*spz_pole != NULL) {
			free(*spz_pole);
		}
		
		*spz_pole = (char*)calloc(10 ,sizeof(str));	//alokacia pola SPZ-tiek na 10 znakov - 7 ma SPZ a 3 kvoli pripadnemu \n v roznych kompilatoroch
	
		if(f!=NULL){
		
			while(fgets(line_value, max_line_count, *f) != NULL){
				line++;
				if(line == 2){
					
					strcat(*spz_pole, line_value);
				}
			
				if(line == 6) line = 0;
		
			}
		}	
	
	}

}

void vypis_spz(char **spz_pole){	//Funkcia s na vypis SPZ-tiek podla predlohy 
	char *spzs = (char*)malloc(100 * sizeof(char));	//alokacia pomocneho pola
	int counter = 0;
	
	if(*spz_pole == NULL) printf("Pole nie je vytvorene\n");	
	
	else{
		for(int i = 0; i < strlen(*spz_pole);i++){
			strcpy(spzs, *spz_pole);	//kopirovanie z pola SPZ do pomocneho
		}
		for(int i = 0; i < strlen(*spz_pole);i++){
			counter++; //pocitanie znakov kvoli vlozeniu medzeri
			if(counter == 3){
				printf(" %c", spzs[i]);
				counter = 0;
			}
			else if(spzs[i] == '\n'){
				counter = 0;
				printf("\n");
			}
			else printf("%c", spzs[i]);
		}
	}
}

void vyskyt_znakov(char **spz_pole, char **str){	//Funkcia m na pocetnost znakov v subore podla predlohy
	int max = 0, pocet_znakov[26] = {0};
	char znak;
	char *spzm = NULL;
	
	if(*spz_pole == NULL) printf("Pole nie je vytvorene\n");
	
	else{
		char *spzm = (char*)malloc(100 * sizeof(char));
	
		for(int i = 0;i < strlen(*spz_pole); i++){
			strcpy(spzm, *spz_pole);
			if((spzm[i] >= 'A') && (spzm[i] <= 'Z')){	//ak je znak v poli SPZ velke pismeno (aby som neratal cisla) pripocitam + 1 a zapamatam si ho
				pocet_znakov[spzm[i] - 'A']++;
			}
		}
	
		for(int j = 1;j < 26;j++){
			if((pocet_znakov[max]) < (pocet_znakov[j])){
				max = j;
			}
		}
	
		znak = 'A' + max;
		printf("%c ", znak);
		printf("%d \n", pocet_znakov[max]);
	
	}
	
}

void palindrom_spz(char **spz_pole, FILE *f){
	if(*spz_pole == NULL) printf("Pole nie je vytvorene\n");
	
	
}

void pocet_okresov(char **spz_pole, FILE *f){

	if(*spz_pole == NULL) printf("Pole nie je vytvorene\n");

//	char **spz_z = NULL;
//	int pocet = 0, line = 0;
//	short int max_line_count = 53;
//	char line_value[53];
//	
//	rewind(f);	
//		
//	while((fgets(line_value,max_line_count,f)) != '\0'){
//		line++;
//		
//		if (line == 2) {
//			pocet++;
//			spz_z = (char**)realloc(spz_z, pocet * sizeof(char*));
//			spz_z[pocet - 1] = (char*)malloc(53 * sizeof(char*));
//			spz_z[pocet - 1] = line_value;
//			printf("%s", spz_z[pocet - 1]);
//		}
//		if(line == 6) {
//			line = 0;
//		}
//		
//	}
//	
}

void vypis_odmeny(FILE *f, int date_int){	//Funkcia o na vypisanie odmien zamestnancom podla zadanych podmienok 
	int line = 0, datumh = 0, pomocna_typ = 0;
	short int max_line_count = 53;
	char line_value[53], *spz_line_value = NULL, *meno_line_value = NULL;
	double odmena = 0.00, pomocna_odmena = 0.00;
	
	if(f == NULL) printf("Subor este nebol otvoreny\n");
	
	else{
		spz_line_value = (char*)malloc(10 *sizeof(char));	//pomocne pole SPZ
		meno_line_value = (char*)malloc(53 *sizeof(char));	//pomocne pole mien
		
		rewind(f);
		
		while((fgets(line_value,max_line_count,f)) != '\0'){
			line++;
		
			if ((line == 1) && ((datumh) - (date_int)) <=- (-10000)) {
				strcpy(meno_line_value, line_value);	//ak je meno viac ako rok tak si zapamatam
			}
			else if ((line == 2) && ((datumh) - (date_int)) <=- (-10000)) {
				strcpy(spz_line_value, line_value);	//ak je SPZ -||-
			}
			else if (line == 3) {
				pomocna_typ = atoi(line_value);	//Funkcia atoi zmeni char na int
			}
			else if (line == 4) {
				if(pomocna_typ == 1){
					pomocna_odmena = atof(line_value); //Funkcia atof zmeni char na double
					odmena = pomocna_odmena/100*2.3;
				}
				if(pomocna_typ == 0){
					pomocna_odmena = atof(line_value);
					odmena = pomocna_odmena/100*5.1;
				}
			}
			else if (line == 5) {
				datumh = atoi(line_value);
				if(((datumh) - (date_int)) <= (-10000)) printf("\n%s%s%.2lf \n", meno_line_value, spz_line_value, odmena);				
			}
		
			if(line == 6) {
				line = 0;
			}	
			
		}
		
	}

}




int main(){
	FILE *f;
	char n, *spz_pole = NULL, *str = NULL;
	int date_int, count_v = 0;
	
	while(n!='k'){ //k - koniec ... pokial nie je k nacitava
		n = getchar();
		if(n == 'v'){ //volanie funkcie v
		count_v = 1;
		vypis(&f);
		}
		if(n == 'o') {	//volanie funkcie o
			scanf("%d", &date_int);
			vypis_odmeny(f, date_int);
		}
		if(n == 's') vypis_spz(&spz_pole);	//volanie funkcie s
		if(n == 'n' && count_v == 1) pocet_zaznamov(&spz_pole, &str, &f);	//volanie funkcie n
		if(n == 'm') vyskyt_znakov(&spz_pole, &str);	//volanie funkcie m
		if(n == 'p') palindrom_spz(&spz_pole, f);	//volanie funkcie p
		if(n == 'z') pocet_okresov(&spz_pole, f);	//volanie funkcie z
	}
	
	return 0;
}
