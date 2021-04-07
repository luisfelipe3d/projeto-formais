package view;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int opcao;
        do{
            menu();
            opcao = in.nextInt();
        } while(opcao != 0);
    }

    public static void menu(){
        System.out.println("1 - Validar cadeia de caracter");
        System.out.println("0 - Encerrar");
    }
}
