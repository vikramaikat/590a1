package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class part3 {
	public static String readFile(String fileName) throws IOException{
		InputStream myFileStream = new FileInputStream(fileName);
	    BufferedReader br = new BufferedReader(new InputStreamReader(myFileStream , "utf-8"));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	
	public static void main(String[] args) throws IOException, InsufficientBitsLeftException {
		String fileName = "part1_text.txt";
		String text = readFile(fileName);
		//System.out.println(text);
		Map<Character, Integer> freq = new HashMap<Character, Integer>();
		Map<Integer, Integer> freqInt = new HashMap<Integer, Integer>();
		
		int uniq = 0;
		
		for(int i = 0; i < text.length(); i++) {
			char cur = text.charAt(i);
			if(freq.containsKey(cur)) {
				freq.put(cur, freq.get(cur)+1);
			}else {
				freq.put(cur, 1);
			}
		}
		double[] p = new double[256];
		for(int i = 0; i < 255; i++) {
			if(freq.containsKey((char)i)) {
				p[i] = ((double)freq.get((char)i)/ text.length());
				System.out.println((char)i + " : " + freq.get((char) i) + " : " + ((double)freq.get((char)i)/ text.length()));
			}
		}
		
		double ent = 0;
		for(int i = 0; i < 255; i++) {
			if(p[i] != 0) {
				ent += p[i] * Math.log(1/p[i]) / Math.log(2);

			}
		}
		System.out.println("Theoretical entropy: " + ent);
		
		String file2 = "data/compressed.dat";
		 String file3 = "data/recompressed.dat";
	    		
	    try
	    {
	    		InputStream fis = new FileInputStream(file2);
	    		InputStreamBitSource fstream = new InputStreamBitSource(fis);
	    		Map<Integer, ArrayList<Integer>> lengths = new HashMap<Integer, ArrayList<Integer>>();
	    		ArrayList<Integer> l = new ArrayList<Integer>();
	    		
	    		for(int i = 0; i <= 255; i++) {
	    			int len = fstream.next(8);
	    			if (lengths.containsKey(len)){
	    				lengths.get(len).add(i);
	    				l.add(len);
	    			}else {
	    				lengths.put(len, new ArrayList<Integer>());
	    				lengths.get(len).add(i);
	    				l.add(len);
	    			}
	    		}
	    		
	    		//Collections.sort(l);
	    		//System.out.println(l);
	    		double e = 0.0;
	    		for(int k = 0; k < 256; k++) {
	    			e += p[k] * l.get(k);
	    		}
	    		System.out.println("Entropy of original compressed file: " + e);
	    
	}catch(FileNotFoundException exception)
	    {
		 System.out.println("The file was not found.");
	    }
		
	    try
	    {
	    		InputStream fis = new FileInputStream(file3);
	    		InputStreamBitSource fstream = new InputStreamBitSource(fis);
	    		Map<Integer, ArrayList<Integer>> lengths = new HashMap<Integer, ArrayList<Integer>>();
	    		ArrayList<Integer> l = new ArrayList<Integer>();
	    		
	    		for(int i = 0; i <= 255; i++) {
	    			int len = fstream.next(8);
	    			if (lengths.containsKey(len)){
	    				lengths.get(len).add(i);
	    				l.add(len);
	    			}else {
	    				lengths.put(len, new ArrayList<Integer>());
	    				lengths.get(len).add(i);
	    				l.add(len);
	    			}
	    		}
	    		
	    		//Collections.sort(l);
	    		//System.out.println(l);
	    		double e = 0.0;
	    		for(int k = 0; k < 256; k++) {
	    			e += p[k] * l.get(k);
	    		}
	    		System.out.println("Entropy of recompressed file: " + e);
	}catch(FileNotFoundException exception)
	    {
		 System.out.println("The file was not found.");
	    }
	}

}
