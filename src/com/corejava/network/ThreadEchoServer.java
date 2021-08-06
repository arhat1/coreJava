package com.corejava.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ThreadEchoServer {
    public static void main(String[] args) {
        try(ServerSocket s = new ServerSocket(8189)){
            int i = 1;
            while (true){
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

class ThreadEchoHandler implements Runnable{
    private Socket incoming;

    public ThreadEchoHandler(Socket incomingSocket){
        incoming = incomingSocket;
    }

    @Override
    public void run() {
        try(InputStream inStream  = incoming.getInputStream();
            OutputStream outStream = incoming.getOutputStream()){
            Scanner in  = new Scanner(inStream, StandardCharsets.UTF_8);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream,StandardCharsets.UTF_8), true);
            out.println("Hello, Enter BYE to exit.");
            boolean done = false;
            while(!done && in.hasNextLine()){
                String line = in.nextLine();
                out.println("Echo: " + line);
                if (line.trim().equals("BYE")){
                    done = true;
                    System.out.println("连接断开！");
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }


    }
}
