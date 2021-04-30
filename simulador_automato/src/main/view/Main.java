package main.view;

import java.util.List;
import java.util.Scanner;
import main.controller.AFDControlador;

public class Main {
    private static final String ACCEPTED = "CADEIA ACEITA!";
    private static final String REJECTED = "CADEIA REJEITADA!";
    private static AFDControlador controlador = new AFDControlador();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        controlador.lerArquivoJSON();
        imprimirAutomato(controlador.getAFD());

        do {
            menuPrincipal();
            opcao = in.nextInt();
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    validarCadeia();
                    break;
                default:
                    System.err.println("Opção inválida");
            }
        } while (opcao != 0);
        in.close();
    }

    public static void menuPrincipal() {
        System.out.println("1 - Validar cadeia de elementos");
        System.out.println("0 - Encerrar");
        System.out.print("Escolha a opção desejada: ");
    }
    
    public static void validarCadeia() {
        System.out.print("Informe a cadeia: ");
        String cadeia = in.next(); in.nextLine();
        String resultado = controlador.validarCadeia(cadeia) ? ACCEPTED : REJECTED;
        
        System.out.println(resultado);
    }
    
    public static void imprimirAutomato(List automatoLido) {
        System.out.println("Alfabeto da linguagem: " + controlador.getAlfabetoLinguagem());
        for (int i = 0; i < automatoLido.size(); i++) {
            System.out.println(automatoLido.get(i));
        }
    }
}
