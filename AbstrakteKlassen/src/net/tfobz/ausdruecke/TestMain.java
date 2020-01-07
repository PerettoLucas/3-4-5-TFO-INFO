package net.tfobz.ausdruecke;

public class TestMain
{

	public static void main(String[] args)
	{
		//Test Operation
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
		
		//Test pow
		Operation pow = new Potenz(new Konstante(3),new Konstante(3));
		System.out.println(pow.toString()+"\n");
		
		//Test logarithmus 
		Logarithmus log = new Logarithmus(new Argument(3), new Konstante(2));		
		System.out.println(log.toString());
		
		//Test Wurzel
		Wurzel wurzel = new Wurzel(new Argument(3), new Konstante(9));
		System.out.println(wurzel.toString());
		
		
		//Ausdruck
		Operand ausdruck = new Potenz(
					new Division(
						new Multiplikation(new Konstante(3),
								new Potenz(new Addition(new Konstante(6), new Konstante(7)),
								new Konstante(5))),
					new Logarithmus(
							new Argument(10),
							new Wurzel(
									new Argument(2),
									new Addition(
											new Division(new Konstante(70), new Konstante(4)),
											new Division(new Konstante(990), new Konstante(8)
						))))),
				new Konstante(4)); 
		
		System.out.println(ausdruck.toString());
	}

}
