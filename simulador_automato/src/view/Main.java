import java.util.Scanner;

import controller.AFDControlador;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int opcao;
        do{
            menu();
            opcao = in.nextInt();
        } while(opcao != 0);
        in.close();
        AFDControlador controller = new AFDControlador();

//        exemplo de chamada do controlador:
//        System.out.println(controller.validarCadeia("1101"));
//        toda saída de dados deve ser feita exclusivamente na view
    }

    public static void menu(){
        System.out.println("1 - Validar cadeia de caracter");
        System.out.println("0 - Encerrar");
    }
}