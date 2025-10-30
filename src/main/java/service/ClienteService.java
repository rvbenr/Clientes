package service;
// Lo usamos para acceder a la base de datos.
import dao.ClienteDAO;
// Esta es la clase de entidad
import model.Cliente;
// Esto lo usamos para manejar fechas de nacimiento
import java.time.LocalDate;
// Esto lo usamos para devolver listas de clientes
import java.util.List;

public class ClienteService {
    private ClienteDAO dao = new ClienteDAO();
    // Creamos el método agregarCliente
    public void agregarCliente(String nombre, String apellidos, String sexo, String ciudad,
                               LocalDate fechaNacimiento, String telefono, String email) {
        // Validamos los datos de entrada
        // Si nombre o apellidos son nulos o están vacíos, lanza una excepción (IllegalArgumentException).
        // Creamos un objeto Cliente
        if (nombre == null || nombre.isBlank() || apellidos == null || apellidos.isBlank())
            throw new IllegalArgumentException("Nombre y apellidos son obligatorios.");
        // Usamos el constructor de Cliente para llenar los campos
        Cliente cliente = new Cliente(nombre, apellidos, sexo, ciudad, fechaNacimiento, telefono, email);
        // Con esto insertamos los datos en la base de datos
        dao.crear(cliente);
    }
    // Con esto simplemente llamamos al "dao" para que nos proporcione los datos
    public List<Cliente> listarClientes() {
        return dao.listar();
    }
    // Buscamos el cliente por su "id"
    // Llama a dao.actualizar(c) para guardar los cambios.
    public void actualizarCliente(Long id, String ciudad, String telefono, String email) {
        Cliente c = dao.buscarPorId(id);
        // Si (c != null), actualiza solo los campos que fueron proporcionados
        if (c != null) {
            if (ciudad != null && !ciudad.isBlank()) c.setCiudad(ciudad);
            if (telefono != null && !telefono.isBlank()) c.setTelefono(telefono);
            if (email != null && !email.isBlank()) c.setEmail(email);
            // Aquí llamamos al "dao" para guardar los cambios
            dao.actualizar(c);
        }
    }
    // Con este método llamamos directamente al "DAO" para eliminar el cliente correspondiente
    public void eliminarCliente(Long id) {
        dao.eliminar(id);
    }
    // Aquí hacemos lo mismo delegando la función en el "DAO"
    public List<Cliente> buscarPorCiudad(String ciudad) {
        return dao.buscarPorCiudad(ciudad);
    }
}
