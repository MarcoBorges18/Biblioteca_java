package gerenciamento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Salvar implements Serializable{
    
    private static File arquivo = new File("src/log/log.ser");

    public static void salvarArquivo(Object obj){
        try {
            arquivo.getParentFile().mkdirs();
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(arquivo));
            output.writeObject(obj);
        } catch (Exception e) {
            System.out.println("Falha ao salvar o arquivo");
        }
    }

    public static Object lerArquivo() {
        try {
            if(arquivo.exists() && arquivo.isFile()){
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo));
                return in.readObject();
            }          
        } catch (Exception e) {
            System.out.println("Falha ao ler o arquivo");
            System.out.println(e);
        }
        return null;
    }
}
