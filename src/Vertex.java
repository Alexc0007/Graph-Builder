

/**
 * @author Alex Cherniak
 *this class represents a vertex
 */
public class Vertex 
{
	/***************************************************************************************************************
	 * Instance Variables
	 **************************************************************************************************************/
	private char name; //represents the name of the vertex
	private int x; //represents the x coordinate of the vertex one the panel
	private int y; //represents the y coordinate of the vertex on the panel
	
	/***************************************************************************************************************
	 * Constructors
	 ***************************************************************************************************************/
	//default constructor with only the name field
	public Vertex(char name)
	{
		this.name = name;
		x=0;
		y=0;
	}
	
	//constructor with all fields
	public Vertex(char name , int x , int y)
	{
		this.name = name;
		this.setX(x);
		this.setY(y);
	}
	
	/***************************************************************************************************************
	 * methods
	 **************************************************************************************************************/
	//get vertex name method
	public char getName()
	{
		return name;
	}
	
	//setName - 1 char
	public void setName(char name)
	{
		this.name = name;
	}
	
	//getX method
	public int getX()
	{
		return this.x;
	}
	
	/*
	 * setX method - will set the x coordinate of the vertex
	 * x must be positive
	 */
	public void setX(int x)
	{
		if(x>0)
		{
			this.x = x;
		}
	}
	
	//getY method
	public int getY()
	{
		return this.y;
	}
	
	/*
	 * setY method - will set the y coordinate of the vertex
	 * y must be positive
	 */
	public void setY(int y)
	{
		if(y>0)
		{
			this.y = y;
		}
	}
	
	//toString method
	public String toString()
	{
		return Character.toString(this.name);
	}
	

}
