package com.example.estudiante.tcp_cliente_s8;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receptor extends Thread{
	Socket socket;

	//Paso 2
	OnMessage observer;
	
	public Receptor(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader( new InputStreamReader(is) );
		
			while(true){
				String line = reader.readLine();
				Log.e("RECIBIDO",line);

				//Paso 4: Solo funciona cuando observer no es nulo
                observer.onReceived(line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Siempre quiero que este en funcionamiento
		
	}
	//Patron observer
	//Paso 1
	public interface OnMessage{
	    public  void onReceived(String mensaje);
    }

    //Paso 3
    public void setObserver(OnMessage observer){
	    this.observer=observer;
    }
}
