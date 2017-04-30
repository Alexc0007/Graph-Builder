
/**
 * @author Alex Cherniak
 *this class represents and Edge
 *each edge is represented by 2 vertices
 */
public class Edge 
{
	/**************************************************************************************************************
	 * instance variables
	 *************************************************************************************************************/
	private Vertex firstVertex; //represents the first vertex on the edge
	private Vertex secondVertex; //represents the second vertex on the edge
	
	
	/***************************************************************************************************************
	 * Constructors
	 **************************************************************************************************************/
	//constructor that builds an edge out of 2 vertices
	public Edge(Vertex a , Vertex b)
	{
		firstVertex = new Vertex(a.getName() , a.getX() , a.getY());
		secondVertex = new Vertex(b.getName() , b.getX() , b.getY());
	}
	//constructor that build an edge out of 2 chars
	public Edge(char a , char b)
	{
		firstVertex = new Vertex('a');
		secondVertex = new Vertex('b');
	}

	/*
	 * return a representation of the edge as a string
	 */
	public String toString()
	{
		return "(" + firstVertex.toString() + "," + secondVertex.toString() + ")";
	}
	
	
	/***************************************************************************************************************
	 * getters and setters
	 **************************************************************************************************************/
	public Vertex getFirstVertex() 
	{
		return new Vertex (firstVertex.getName() , firstVertex.getX() , firstVertex.getY());
	}


	public void setFirstVertex(Vertex vertex) 
	{
			this.firstVertex = new Vertex(vertex.getName(), vertex.getX() , vertex.getY());
	}


	public Vertex getSecondVertex() 
	{
		return new Vertex (secondVertex.getName() , secondVertex.getX() , secondVertex.getY());
	}


	public void setSecondVertex(Vertex vertex) 
	{
		this.secondVertex = new Vertex(vertex.getName(), vertex.getX() , vertex.getY());
	}
	
	
}
