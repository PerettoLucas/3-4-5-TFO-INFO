package net.tfobz.jtree;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import net.tfobz.funktionsplotter.Wurzel;

@SuppressWarnings("serial")
public class GUI extends JFrame
{

	private JPanel contentPane;
	private TreePopup treePopup = null;
	private DefaultMutableTreeNode wurzel = null;
	private TreePath treePath;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GUI frame=new GUI();
					frame.setVisible(true);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,624,455);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//DefaultMutableTreeNode ermöglicht es Dynamisch "Kinder hinzuzufügen"
		wurzel = new DefaultMutableTreeNode("Operation");
		wurzel.add(new Konstante(3));
	
		
		//Renderer setzt die Icons der Baumäste
		MeinDefaultTreeCellRenderer meinDefaultTreeCellRenderer = new MeinDefaultTreeCellRenderer();
		TreeModel treeModel = new DefaultTreeModel(wurzel);
		
		JTree tree = new JTree(treeModel);
		tree.setBounds(12, 0, 600, 418);
		contentPane.add(tree);
		//SetEditable true damit mann den inhalt verändern kann
		tree.setEditable(true);
		tree.setCellRenderer(meinDefaultTreeCellRenderer);

		//treePopup bietet das Menu um Veränderungen durchzuführen
		treePopup = new TreePopup(tree);
		
		tree.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				
			}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(e.getButton() == MouseEvent.BUTTON3) treePopup.show(e.getComponent(), e.getX(), e.getY());;
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	class TreePopup extends JPopupMenu 
	{
		public TreePopup(JTree tree) 
		{
			JMenuItem DeleteItem = new JMenuItem("Delete");
			JMenu AddItem = new JMenu("Neu");
			      
			JMenuItem konstanteItem = new JMenuItem("Konstante");
			JMenuItem additionItem = new JMenuItem("Addition");
			JMenuItem subtraktionItem = new JMenuItem("Subtraktion");
			JMenuItem multiplikationItem = new JMenuItem("Multiplikation");
			JMenuItem divisionItem = new JMenuItem("Division");
			      
			//Adding Items to the Menu-List
			AddItem.add(konstanteItem);
			AddItem.add(additionItem);
			AddItem.add(subtraktionItem);
			AddItem.add(multiplikationItem);
			AddItem.add(divisionItem);
	
			//Action Listener
			DeleteItem.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent ae) 
			  {
			 	 System.out.println("Delete child");
			   }
			});
	
			konstanteItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					int x=1;
					wurzel.add(new Konstante(x));
					x++;
				}
			});
			additionItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					DefaultMutableTreeNode SelectedNode;
					
					treePath = tree.getSelectionPath();
					
					
					SelectedNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
					
					wurzel.add(new Addition());
				}
			});   
			
			      
			add(DeleteItem);
			add(new JSeparator());
			add(AddItem);
		}
	}	
}
