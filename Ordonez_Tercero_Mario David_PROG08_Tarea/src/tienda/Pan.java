package tienda;

/**
 *
 * @author Mario
 * @version 10/04/2017
 */
public class Pan extends Producto {

    private String gluten;
    private static final long serialVersionUID = 1L;

    /**
     * Constructor con parámetros, hereda los atributos de la clase padre
     * Producto y tiene un atributo adicional gluten
     *
     * @param codigo Codigo del producto Pan
     * @param nombre Nombre del producto Pan
     * @param gluten Especifica si tiene o no gluten
     * @param cantidad Cantidad de panes
     */
    public Pan(Integer codigo, String nombre, int cantidad, String gluten) {
        super(codigo, nombre, cantidad);
        this.gluten = gluten;

    }
    
    /**
     * Método getter que obtiene si el pan tiene gluten o no
     * @return Si tiene gluten o no el pan
     */
    public String getGluten() {       
        return gluten;
    }

    /**
     * Método setter que establece si el pan tiene gluten o no
     * @param gluten Si tiene gluten o no el pan
     */
    public void setGluten(String gluten) {
        this.gluten = gluten;
    }
    
      
    /**
     * Método toString que muestra los datos de los productos Pan
     *
     * @return Una cadena con los datos de los productos Pan
     */
    @Override
    public String toString() {
        return " Pan: Código del producto: " + codigo + "\n Nombre del producto: " + nombre + "\n ¿Es sin gluten?: " + gluten+"\n";
    }

}
