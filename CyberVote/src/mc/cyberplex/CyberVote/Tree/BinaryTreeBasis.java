package mc.cyberplex.CyberVote.Tree;

class TreeNode {
	
	int vote;
	String playerName;
	TreeNode leftChild;
	TreeNode rightChild;
	
	public TreeNode(int vote_, String playerName_) {
		vote = vote_;
		playerName = playerName_;
		leftChild = null;
		rightChild = null;
	}
	
	public TreeNode(int vote_, String playerName_, TreeNode left, TreeNode right) {
		vote = vote_;
		playerName = playerName_;
		leftChild = left;
		rightChild = right;
	}
	
}

public abstract class BinaryTreeBasis {

	protected TreeNode root;
	
	public BinaryTreeBasis() {
		root = null;
	}
	
	public BinaryTreeBasis(int rootVote, String rootPlayer) {
		root = new TreeNode(rootVote, rootPlayer, null, null);
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void makeEmpty() {
		root = null;
	}
	
	public String getRootItem() throws TreeException {
		if(root == null) {
			throw new TreeException("TreeException: Empty tree");
		} else {
			return root.playerName;
		}
	}
	
	public abstract void setRootItem(int vote, String player);
	
}
