Resumen de la Aplicación de Gestión de Clientes

1. Estructura

Organizamos todo con package siguiendo la siguiente estructura
UI (Main.java): Menú interactivo en consola.
Servicio (ClienteService.java): Lógica de validación y coordinación con la base de datos.
DAO (ClienteDAO.java): Acceso a datos mediante JPA/Hibernate.
Model (Cliente.java): Representa la entidad cliente con sus atributos.

2. Base de Datos

Utilizamos  como base de datos MySQL.
Creamos dentro de MySQL una tabla con: id, nombre, apellidos, sexo, ciudad, fecha_nacimiento, telefono, email.   

3. Funcionalidades

Añadimos todas estas funcionalidades al programa mediante métodos y clases
Agregar cliente.
Listar todos los clientes.
Actualizar cliente.
Eliminar cliente.
Buscar clientes por ciudad.
