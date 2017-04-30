import java.util.ArrayList;



/**
 * @author Alex Cherniak
 *this class represents a graph that is made of vertices and edges
 */
public class Graph 
{
	/***********************************************************************************************************
	 * Instance Variables
	 **********************************************************************************************************/
	protected ArrayList<Vertex> vertices = new ArrayList<Vertex>(); //array list of characters to represent the vertices on the graph
	protected ArrayList<Edge> edges = new ArrayList<Edge>(); //array list of edges to represent the edges on the graph
	
	
	/***********************************************************************************************************
	 * Constructors
	 ***********************************************************************************************************/
	//empty constructor - creates an empty graph
	public Graph()
	{
		
	}
	 //a constructor that gets an array of vertices and an array of edges and creates a graph out of them
	public Graph(Vertex[] vertices , Edge[] edges)
	{
		for(int i=0;i<vertices.length;i++) //loop over the vertices array and try to add all the vertices to the graph
		{
			this.addVertex(vertices[i]);
		}
		for (int i=0;i<edges.length;i++)
		{
			if(vertexIsPresent(edges[i].getFirstVertex()) && vertexIsPresent(edges[i].getSecondVertex())) //verify that both vertices are present
			{
				this.addEdge(edges[i].getFirstVertex(), edges[i].getSecondVertex()); //add edge between those 2 vertices
			}
		}
	}
	
	/************************************************************************************************************
	 * Methods
	 ************************************************************************************************************/
	
	/*
	 * add vertex method:
	 * will return true if vertex is not present in the vertices list and was added to the list
	 * will return false if the vertex that passed as an argument is already on the list and wasnt added
	 * will also return false when the vertex's name isnt a letter
	 */
	public boolean addVertex(Vertex vertex)
	{
		for(int i=0;i<vertices.size();i++) //loop over the array list
		{
			if(Character.isLetter(vertex.getName())== false) //vertex's name isnt a letter
			{
				return false; //invalid name for argument
			}
			if(vertex.getName() == vertices.get(i).getName()) //check if the vertex is already in the vertices list
				return false; //found the current vertex in the list - return false
		}
		vertices.add(new Vertex(vertex.getName() , vertex.getX() , vertex.getY())); //no such vertex in the list - adding the vertex to the list
		return true; //return true when vertex is added to the list
	}
	
	/*
	 * remove vertex method:
	 * will return true if the vertex that was passed as an argument was removed successfully
	 * will return false if the vertex that was passed as argument wasnt removed successfully
	 * false state can be cased by: invalid character passed on the argument OR vertex is not present on the graph
	 */
	public boolean removeVertex(Vertex vertex)
	{
		for(int i=0;i<this.vertices.size();i++) //go over the vertices list and look for the given vertex
		{
			if(vertex.getName() == vertices.get(i).getName())
			{
				for(int j=0;j<edges.size();j++) //scan for all edges to find removed vertex and remove all edges that interacts with the vertex
				{
					if(vertex.getName() == edges.get(j).getFirstVertex().getName() || vertex.getName() == edges.get(j).getSecondVertex().getName())
					{
						removeEdge(edges.get(j).getFirstVertex(), edges.get(j).getSecondVertex()); //remove edge with the removed vertex
						j--;
					}
				}
				vertices.remove(i);
				return true; //return true when vertex removed successfully
			}
		}
		return false; //return false when was unable to remove the vertex passed in the argument
	}
	
	/*
	 * add edge method - will add an edge to the graph between 2 existing vertices
	 * return true when found both of the vertices given as arguments on the graph and added the new edge
	 * return false if the edge is already on the graph or if unable to add the edge because vertices are missing on the graph
	 */
	public boolean addEdge(Vertex vertex1 , Vertex vertex2)
	{
		int count=0; //a counter
		//check if the edge is already on the graph
		Edge newEdge = new Edge(vertex1, vertex2); //create the new edge from the arguments vertex1 and vertex2
		for(int i=0;i<edges.size();i++)
		{
			if(vertex1.getName() == edges.get(i).getFirstVertex().getName() || vertex1.getName() == edges.get(i).getSecondVertex().getName())
			{
				if(vertex2.getName() == edges.get(i).getFirstVertex().getName() || vertex2.getName() == edges.get(i).getSecondVertex().getName())
				{
					return false; //the edge is already on the graph
				}
			}
		}
		//check that vertex1 and vertex2 are present on the graph
		for(int i=0;i<vertices.size();i++)
		{
			if(vertex1.getName() == vertices.get(i).getName())
			{
				count++;
				newEdge.setFirstVertex(vertices.get(i));
			}
				
			if(vertex2.getName() == vertices.get(i).getName())
			{
				count++;
				newEdge.setSecondVertex(vertices.get(i));
			}
		}
		/*
		 * if count is 2 , it means that both vertices are present on the current graph
		 * since we cant duplicate vertices , count can only be 0,1,2
		 * if the count is 0 , it means that nor vertex1 or vertex2 are on the graph
		 * if the count is 1 - it means only 1 of the vertices (vertex1 or vertex2) are on the graph
		 */
		if(count == 2) //found vertex1 and vertex2 on the graph
		{
			edges.add(newEdge); //add the new edge to the edges list
			return true;
		}
		return false; //count is not 2 - one or both of the vertices are not present on the graph
	}
	
	/*
	 * remove edge method - will remove an edge represented by 2 vertices from the graph if the edge is present
	 * will return true if successfully removed an edge from the graph
	 * will return false if couldnt find the edge on the graph or if the arguments that passed to the method are not viable letters
	 */
	public boolean removeEdge(Vertex vertex1 , Vertex vertex2)
	{
		if(vertex1.getName() == vertex2.getName())
		{
			return false; //vertex1 is the same vertex as vertex2
		}
		//need to find vertex1 and vertex2 in the edges list
		for(int i=0;i<edges.size();i++)
		{
			if(vertex1.getName() == edges.get(i).getFirstVertex().getName())
			{
				if(vertex2.getName() == edges.get(i).getSecondVertex().getName()) //found the edge on the graph
				{
					edges.remove(i); //remove the edge from the graph
					return true;
				}
			}
		}
		for(int i=0;i<edges.size();i++)
		{
			if(vertex2.getName() == edges.get(i).getFirstVertex().getName())
			{
				if(vertex1.getName() == edges.get(i).getSecondVertex().getName()) //found the edge on the graph
				{
					edges.remove(i); //remove the edge from the graph
					return true;
				}
			}
		}
		//didnt find the edge on the graph
		return false;
	}
	
	/*
	 * vertexIsPresent checks if the given vertex is present on the graph
	 * return true if vertex is present
	 * return false if vertex is not present or is not a viable letter
	 */
	public boolean vertexIsPresent(Vertex vertex)
	{
		for(int i=0;i<vertices.size();i++)
		{
			if(vertex.getName() == vertices.get(i).getName())
			{
				return true; //found the vertex on the graph
			}
		}
		return false; //didnt find the vertex on the graph
	}
	
	/*
	 * edgeIsPresent method - checks if and edge that is represented by vertex1 and vertex2 is present on the graph
	 * return true if edge is present on the graph
	 * return false if edge is not present on the graph or if vertex1 /vertex2 are not viable letters
	 */
	public boolean edgeIsPresent(Vertex vertex1 , Vertex vertex2)
	{
		for(int i=0;i<edges.size();i++)
		{
			if(vertex1.getName() == edges.get(i).getFirstVertex().getName())
			{
				if(vertex2.getName() == edges.get(i).getSecondVertex().getName()) 
				{
					return true;//found the edge on the graph
				}
			}
		}
		for(int i=0;i<edges.size();i++)
		{
			if(vertex2.getName() == edges.get(i).getFirstVertex().getName())
			{
				if(vertex1.getName() == edges.get(i).getSecondVertex().getName()) 
				{
					return true;//found the edge on the graph
				}
			}
		}
		return false; //didnt find the edge on the graph
	}
	
	/*
	 * return a string representation of the graph
	 */
	public String toString()
	{
		String vertices = "";
		String edges = "";
		for(int i=0;i<this.vertices.size();i++)
		{
			vertices = vertices + this.vertices.get(i).toString(); //add the vertex to the string vertices
			if(i < this.vertices.size()-1)
				vertices = vertices + ","; //add comma until we get to the last vertex
		}
		
		for(int i=0;i<this.edges.size();i++)
		{
			edges = edges + this.edges.get(i).toString();
			if(i < this.edges.size()-1)
				edges = edges + ",";
		}
		
		return "Vertices:" + "{" +  vertices + "}\n" + "Edges:" + "{" + edges + "}";
	}
	
	public boolean equals(Object graph)
	{
		if(graph instanceof Graph != true)
		{
			return false; //graphs are not equal because graph isnt a Graph
		}
		Graph g = new Graph();
		g = (Graph)graph;
		for(int i=0;i<this.vertices.size();i++)
		{
			if(g.vertexIsPresent(this.vertices.get(i)) == false) //make sure that all the vertices in this are vertices in graph
			{
				return false; //if found 1 vertex that is not in graph - return false , graph's are not equal
			}
		}
		for(int i=0;i<this.edges.size();i++)
		{
			//check if all the edges in this are edges in graph
			if(g.edgeIsPresent(this.edges.get(i).getFirstVertex(), this.edges.get(i).getSecondVertex()) == false)
			{
				return false; //found an edge from: this that is not in graph
			}
		}
		for(int i=0;i<g.vertices.size();i++)
		{
			if(this.vertexIsPresent(g.vertices.get(i)) == false) //check if all vertices graph are present in this
			{
				return false; //found a vertex in graph that is not present in this
			}
		}
		for(int i=0;i<g.edges.size();i++)
		{
			//check if all the edges in graph are present in this
			if(this.edgeIsPresent(g.edges.get(i).getFirstVertex(), g.edges.get(i).getSecondVertex()) == false)
			{
				return false; //found an edge in graph that is not present in this
			}
		}
		
		//if got here , it means all the edges and vertices in graph are present in this and all the edges and vertices in this are present in graph
		return true; //graphs are equal
	}
}                       
