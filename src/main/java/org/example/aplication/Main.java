package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ElectrodomesticoRepository repo = new ArchivoElectrodomesticoRepository();
    private static final ElectrodomesticoService service = new ElectrodomesticoServiceImpl(repo);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1 -> crearElectrodomestico();
                    case 2 -> listarElectrodomesticos();
                    case 3 -> leerElectrodomestico();
                    case 4 -> actualizarElectrodomestico();
                    case 5 -> eliminarElectrodomestico();
                    case 6 -> {
                        continuar = false;
                        System.out.println("Saliendo del sistema...");
                    }
                    default -> System.out.println("Opción no válida. Intenta de nuevo.");
                }

                // Pausa para que el usuario pueda ver el resultado antes de repetir el menú
                if (continuar) {
                    System.out.println("\nPresiona Enter para continuar...");
                    scanner.nextLine();
                }

            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Gestión de Electrodomésticos ---");
        System.out.println("1. Crear Electrodoméstico");
        System.out.println("2. Listar Electrodomésticos");
        System.out.println("3. Leer Electrodoméstico por ID");
        System.out.println("4. Actualizar Electrodoméstico");
        System.out.println("5. Eliminar Electrodoméstico");
        System.out.println("6. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static void crearElectrodomestico() {
        System.out.print("\nIngrese ID del electrodoméstico: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese nombre del electrodoméstico: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese marca del electrodoméstico: ");
        String marca = scanner.nextLine();

        Electrodomestico e = new Electrodomestico(id, nombre, marca);
        service.agregarElectrodomestico(e);
        System.out.println("Electrodoméstico creado exitosamente.");
    }

    private static void listarElectrodomesticos() {
        List<Electrodomestico> electrodomesticos = service.obtenerTodos();
        System.out.println("\n--- Lista de Electrodomésticos ---");
        if (electrodomesticos.isEmpty()) {
            System.out.println("No hay electrodomésticos registrados.");
        } else {
            electrodomesticos.forEach(System.out::println);
        }
    }

    private static void leerElectrodomestico() {
        System.out.print("\nIngrese ID del electrodoméstico a buscar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Electrodomestico e = service.obtenerElectrodomestico(id);
        if (e != null) {
            System.out.println("Electrodoméstico encontrado: " + e);
        } else {
            System.out.println("No se encontró un electrodoméstico con el ID proporcionado.");
        }
    }

    private static void actualizarElectrodomestico() {
        System.out.print("\nIngrese ID del electrodoméstico a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Electrodomestico e = service.obtenerElectrodomestico(id);
        if (e != null) {
            System.out.print("Ingrese nuevo nombre del electrodoméstico: ");
            String nuevoNombre = scanner.nextLine();
            System.out.print("Ingrese nueva marca del electrodoméstico: ");
            String nuevaMarca = scanner.nextLine();

            e.setNombre(nuevoNombre);
            e.setMarca(nuevaMarca);
            service.actualizarElectrodomestico(e);
            System.out.println("Electrodoméstico actualizado exitosamente.");
        } else {
            System.out.println("No se encontró un electrodoméstico con el ID proporcionado.");
        }
    }

    private static void eliminarElectrodomestico() {
        System.out.print("\nIngrese ID del electrodoméstico a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Electrodomestico e = service.obtenerElectrodomestico(id);
        if (e != null) {
            service.eliminarElectrodomestico(id);
            System.out.println("Electrodoméstico eliminado exitosamente.");
        } else {
            System.out.println("No se encontró un electrodoméstico con el ID proporcionado.");
        }
    }
}


