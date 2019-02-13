package io;

class EncodeNode { 
  
    int value; 
    int c; 
  
    EncodeNode left; 
    EncodeNode right; 
    
    public int height(EncodeNode node)  
    { 
        if (node == null) {
        	return 0;
        } 
        else { 
            int left = height(node.left); 
            int right = height(node.right); 
   
            if (left > right) {
                return (left + 1); 	
            }else {
            	 return (right + 1); 
             }
                 
        } 
    } 
} 