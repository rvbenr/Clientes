package model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
// Aquí le damos el nombre "clientes" a la tabla
@Table(name = "clientes")
public class Cliente {

    // "@Id" Indica que este campo es la clave primaria de la entidad
    @Id
    // "@GeneratedValue" Indica que el valor de "Id" se genera automáticamente por la base de datos
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Aquí definimos de que tipo es cada variable
    private Long id;

    private String nombre;
    private String apellidos;
    private String sexo;
    private String ciudad;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;

    // Creamos el constructor
    public Cliente() {}

    public Cliente(String nombre, String apellidos, String sexo, String ciudad,
                   LocalDate fechaNacimiento, String telefono, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
    }

    // Creamos los Getters y Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    // Esto devuelve una representación en texto del objeto
    public String toString() {
        return String.format("[%d] %s %s - %s (%s) - %s - %s - %s",
                id, nombre, apellidos, sexo, ciudad, fechaNacimiento, telefono, email);
    }
}
