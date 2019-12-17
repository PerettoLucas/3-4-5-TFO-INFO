package net.tfobz.ausdruecke;

public class TestMain
{

	public static void main(String[] args)
	{
		Operation o = new Division(
						new Multiplikation(
							new Konstante(2),
								new Addition(
										new Konstante(3.0),
										new Konstante(4.0)
								)
						),
						new Subtraktion(
							new Konstante(7.0),
							new Konstante(2.0)
						)
					);
		
		System.out.println(o.toString());
		
		
		Operation pow = new Potenz(new Konstante(3),new Konstante(3));
		
		System.out.println(pow.toString());
	}

}
