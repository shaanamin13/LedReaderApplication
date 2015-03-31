package com.example.shaan.ledreaderapplication;

public class BinarytoAscii {

    private static StringBuffer string = new StringBuffer();
    private static String[] stringAttributes =  new String[8];
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
        String totalStringOut = "";
        if (binaryOutput.charAt(start) == '1') {

            while (binaryOutput.charAt(stop) == '0' && foundStop == false) {

                if (binaryOutput.charAt(stop) == '1')

                    foundStop = true;

                else if (stop == start) {

                    return "No stop bit was found.";

                } else {

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

                return "No Led Detected, Please Try Again";

        } else {

            while (binaryOutput.charAt(start) == '0' && foundStart == false) {

                if (binaryOutput.charAt(start + 1) == '1')
                    foundStart = true;
                else {
                    start++;
                    foundStart = false;
                    if (binaryOutput.length() == start+1){
                        return "No start bit was found";
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
                        return "Stop bit was not found";
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

                return "Try Again ...";

        }


        System.out.println("String:" + string);

        totalStringOut = buildAttributes(0); // find each attribute

        return totalStringOut;

    } // end main

    public static String buildAttributes(int i) {
        boolean startAttribute = false;
        String total = "";
        stringAttributes[0] = "";
        stringAttributes[1] = "";
        stringAttributes[2] = "";
        stringAttributes[3] = "";
        stringAttributes[4] = "";
        stringAttributes[5] = "";
        stringAttributes[6] = "";
        stringAttributes[7] = "";

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
                    stringAttributes[0] = stringAttributes[0] + string.charAt(j);
                } else if (counter == 1 && string.charAt(j) != '*') {
                    stringAttributes[1] = stringAttributes[1] + string.charAt(j);
                } else if (counter == 2 && string.charAt(j) != '*') {
                    stringAttributes[2] = stringAttributes[2] + string.charAt(j);
                } else if (counter == 3 && string.charAt(j) != '*') {
                    stringAttributes[3] = stringAttributes[3] + string.charAt(j);
                } else if (counter == 4 && string.charAt(j) != '*') {
                    stringAttributes[4] = stringAttributes[4] + string.charAt(j);
                } else if (counter == 5 && string.charAt(j) != '*') {
                    stringAttributes[5] = stringAttributes[5] + string.charAt(j);
                } else if (counter == 6 && string.charAt(j) != '*') {
                    stringAttributes[6] = stringAttributes[6] + string.charAt(j);
                } else if (counter == 7 && string.charAt(j) != '*') {
                    stringAttributes[7] = stringAttributes[7] + string.charAt(j);
                }
            }
        }

        string.delete(0,string.length()-1);
        total = "" + stringAttributes[0] + " " + stringAttributes[1] + " " + stringAttributes[2] + " " + stringAttributes[3] + " "
                + stringAttributes[4] + " " + stringAttributes[5] + " " + stringAttributes[6] + " " + stringAttributes[7];

        if (i == 0){
            return total;
        } else if (i == 1){
            return stringAttributes[0];
        } else if (i == 2){
            return stringAttributes[1];
        } else if (i == 3){
            return stringAttributes[2];
        } else if (i == 4){
            return stringAttributes[3];
        } else if (i == 5){
            return stringAttributes[4];
        } else if (i == 6){
            return stringAttributes[5];
        } else if (i == 7){
            return stringAttributes[6];
        } else if (i == 8){
            return stringAttributes[7];
        } else {
            return "Nothing requested...";
        }
    } // end method

}

