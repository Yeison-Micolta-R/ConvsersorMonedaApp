package com.challenge.conversorapp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ConversorApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        float valor, resultado;
        ConsultaDivisa divisa = new ConsultaDivisa();
        Calclulo calculo = new Calclulo();
        List<String> historial = new ArrayList<>();

        do {
            mostrarMenu();

            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, ingresa un número válido: ");
                scanner.next();
            }

            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if (opcion >= 1 && opcion <= 6) {
                String[] divisas = obtenerDivisas(opcion);
                String from = divisas[0];
                String to = divisas[1];
                System.out.printf("***** %s ==> %s *****%n", from, to);
                System.out.print("Ingrese el valor que desea convertir: ");

                while (!scanner.hasNextFloat()) {
                    System.out.print("Por favor, ingresa un número válido: ");
                    scanner.next();
                }

                valor = scanner.nextFloat();
                float[] tasas = divisa.consultarDivisa(from, to);
                resultado = calculo.calculoDivisa(valor, tasas[1]);
                String conversion = impresion(tasas[0], tasas[1], valor, resultado, from, to);
                System.out.println(conversion);

                historial.add(conversion);
            } else if (opcion == 7) {
                System.out.println("===== Historial de conversiones =====");
                if (historial.isEmpty()) {
                    System.out.println("No hay conversiones registradas.");
                } else {
                    for (String registro : historial) {
                        System.out.println(registro);
                        System.out.println("-------------------------------");
                    }
                }
            } else if (opcion == 0) {
                System.out.println("Saliendo del programa...");
            } else {
                System.out.println("Opción no válida. Intenta nuevamente.");
            }

            if (opcion != 0) {
                System.out.print("\nPresiona Enter para continuar...");
                scanner.nextLine();
            }

            System.out.println();
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.print("""
        ===== Bienvenido/a al Conversor de Divisas =====

        1. Dólar (USD) ==> Peso Colombiano (COP)
        2. Peso Colombiano (COP) ==> Dólar (USD)
        3. Peso Mexicano (MXN) ==> Peso Colombiano (COP)
        4. Peso Colombiano (COP) ==> Peso Mexicano (MXN)
        5. Euro (EUR) ==> Peso Colombiano (COP)
        6. Peso Colombiano (COP) ==> Euro (EUR)
        7. Ver historial de conversiones
        0. Salir

        Selecciona una opción: """);
    }

    private static String[] obtenerDivisas(int opcion) {
        return switch (opcion) {
            case 1 -> new String[]{"USD", "COP"};
            case 2 -> new String[]{"COP", "USD"};
            case 3 -> new String[]{"MXN", "COP"};
            case 4 -> new String[]{"COP", "MXN"};
            case 5 -> new String[]{"EUR", "COP"};
            case 6 -> new String[]{"COP", "EUR"};
            default -> new String[]{"", ""};
        };
    }

    public static String impresion(float valDivisa1, float valDivisa2, float cantidadDivisa, float resultado, String divisa1, String divisa2) {
        return String.format("""
            %.2f %s equivale a %.4f %s
            %.2f %s son aproximadamente %.2f %s
            """, valDivisa1, divisa1, valDivisa2, divisa2, cantidadDivisa, divisa1, resultado, divisa2);
    }
}
