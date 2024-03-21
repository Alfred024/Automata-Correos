import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class automatas {
    public static void main(String[] args) {
        String fileName = "matriz-automata-mail.txt";
        char[][] matrix = populateMatrix(fileName);

        // Lo que tendría que hacer es leer todo el txt y escribirlo en un String
        //String textFile = "$$  21030761@itcelaya.edu.mx ju$an.2003@work2 ($.) juan.2003@work1";
        String textFile = "$$  21030761@itcelaya.edu.mx ju$an.2003@work2 juan.2003@work1 ";
        String correoValido = "";

        // >>>>>>>>>> Parte del autómata <<<<<<<<<<<
        int indexChar = 1;
        int indexEstado = 1;
        
        for (int indexTextFile = 0; indexTextFile < textFile.length(); indexTextFile++) {
            while (matrix[0][indexChar] != textFile.charAt(indexTextFile)) {
                indexChar++;
            }

            // EQUIVALENTES EN ASCII del valor 0(48) y 9(57)
            if(matrix[indexEstado][indexChar] != 48 && matrix[indexEstado][indexChar] != 57){
                correoValido += textFile.charAt(indexTextFile);
            }else{
                correoValido = "";
            }
            
            // El 48 es para que me de el vaor literal, y no ASCII
            indexEstado = (matrix[indexEstado][indexChar]-48)+1; // El índice de los estados empieza una posición después del inicio
            // !!!DEBERÍAN SER 8 Y 9!!!!
            if(indexEstado == 9){
                // Debe escribir el "correoValido" en el archivo
                System.out.println("Escribo:"+correoValido);
                correoValido = "";
                indexEstado = 1;
            }
            if(indexEstado == 10){
                while (textFile.charAt(indexTextFile) != ' ' || indexTextFile > textFile.length()) {
                    indexTextFile++;
                }
                indexEstado = 1;
            }
            indexChar = 1;
        }

    }

    //public static void automataEmails(File emailsFile){
    public static void automataEmails(String emailsFile, char[][] matrizEstados){
        
    }

    public static char[][] populateMatrix(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            int numRows = 9; 
            int numCols = 44; 

            char[][] matrix = new char[numRows][numCols];

            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < numRows) {
                for (int column = 0; column < numCols && column < line.length(); column++) {
                    matrix[row][column] = line.charAt(column);
                }
                row++;
            }
            reader.close();

            return matrix;
            
        } catch (IOException e) {
            throw new Error(e);
            //e.printStackTrace();
        }
    }
}
