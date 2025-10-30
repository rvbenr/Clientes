package dao;

import jakarta.persistence.*;
import model.Cliente;
import java.util.List;

public class ClienteDAO {
    // EntityManagerFactory se usa para crear instancias de EntityManager, que son los objetos que gestionan la conexión a la base de datos.
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClientesPU");
    // Generamos un método para crear clientes
    public void crear(Cliente cliente) {
        // Creamos el EntityManager
        EntityManager em = emf.createEntityManager();
        // Iniciamos una transacción
        em.getTransaction().begin();
        // Con esto le indicamos que guarde en la base de datos persist
        em.persist(cliente);
        // Con esto concluimos la operación
        em.getTransaction().commit();
        em.close();
    }
    // Aquí creamos el método listar
    public List<Cliente> listar() {
        // Creamos el EntityManager
        EntityManager em = emf.createEntityManager();
        // Creamos una consulta JPQL
        // El segundo parámetro de createQuery indica el tipo de resultado esperado (una lista de objetos Cliente).
        // El getResultList() ejecuta la consulta en la base de datos y devuelve una lista (List<Cliente>) con todos los resultados.
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        // Aquí cerramos el EntityManager
        em.close();
        return clientes;
    }
    // Aquí abrimos el método buscarPorId
    public Cliente buscarPorId(Long id) {
        // Creamos el EntityManager
        EntityManager em = emf.createEntityManager();
        // Usamos em.find() para buscar un cliente por su clave primaria "id".
        Cliente c = em.find(Cliente.class, id);
        // Cerramos el EntityManager
        em.close();
        return c;
    }
    // Creamos el método actualizar
    public void actualizar(Cliente cliente) {
        // Creamos el EntityManager
        EntityManager em = emf.createEntityManager();
        // Iniciamos una transacción
        em.getTransaction().begin();
        // Con ".merge" actualizamos los datos de una cliente ya existente
        em.merge(cliente);
        // Confirmamos
        em.getTransaction().commit();
        // Y cerramos el EntityManager
        em.close();
    }
    // Creamos el método eliminar
    public void eliminar(Long id) {
        // Buscamos al cliente por su id.
        // Si existe, lo eliminamos con remove().
        // Cierramos la transacción y la conexión.
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) em.remove(cliente);
        em.getTransaction().commit();
        em.close();
    }
    // Creamos el método buscarPorCiudad
    public List<Cliente> buscarPorCiudad(String ciudad) {
        //Creamos una consulta JPQL que filtra los clientes por el campo ciudad.
        //Usamos LOWER() para que la búsqueda no distinga mayúsculas/minúsculas.
        //Usamos ":ciudad" como parámetro.
        //Así el programa devolverá una lista de clientes que viven en esa ciudad.
        EntityManager em = emf.createEntityManager();
        List<Cliente> clientes = em.createQuery(
                        "SELECT c FROM Cliente c WHERE LOWER(c.ciudad) = LOWER(:ciudad)", Cliente.class)
                .setParameter("ciudad", ciudad)
                .getResultList();
        em.close();
        return clientes;
    }
}
