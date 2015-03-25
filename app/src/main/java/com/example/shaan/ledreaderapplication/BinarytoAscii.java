package com.example.shaan.ledreaderapplication;

public class BinarytoAscii {

    private static StringBuffer string = new StringBuffer();
//
//    private static String binaryOutput = "001001010100010101000111111100";
//
//    public static void main(String[] args) {
//
//        conversion(binaryOutput);
//
//    }

    public static String conversion(String binaryOutput) {

        int start = 0;

        int stop = binaryOutput.length() - 1;

        boolean foundStart = false;

        boolean foundStop = false;

        if (binaryOutput.charAt(start) == '1') {

            while (binaryOutput.charAt(stop) == '0' && foundStop == false) {

                if (binaryOutput.charAt(stop) == '1')

                    foundStop = true;

                else {

                    stop--;

                    foundStop = false;

                }

            } // end while

            int errCheck = stop - (start + 1);

            if (errCheck % 8 == 0) {

                for (int k = 1; k < stop; k += 8) {

                    string.append((char) Integer.parseInt(

                            binaryOutput.substring(k, k + 8), 2));

                } // end for

            } else

                string.append("**Try Againn?");

        } else {

            while (binaryOutput.charAt(start) == '0' && foundStart == false) {

                if (binaryOutput.charAt(start + 1) == '1')

                    foundStart = true;

                else {

                    if(binaryOutput.length() >= start + 1) {
                        start++;
                        foundStart = false;
                    }
                    else{
                        return "No code detected please try again";
                    }



                }

            } // end while

            while (binaryOutput.charAt(stop) == '0' && foundStop == false) {

                if (binaryOutput.charAt(stop) == '1')

                    foundStop = true;

                else {
                    if(stop != start) {
                        stop--;

                        foundStop = false;
                    }
                    else{
                        return "stopbit was not found";
                    }
                }

            } // end while

            int errCheck = (stop - 1) - (start + 1);

            if (errCheck % 8 == 0) {

                for (int k = start + 2; k < stop; k += 8) {

                    string.append((char) Integer.parseInt(

                            binaryOutput.substring(k, k + 8), 2));

                } // end for

            } else

                return "**Try Again?";

        }

        System.out.println("String:" + string);

        binaryOutput = buildAttributes(); // find each attribute

        return binaryOutput;

    } // end main

    public static String buildAttributes() {
        String total = "";
        boolean startAttribute = false;

        String attribute0 = "";

        String attribute1 = "";

        String attribute2 = "";

        String attribute3 = "";

        String attribute4 = "";

        String attribute5 = "";

        String attribute6 = "";

        String attribute7 = "";

        if (string.charAt(0) == '*' && string.charAt(1) == '*') {

            startAttribute = true;

        } else {

            startAttribute = false;

        }

// found startAttribute

        if (startAttribute) {

            int counter = 0;

            for (int j = 2; j < string.length(); j++) {

                if (string.charAt(j) == '?') {

                    break;

                }

                if (string.charAt(j) == '*') {

                    counter++;

                }

                if (counter == 0 && string.charAt(j) != '*') {

                    attribute0 = attribute0 + string.charAt(j);

                } else if (counter == 1 && string.charAt(j) != '*') {

                    attribute1 = attribute1 + string.charAt(j);

                } else if (counter == 2 && string.charAt(j) != '*') {

                    attribute2 = attribute2 + string.charAt(j);

                } else if (counter == 3 && string.charAt(j) != '*') {

                    attribute3 = attribute3 + string.charAt(j);

                } else if (counter == 4 && string.charAt(j) != '*') {

                    attribute4 = attribute4 + string.charAt(j);

                } else if (counter == 5 && string.charAt(j) != '*') {

                    attribute5 = attribute5 + string.charAt(j);

                } else if (counter == 6 && string.charAt(j) != '*') {

                    attribute6 = attribute6 + string.charAt(j);

                } else if (counter == 7 && string.charAt(j) != '*') {

                    attribute7 = attribute7 + string.charAt(j);

                }

            }

        }

        total = "" + attribute0 + attribute1 + attribute2 + attribute3 + attribute4 + attribute5 + attribute6 + attribute7;
        return total;

//        System.out.println("Attribute0: " + attribute0);
//
//        System.out.println("Attribute1: " + attribute1);
//
//        System.out.println("Attribute2: " + attribute2);
//
//        System.out.println("Attribute3: " + attribute3);
//
//        System.out.println("Attribute4: " + attribute4);
//
//        System.out.println("Attribute5: " + attribute5);
//
//        System.out.println("Attribute6: " + attribute6);
//
//        System.out.println("Attribute7: " + attribute7);

    } // end method

}

