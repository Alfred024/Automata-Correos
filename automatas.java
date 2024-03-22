import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class automatas {
    public static void main(String[] args) throws FileNotFoundException {
        //#1 Convertir el archivo.txt a la matriz de estados
        String estadosFile = "matriz-automata-mail.txt";
        char[][] matrizEstados = populatematrizEstados(estadosFile);

        //#2 Procesar el archivo de prueba que contiene los emails
        // String emailsFile = "automata01.in.txt";
        // String stringifiedFile = fileToString(emailsFile);

        String stringifiedFile = "   en la ciudad de celaya guanajuato. se considera que existe personal adscrito a dicho municipio en cual cuenta con diferentes correos electronino tales como juan..martinez@com.mx a1a1@@mexico.mx y algunos otros que se han considerado como indefinidos dentros de los diferentes ecosistemas validos para los correos electronicos. el problema radica en que pocas veces pocdemos encotrar los nombres que pertenecen al nombre de l iudad celaya@gto.mx dentro del contexto valido de las personas. por otra parte en la escuela de nombre universidad@celaya.gto.mx. podemos ver como se han asignado diversos correos electronicos a sus estudiantes alguno al parecer son bastan raros o poco comunes para otras persona tales como 1.2.3.4.5@a.b.c y otros muy  adecuandos para la gran mayoria de los ciudadan@s todo esto sin importar cuanto han estado trabajando para identificar los nombres correctos qutene pertenecen a esta gran familia de correos@validos@mx";
        
        //#3 Obtener el String con los emails válidos
        String correosValidos = automataEmails(stringifiedFile, matrizEstados);    
        System.out.println(correosValidos); 

        //#4 Escribir los correos válidos en un archivo 
        PrintWriter writer = new PrintWriter("automata01.out");
        writer.println(correosValidos);
        writer.close();
    }
    
    public static String automataEmails(String emailsFile, char[][] matrizEstados){
        String correoValido = "";
        String correosValidos = "";
    
        int indexChar = 1;
        int indexEstado = 1;
        
        for (int indexemailsFile = 0; indexemailsFile < emailsFile.length(); indexemailsFile++) {
            while (matrizEstados[0][indexChar] != emailsFile.charAt(indexemailsFile)) {
                indexChar++;
            }
            // System.out.print(" -"+matrizEstados[0][indexChar]+"- ");
    
            if(matrizEstados[indexEstado][indexChar] != 48 && matrizEstados[indexEstado][indexChar] != 57){
                correoValido += emailsFile.charAt(indexemailsFile);
            }else{
                correoValido = "";
            }
            
            indexEstado = (matrizEstados[indexEstado][indexChar]-48)+1; // El índice de los estados empieza una posición después del inicio

            if(indexEstado == 9){
                //System.out.println("Escribo:"+correoValido);
                correosValidos += correoValido+"\n";
                correoValido = "";
                indexEstado = 1;
            }

            if(indexEstado == 10){
                while (indexemailsFile < emailsFile.length() && emailsFile.charAt(indexemailsFile) != ' ') {
                    indexemailsFile++;
                }
                indexEstado = 1;
            }
            indexChar = 1;
        }
        if(correosValidos.length() == 0) return "0";
        return correosValidos;
    }

    // TODO: Debe calcular el tamaño de la matríz dinámicamente
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

    public static String fileToString(String fileName){
        StringBuilder fileStringied = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String linea;
            while ((linea = reader.readLine()) != null) {
                fileStringied.append(linea).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            throw new Error(e);
            //e.printStackTrace();
        }
        return fileStringied.toString();
    }
}
