package Classes;

import Interfaces.ICalculator; 


public class Calculator implements ICalculator
{
	public int add(int x, int y)
	{
		return x+y;
	}
	
	public float divide(int x, int y) throws RuntimeException
	{
		if(y == 0)
		{
			throw new RuntimeException("Can't divide by 0");
		}
		return (float)x/y;
	}
}
