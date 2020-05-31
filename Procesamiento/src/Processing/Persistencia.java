
package Processing;

import Additionals.Texto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class Persistencia {
    
    public static String setUrl(){
        String system = System.getProperty("os.name");
        
        String user = System.getProperty("user.name");
        String language = System.getProperties().getProperty("user.language");
        String url = "";
        
        if (system.startsWith("Linux")) {
            if(language.equals("es")){
                url = "/home/"+user+"/Documentos/Persistencia.txt";           
            }else{
                url = "/home/"+user+"/Documents/Persistencia.txt";  
            }
                    
        }else if (system.startsWith("Windows")){
            url = "C:/Users/"+user+"/Documents/Persistencia.txt";   
        }
        return url;
    }
    
    public static void WriteFile(){
        try {
            int lenght=0;
            File file = new File(setUrl());
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter write = new FileWriter(file,true);
            FileReader reader = new FileReader(file);
            
            BufferedWriter bw = new BufferedWriter(write);
            BufferedReader bf = new BufferedReader(reader);
            while ((bf.readLine())!=null) {
                lenght++;
            }
            
            for (int i = lenght; i < DuchaInfo.duchas.size(); i++) {
                bw.append(DuchaInfo.duchas.get(i).getFecha()+" "+
                         DuchaInfo.duchas.get(i).getGasto()+" "+
                         DuchaInfo.duchas.get(i).getTiempo()+"\r\n");
            }
            bw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,Texto.AV6);
        }
    }
    
    public static void LoadFile(){
        DuchaInfo.duchas.clear();
        File file = new File(setUrl());
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bf = new BufferedReader(reader);
            String l;
            while ((l = bf.readLine())!=null) {
                String line[] = l.split(" ");
                String fecha = line[0]+" "+line[1];
                double gasto = Double.parseDouble(line[2]);
                int tiempo = Integer.parseInt(line[3]);
                
                DuchaInfo.duchas.add(new DuchaInfo(fecha, gasto, tiempo));
            }
            JOptionPane.showMessageDialog(null,Texto.AV2);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,Texto.AV6);
        }
    }
}
