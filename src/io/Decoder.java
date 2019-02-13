package io;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class Decoder {
	
	 @SuppressWarnings("deprecation")
	public static void main(String[] args) throws InsufficientBitsLeftException, IOException {
		//String fileName = "data/compressed.dat";
		String fileName = "recompressed.dat";
	    		
	    try
	    {
	    		InputStream fis = new FileInputStream(fileName);
	    		InputStreamBitSource fstream = new InputStreamBitSource(fis);
	    		Map<Integer, ArrayList<Integer>> lengths = new HashMap<Integer, ArrayList<Integer>>();
	    		ArrayList<Integer> l = new ArrayList<Integer>();
	    		
	    		for(int i = 0; i <= 255; i++) {
	    			int len = fstream.next(8);
	    			if (lengths.containsKey(len)){
	    				lengths.get(len).add(i);
	    			}else {
	    				lengths.put(len, new ArrayList<Integer>());
	    				lengths.get(len).add(i);
	    				l.add(len);
	    			}
	    		}
	    		
	    		Collections.sort(l);
	    		//System.out.println(l);
	    		
	    		int symbols = fstream.next(32);
	    		
	    		HuffmanTree tree = new HuffmanTree();
	    		//System.out.println(lengths);
	    		System.out.println(symbols);
	    		for(int i = 0; i < lengths.size(); i++) {
	    			int len = l.get(i);
	    			//System.out.println(len);
	    			ArrayList<Integer> vals = lengths.get(len);
	    			//System.out.println(vals);
	    			for(int j = 0; j < vals.size(); j++) {
	    				tree.insert(vals.get(j), len);
	    			}
	    		}
	    		
	    		String fin = "";
	    		PrintWriter out = new PrintWriter("part2_decoded.txt");

	    		for(int i = 0; i < symbols; i++) {
	    			Node cur = tree.root;
	    			while(true) {
	    				int nextBit = fstream.next(1);
	    				if(nextBit == 0) {
	    					cur = cur.left;
	    				}else if(nextBit == 1) {
	    					cur = cur.right;
	    				}
	    				if (cur.isLeaf()) {
	    					fin += new Character((char)cur.value).toString();
	    					out.print((char)cur.value);
	    					break;
	    				}
	    			}
	    		}
	    		
	    		System.out.println(fin);
	    		
	    		//PrintWriter out = new PrintWriter("filename.txt");
	    		//out.println(fin);
	    		out.close();

	    		
	    }
	    catch(FileNotFoundException exception)
	    {
	    }
		
		
		 }
}