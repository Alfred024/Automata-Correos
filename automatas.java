// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.io.Reader;
// import java.nio.charset.Charset;

// public class automatas {
//     public static void main(String[] args) throws IOException {
//         File file = new File("./matriz-automata-mail.txt");
//         // Retorna el charset del S.O. 
//         Charset encoding = Charset.defaultCharset();
//         readFile(file, encoding);
        
//     }

//     private static void readFile(File file, Charset encoding) throws IOException {
//         try (InputStream in = new FileInputStream(file);
//              Reader reader = new InputStreamReader(in, encoding);
//              // buffer para la eficiencia
//              Reader buffer = new BufferedReader(reader)) {
//             readCharacters(buffer);
//         }
//     }

//     // private static void readCharacters(Reader reader) throws IOException {
//     //     int r;
       
//     //     while ((r = reader.read()) != -1) {
//     //         // Detectar que es un salto de línea, 
//     //             // 
//     //         char ch = (char) r;
//     //         System.out.println("Do something with " + ch);
//     //     }
//     // }

//     private static void readCharacters(Reader reader) throws IOException {
//         int r;
//         //int lenghtX = getMatrixWidth(reader);
//         //System.out.println("X lenght: "+lenghtX);

//         while ((r = reader.read()) != -1) {
//             char ch = (char) r;
//             System.out.print(ch+"-");
//         }
//     }
    
//     // Este método vaciá la promera fila de los caracteres en el archivo
//     private static int getMatrixWidth(Reader reader) throws IOException {
//         int r;
//         int cicle = 1;
//         int columnLenght = 0;
//         while ((r = reader.read()) != '\n' && cicle <= 2) {
//             cicle++;
//             if (cicle == 2) {
//                 columnLenght++;
//             }
//         }
//         return columnLenght-1;
//     }


//     private static char[][] getMatrix(File file){

        
//         short lenghtX = 0, lenghtY = 0;
//         char[][] matrix = new char[lenghtX][lenghtY];

//         return matrix;
//     }
// }

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class automatas {
    public static void main(String[] args) {
        String fileName = "matriz-automata-mail.txt";
        char[][] matrix = populateMatrix(fileName);

        // Lo que tendría que hacer es leer todo el txt y escribirlo en un String
        String textFile = "$$  21030761@itcelaya.edu.mx ju$an.2003@work1 ($.) juan.2003@work1";
        String correoValido = "";

        // >>>>>>>>>> Parte del autómata <<<<<<<<<<<
        int indexChar = 1;
        int indexEstado = 1;
        boolean valido = false; // A lo mejor no es necesario esta bandera 
        

        for (int indexTextFile = 0; indexTextFile < textFile.length(); indexTextFile++) {
            while (matrix[0][indexChar] != textFile.charAt(indexTextFile)) {
                indexChar++;
            }
            // El correo es válido si el estado no es 9 o 0 
            // ESTOY ESCRIBIENDO LOS EQUIVALENTES EN ASCII
            if(matrix[indexEstado][indexChar] != 48 && matrix[indexEstado][indexChar] != 57){
                correoValido += textFile.charAt(indexTextFile);
                valido = true; 
            }
            // El correo no es válido
            else{
                correoValido = "";
                valido = false;
            }
            
            // El 48 es para que me de el vaor literal, y no ASCII
            indexEstado = (matrix[indexEstado][indexChar]-48)+1; // Es más uno porque los estados empiezan una fila después
            // !!!DEBERÍAN SER 8 Y 9!!!!
            if(indexEstado == 9){
                // Debe escribir el "correoValido" en el archivo
                System.out.println("Escribo:"+correoValido);
                indexEstado = 1;
            }
            if(indexEstado == 10){
                indexEstado = 1; // Aquí es uno porque el estado 0 está en ese índice
            }
            // System.out.println("File char: "+matrix[indexEstado][indexChar]);
            // System.out.println("Index estado: "+indexEstado);

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
