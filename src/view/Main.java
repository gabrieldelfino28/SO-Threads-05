package view;

import controller.ThreadRedeController;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String server = "";
        for (int i = 1; i <= 3 ; i++) {
            switch (i) {
                case 1 -> server = "www.uol.com.br";
                case 2 -> server = "www.terra.com.br";
                case 3 -> server = "www.google.com.br";
            }
            Thread t = new ThreadRedeController(server);
            t.start();
        }
    }
}
