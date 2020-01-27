package net.tfobz.jtree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

public class MenueActionListener implements ActionListener
{
	private JTree jTree ;
	
	public MenueActionListener(JTree jTree)
	{
		this.jTree = jTree;
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		Operation operation_to_set;
		
		
		DefaultTreeModel treeModel = (DefaultTreeModel)jTree.getModel();
		if(jTree.getSelectionPath() == null)
		{
			//H�nge einen neuen Knoten an die Wurzel
			DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
			if(root.getChildCount() == 0)
			{
				treeModel.insertNodeInto(new Konstante(), root, 0);
				treeModel.reload();
			}
			jTree.setSelectionPath(new TreePath(root));
		} else {
			//H�nge den Knoten zum ausgew�hlten knoten
			MutableTreeNode treeNode = (MutableTreeNode) jTree.getSelectionPath().getLastPathComponent();
			System.out.println(treeNode.getChildCount());
			if(treeNode instanceof Operation && ((Operation)treeNode).getChildCount() < 2) 
			{	
				System.out.println("New");
				treeModel.insertNodeInto(new Konstante(), treeNode, 0);
				TreePath treePath = jTree.getSelectionPath();
				System.out.println(treePath.toString());
				treeModel.reload();
				jTree.expandPath(treePath);
			}
		}
	}

}
