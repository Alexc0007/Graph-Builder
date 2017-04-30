import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;


/**
 * @author Alex Cherniak
 * this class represents the main window for the Graph Builder app
 * all the components will be built on it
 */
public class GrapWindow extends JPanel
{

	/**************************************************************************************************************************
	 * Instance Variables
	 **************************************************************************************************************************/
	private JFrame frmGraphBuilder;
	private DrawPanel drawPanel;
	private JButton clearScreenBtn;
	private JButton addEdgeBtn;
	private JButton removeEdgeBtn;
	private JButton removeVertexBtn;
	private JTextArea screenTA;
	private JButton showGraphBtn;
	

	/**************************************************************************************************************************
	 * Launch the application - Main
	 *************************************************************************************************************************/
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					GrapWindow window = new GrapWindow();
					window.frmGraphBuilder.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/***************************************************************************************************************************
	 * Create the application.
	 **************************************************************************************************************************/
	public GrapWindow() 
	{
		initialize();
	}

	/***************************************************************************************************************************
	 * Initialize the contents of the frame.
	 ***************************************************************************************************************************/
	private void initialize() 
	{
		//create the frame
		frmGraphBuilder = new JFrame();
		frmGraphBuilder.setResizable(false);
		frmGraphBuilder.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\\u05DE\u05D3\u05E2\u05D9 \u05D4\u05DE\u05D7\u05E9\u05D1 - \u05EA\u05D5\u05D0\u05E8 \u05E8\u05D0\u05E9\u05D5\u05DF\\Advanced Java\\Graphs\\src\\graphIcon.png"));
		frmGraphBuilder.setTitle("Graph Builder");
		frmGraphBuilder.setBounds(100, 100, 1009, 717);
		frmGraphBuilder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGraphBuilder.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		//create the main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(UIManager.getColor("info"));
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(192, 192, 192), new Color(255, 165, 0)));
		frmGraphBuilder.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		
		//clear screen button
		clearScreenBtn = new JButton("Clear Screen");
		clearScreenBtn.setForeground(new Color(0, 0, 255));
		clearScreenBtn.setFont(new Font("Nirmala UI", Font.ITALIC, 14));
		clearScreenBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				drawPanel.myGraph = new Graph(); //create a new empty graph
			}
		});
		clearScreenBtn.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 0), null, null, null));
		clearScreenBtn.setBackground(new Color(255, 51, 51));
		clearScreenBtn.setBounds(12, 599, 132, 47);
		mainPanel.add(clearScreenBtn);
		
		//create drawpanel which the graph sits on
		drawPanel = new DrawPanel();
		drawPanel.setBackground(new Color(240, 255, 255));
		drawPanel.setBounds(12, 13, 977, 573);
		mainPanel.add(drawPanel);
		
		//add edge button
		addEdgeBtn = new JButton("Add Edge");
		addEdgeBtn.setFont(new Font("Nirmala UI", Font.ITALIC, 14));
		addEdgeBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				char[] charInput;
				String input = JOptionPane.showInputDialog("Please insert the new edge to insert to the graph , exampe: A,B");
				if(input != null) //user inserted some input
				{
					input = input.replaceAll("\\s+", ""); //remove white spaces from input
					//a valid input will contain 3 characters: A,B
					charInput = input.toCharArray();
					if(charInput.length == 3) //a valid length of the input
					{
						if(Character.isLetter(charInput[0]) && charInput[1] == ',' && Character.isLetter(charInput[2])) //a valid input
						{
							//create 2 new vertices
							Vertex a = new Vertex(charInput[0]);
							Vertex b = new Vertex(charInput[2]);
							if(drawPanel.myGraph.addEdge(a, b))//if edge added successfully
							{
								JOptionPane.showMessageDialog(null, "Edge between " + a.getName() + " and " + b.getName() + " added successfully!");
								drawPanel.repaint();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Edge is either alrady on the graph OR one/both of the vertices is missing", "Edge was not added", JOptionPane.ERROR_MESSAGE);

							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Invalid input", "Invalid Input", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid input", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid input", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		addEdgeBtn.setBackground(new Color(255, 255, 240));
		addEdgeBtn.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLUE, new Color(255, 255, 240), null, null));
		addEdgeBtn.setBounds(575, 599, 131, 47);
		mainPanel.add(addEdgeBtn);
		
		//remove edge button
		removeEdgeBtn = new JButton("Remove Edge");
		removeEdgeBtn.setFont(new Font("Nirmala UI", Font.ITALIC, 14));
		removeEdgeBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				char[] charInput;
				String input = JOptionPane.showInputDialog("Please insert Edge you would like to remove! example: A,B");
				if(input != null)//means user inserted some input
				{
					input = input.replaceAll("\\s+", ""); //remove white spaces from input
					charInput = input.toCharArray(); //convert input into charArray
					if(charInput.length == 3) //a valid input length
					{
						if(Character.isLetter(charInput[0]) && charInput[1] == ',' && Character.isLetter(charInput[2])) //true when input fits the pattern
						{
							Vertex v1 = new Vertex(charInput[0]);
							Vertex v2 = new Vertex(charInput[2]);
							if(drawPanel.myGraph.removeEdge(v1, v2)) //true if edge removed successfully 
							{
								JOptionPane.showMessageDialog(null, "Edge " + charInput[0] + "," + charInput[2] + " removed successfully!");
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Edge could not be removed because its not present on the Graph", "Error Removing Edge", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else //incorrect input length
					{
						JOptionPane.showMessageDialog(null, "Invalid Input", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "you cancelled", "no input", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		removeEdgeBtn.setBackground(new Color(153, 153, 255));
		removeEdgeBtn.setBorder(new BevelBorder(BevelBorder.RAISED, Color.CYAN, null, null, null));
		removeEdgeBtn.setBounds(718, 599, 115, 47);
		mainPanel.add(removeEdgeBtn);
		
		//remove vertex button
		removeVertexBtn = new JButton("Remove Vertex");
		removeVertexBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				char[] charInput;
				String input = JOptionPane.showInputDialog("Please insert vertex to remove! example: A");
				if(input != null) //user inserted some input
				{
					input = input.replaceAll("\\s+", ""); //remove white spaces
					charInput = input.toCharArray(); //convert input into a char array
					if(charInput.length == 1) //correct input patter
					{
						if(Character.isLetter(charInput[0])) //true when input is 1 letter
						{
							Vertex v = new Vertex(charInput[0]);
							if(drawPanel.myGraph.removeVertex(v))
							{
								JOptionPane.showMessageDialog(null, "Vertex " + charInput[0] + " Removed successfully");
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Vertex " + charInput[0] + " is not on the Graph" , "Error" , JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Invalid Input", "Invalid Input", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid Input", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "You Cancelled", "No vertex removed", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		removeVertexBtn.setFont(new Font("Nirmala UI", Font.ITALIC, 14));
		removeVertexBtn.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 0), null, null, null));
		removeVertexBtn.setBackground(new Color(255, 160, 122));
		removeVertexBtn.setBounds(845, 599, 132, 47);
		mainPanel.add(removeVertexBtn);
		
		//add scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 598, 321, 47);
		mainPanel.add(scrollPane);
		//add screen on scroll pane
		screenTA = new JTextArea();
		screenTA.setEditable(false);
		scrollPane.setViewportView(screenTA);
		
		//show graph button - will display graph's toString on a screen
		showGraphBtn = new JButton("<html>Show<br />Graph</html>");
		showGraphBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				screenTA.setText(drawPanel.myGraph.toString());
			}
		});
		showGraphBtn.setFont(new Font("Nirmala UI", Font.PLAIN, 10));
		showGraphBtn.setBounds(489, 600, 74, 47);
		mainPanel.add(showGraphBtn);
		
		//created by label
		JLabel lblCreatedByAlex = new JLabel("Created By: Alex Cherniak");
		lblCreatedByAlex.setForeground(Color.RED);
		lblCreatedByAlex.setFont(new Font("Nirmala UI Semilight", Font.ITALIC, 11));
		lblCreatedByAlex.setBounds(855, 659, 134, 16);
		mainPanel.add(lblCreatedByAlex);
		
		
	
	}
}
