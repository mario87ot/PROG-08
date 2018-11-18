package tienda;

/**
 *
 * @author Mario
 * @version 10/04/2017
 */
public class Fruta extends Producto {

    private String temporada;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor con parámetros, hereda los atributos de la clase padre
     * Producto y tiene un atributo adicional temporada
     *
     * @param codigo Codigo del producto Fruta
     * @param nombre Nombre del producto Fruta
     * @param temporada Especifica si la fruta es de temporada o no
     * @param cantidad Cantidad de frutas
     */
    public Fruta(Integer codigo, String nombre, int cantidad, String temporada) {
        super(codigo, nombre, cantidad);
        this.temporada = temporada;

    } 
    
    /**
     * Método getter para obtener si es de temporada o no
     *
     * @return Si la fruta es de temporada o no
     */
    public String getTemporada() {
        return temporada;
    }

    /**
     * Método setter para establecer si es de temporada o no
     *
     * @param temporada Si la fruta es de temporada o no
     */
    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }


    /**
     * Método toString que muestra los datos de las frutas
     *
     * @return Una cadena con los datos de los productos Fruta
     */
    @Override
    public String toString() {
        return " Fruta: Código del producto: " + codigo + "\n Nombre del producto: " + nombre + "\n ¿Es fruta de temporada?: " + temporada+"\n";
    }

}

