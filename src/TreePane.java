import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.tree.*;
import java.io.*;

public class TreePane extends JScrollPane implements Serializable {
    protected DefaultMutableTreeNode rootNode;
    protected DefaultTreeModel treeModel;
    protected JTree tree;
    private File serializedFile;

    //making this class a scroll pane was the easiest way to implement dynamic structure changes
    public TreePane() {
        //tree model starts out null so it can be deserialized later
        treeModel = null;
        rootNode = new DefaultMutableTreeNode("Set Name");
        if (treeModel == null) {
            treeModel = new DefaultTreeModel(rootNode);
        }
        treeModel.addTreeModelListener(new TreeModelListener());
        tree = new JTree(treeModel);
        tree.setEditable(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);

        setViewportView(tree); //puts tree in scrollpane
    }
    //removes all child nodes from the root node
    //sets tree back to one node (root)
    public void clear() {
        rootNode.setUserObject("Set Name"); //changes name of node
        rootNode.removeAllChildren();
        treeModel.reload(); //you would think that if this works, deserialization would work by just reloading
    }

    //deletes a node
    public void removeCurrentNode() {
        TreePath currentSelection = tree.getSelectionPath();
        if (currentSelection != null) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)
                    (currentSelection.getLastPathComponent());
            MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
            if (parent != null) {
                treeModel.removeNodeFromParent(currentNode);
                return;
            }
        }
    }

    //add the note from addObject(parent, child, shouldBeVisible) based on where the parent node is
    public DefaultMutableTreeNode addObject(Object child) {
        DefaultMutableTreeNode parentNode = null;
        TreePath parentPath = tree.getSelectionPath();

        if (parentPath == null) {
            parentNode = rootNode;
        } else {
            parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
        }
        return addObject1(parentNode, child, true);
    }

    //inserts a node into the tree. The node is given to addObject(child)
    public DefaultMutableTreeNode addObject1(DefaultMutableTreeNode parent, Object child,
                                             boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
        if (parent == null) {
            parent = rootNode;
        }

        treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

        if (shouldBeVisible) {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }

    //notifies on node change
    class TreeModelListener implements javax.swing.event.TreeModelListener {
        @Override
        public void treeNodesChanged(TreeModelEvent e) {
            DefaultMutableTreeNode node;

            node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());
            if(node != rootNode) {
                int index = e.getChildIndices()[0];
                node = (DefaultMutableTreeNode) (node.getChildAt(index));
            }
            System.out.println("Finished editing node.");
            System.out.println("New value: " + node.getUserObject());
        }

        @Override
        public void treeNodesInserted(TreeModelEvent e) {

        }

        @Override
        public void treeNodesRemoved(TreeModelEvent e) {

        }

        @Override
        public void treeStructureChanged(TreeModelEvent e) {

        }
    }

    //allows user to save tree structures
    public void serialize() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int value = fileChooser.showSaveDialog(new TreePane());
            if (value == JFileChooser.APPROVE_OPTION){
                serializedFile = fileChooser.getSelectedFile();
                FileOutputStream file = new FileOutputStream(serializedFile);
                ObjectOutputStream outputStream = new ObjectOutputStream(file);
                outputStream.writeObject(treeModel);
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //loads saved tree structures
    public void deserialize() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int value = fileChooser.showOpenDialog(new TreePane());
            if (value == JFileChooser.APPROVE_OPTION) {
                serializedFile = fileChooser.getSelectedFile();
                FileInputStream file = new FileInputStream(serializedFile);
                ObjectInputStream inputStream = new ObjectInputStream(file);
                treeModel = (DefaultTreeModel) inputStream.readObject();

                //these make the deserialized tree appear
                treeModel.reload(); //refreshes tree model
                tree.setModel(treeModel); //puts new tree model into the tree

                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
