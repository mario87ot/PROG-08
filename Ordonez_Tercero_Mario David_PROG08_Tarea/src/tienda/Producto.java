package tienda;

import java.io.Serializable;

/**
 *
 * @author Mario
 * @version 10/04/2017 
 * La clase Producto será abstracta, ya que no se van a
 * crear objetos de esta clase, sino de las clases que heredan de ella.
 *
 */
public abstract class Producto implements Serializable {

    protected Integer codigo;
    protected String nombre;
    protected int cantidad;
    protected static int totalProductos = 0;//Variable que almacena la cantidad total de productos
    protected static int codigoSiguiente = 1;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor con parámetros
     *
     * @param codigo Codigo del producto
     * @param nombre Nombre del producto
     * @param cantidad Cantidad de productos (de frutas o de panes)
     */
    public Producto(Integer codigo, String nombre, int cantidad) {
        /*Asignamos a codigo la variable codigoSiguiente, que se incrementará en uno
        cada vez que creamos un objeto nuevo, de forma que el código de cada producto
        se asigne automáticamente sin reutilizarse uno ya usado*/
        this.codigo = codigoSiguiente;
        this.setNombre(nombre);
        this.setCantidad(cantidad);
        codigoSiguiente++;
        totalProductos++;

    }

    /**
     * Método getter que obtiene el código del producto
     *
     * @return El código del producto
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Método setter para estblecer el código del producto
     *
     * @param codigo Código del producto
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * Método getter que obtiene el nombre del producto
     *
     * @return Nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método setter que establece el nombre del producto
     *
     * @param nombre Nombre del producto
     * @throws IllegalArgumentException Lanza una excepción de este tipo si el
     * nombre es vacío o nulo
     */
    public void setNombre(String nombre) throws IllegalArgumentException {

        if (nombre == null || nombre.equals("")) {
            throw new IllegalArgumentException("El nombre no puede ser vacío o nulo");
        } else {
            this.nombre = nombre;
        }

    }

    /**
     * Método getter que obtiene la cantidad de productos (frutas o panes)
     *
     * @return Cantidad de productos (frutas o panes)
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Método setter que establece la cantidad de productos (frutas o panes)
     *
     * @param cantidad Cantidad de productos (frutas o panes)
     * @throws IllegalArgumentException Lanza una excepción si la cantidad no es
     * mayor que cero
     */
    public void setCantidad(int cantidad) throws IllegalArgumentException {
        if (cantidad > 0) {
            this.cantidad = cantidad;
        } else {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        }

    }

    /**
     * Método toString que muestra los datos de los productos
     *
     * @return Cadena con los datos del producto
     */
    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", cantidad=" + cantidad + '}';
    }

}
