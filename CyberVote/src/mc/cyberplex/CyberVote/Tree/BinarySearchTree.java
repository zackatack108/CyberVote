package mc.cyberplex.CyberVote.Tree;

public class BinarySearchTree extends BinaryTreeBasis {

	public BinarySearchTree() { }
	
	public BinarySearchTree(int rootVote, String rootPlayer) {
		super(rootVote, rootPlayer);
	}
	
	public void setRootItem(int vote, String Player) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	public void insert(int vote, String player) {
		root = insertItem(root, vote, player);
	}
	
	protected TreeNode insertItem(TreeNode tNode, int vote, String player)	{
		TreeNode newSubTree;
		if(tNode == null) {
			tNode = new TreeNode(vote, player, null, null);
			return tNode;
		}
		int nodeItem = tNode.vote;
		
		if(vote < nodeItem) {
			newSubTree = insertItem(tNode.leftChild, vote, player);
			tNode.leftChild = newSubTree;
			return tNode;			
		} else {
			newSubTree = insertItem(tNode.rightChild, vote, player);
			tNode.rightChild = newSubTree;
			return tNode;
		}
		
	}
	
}
