import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class automatas {
    public static void main(String[] args) {
        String fileName = "matriz-automata-mail.txt";
        char[][] matrizEstados = populatematrizEstados(fileName);
        String emailsFile = "$$  21030761@itcelaya.edu.mx ju$an.2003@work2 juan.2003@work1 ";   
        
        String correosValidos = automataEmails(emailsFile, matrizEstados);     
        System.out.println("CORREOS VÁLIDOS");
        System.out.println(correosValidos);

        
    }
    
    //public static void automataEmails(File emailsFile){
    public static String automataEmails(String emailsFile, char[][] matrizEstados){
        String correoValido = "";
        String correosValidos = "";
    
        int indexChar = 1;
        int indexEstado = 1;
        
        for (int indexemailsFile = 0; indexemailsFile < emailsFile.length(); indexemailsFile++) {
            while (matrizEstados[0][indexChar] != emailsFile.charAt(indexemailsFile)) {
                indexChar++;
            }
    
            // EQUIVALENTES EN ASCII del valor 0(48) y 9(57)
            if(matrizEstados[indexEstado][indexChar] != 48 && matrizEstados[indexEstado][indexChar] != 57){
                correoValido += emailsFile.charAt(indexemailsFile);
            }else{
                correoValido = "";
            }
            
            // El 48 es para que me de el vaor literal, y no ASCII
            indexEstado = (matrizEstados[indexEstado][indexChar]-48)+1; // El índice de los estados empieza una posición después del inicio
            // !!!DEBERÍAN SER 8 Y 9!!!!
            if(indexEstado == 9){
                System.out.println("Escribo:"+correoValido);
                correosValidos += correoValido+"\n";
                correoValido = "";
                indexEstado = 1;
            }
            if(indexEstado == 10){
                while (emailsFile.charAt(indexemailsFile) != ' ' || indexemailsFile > emailsFile.length()) {
                    indexemailsFile++;
                }
                indexEstado = 1;
            }
            indexChar = 1;
        }
        
        return correosValidos;
    }

    public static char[][] populatematrizEstados(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            int numRows = 9; 
            int numCols = 44; 

            char[][] matrizEstados = new char[numRows][numCols];

            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < numRows) {
                for (int column = 0; column < numCols && column < line.length(); column++) {
                    matrizEstados[row][column] = line.charAt(column);
                }
                row++;
            }
            reader.close();

            return matrizEstados;
            
        } catch (IOException e) {
            throw new Error(e);
            //e.printStackTrace();
        }
    }
}
