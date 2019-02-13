package io;

import java.util.Comparator;

class HeightComparator implements Comparator<EncodeNode> { 
    public int compare(EncodeNode x, EncodeNode y) 
    { 
    	if(x.value == y.value) {
    		return (x.height(x) - y.height(y));
    	}
    		
        return x.value - y.value; 
    } 
} 