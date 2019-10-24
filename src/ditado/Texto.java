/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ditado;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danillo
 */

/*Essa classe é responsável por lidar com o texto do porgrama.*/
public class Texto {

    private String nomeDoArquivo;
    private String path;
    
    /*Indice que marca a proxima palavra a ser ditada no texto.*/
    public static int i = 0;

    public Texto(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
        try {
            this.path = new File("file://../Textos/").getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(Texto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNomeDoArquivo(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    }

    /*Esse metodo devolve a primeira linha do arquivo de texto com o titulo do texto.*/
    public String pegaTitulo() throws FileNotFoundException {
        Scanner scanner;
        String titulo;

        scanner = new Scanner(new FileReader(path + "/" + nomeDoArquivo));
        titulo = scanner.nextLine();

        return titulo;
    } 

    /*Esse metodo devolve a proxima palavra do texto.*/
    public String darProximaPalavra() throws FileNotFoundException {
        Scanner scanner;
        int indice = 0;
        String nome = "#FIM#";
        String titulo;

        scanner = new Scanner(new FileReader(path + "/" + nomeDoArquivo)).useDelimiter("\\ |\\n");
        
        /*Retira o titulo*/
        titulo = scanner.nextLine();

        while (scanner.hasNext()) {
            nome = scanner.next();

            if (indice == i) {
                break;
            }
            indice++;

        }
        i++;
        return nome;
    }

    /*Esse metodo recebe uma variavel String com a palavra digitada pelo usuario 
    e compara com a palavra do texto. Caso as palavras sejam iguais é devolvida 
    a palavra do texto. Caso as palavras sejam diferentes é devolvida uma String
    inciada com o simbolo #que mostra as letras que o usuário acertou. Caso o 
    texto nao tenha mais palavras é retornado a String #FIM#.*/
    public String comparaPalavra(String palavraRecebida) throws FileNotFoundException {
        String palavraOriginal;
        String copiaPalavraOriginal;
        String copiaPalavraRecebida;
        String palavraFinal = "";
        CharSequence sinaisFinal[] = {"\"", "'", "!", ")", "?", ",", ".", ":", ";"};
        CharSequence sinaisInicio[] = {"\"", "'", "("};
        int j = 0;

        palavraOriginal = darProximaPalavra();
        if (palavraOriginal.equals("#FIM#")) {
            return "#FIM#";
        }
        
        copiaPalavraOriginal = palavraOriginal.toLowerCase();
        /*Retira sinais de pontuacao no inicio da palavra.*/
        while (j < sinaisInicio.length) {

            if (copiaPalavraOriginal.substring(0, 1).contains(sinaisInicio[j])) {
                copiaPalavraOriginal = copiaPalavraOriginal.substring(1, copiaPalavraOriginal.length());
                break;
            }

            j++;
        }

        j = 0;

        /*Retira sinais de pontuacao no fim da palavra.*/
        while (j < sinaisFinal.length) {
            if (copiaPalavraOriginal.substring(copiaPalavraOriginal.length() - 1, copiaPalavraOriginal.length()).contains(sinaisFinal[j])) {
                copiaPalavraOriginal = copiaPalavraOriginal.substring(0, copiaPalavraOriginal.length() - 1);
                break;
            }
            j++;
        }

        copiaPalavraRecebida = palavraRecebida.toLowerCase();
        if (copiaPalavraRecebida.equals(copiaPalavraOriginal)) {
            palavraFinal = palavraOriginal;
        } else {
            j = 0;
            palavraFinal += "#";
            while ((j < copiaPalavraOriginal.length()) && (j < copiaPalavraRecebida.length())) {
                if (copiaPalavraRecebida.charAt(j) == copiaPalavraOriginal.charAt(j)) {
                    palavraFinal += palavraOriginal.charAt(j);
                } else {
                    palavraFinal += "*";
                }
                j++;
            }
            while (j < copiaPalavraOriginal.length()) {
                palavraFinal += "*";
                j++;
            }
            i--;
        }

        return palavraFinal;
    }

}
