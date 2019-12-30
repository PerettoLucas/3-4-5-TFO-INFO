package net.tfobz.ausdruecke;

public abstract class ArgOperation extends Operand
{
	private Operand operand = null;
	private Argument argument = null;
	

	public ArgOperation(Argument argument, Operand operand)
	{
		this.setOperand(operand);
		this.setArgument(argument);
	}
	
	public ArgOperation()
	{
		super();
	}

	public Operand getOperand()
	{
		return this.operand;
	}
	
	public void setOperand(Operand operand)
	{
		if(operand != null) this.operand = operand;
	}
	
	public Argument getArgument()
	{
		return this.argument;
	}
	
	public void setArgument(Argument argument)
	{
		if(argument != null) this.argument = argument;
	}
}
