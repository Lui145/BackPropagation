import java.util.Random;

public class Main {
	
	static double tA = 0.5;   //tasa de aprendizaje
	static int cantX = 2;
	static int cantY = 2;
	static int cantH = 4;
	static int ciclos = 500000;
	static int cantPuntos = 50;
	static int [][] clases = {{1,3},{3,1}};
	
	public static void main(String[] args) {
		double [] valCorrecto = new double[clases.length];
		double [] x = new double [cantX];
		double [] y = new double [cantY];
		
		Punto [] puntos = new Punto[cantPuntos];
		
		RNA rn = new RNA(tA, cantX, cantY,cantH); 
		
		for(int c=0; c<ciclos; c++) {
			if(c%20000 == 0)
				System.out.print((char)(187));
				
			for(int i = 0; i < clases.length; i++)
				valCorrecto[i] = 0;
			
			for (int i = 0; i< cantPuntos; i+=2) {
				puntos[i] = new Punto(1 + Math.random()*2-1, 3 + Math.random()*1.5-.75);
				puntos[i+1] = new Punto(3 + Math.random()*2-1, 1 + Math.random()*2-1.5);
			}
			
			for(Punto p : puntos) {
				valCorrecto[0] = 0;
				valCorrecto[1] = 0;
				if(Math.hypot(p.x-clases[0][0], p.y-clases[0][1]) < 1.5) {
					valCorrecto[0] = 1;
				}else if(Math.hypot(p.x-clases[1][0], p.y-clases[1][1]) < 1.5) {
					valCorrecto[1] = 1;
				}
				
				x[0] = p.x;
				x[1] = p.y;
				rn.entrenar(x, valCorrecto);
			}
		}
		
		System.out.print("\n          Entrenada. \n\n");
		
		Punto [] pPruebas = new Punto[2];
		
		pPruebas[0] = new Punto(1 + Math.random()*2-1, 3 + Math.random()*2-1);
		
		x[0] = pPruebas[0].x;
		x[1] = pPruebas[0].y;
		
		System.out.println("Punto 1  X = " + x[0] + " Y = " + x[1]);
		System.out.println(Math.hypot(x[0]-clases[0][0], x[1]-clases[0][1]));
		System.out.println(Math.hypot(x[0]-clases[1][0], x[1]-clases[1][1]));
		y = rn.clasificar(x);
		System.out.println("Clase 1: " + y[0] + "\n" +
							"Clase 2: " + y[1] + "\n");
		
		pPruebas[1] = new Punto(3 + Math.random()*2-1, 1 + Math.random()*2-1);
		x[0] = pPruebas[1].x;
		x[1] = pPruebas[1].y;
		
		System.out.println("Punto 2  X = " + x[0] + " Y = " + x[1]);
		System.out.println(Math.hypot(x[0]-clases[0][0], x[1]-clases[0][1]));
		System.out.println(Math.hypot(x[0]-clases[1][0], x[1]-clases[1][1]));
		y = rn.clasificar(x);
		System.out.println("Clase 1: " + y[0] + "\n" +
							"Clase 2: " + y[1]);
	}

}
