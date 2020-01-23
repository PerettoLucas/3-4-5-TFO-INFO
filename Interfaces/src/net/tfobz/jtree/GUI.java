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
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class GUI extends JFrame
{

	private JPanel contentPane;
	private TreePopup treePopup = null;
	private DefaultMutableTreeNode wurzel = null;
	
	
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
		wurzel = new DefaultMutableTreeNode("unsichtbare Wurzel");
		wurzel.add(new Addition());
	
		
		//Renderer setzt die Icons der Baumäste
		MeinDefaultTreeCellRenderer meinDefaultTreeCellRenderer = new MeinDefaultTreeCellRenderer();
		TreeModel treeModel = new DefaultTreeModel(wurzel);
		
		JTree tree = new JTree(treeModel);
		tree.setBounds(12, 0, 600, 418);
		contentPane.add(tree);
		//SetEditable true damit mann den inhalt verändern kann
		tree.setEditable(true);
		tree.setCellRenderer(meinDefaultTreeCellRenderer);
		tree.setRootVisible(false);

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
			JMenuItem VertauscheItem = new JMenuItem("Vertausche");
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
			DeleteItem.addActionListener(event ->
			{
				DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();
				if(tree.getSelectionPath() != null)
				{
					TreePath parentTreePath = tree.getSelectionPath().getParentPath();
					
					//Getting TreeNode to delete from tree
					MutableTreeNode treeNode = (MutableTreeNode) tree.getSelectionPath().getLastPathComponent();
					MutableTreeNode parentTreeNode = (MutableTreeNode) parentTreePath.getLastPathComponent();
					
					//removing Child from parenttree
					parentTreeNode.remove(treeNode);

					treeModel.reload();
					tree.expandPath(parentTreePath);
				}
			});

			VertauscheItem.addActionListener(event ->
			{
				DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();
				
				if(tree.getSelectionPath() != null)
				{
					TreePath parentTreePath = tree.getSelectionPath().getParentPath();
					

					MutableTreeNode treeNode = (MutableTreeNode) tree.getSelectionPath().getLastPathComponent();
					MutableTreeNode parentTreeNode = (MutableTreeNode) parentTreePath.getLastPathComponent();
					
					((Operation)parentTreeNode).vertausche();
					
					
					treeModel.reload();
					tree.expandPath(parentTreePath);
				}
				
				
				
			});
			
			konstanteItem.addActionListener(event -> insertOperand(tree, new Konstante()));
			additionItem.addActionListener(event -> insertOperand(tree, new Addition()));   
			subtraktionItem.addActionListener(event -> insertOperand(tree, new Subtraktion()));
			multiplikationItem.addActionListener(event -> insertOperand(tree, new Multiplikation()));
			divisionItem.addActionListener(event -> insertOperand(tree, new Division()));
			
			konstanteItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			
			
			add(AddItem);
			add(new JSeparator());
			add(VertauscheItem);
			add(new JSeparator());
			add(DeleteItem);
		}
		
		private void insertOperand(JTree tree, Operand operation_to_set)
		{
			DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();
			if(tree.getSelectionPath() == null)
			{
				//Hänge einen neuen Knoten an die Wurzel
				DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
				if(root.getChildCount() == 0)
				{
					treeModel.insertNodeInto(new Konstante(), root, 0);
					treeModel.reload();
				}
			} else {
				//Hänge den Knoten zum ausgewählten knoten
				MutableTreeNode treeNode = (MutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				System.out.println(treeNode.getChildCount());
				if(treeNode instanceof Operation && ((Operation)treeNode).getChildCount() < 2) 
				{	
					System.out.println("New");
					treeModel.insertNodeInto(operation_to_set, treeNode, 0);
					TreePath treePath = tree.getSelectionPath();
					System.out.println(treePath.toString());
					treeModel.reload();
					tree.expandPath(treePath);
				}
			}
		}
		
	}	
}
