package com.example.chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private boolean finished = false;
    private static final int SERVER_PORT = 4444;
    private static final String SERVER_IP = "46.101.96.234";

    @Override
    public void run() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!finished) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

   public String getString() throws IOException {
        if (reader.ready())
            return reader.readLine();
        return "No data";
    }

    public String getStringForce() throws IOException {
        return reader.readLine();
    }

    public void MessageSender(final String string) {
            try {
                 final PrintWriter writer = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())),
                        true);
                    final Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {

                        writer.println(string);
                    }});
                    thread.start();
            } catch (IOException e) {
            }
            }
}

