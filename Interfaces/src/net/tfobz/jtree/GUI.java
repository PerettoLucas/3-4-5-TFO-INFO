package net.tfobz.jtree;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

public class GUI extends JFrame
{

	private JPanel contentPane;
	private TreePopup treePopup = null;

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
		
		DefaultTreeModel root = new DefaultTreeModel(new Konstante(2));
		
		TreeNode k2 = new Konstante(2);
		/*  http://esus.com/displaying-a-popup-menu-when-right-clicking-on-a-jtree-node/  */
		
		TreeModel treeModel = new DefaultTreeModel((TreeNode) root);
		
		JTree tree = new JTree(treeModel);
		tree.setBounds(12, 0, 600, 418);
		contentPane.add(tree);

		
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
	
	@SuppressWarnings("serial")
	class TreePopup extends JPopupMenu 
	{
		   public TreePopup(JTree tree) 
		   {
		      JMenuItem itemDelete = new JMenuItem("Delete");
		      JMenuItem itemAdd = new JMenuItem("Add");
		      
		      //Action Listener
		      itemDelete.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent ae) {
		            System.out.println("Delete child");
		         }
		      });
		      itemAdd.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent ae) {
		            System.out.println("Add child");
		            
		         }
		      });
		  
		      add(itemDelete);
		      add(new JSeparator());
		      add(itemAdd);
		   }
	}
}
