package io;

public class Node {
	Node left;
	Node right;
	int value;
	
	public Node(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public Node() {
		this.value = -1;
		this.left = null;
		this.right = null;
	}
	
	boolean isFull() {
		
		if(isLeaf()) {
			return true;
		}
		
		//if(this.value != -1) {
		//	return true;
		//}
		
		if(this.left == null || this.right == null) {
			return false;
		}
		
		if(left.isFull() && right.isFull()){
			return true;
		}		
		
		//if(this.left.isLeaf() && this.left.isLeaf()) {
		//	return true;
		//}
		
		return false;
		
	}
	
	boolean isLeaf() {
		if(this.left == null && this.right == null && this.value != -1) {
			return true;
		}
		return false;
	}
}
