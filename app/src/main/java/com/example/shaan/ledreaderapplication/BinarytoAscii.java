package com.example.shaan.ledreaderapplication;

public class BinarytoAscii {

    private static StringBuffer string = new StringBuffer();
    private static String[] stringAttributes =  {"","","","","","","",""};
    private static int check = 0;

    public static String conversion(String binaryOutput) {

        check = 0;
        int start = 0;
        int stop = binaryOutput.length() - 1;
        boolean foundStart = false;
        boolean foundStop = false;

        System.out.println("Initial String Value: " + string);
        if(string.length()>0){
            string.setLength(0);
        }
        System.out.println("After Delete String Value: " + string);


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
                    check = 1;
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
                    check = 1;
                } // end for
            } else
                return "Try Again ...";
        }

        System.out.println("String:" + string);
        String totalStringOut = buildAttributes(0); // find each attribute

        return "!"+totalStringOut;

    } // end main

    public static String buildAttributes(int i) {
        boolean startAttribute;

        stringAttributes[0] = "";
        stringAttributes[1] = "";
        stringAttributes[2] = "";
        stringAttributes[3] = "";
        stringAttributes[4] = "";
        stringAttributes[5] = "";
        stringAttributes[6] = "";
        stringAttributes[7] = "";

        System.out.println("i: "+i);

        if (string.charAt(0) == '*' && string.charAt(1) == '*') {
            startAttribute = true;
        } else {
            startAttribute = false;
        }
        System.out.println("startAttribute: "+startAttribute);
        // found startAttribute
        if (startAttribute) {
            int counter = 0;

            for (int j = 2; j < string.length(); j++) {

                if (string.charAt(j) == '?') {
                    System.out.println("?j: "+string.charAt(j));
                    break;
                }

                if (string.charAt(j) == '*') {
                    System.out.println("*j: "+string.charAt(j));
                    counter++;
                }

                if (counter == 0 && string.charAt(j) != '*'){
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

        String total = "" + stringAttributes[0] + " " + stringAttributes[1] + " " + stringAttributes[2] + " " + stringAttributes[3] + " "
                + stringAttributes[4] + " " + stringAttributes[5] + " " + stringAttributes[6] + " " + stringAttributes[7];

        System.out.println("Attributes: "+total);

        if (i == 0){
            return total;
        } else if (i == 1){
            String attribute1 = stringAttributes[0];
            return attribute1;
        } else if (i == 2){
            String attribute2 = stringAttributes[1];
            return attribute2;
        } else if (i == 3){
            String attribute3 = stringAttributes[2];
            return attribute3;
        } else if (i == 4){
            String attribute4 = stringAttributes[3];
            return attribute4;
        } else if (i == 5){
            String attribute5 = stringAttributes[4];
            return attribute5;
        } else if (i == 6){
            String attribute6 = stringAttributes[5];
            return attribute6;
        } else if (i == 7){
            String attribute7 = stringAttributes[6];
            return attribute7;
        } else if (i == 8){
            String attribute8 = stringAttributes[7];
            return attribute8;
        } else {
            return "Nothing requested...";
        }
    } // end method

    public static boolean checkValidString(){
        boolean valid = false;
        if (check == 1){
            System.out.println("Check: "+check);
            valid = true;
        }
        check = 0;
        return valid;
    } // end method

} // end class

