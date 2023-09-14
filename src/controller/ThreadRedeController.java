package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadRedeController extends Thread {
    String server;

    public ThreadRedeController(String server) {
        this.server = server;
    }

    private boolean isLinux() {
        if (System.getProperty("os.name").contains("Linux")) {
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        ThreadPing();
    }

    private void ThreadPing() {
        StringBuilder pingAs = new StringBuilder();
        if (isLinux()) {
            try {
                Process ping = Runtime.getRuntime().exec("ping -4 -c 10 "+ server);
                InputStream stream = ping.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader buff = new BufferedReader(reader);
                String linha = buff.readLine();

                while (linha != null) {
                    linha = buff.readLine();
                    if (linha.contains("packets transmitted")) {
                        linha = buff.readLine();
                        String[] entireLine = linha.split("= ");
                        String[] average = entireLine[1].split("/");

                        pingAs.append(" -> Average ping: ").append(average[1]).append(" ms");
                    }
                    linha = buff.readLine();
                }
                System.out.println("Thread #"+getId() +" Server pinged: "+ server + pingAs);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("Not a Linux OS");
        }
    }
}
