package net.tfobz.Philosopher;

public class PhilosopherMain {

	public static void main(String[] args) {
		Fork f1 = new Fork("F1");
		Fork f2 = new Fork("F2");
		Fork f3 = new Fork("F3");
		Philosopher p1 = new Philosopher("P1", new ForkControl(f3,f1));
		Philosopher p2 = new Philosopher("P2", new ForkControl(f1,f2));
		Philosopher p3 = new Philosopher("P3", new ForkControl(f2,f3));
		p1.start(); 
		p2.start(); 
		p3.start();
	}
}
