package br.uffs.bibliotecapessoal.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    private  static Scanner scanner = new Scanner(System.in);
    
    public static void limparTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar o terminal.");
        }
    }

    public static int getInt(){
        int r = 0;
        while(true){    
            try{
                r = scanner.nextInt();
            } catch (InputMismatchException e) {
                    System.out.println("Erro: Por favor, insira um número.");
                    scanner.nextLine();
                    continue;
            }
            break;
        }    
        return r;
    }

    public static void consumirLinha() {
        scanner.nextLine();
    }

}
