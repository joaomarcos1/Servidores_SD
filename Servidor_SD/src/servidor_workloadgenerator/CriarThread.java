/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_workloadgenerator;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JLabel;

/**
 *
 * @author Helbert Monteiro
 */
public class CriarThread {

    GeradorGrafico grafico = new GeradorGrafico();

    private ServerSocket servidor;
    private Socket cliente;

    ObjectOutputStream saida;
    Scanner entrada;

    private int tempo, indice;
    private double media, valor, soma;

    //public void run(String celular, JLabel aparelho, int porta) {
    public void run(String palavras, int vezes){
        try {
            //servidor = new ServerSocket(porta);
            //System.out.println("Servidor ouvindo a porta " + porta);

            //cliente = servidor.accept();
            //System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
            saida = new ObjectOutputStream(cliente.getOutputStream());

            saida=  new ObjectOutputStream()
            
            
            entrada = new Scanner(cliente.getInputStream());
            valor = Double.parseDouble(entrada.nextLine());
            System.out.println(celular + ": " + valor);

            grafico.addValor(valor, tempo, celular, media);
            grafico.exibeGrafico();
            

                saida.flush();
                saida.writeObject(new Date());
                saida.close();

        } catch (Exception a) {
            System.out.println("Erro: " + a.getMessage());
        }
    }
}
