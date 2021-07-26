import java.io.File;

/**
 * Lab06 Recursive methods
 * @author Hazal Buluş
 * @version 1.0 26.07.2021
 */
 public class RecursiveMethods{
    public static void main(String[] args) {

        //Variables
        String q1, q2;
        int n;
        String pathName;
        File file;

        //Initializing the variables
        q1 = "CS102 is the best";
        q2 = "CS102 is a good course";
        n=3;
        pathName = "C:";
        file = new File(pathName);


        System.out.println("============Q1==========");
        System.out.println("Input: " + q1 + "\nOutput: " + lengthOfString(q1));
        System.out.println("\n============Q2==========");
        System.out.println("Input: " + q2 + "\nOutput: " + noOfConsonants(q2));
        System.out.println("\n============Q3==========");
        System.out.println("Input: " + n + "\nOutput: ");
        System.err.println();
        toBinary(n);
        System.out.println("\n============Q4==========");
        System.out.println("\nOutput: " + numOfFiles(file));
    }
    /**
     * Question 1 - Recursive method that returns the given string's length 
     * @param str
     * @return count the length of string
     */
    public static int lengthOfString(String str){
        //Base case
        if(str.equals("")){
            return 0;
        }
        //Recursive part
        else{
            return 1 + lengthOfString(str.substring(1));
        }
    }

    /**
     * Question 2 - Recursive method that returns the number of nonvowel letters in the given string
     * @param str
     * @param length
     * @return count consonant letters in the string
     */
    public static int noOfNonVowels(String str, int length){
        //Base Case 
        String consonants = "BCDFGHJKLMNPQRSTVWXYZ";
        if(length==1){

            //Make upper case the char
            char letter;
            letter = Character.toUpperCase(str.charAt(0));
            //Check if the char is consonant or not
            if(consonants.contains(String.valueOf(letter)))
                return 1;
            else
                return 0;
        }
        //If the String's length longer than 1, look at the last char
        char letter;
        letter = Character.toUpperCase(str.charAt(length-1));

        if( consonants.contains(String.valueOf(letter)))
        {
            return noOfNonVowels(str, length-1) + 1;
        }
        else{
            return noOfNonVowels(str, length-1);
        }
    }
    /**
     * Question 2 - Recursive method that returns the number of nonvowel letters in the given string
     * @param str
     * @return recursive method with two parameter
     */
    public static int noOfConsonants(String str){
        return noOfNonVowels(str, str.length());
    }

    /**
     * Question 3 - Generate all binary strings of length n (that is your input) without 1's that come together 
     * @param n
     * @param k digit count
     * @param ch array of chars
     */
    public static void toBinaryString(int n, int k, String digit){
        //Base case
        if(n == k){
            System.out.println(digit);
            return;
        }
        else{
            //if current character is zero, set next character both 0 and 1
            //increase the digit number and call the method itself recursively
            if(digit.charAt(k-1) == '0'){

                toBinaryString(n, k+1, digit+"0");

                toBinaryString(n, k+1, digit+"1");

            }
            //if current character is 1, set next character to 0
            //increase the digit number and call the method itself recursively
            else if(digit.charAt(k-1) == '1'){
                
                toBinaryString(n, k+1, digit+"0");
            }
        }
    }
    /**
     * Question 3 - Generate all binary strings of length n (that is your input) without 1's that come together 
     * @param n
     */
    public static void toBinary(int n){
        String digit;

        if(n<=0){
            return;
        }
        digit= "0";
        toBinaryString(n, 1, digit);

        digit = "1";
        toBinaryString(n, 1, digit);
    }

    /**
     * Question 4 - count the files in given directory 
     * @param files
     * @param length
     */
    public static int fileCounter(File[] files, int i, int countFile, int countDir){

        if(i == files.length){
            return 0;
        }
        if(files[i].isFile())
            countFile = fileCounter(files, i+1, countFile +1, countDir) +1 ;
        if(!files[i].isFile()){
            countFile = fileCounter(files, i+1, countFile, countDir);
        }
        if(files[i].isDirectory())
            countDir = fileCounter(files[i].listFiles(), 0, countFile, countDir);
        
        return countFile + countDir;
    }
    public static int numOfFiles(File file){
        if(file.isDirectory())
            return fileCounter(file.listFiles(), 0, 0, 0);
        else
            return 0;    
    }

}