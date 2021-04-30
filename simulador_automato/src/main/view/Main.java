package main.view;

import main.controller.AFDControlador;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


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

    private static void menuPrincipal() {
        System.out.println("1 - Validar cadeia de elementos");
        System.out.println("0 - Encerrar");
        System.out.print("Escolha a opção desejada: ");
    }
    
    private static String validarCadeia(String cadeia){
        return controlador.validarCadeia(cadeia) ? ACCEPTED : REJECTED;
    }
    public static void validarCadeia() {
        System.out.print("Informe a cadeia: ");
        String cadeia = in.next(); in.nextLine();
        System.out.println(validarCadeia(cadeia));
    }
    
    public static void imprimirAutomato(List automatoLido) {
        final String alfabeto = controlador.getAlfabetoLinguagem();
        System.out.println("Alfabeto da linguagem: " + alfabeto);
        automatoLido.forEach(System.out::println);
    }
    
    private static void argumentoMain(String[] arg){
        switch (arg.length) {
            case 0:
                controlador.lerArquivoJSON();
                break;
            case 1:
                controlador.lerArquivoJSON(arg[0]);
                break;
            case 2:
                controlador.lerArquivoJSON(arg[0]);
                System.out.println(validarCadeia(arg[1]));
                break;
            default:
                break;
        }
        imprimirAutomato(controlador.getAFD());
    }
}
