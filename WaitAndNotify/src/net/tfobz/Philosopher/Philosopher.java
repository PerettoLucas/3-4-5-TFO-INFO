package net.tfobz.Philosopher;

public class Philosopher extends Thread
{
	public static final int MAX_THINK_TIME = 100;
	public static final int MAX_EAT_TIME = 50;
	
	private ForkControl forkControl = null;
	
	public Philosopher(String name, ForkControl forkControl) {
		setName(name); 
		this.forkControl = forkControl;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				sleep((int)(Math.random() * MAX_THINK_TIME));
			} catch (InterruptedException e) { ; 	}
			forkControl.get(this);
			try {
				sleep((int)(Math.random() * MAX_EAT_TIME));
			} catch (InterruptedException e) { ; }
			forkControl.put(this);
		}
	}
}
