package com.example.estudiante.tcp_cliente_s8;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente extends Thread{

    Socket s;
    Receptor r;
    MainActivity activity;

    public Cliente(MainActivity activity){
        this.activity=activity;
    }

    @Override
    public void run() {
        try {
            s = new Socket("10.0.2.2",5000);
            r=new Receptor(s);
            r.setObserver(activity);
            r.start();


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void enviar(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OutputStream os = s.getOutputStream();
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(os));
                    out.println("Hola mundo, funciona!");
                    out.flush();
                }catch (IOException e){

                }
            }
        }).start();


    }



}

