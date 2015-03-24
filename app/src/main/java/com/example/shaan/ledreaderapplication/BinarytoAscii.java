package com.example.shaan.ledreaderapplication;

public class BinarytoAscii {
	private static StringBuffer string = new StringBuffer();

	public static String conversion(String binaryOutput){
		int i = 0;
		boolean found = false;
		
		if (binaryOutput.charAt(i) == '1'){ /*First case with start bit immediate*/
			for (int j = 1; j < binaryOutput.length(); j += 8 ){
				string.append((char) Integer.parseInt(binaryOutput.substring(j, j+8), 2));
			} // end for
		} else {
			while(binaryOutput.charAt(i) == '0' && !found){
				if (binaryOutput.charAt(i) == '1'){
					found = true;
				}
				else {
					i++;
					found = false;
				}
			} // end while
			for (int j = i+1; j < binaryOutput.length(); j += 8 ){
				string.append((char) Integer.parseInt(binaryOutput.substring(j, j+8), 2));
			} // end for
		}
		System.out.println(string);
		buildAttributes(); // find each attribute
        return binaryOutput;
	} // end main

	
	public static void buildAttributes(){
		boolean startAttribute = false;
		
		String attribute0 = "";
		String attribute1 = "";
		String attribute2 = "";
		String attribute3 = "";
		String attribute4 = "";
		String attribute5 = "";
		String attribute6 = "";
		String attribute7 = "";
		
		if (string.charAt(0) == '*' && string.charAt(1) == '*'){
				startAttribute = true;
		} else {
			startAttribute = false;
		}
		
		// found startAttribute
		if (startAttribute){
			int counter = 0;
			for (int j = 2; j < string.length(); j++){
                if (string.charAt(j) == '?'){
                    break;
                }
				if (string.charAt(j) == '*'){
					counter++;
				}
				
				if (counter == 0 && string.charAt(j) != '*'){
					attribute0 = attribute0 + string.charAt(j);
				} else if (counter == 1 && string.charAt(j) != '*'){
					attribute1 = attribute1 + string.charAt(j);
				} else if (counter == 2 && string.charAt(j) != '*'){
					attribute2 = attribute2 + string.charAt(j);
				} else if (counter == 3 && string.charAt(j) != '*'){
					attribute3 = attribute3 + string.charAt(j);
				} else if (counter == 4 && string.charAt(j) != '*'){
					attribute4 = attribute4 + string.charAt(j);
				} else if (counter == 5 && string.charAt(j) != '*'){
					attribute5 = attribute5 + string.charAt(j);
				} else if (counter == 6 && string.charAt(j) != '*'){
					attribute6 = attribute6 + string.charAt(j);
				} else if (counter == 7 && string.charAt(j) != '*'){
					attribute7 = attribute7 + string.charAt(j);
				}
					
			}
		}
		
		System.out.println("Attribute0: " + attribute0);
		System.out.println("Attribute1: " + attribute1);
		System.out.println("Attribute2: " + attribute2);
		System.out.println("Attribute3: " + attribute3);
		System.out.println("Attribute4: " + attribute4);
		System.out.println("Attribute5: " + attribute5);
		System.out.println("Attribute6: " + attribute6);
		System.out.println("Attribute7: " + attribute7);
	} // end method
} // end class
