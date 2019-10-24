/*
 * Esse é um programa de ditado. Os arquivos de áudio estão na pasta AudiosMp3 e
 * os arquivos de texto estão na pasta Textos. O programa excuta o áudio e 
 * compara a entrada do usuário com o conteúdo do texto. em caso correto a 
 * palavra aparece no texo caso contrario aparece uma dica iniciando com o 
 * simbolo '#'. Há botões para tocar, avançar ou retroceder o áudio e um botão 
 * para ir para o próximo texto.
 */
package ditado;

/**
 *
 * @author danillo
 */
public class Main {

    /*Essa função abre a janela do programana na tela do computador.*/
    public static void main(String[] args) {
        Janela tela = new Janela();
        tela.setVisible(true);
    }
    
}
