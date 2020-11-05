package chat5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

//서버가 보내는 Echo메세지를 읽어오는 쓰레드 클래스
public class Receiver extends Thread {
	
	Socket socket;
	BufferedReader in = null;
	
	//Client가 접속 시 생성한 Socket객체를 생성자에서 매개변수로 받음
	public Receiver(Socket socket) {
		this.socket = socket;
		
		/*
		Socket 객체를 기반으로 input스트림을 생성한다.
		 */
		try {
			in = new BufferedReader(new 
					InputStreamReader(socket.getInputStream()));
		}
		catch(Exception e) {
			System.out.println("예외>Receiver>생성자:"+e);
		}	
	}
	
	@Override
	public void run() {
		while(in != null) {
			try {
				System.out.println("Thread Receive :"+in.readLine());
			}	
			catch(SocketException e) {
				System.out.println("SocketException발생됨, 루프탈출");
				break;
			}
			catch (Exception e) {
				System.out.println("예외>Receiver>run1:"+e);
			}
		}
		try {
			in.close();
		}
		catch (Exception e) {
			System.out.println("예외>Receiver>run2"+e);
		}
	}
}
