/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ditado;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 *
 * @author danillo
 */

/*Essa classe é responsável por lidar com o audio do porgrama.*/
public class Audio {

    private AdvancedPlayer player;
    private String path;
    private String nomeDoArquivo;

    public Audio(String nomeDoArquivo) {
        try {
            this.path = new File("file://../AudiosMp3/").getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.nomeDoArquivo = nomeDoArquivo;
    }

    public void setNomeDoArquivo(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    }

    /*Esse metodo recebe dois valores inteiros que representam o frame inicial 
    e o frame final de um trecho do audio. O metodo toca o trecho de audio 
    representado pelas variaveis inicio e fim.*/
    public void play(int inicio, int fim) throws FileNotFoundException, JavaLayerException {

        FileInputStream fis = new FileInputStream(path + "/" + nomeDoArquivo);
        BufferedInputStream bis = new BufferedInputStream(fis);

        this.player = new AdvancedPlayer(bis);

        new Thread() {
            @Override
            public void run() {
                try {
                    player.play(inicio, fim);

                } catch (JavaLayerException ex) {
                    Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

    }

    /*Esse metodo interrompe a reprodução do audio.*/
    public void stop() {
        player.close();
    }

}
