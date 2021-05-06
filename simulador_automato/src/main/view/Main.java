package main.view;

import main.controller.AFDControlador;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final int DEFAULT_VALUE = 1;
    private static final String ACCEPTED_CHAIN = "CADEIA ACEITA!";
    private static final String REJECTED_CHAIN = "CADEIA REJEITADA!";
    private static final String INVALID_OPTION = "OPÇÃO INVÁLIDA!";
    private static final AFDControlador controlador = new AFDControlador();

    public static void main(String[] args) {
        int opcao = DEFAULT_VALUE;
        argumentosMain(args);
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
                        System.err.println(INVALID_OPTION);
                }
            } catch (InputMismatchException ime) {
                System.err.println(INVALID_OPTION);
            } finally {
                in.nextLine();
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
        return controlador.validarCadeia(cadeia) ? ACCEPTED_CHAIN : REJECTED_CHAIN;
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
    
    private static void argumentosMain(String[] arg){
        switch (arg.length) {
            case 0:
                // usuário não informa nenhum argumento
                controlador.lerArquivoJSON();
                break;
            case 1:
                // usuário informa o path do arquivo JSON
                controlador.lerArquivoJSON(arg[0]);
                break;
            case 2:
                // usuário informa o path do arquivo e a cadeia a ser validada
                controlador.lerArquivoJSON(arg[0]);
                System.out.println(validarCadeia(arg[1]));
                break;
            default:
                break;
        }
        imprimirAutomato(controlador.getAFD());
    }
}
