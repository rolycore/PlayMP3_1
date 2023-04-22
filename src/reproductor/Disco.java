package reproductor;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import org.tritonus.share.sampled.file.TAudioFileFormat;

public class Disco {
    public BasicPlayer player;
    public static float[] Eq = new float[32];
    public static int[] EqP = new int[10];
    public static float Balance;
    public static int itemEc;
    public double Volumen;
    public long Duracion;
    public String RutaAbsoluta,Titulo,Artista,Album,Ano,Comentario,Copyright;
    
    public Disco(){
        this.Volumen = 1;
        player=new BasicPlayer();
    }
    
    public boolean Abrir(String Ruta){
        File Archivo=new File(Ruta);
        try {
            RutaAbsoluta=Ruta;
            LimpiarDatos();
            ObternerDatos();
            player.open(Archivo);
            return true;
        } catch (BasicPlayerException ex) {
            return false;
        }
    }
    
    public boolean Reproducir(){
        try {
            if(player.getStatus()==1){
                player.resume();
            }else{
                player.play();
            }
            CambiarVolumen(Volumen);
            return true;
        } catch (BasicPlayerException ex) {
            return false;
        }
    }
    
    public boolean Parar(){
        try {
            if(player.getStatus()==1 || player.getStatus()==0){
                player.stop();   
            }
            return true;
        } catch (BasicPlayerException ex) {
                return false;
        }
    }
    
    public boolean Pausar(){
        try {
            if(player.getStatus()==0){
                player.pause();
            }
            return true;
        } catch (BasicPlayerException ex) {
            return false;
        }
    }
    
    public boolean CambiarPor(String Ruta,boolean Reproducir){
        Parar();
        if(Abrir(Ruta)){
            if(Reproducir){
                Reproducir();
            }
            return true;
        }else{
            return false;
        }
    }
    
    public boolean Ubicar(long posicion){
        try {
            boolean SeReproduce=false;
            if(player.getStatus()==0){
                SeReproduce=true;
            }
            Parar();
            player.seek(posicion);
            if(SeReproduce){
                Reproducir();
            }
            return true;
        } catch (BasicPlayerException ex) {
            return false;
        }
    
    }
    
    public int Estado(){
        return player.getStatus();
    }
    
    public boolean CambiarVolumen(double volumen){
        try {
            Volumen=volumen;
            player.setGain(volumen);
        } catch (BasicPlayerException ex) {
            return false;
        }
        return true;
    }
    
    public boolean Balance(float bal){
        try {
            player.setPan(bal);
        } catch (BasicPlayerException ex) {
            return false;
        }
        return true;
    }
    
    public void LimpiarDatos(){
        Titulo=""; Artista=""; Album="";   
        Ano=""; Comentario=""; Copyright="";
        Duracion=0;
    }
    
    public void ObternerDatos(){
        File mp3Archivo = new File(RutaAbsoluta);
        AudioFileFormat baseFileFormat = null;
        try {
            baseFileFormat = AudioSystem.getAudioFileFormat(mp3Archivo);
        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(baseFileFormat instanceof TAudioFileFormat){
            Map properties = ((TAudioFileFormat)baseFileFormat).properties();
            Titulo=(String)properties.get("title");
            Artista=(String)properties.get("author");
            Album=(String)properties.get("album");
            Ano=(String)properties.get("date");
            Comentario=(String)properties.get("comment");
            Copyright=(String)properties.get("copyright");
            Duracion=(long)properties.get("duration")/1000000;
        }
    }
    
    public String NombreArchivo(){
        String aux=new File(RutaAbsoluta).getName();
        return aux.substring(0,aux.length()-4);
    }
}
