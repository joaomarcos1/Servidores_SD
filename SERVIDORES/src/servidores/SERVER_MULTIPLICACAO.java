/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidores;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static servidores.SERVER_SOMA.soma;

public class SERVER_MULTIPLICACAO extends JFrame {

    static float valor_1, valor_2, soma;
    JLabel valor1 = new JLabel("valor 1");
    JLabel valor2 = new JLabel("valor 2");

    JLabel mostrarsoma = new JLabel("Multiplicação dos valores");
    JTextField tfsoma = new JTextField();
    JTextField tfvalor1 = new JTextField();
    JTextField tfvalor2 = new JTextField();

    JButton Iniciar = new JButton("Receber Dados");

    public SERVER_MULTIPLICACAO() {

        JPanel tela = new JPanel();
        tela.setLayout(null);
        tela.setBackground(Color.white);

        tela.add(Iniciar);
        Iniciar.setBounds(150, 10, 150, 40);
        Iniciar.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Thread tsensor3 = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            ServerSocket servidor3 = new ServerSocket(9009);

                            System.out.println("Esperando cliente se conectar ao servidor pela porta 9008");
                            while (true) {
                                Socket cliente1 = servidor3.accept();
                                System.out.println("Cliente " + cliente1.getInetAddress().getHostAddress() + " conectado...");

                                Scanner entrada = new Scanner(cliente1.getInputStream());

                                System.out.println("recebendo dados do cliente");

                                String a = entrada.nextLine();
                                System.out.println("dados recebidos\n");
                                System.out.println(a);
                                System.out.println("aaaaa");
                                StringTokenizer st = new StringTokenizer(a);
                                System.out.println("bbbbbbbbb");

                                String val_1 = st.nextToken("/");//

                                String val_2 = st.nextToken("/");
                                //String val_3 = st.nextToken("/");

                                System.out.println("valor 1: " + val_1);
                                System.out.println("valor 2: " + val_2);

                                tfvalor1.setText(val_1);
                                tfvalor2.setText(val_2);

                                System.out.println("aaaaaa");

                                valor_1 = Float.parseFloat(val_1);
                                valor_2 = Float.parseFloat(val_2);
                                soma = valor_1 * valor_2;

                                String x = soma + "";
                                tfsoma.setText(x);

                                System.out.println("aaaa");
                                //int result = Integer.parseInt(x);
                                //sd.write(result);
                                //sd.close();
                                //if (!"".equals(tfsoma.getText())) {
                                    Socket cliente = new Socket("127.0.0.1", 9011);
                                    System.out.println("Enviando resposta para Middleware");

                                    PrintStream saida = new PrintStream(cliente.getOutputStream());
                                    System.out.println("Resposta enviada");
                                    //Scanner entrada = new Scanner(cliente.getInputStream());  
                                    //String b = entrada.nextLine();
                                    //tfsoma.setText(b);
                                    saida.print(Float.toString(soma));
                                    saida.close();
                                    cliente.close();
                                //}
                                //tfsoma.setText("");
                                //PrintStream saida1 = new PrintStream(cliente1.getOutputStream());

                                //saida1.flush();
                                //saida1.println(x);
                                //saida1.close(); 
                                // cliente1.close();
                            }

                        } catch (Exception a) {
                            System.out.println("\n\n Erro no servidor " + a.getMessage());

                        }

                    }
                });
                tsensor3.start();

            }
        }
        );

        tela.add(valor1);
        valor1.setBounds(50, 60, 350, 40);

        tela.add(tfvalor1);
        tfvalor1.setBounds(150, 64, 150, 30);

        tela.add(valor2);
        valor2.setBounds(50, 120, 350, 40);

        tela.add(tfvalor2);
        tfvalor2.setBounds(150, 124, 150, 30);

        tela.add(mostrarsoma);
        mostrarsoma.setBounds(20, 300, 350, 40);

        tela.add(tfsoma);
        tfsoma.setBounds(150, 304, 150, 30);

        add(tela);
        setVisible(true);
        setSize(440, 440);
        setLocation(440, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new SERVER_MULTIPLICACAO();

    }
}
