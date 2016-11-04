package sentenceScrambler;
import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Scrambler {

	public Scrambler(){
		
	}
	public String shuffleString(String str){
		StringTokenizer st = new StringTokenizer(str);
		String newSentence = "";
		String tempStr = "", token = "";
		//loop per token
		while (st.hasMoreTokens()) {
			token = st.nextToken();
			for(int i = 0; i<10; i++){
				tempStr = shuffleStringInternal(token);
				if(tempStr != token || tempStr.length() < 4){
					i=11;
				}
			}
			newSentence += tempStr + " ";
		}
		return newSentence;
	}
	private String shuffleStringInternal(String str) {
		if(str.length() < 4)
			return str;
		
		Random rnd = new Random();
		char[] ar;
		final String ORIGINAL_STRING = str;
		int i, start = -1, end = -1;
		Map<Integer, Character> characterMap = new HashMap<Integer, Character>();	
		
		//build a map of all non-alphabetical characters in the string to remove
		for(i = 0; i<str.length(); i++){
			if(!Character.isLetter(str.charAt(i))){
				characterMap.put(i, str.charAt(i));
			}
			else{
				if(start < 0){
					start = i;
					characterMap.put(i,str.charAt(i));
				}
				end = i;
			}
		}
		if(start == -1 || end == -1){
			return str;
		}
		//remember last character
		characterMap.put(end, str.charAt(end));
		
		//if start-end is less than 2, the string is short, so just return
		if(end-start < 2){
			return str;
		}
		
		//remove non alphabetical characters from the string
		i = 0;
		ArrayList<Integer> values = new ArrayList<Integer>(characterMap.keySet());
		values.sort((Integer first, Integer second) -> first - second);
		
		for(int index : values){
			if(index == 0)
				str = str.substring(1);
			else
				str = str.substring(0,index-i) + str.substring(index-i+1);
			i++;
		}
		

		//now scramble
		ar = str.toCharArray();
		if(ar.length > 2){
			for (i = ar.length - 1; i > 0; i--) {
				int index = rnd.nextInt(i + 1);
				// Simple swap
				char a = ar[index];
				ar[index] = ar[i];
				ar[i] = a;
			}
		}else if(ar.length == 2){
			char a = ar[0];
			ar[0] = ar[1];
			ar[1] = a;
		}
		
		//now reconstruct the string
		i=0;
		int j = 0;
		char[] finalString = new char[ORIGINAL_STRING.length()];
		for(int index : values){
			while(i<index && i<finalString.length && j<ar.length){
				finalString[i] = ar[j];
				i++;
				j++;
			}
			finalString[i] = characterMap.get(index);
			i++;
		}
		
		return new String(finalString);
	}
}
