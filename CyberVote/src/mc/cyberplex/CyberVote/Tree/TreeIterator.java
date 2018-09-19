package mc.cyberplex.CyberVote.Tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TreeIterator implements Iterator<String>{
	private BinaryTreeBasis binTree;
	private TreeNode currentNode;
	private LinkedList<TreeNode> queue;
	
	public TreeIterator(BinaryTreeBasis btree) {
		binTree = btree;
		currentNode = null;
		queue = new LinkedList<TreeNode>();
	}
	
	public boolean hasNext() {
		return !queue.isEmpty();
	}
	
	public String next() throws NoSuchElementException {
		currentNode = queue.remove();
		return currentNode.playerName;
	}
	
	public void remove() throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	
	public void setPreorder() {
		queue.clear();
		preorder(binTree.root);
	}
	
	public void setInorder() {
		queue.clear();
		inorder(binTree.root);
	}
	
	public void setPostorder() {
		queue.clear();
		postorder(binTree.root);
	}
	
	private void preorder(TreeNode treeNode) {
		if(treeNode != null) {
			queue.add(treeNode);
			preorder(treeNode.leftChild);
			preorder(treeNode.rightChild);
		}
	}
	
	private void inorder(TreeNode treeNode) {
		if(treeNode != null) {
			inorder(treeNode.leftChild);
			queue.add(treeNode);
			inorder(treeNode.rightChild);
		}
	}
	
	private void postorder(TreeNode treeNode) {
		if(treeNode != null) {
			postorder(treeNode.leftChild);
			postorder(treeNode.rightChild);
			queue.add(treeNode);
		}
	}
	
}
