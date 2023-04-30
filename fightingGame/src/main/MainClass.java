package main;

import Auth.Connect;
import Auth.LoginFrame;

public class MainClass {

	public static void main(String[] args) {

		Connect connect = new Connect();
		LoginFrame lgFrame = new LoginFrame(connect);
	}
}
