package net.tfobz.Philosopher;

public class ForkControl
{
	private Fork left, right = null;
	
	public ForkControl(Fork left, Fork right)
	{
		this.left = left;
		this.right = right;
	}
	
	public synchronized void get(Philosopher p)
	{
		left.get(p);
		right.get(p);
	}
	
	public synchronized void put(Philosopher p)
	{
		left.put(p);
		right.put(p);
	}
	
}
