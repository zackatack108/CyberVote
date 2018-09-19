package mc.cyberplex.CyberVote.Tree;

public class BinaryTree extends BinaryTreeBasis {
	public BinaryTree() {}

	public BinaryTree(int rootVote, String rootPlayer) {
		super(rootVote, rootPlayer);
	}
	
	public BinaryTree(int rootVote, String rootPlayer, BinaryTree leftTree, BinaryTree rightTree) {
		root = new TreeNode(rootVote, rootPlayer, null, null);
		attachLeftSubTree(leftTree);
		attachRightSubTree(rightTree);
	}
	
	public void setRootItem(int rootVote, String rootPlayer) {
		if(root != null) {
			root.vote = rootVote;
			root.playerName = rootPlayer;
		} else {
			root = new TreeNode(rootVote, rootPlayer, null, null);
		}
	}
	
	public void attachLeft(int vote, String player) {
		if(!isEmpty() && root.leftChild == null) {
			root.leftChild = new TreeNode(vote, player, null, null);
		}
	}
	
	public void attachRight(int vote, String player) {
		if(!isEmpty() && root.rightChild == null) {
			root.rightChild = new TreeNode(vote, player, null, null);
		}
	}
	
	public void attachLeftSubTree(BinaryTree leftTree) throws TreeException {
		if(isEmpty()) {
			throw new TreeException("TreeException: Empty tree");
		} else if(root.leftChild != null) {
			throw new TreeException("TreeException: " + "Cannot overwrite left subtree");
		} else {
			root.leftChild = leftTree.root;
			leftTree.makeEmpty();
		}
	}
	
	public void attachRightSubTree(BinaryTree rightTree) throws TreeException {
		if(isEmpty()) {
			throw new TreeException("TreeException: Empty tree");
		} else if(root.rightChild != null) {
			throw new TreeException("TreeException: " + "Cannot overwrite right subtree");
		} else {
			root.rightChild = rightTree.root;
			rightTree.makeEmpty();
		}
	}
	
	protected BinaryTree(TreeNode rootNode) {
		root = rootNode;
	}
	
	public BinaryTree detachLeftSubTree() throws TreeException {
		if(isEmpty()) {
			throw new TreeException("TreeException: Empty Tree");
		} else {
			BinaryTree leftTree;
			leftTree = new BinaryTree(root.leftChild);
			root.leftChild = null;
			return leftTree;
		}
	}
	
	public BinaryTree detachRightSubTree() throws TreeException {
		if(isEmpty()) {
			throw new TreeException("TreeException: Empty Tree");
		} else {
			BinaryTree rightTree;
			rightTree = new BinaryTree(root.rightChild);
			root.rightChild = null;
			return rightTree;
		}
	}
	
}
