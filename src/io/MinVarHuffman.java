package io;

import java.util.Collections;
import java.util.PriorityQueue;

public class MinVarHuffman {
	static int[] lengths = new int[256];
    public static void getlens(EncodeNode root, String s) 
    {       if (root.left == null && root.right == null  && (root.c >= 0)) 
    	{ 
            //System.out.println(root.c + ":" + s); 
            lengths[root.c] = s.length(); 
            return; 
        } 
    	getlens(root.left, s + "0"); 
    	getlens(root.right, s + "1"); 
    } 
    
    public static int[] makeTree(int[] codes, int[] freqs, int n)
    {   
    	PriorityQueue<EncodeNode> q = new PriorityQueue<EncodeNode>(n, new HeightComparator()); 
  
        for (int i = 0; i < n; i++) { 
        	EncodeNode hn = new EncodeNode(); 
        	hn.left = null; 
            hn.right = null; 
            hn.c = codes[i]; 
            hn.value = freqs[i]; 
            
            q.add(hn); 
        } 
  
        EncodeNode root = null; 
        while (q.size() > 1) { 
        	EncodeNode x = q.peek(); 
            q.poll(); 

            EncodeNode y = q.peek(); 
            q.poll(); 
  
            EncodeNode f = new EncodeNode(); 
  
            //Combine the top 2
            f.value = x.value + y.value; 
            // Give it no char value
            f.c = -1; 
            f.left = x; 
            f.right = y; 
            root = f; 
            q.add(f); 
        } 
        getlens(root, ""); 
        //int max = 0;
        //for(int i = 0; i < lengths.length; i++) {
        	//System.out.println(i + " : " + lengths[i]);
        //	if(lengths[i] > max) {
        //		max = lengths[i];
        //	}
        //}
        //for(int i = 0; i < lengths.length; i++) {
        //	if(lengths[i] == 0) {
        //		lengths[i] = max;
        //	}
        //	System.out.println(i + " : " + lengths[i]);
        //}
        return lengths;

    } 
}
