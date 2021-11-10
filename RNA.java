import javax.swing.plaf.synth.SynthOptionPaneUI;

public class RNA {
	int i, j, cantX, cantY, cantH;
	double sum, error, tA;
	double [] x, y, dy, by, h, dh, bh;
	double [][] wh, wy; 
	
	public RNA(double tA, int cantX, int cantY, int cantH) {
		this.tA = tA;
		x = new double[cantX];
		y = new double[cantY];
		dy = new double[cantY];
		by = new double[cantY];
		h = new double[cantH];
		dh = new double[cantH];
		bh = new double[cantH];
		wh = new double[cantY][cantH];
		wy = new double[cantH][cantY];
		this.cantX = cantX;
		this.cantY = cantY;
		this.cantH = cantH;
		
		for(i = 0; i < cantY; i++)
			for(j = 0; j< cantH; j++)
				wh[i][j] = Math.random()+.1;
		
		for(i = 0; i < cantH; i++)
			for(j = 0; j< cantY; j++)
				wy[i][j] = Math.random()+.1;
		
		for(i = 0; i < cantY; i++)
			by[i] = Math.random()+.1;
		
		for(i = 0; i < cantH; i++)
			bh[i] = Math.random()+.1;
	}
	
	public double [] clasificar(double[] x) {
		for(i = 0; i < h.length; i++) {
			sum = bh[i];
			
			for(j = 0; j< x.length; j++) 
				sum += x[j]*wh[j][i];
			
			h[i] = f(sum);
		}
		
		for(i = 0; i < y.length; i++) {
			sum = by[i];
			
			for(j = 0; j< h.length; j++) 
				sum += h[j]*wy[j][i];
			
			y[i] = f(sum);
		}
		
		return y;
	}
	
	double f(double x){
	     return 1/(1+Math.exp(-x));
	}
	
	double df(double x) {
		return f(x)*(1-f(x));
	}
	
	public void entrenar(double [] x, double [] correcto) {
		double [] clasif;
		
		clasif = clasificar(x);
		
		dy = new double[cantY];
		for(i = 0; i < clasif.length; i++) 
			dy[i] = (correcto[i] - clasif[i])*df(clasif[i]);
		
		dh = new double[cantH];
		for(i = 0; i < h.length; i++) {
			error = 0;
			
			for(j = 0; j < clasif.length; j++) {
				error += dy[j]*wy[i][j];
			}
						
			dh[j] = error*df(h[i]);
		}
		
		for (int i=0; i<clasif.length; i++) {
	        by[i] += dy[i]*tA;
	        
	        for (int j=0; j<h.length ; j++)
	            wy[j][i] += h[j]*dy[i]*tA;
	    }
		
		for(i = 0; i < clasif.length; i++) {
			bh[i] += dh[i]*tA;
			
			for(j = 0; j< x.length; j++) 
				wh[j][i] += x[j]*dh[i]*tA;
		}
		 
	}
}

























