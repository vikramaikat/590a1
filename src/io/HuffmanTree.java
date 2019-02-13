package io;

public class HuffmanTree {
	Node root;
	
	public HuffmanTree() {
		this.root = new Node();
	}
	
	public void insert(int value, int length) {
		Node currentNode = root;
		for(int i = 0; i < length; i++){
			if(currentNode.left == null) {
				if(i + 1 == length) {
					currentNode.left = new Node(value);
					break;
				}
				else {
					currentNode.left = new Node(-1);
				}
				currentNode = currentNode.left;
			}else if(!currentNode.left.isFull()){
				currentNode = currentNode.left;
			}else if(currentNode.right == null) {
				if(i + 1 == length) {
					currentNode.right = new Node(value);
					break;
				}
				else {
					currentNode.right = new Node(-1);
				}
				currentNode = currentNode.right;
			}else if(!currentNode.right.isFull()) {
				currentNode = currentNode.right;
			}
		}
		
	}

}
