package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadPing extends Thread{

	String servidor;
	
	public ThreadPing(String servidor) {
		this.servidor = servidor;
	}
	
	public void run() {
		ping();
	}
	
	private void ping() {
		String os =os();
		String site = "";
		if (os.contains("Linux")) {
			switch (servidor) {
			case "UOL": {
				site = "www.uol.com.br";
				break;
			}
			case "Terra": {
				site = "www.terra.com.br";
				break;
			}
			case "Google": {
				site = "www.google.com.br";
				break;	
			}
			}
			String process = "ping -4 -c 10 " + site;
			try {
				Process p = Runtime.getRuntime().exec(process);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while(linha != null) {
					if (linha.contains("time=")) {
						String[] l = linha.split("=");
						System.out.println("Servidor: "+servidor+"; Tempo: "+l[l.length-1]);
					}
					if (linha.contains("rtt")) {
						String[] l = linha.split("/");
						System.out.println("\nServidor: "+servidor+"; Tempo Médio: "+l[4]+"ms");
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}		
		}
		else {
			System.out.println("Sistema Operacional Inválido");
		}
	}
	
	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}

}
