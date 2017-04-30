import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * @author Alex Cherniak
 *this class represents a drawing panel for a graph
 *a click on the panel will add a vertex into the graph
 *the rest of the options will be represented by buttons on the app's screen
 */
public class DrawPanel extends JPanel
{	
	/******************************************************************************************************************************
	 * Instance Variables
	 *****************************************************************************************************************************/
	protected Graph myGraph = new Graph(); //creates a new empty graph
	
	/******************************************************************************************************************************
	 * Constructor
	 ******************************************************************************************************************************/
	public DrawPanel()
	{
		super();
		this.addMouseListener(new Listener());
	}
	
	/******************************************************************************************************************************
	 * Graphics
	 ******************************************************************************************************************************/
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int radius = 10;
		for(int i=0;i<myGraph.vertices.size();i++)
		{
			g2.fillOval(myGraph.vertices.get(i).getX()-(radius/2), myGraph.vertices.get(i).getY()-(radius/2), 20, 20);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawString(myGraph.vertices.get(i).toString(), myGraph.vertices.get(i).getX(), myGraph.vertices.get(i).getY() - 10);
		}
		g2.setStroke(new BasicStroke(3));
		for(int i=0;i<myGraph.edges.size();i++)
		{
			g2.drawLine(myGraph.edges.get(i).getFirstVertex().getX(), myGraph.edges.get(i).getFirstVertex().getY(), myGraph.edges.get(i).getSecondVertex().getX(), myGraph.edges.get(i).getSecondVertex().getY());
		}
		repaint();
	}
	
	private class Listener extends MouseAdapter
	{

		@Override
		public void mouseClicked(MouseEvent e) 
		{
			char[] charInput;
			Vertex inputVertex;
			String input = JOptionPane.showInputDialog("Please enter Vertex - 1 letter Character");
			if(input != null) //user didnt press cancel
			{
				charInput = input.toCharArray();
				if(charInput.length != 1) //user inserted more than 1 char
				{
					JOptionPane.showMessageDialog(null, "incorrect input");
				}
				else
				{
					if(Character.isLetter(charInput[0]))
					{
						inputVertex = new Vertex(charInput[0] , e.getX() , e.getY());
						if(myGraph.addVertex(inputVertex))
						{
							JOptionPane.showMessageDialog(null, "Vertex " + input + " added to the Graph"); //added successfully
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Vertex wasnt added - Vertex is already on the Graph");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please insert a letter", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
			}
			
		}
		
	}
	
	
	
}
