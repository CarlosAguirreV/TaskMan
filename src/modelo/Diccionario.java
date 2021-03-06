package modelo;

import static modelo.Constantes.*;

/**
 * Aqui se recogen todas las cadenas de texto de la aplicacion.
 *
 * @author Carlos Aguirre
 */
public interface Diccionario {

    String NOMBRE_APLICACION = "Task Man";
    String YA_EXISTE_PROYECTO = "Ya existe un proyecto con ese nombre, usa otro.";
    String NO_SE_PUDO_GUARDAR = "No se ha podido guardar el proceso.";
    String NO_SE_PUDO_GUARDAR_PRJ = "No se ha podido guardar el nuevo proyecto.";
    String NO_SE_PUDO_GUARDAR_CONF = "No se pudo guardar la configuracion.";
    String NO_SE_PUDO_ELIMINAR_PRJ = "No se ha podido eliminar el proyecto.";
    String SEGURO_BORRAR = "¿Estas seguro de que deseas borrarlo?";
    String[] ESTADO_PRJ = {"Sin empezar", "En proceso", "Terminado"};
    String INTRODUCE_NOMBRE_PRJ = "Introduce el nombre del proyecto.\n(No debe exceder los " + LIMITE_NOMBRE + " caracteres)";
    String INTRODUCE_NOMBRE_ELEMENTO = "Introduce el nuevo nombre del elemento.";
    String MODIFICAR = "Modificar";
    String FALLO_NAVEGADOR = "Por algún motivo no se ha podido abrir el navegador.\nNo importa, te pongo el link aquí abajo.";
    String ACERCA = "<html>"
            + "<h3>" + NOMBRE_APLICACION + "</h3>"
            + "<p>Task Man</p><p></p>"
            + "<h3>Desarrollador</h3>"
            + "<p>Carlos Aguirre</p><p></p>"
            + "<h3>Sobre esta aplicación</h3>"
            + "<p>Versión: " + VERSION + "</p>"
            + "<p>Este programa permite gestionar las tareas de forma sencilla.</p>"
            + "<p>Cada proyecto se guarda en un archivo con extension ." + EXTENSION + " dentro de la carpeta proyectos, de esta manera los puedes mover fácilmente.</p><p></p>"
            + "<p>Si te gusta el proyecto y quieres contribuir puedes hacer un pequeño donativo haciendo click en el botón <em>Invitar a un cafe</em>.</p><p></p>"
            + "<p>Muchas gracias por usar esta aplicación.</p>"
            + "</html>";
    String DONATIVO = "Donativo";
    String CONFIRMAR = "Confirmar";
    String CREAR_PRJ = "Crear proyecto";
    String NUEVO_PRJ = "Nuevo proyecto";
    String ACEPTAR = "Aceptar";
    String CERRAR = "Cerrar";
    String ELIMINAR = "Eliminar";
    String EDITAR = "Editar";
    String SELECCIONA_PRJ = "Selecciona un proyecto";
    String TITULO = "Título";
    String NOMBRE_ARCHIVO = "Nombre de archivo";
    String FECHA_CREACION = "Fecha de creación";
    String FECHA_MODIFICACION = "Última modificación";
    String ESTADO = "Estado";
    String INVITAR_CAFE = "Invitar a un café";
    String GIT_HUB = "Git Hub";
    String ABRIR_SELECCIONADO = "Abrir seleccionado";
    String TAREAS = "Tareas";
    String LISTA_TAREAS = "Lista de tareas";
    String EN_PROCESO = "En proceso";
    String HECHOS = "Hechos";
    String NADA = "#";
    String NADA_DIGITO = "--";

}
