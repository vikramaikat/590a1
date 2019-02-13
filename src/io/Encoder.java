package io;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Encoder {
	
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
	
	public static void main(String[] args) throws IOException{
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
		for(int i = 0; i < text.length(); i++) {
			int cur = (int) text.charAt(i);
			if(freqInt.containsKey(cur)) {
				freqInt.put(cur, freqInt.get(cur)+1);
			}else {
				freqInt.put(cur, 1);
				uniq += 1;
			}
		}
		for(int i = 0; i < 256; i++) {
			if(!freqInt.containsKey(i)) {
				freqInt.put(i,0);
			}
		}
		
		int[] codes = new int[256];
		int[] freq11 = new int[256];
		int ct = 0;
		for(int i = 0; i < 256; i++) {
			if(freqInt.containsKey(i)) {
				freq11[ct] = freqInt.get(i);
				codes[ct] = i;
				ct = ct + 1;
			}
			
		}
		
		//System.out.println(uniq);
		//System.out.println(freq.size());
		//System.out.println(freq11);
		
		int[] lens = MinVarHuffman.makeTree(codes, freq11, 256);
		
		
		ArrayList<Integer> canonLen = new ArrayList<Integer>();
		for(int i = 0; i < lens.length; i++) {
			canonLen.add(lens[i]);
		}
		
		System.out.println(canonLen);
		
		Map<Integer, ArrayList<Integer>> lengths = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> l = new ArrayList<Integer>();
		
		for(int i = 0; i <= 255; i++) {
			int len = canonLen.get(i);
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
		
		
		HuffmanTree tree = new HuffmanTree();
		//System.out.println(lengths);
		//System.out.println(symbols);
		for(int i = 0; i < lengths.size(); i++) {
			int len = l.get(i);
			//System.out.println(len);
			ArrayList<Integer> vals = lengths.get(len);
			//System.out.println(vals);
			for(int j = 0; j < vals.size(); j++) {
				tree.insert(vals.get(j), len);
			}
		}
		
		String[] strs = new String[256];
		treeFinder(tree.root, strs, "");
		for(int i=0; i<strs.length; i++) {

			//System.out.println(i + " : " + strs[i].length() + " , " + canonLen.get(i));
			
		}
		
		System.out.println("begfore firle");
		FileOutputStream fos = new FileOutputStream("recompressed.dat");
		OutputStreamBitSink bit_sink = new OutputStreamBitSink(fos);
		
		System.out.println("after firle " + canonLen.size());
		for(int i=0; i< canonLen.size(); i++) {
			int l1 = canonLen.get(i);
			//System.out.println(l1 + " " + canonLen.get(i));
			//for(int j = l1; j < 8; j++) {
			//	bit_sink.write("0");
			//}
			bit_sink.write(l1, 8);
		}
		
		System.out.println("after lengths");
		System.out.println("Text length " + text.length());
		//System.out.println(len);
		//for(int i = len.length(); i < 32; i++) {
		//	bit_sink.write("0");
		//}
		bit_sink.write(text.length(), 32);
		
		for(int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			//System.out.print(text.charAt(i));
			int value = (int) c;
			//if(value <= 256) {

				//System.out.println(value);
				String str = strs[value];
				bit_sink.write(str);
			//}
		}
		
		
		
		//for(int i = 0; i < text.length(); i++) {
		//	
		//}
		
		bit_sink.padToWord();
		fos.close();
		
	}
	
	public static void treeFinder(Node root, String[] strs, String s) {
		if(root.isLeaf()) {
			strs[root.value] = s;
			//System.out.println(root.value);
		} else {
			if(root.left != null) {
				treeFinder(root.left, strs, s + "0");
			}
			if(root.right != null) {
				treeFinder(root.right, strs, s + "1");
			}
		}
		
	}
	
	public static String converter(int in)
    {
		int a;
        String x = "";
        int n = in;
        while(n > 0)
        {
            a = n % 2;
            x = x + "" + a;
            n = n / 2;
        }
        return x;
    }
		

}