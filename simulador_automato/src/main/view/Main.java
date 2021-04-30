package main.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import main.controller.AFDControlador;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final String ACCEPTED = "CADEIA ACEITA!";
    private static final String REJECTED = "CADEIA REJEITADA!";
    private static final String INVALID = "OPÇÃO INVÁLIDA!";
    private static final AFDControlador controlador = new AFDControlador();

    public static void main(String[] args) {
        int opcao = 1;
        argumentoMain(args);
        do {
            try {
                menuPrincipal();
                opcao = in.nextInt();
                switch (opcao) {
                    case 1:
                        validarCadeia();
                        break;
                    case 0:
                        break;
                    default:
                        System.err.println(INVALID);
                }
            } catch (InputMismatchException ime) {
                System.err.println(INVALID);
            } finally {
                in.nextLine(); // Limpa o buffer
            }
        } while(opcao != 0);
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
    
    public static void argumentoMain(String[] arg){
        if(arg.length == 0){
            controlador.lerArquivoJSON();
        } else if(arg.length == 1){
            controlador.lerArquivoJSON(arg[0]);
        }
        imprimirAutomato(controlador.getAFD());
    }
}
