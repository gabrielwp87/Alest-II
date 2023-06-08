/*
 * Aluno: Gabriel Wagner Piazenski
 * 
 * Professor: Edson Moreno
 */

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class GabrielPiazenskiT2 {
    public static void main(String[] args) {
        String case0 = "T2/case0.map";
        String case1 = "T2/case1.map";
        String case2 = "T2/case2.map";
        String case3 = "T2/case3.map";
        String case4 = "T2/case4.map";
        String case5 = "T2/case5.map";
        boolean line1 = false;
    
    
        try {
            File myObj = new File(case0);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String lines = myReader.nextLine();
                if (!line1) {
                    // System.out.println(lines);
                    line1 = true;
                    String[] aux = lines.split(" ");
                    String nRowsString = aux[0];
                    String nColumsString = aux[1];
                    Integer nRows = Integer.parseInt(nRowsString);
                    Integer nColums = Integer.parseInt(nColumsString);

                    // System.out.println(nRowsString);
                    // System.out.println(nColumsString);
                    // System.out.println(nRows);
                    // System.out.println(nColums);
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



        
    }
}