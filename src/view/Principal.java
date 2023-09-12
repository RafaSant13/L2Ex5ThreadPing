package view;

import controller.ThreadPing;

public class Principal {

	public static void main(String[] args) {
		String UOL = "UOL";
		String Terra = "Terra";
		String Google = "Google";
		
		Thread uol = new ThreadPing(UOL);
		Thread terra = new ThreadPing(Terra);
		Thread google = new ThreadPing(Google);
		uol.start();
		terra.start();
		google.start();

	}

}
