#include <stdio.h>

double speed(double v0, int a, double dt){
	double actualSpeed = v0 + (a * dt);
	return actualSpeed;
}

double height(double v0, double dt, double a, double h0){
	double actualHeight = h0 + (v0 * dt) + (a * dt * dt * 0.5);

	return actualHeight;
}

int engine(double h, double de, double vsoft, double v){
	if(h < de && v < vsoft) return 1;
	else return 0;
}

int main(){

	double m, T, v0, H, g, vsoft, dt;

	double df, A, de, v, h, h0, a, t, counterS = 0;
	int s;

	scanf("%lf", &m);
	scanf("%lf", &T);
	scanf("%lf", &v0);
	scanf("%lf", &H);
	scanf("%lf", &g);
	scanf("%lf", &vsoft);
	scanf("%lf", &dt);

	t = 0;
	
	h0 = H;
	h = H;

	A = T / m;
	df = ((A - g) * H) / A;
	de = H - df;

	printf("m=%.3lf\n", m);
	printf("T=%.3lf\n", T);
	printf("v0=%.3lf\n", v0);
	printf("H=%.3lf\n", H);
	printf("g=%.3lf\n", g);
	printf("vsoft=%.3lf\n", vsoft);
	printf("dt=%.3lf\n", dt);
	printf("df=%.3lf\n", df);

	while (h > 0){

		s = engine(h, de, vsoft, v);

		if (s == 1) {
			a = A * g;
			counterS = counterS + dt;
		}
		if (s == 0) a = -g;

		printf("t=%.3lf ", t);	// Casovy usek

		printf("h=%.3lf ", h);

		printf("v=%.3lf ", v);

		printf("s=%d\n", s);

		h0 = h;
		h = height(v0, dt, a, h0);
		
		v0 = v;
		v = speed(v0, a, dt);

		t = t + dt;
	}
    printf("t=%.3lf ", t);
    printf("h=%.3lf ", h);
    printf("v=%.3lf\n", v);
	printf("total=%.3lf", counterS);

	return 0;
}
