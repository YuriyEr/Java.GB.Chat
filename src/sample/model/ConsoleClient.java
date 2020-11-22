package sample.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    static final String SERVER_ADRESS = "localhost";
    static final int SERVER_PORT = 8189;

    private String host;
    private  int port;

    static public Socket socket;

    public static void main(String[] args) {
        ConsoleClient.generateConsoleClient();
    }

    public ConsoleClient(String host, int port) {
        this.host = SERVER_ADRESS;
        this.port = SERVER_PORT;


    }

    static public void generateConsoleClient(){
        ConsoleClient consoleClient = new ConsoleClient(SERVER_ADRESS, SERVER_PORT);
        if (consoleClient.connect()){
            System.out.println("Можете отправить сообщение");
            Scanner in = new Scanner(System.in);
            consoleClient.scanResult();
            while (true) {
                String text = in.nextLine();
                if (text.equals("/exit")){
                    consoleClient.closeConnect();
                    break;
                }
                consoleClient.sendMessage(text);
            }
        }
    }

    public boolean connect() {
        try {
            socket = new Socket(host, port);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            return true;

        } catch (IOException e) {
            System.out.println("Ошибка подключения к host:" + host + ":" + port);
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnect() {
        try {
            socket.close();
            System.out.println("Завершение соединения:" + host + ":" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scanResult(){
        Thread thread = new Thread(()->{
           try {
               while (true) {
                   String message = dataInputStream.readUTF();
                   System.out.println(message);
               }
           } catch (IOException e) {
               e.printStackTrace();
               System.out.println("Соединение потеряно!");
           }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void sendMessage(String message){
        try {
            dataOutputStream.writeUTF(message);
            System.out.println();
        } catch (IOException e) {
            System.out.println("Не удалось отправить сообщение: " + message);
            e.printStackTrace();
        }
    }
}
