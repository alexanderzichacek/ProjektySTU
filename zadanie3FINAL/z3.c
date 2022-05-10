#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <memory.h>
#include <unistd.h>
#include <ctype.h>

char *readline() {
    const size_t BSIZE = 100;
    char *line = NULL;
    size_t capacity = 0;

    do {
        // Priprav dostatok pamate.
        capacity += BSIZE;

        // Alokuj miesto na capacity + '\0'.
        line = (char *) realloc(line, (capacity + 1) * sizeof(char));
        if (line == NULL)
            return line;

        // Nacitaj zo vstupu riadok (resp. jeho zvysok).
        if (fgets(line + capacity - BSIZE, BSIZE + 1, stdin) == NULL) {
            free(line);
            return NULL; // doslo k chybe citania alebo sme narazili na EOF
        }
        // Opakuj, pokym sa nenacita cely riadok.
        // Indikacia, ze zostal zvysok riadku:
        //   1. je naplnena cela BSIZE
        //   2. na konci nie je '\n'
    } while (strlen(line + capacity - BSIZE) >= BSIZE
             && line[capacity - 1] != '\n');
    // nacitany riadok obsahuje na konci znak '\n'
    return line;
}

int main(int argc, char *argv[]){ 
	int i, opt, j = 0, aSwitch = 0, cSwitch = 0, uSwitch = 0, lSwitch = 0, rSwitch = 0; 
	char *line = NULL;
	char *optString = ":aculr:";
	
	while ((opt = getopt(argc, argv, optString)) != -1){
		switch(opt){
			case 'a':
				aSwitch = 1;
				break;
			////////////////////////////////////////////////////////////////////////// PREPINAC A	
			case 'r':
				rSwitch = 1;
				break;
			////////////////////////////////////////////////////////////////////////// PREPINAC R	
			case 'c':
				cSwitch = 1;
				break;
			////////////////////////////////////////////////////////////////////////// PREPINAC C
			case 'u':
				if (lSwitch == 1) return 3;
				uSwitch = 1;
				break;
			////////////////////////////////////////////////////////////////////////// PREPINAC U
			case 'l':
				if (uSwitch == 1) return 3;
				lSwitch = 1;
				break;
			////////////////////////////////////////////////////////////////////////// PREPINAC L
			case '?':
				free(line);
        		return 1;
        		break;
        	////////////////////////////////////////////////////////////////////////// NEPLATNY PREPINAC
			default:
				free(line);
				return 2;
		}
	}

	while ((line = readline()) != NULL) {
			if (strlen(line) == 1){
				free(line);
				break;
			}
		if (aSwitch == 1){//////////////////////////////////////////////////////////////////// A PREPINAC
				for (i = 0;i < strlen(line);i++){
					if ((line[i] < 65) && line[i] != '\0' && line[i] != '\n') line[i] = ' ';
					if ((line[i] > 90) && (line[i] < 97) && line[i] != '\0' && line[i] != '\n') line[i] = ' ';
					if ((line[i] > 122) && line[i] != '\0' && line[i] != '\n') line[i] = ' ';
				}
			}
	
		if (cSwitch == 1){//////////////////////////////////////////////////////////////////// C PREPINAC
			for (i = 0;i < strlen(line);i++){
				if ((line[i] < 65)){
					memmove(&line[i], &line[i + 1], strlen(line) - i);
					i--;
				} 
				else if ((line[i] > 90) && (line[i] < 97)){
					memmove(&line[i], &line[i + 1], strlen(line) - i);
					i--;
				}
				else if ((line[i] > 122)){
					memmove(&line[i], &line[i + 1], strlen(line) - i);
					i--;
				}
				else{
					line[i] = line[i];
				}
			}
			
		}
		
		if (uSwitch == 1 && lSwitch == 1) return 3;
		
		if (uSwitch == 1 && lSwitch == 0){//////////////////////////////////////////////////////////////////// U PREPINAC
				for (i = 0;i < strlen(line);i++){
					if ((line[i] >= 97) && (line[i] <= 122)) line[i] = line[i]-32;
				}
		}
		
		if (lSwitch == 1 && uSwitch == 0){//////////////////////////////////////////////////////////////////// L PREPINAC
				for (i = 0;i < strlen(line);i++){
					if ((line[i] >= 65) && (line[i] <= 90)) line[i] = line[i]+32;
				} 
		}
		
		if (rSwitch == 1){//////////////////////////////////////////////////////////////////// R PREPINAC
			
		}
		
		if (rSwitch == 0){
			if (optind < argc){
				char *help;
				for (i = optind;i < argc;i++){
					help = strstr(line,argv[i]);
					while(1){
						if (strstr(help,argv[i])){
							help = strstr(line,argv[i]);
							for(j = 0;j < strlen(argv[i]);j++){
								*(help + j) = '*';
							}
						}
						else {
							break;	
						}
					}
				}
			}
		}
		if (aSwitch == 1 || cSwitch == 1) printf("%s\n", line);
		else printf("%s", line);
		free(line);
	}
	
	return 0;
}
