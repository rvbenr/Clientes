package app;

import service.ClienteService;
import model.Cliente;
import java.time.LocalDate;
import java.util.*;

public class Main {
    // Abrimos el Scanner
    private static Scanner sc = new Scanner(System.in);
    private static ClienteService service = new ClienteService();

    public static void main(String[] args) {

        // Generamos la valiable para las opciones como entero
        int opcion;
        do {
            // Aquí generamos el menú con souts
            System.out.println("\n=== GESTIÓN DE CLIENTES ===");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Buscar por Ciudad");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            // La siguiente variable que introduzcamos en la consola, la máquina lo leerá
            opcion = sc.nextInt();

            // Limpiamos el buffer
            sc.nextLine();

            // Hacemos un switch para las opciones del menú
            switch (opcion) {
                case 1 -> agregar();
                case 2 -> listar();
                case 3 -> actualizar();
                case 4 -> eliminar();
                case 5 -> buscarPorCiudad();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }

        // Hacemos un while para salir del bucle, hasta que el usuario pulse 0, seguiremos en el bucle
        } while (opcion != 0);
    }

    // Creamos el método agregar, generando variables Strings para guardar cada dato gracias al Scanner
    private static void agregar() {
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Apellidos: ");
            String apellidos = sc.nextLine();
            String sexo;
            while (true) {
                System.out.print("Sexo (M/F): ");
                sexo = sc.nextLine().trim().toUpperCase();
                if (sexo.equals("M") || sexo.equals("F")) {
                    break;
                } else {
                    System.out.println("Dato no válido, solo se permite 'M' o 'F'. Inténtelo de nuevo.");
                }
            }
            System.out.print("Ciudad: ");
            String ciudad = sc.nextLine();
            System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
            // Usamos la librería LocalDate para la fecha de nacimiento
            LocalDate fecha = LocalDate.parse(sc.nextLine());
            System.out.print("Teléfono: ");
            String telefono = sc.nextLine();
            System.out.print("Correo electrónico: ");
            String email = sc.nextLine();

            service.agregarCliente(nombre, apellidos, sexo, ciudad, fecha, telefono, email);

            // Ponemos este sout para que el usuario sepa que todo fue agregado correctamente
            System.out.println("Cliente agregado correctamente.");
        } catch (Exception e) {
            System.out.println("Datos no válidos");
        }
    }

    // Creamos otro método para listar a los usuarios
    private static void listar() {
        List<Cliente> clientes = service.listarClientes();
        // Aquí aplicamos un if para que la consola compruebe si hay algún usuario registrado, si no lo hay responderá ese sout
        if (clientes.isEmpty()) System.out.println("No hay clientes registrados.");
        else clientes.forEach(System.out::println);
    }

    // Creamos el método para la función de actualizar de nuestro programa siguiendo el mismo procedimiento que en el método de agregar
    private static void actualizar() {
        System.out.print("Ingrese ID del cliente a actualizar: ");
        Long id = sc.nextLong();
        sc.nextLine();
        // Aquí solo actualizamos datos que no son fijos, suponiendo que no cambiamos de nombre, apelllidos, de sexo ni de fecha de nacimiento
        System.out.print("Nueva ciudad: ");
        String ciudad = sc.nextLine();
        System.out.print("Nuevo teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Nuevo email: ");
        String email = sc.nextLine();

        service.actualizarCliente(id, ciudad, telefono, email);
        System.out.println("Cliente actualizado correctamente.");
    }

    // Aquí generamos el método para la función eliminar, pidiéndole el ID al usuario del usuario que quiere eliminar
    private static void eliminar() {
        System.out.print("Ingrese ID del cliente a eliminar: ");
        Long id = sc.nextLong();
        sc.nextLine();

        service.eliminarCliente(id);
        System.out.println("Cliente eliminado correctamente.");
    }

    // Creamos el método para que el usuario pueda buscar por ciudades
    private static void buscarPorCiudad() {
        System.out.print("Ingrese la ciudad: ");
        String ciudad = sc.nextLine();

        List<Cliente> clientes = service.buscarPorCiudad(ciudad);
        // Utilizamos el if sumado al .isEmpty, para comprobar que no está vacía la variable
        // Si está vacía se lo indicamos al usuario con el siguiente sout
        if (clientes.isEmpty()) System.out.println("No se encontraron clientes en " + ciudad);
        else clientes.forEach(System.out::println);
    }
}
