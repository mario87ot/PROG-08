package tienda;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Mario
 * @version 10/04/2017
 */
public class Main {

    private static boolean cambiosEnArray = false;//variable para comprobar si ha habido algún cambio en el array desde la última vez que se guardaron cambios
    private static ArrayList<Producto> arrayProductos = new ArrayList<>();
    

    public static void main(String[] args) {

        /*Al ejecutar la aplicación, recuperamos los datos que haya guardados en el
        fichero productos.dat y lo volcamos en el arraylist de productos. Si no existe
        el fichero, se avisa al usuario de que no hay datos grabados*/
        arrayProductos = Main.recuperaDatos("productos.dat");

        int opcion;
        do {
            opcion = ES.leeEntero("========================================\n"
                    + "========= Gestión de la tienda =========\n"
                    + "========================================\n"
                    + "1.- Añadir un producto.\n"
                    + "2.- Listar productos.\n"
                    + "3.- Borrar un producto.\n"
                    + "4.- Guardar datos en fichero.\n"
                    + "5.- Recuperar datos desde fichero.\n"
                    + "6.- Escribir lista de productos a txt.\n"
                    + "0.- Salir de la aplicación.\n"
                    + "========================================\n"
                    + "Introduzca la opción elegida: ", 0, 6);

            switch (opcion) {

                case 1:
                    altaProducto();
                    break;

                case 2:
                    Main.listarProductos();
                    break;

                case 3:
                    Main.borrarProducto(arrayProductos);
                    break;

                case 4:
                    Main.guardaDatos();
                    cambiosEnArray = false;
                    break;

                case 5:
                    if (cambiosEnArray == true) {
                        System.out.println("Ha realizado cambios que no ha guardado en disco. \n"
                                + "Si continúa la carga del archivo se restaurarán los datos\n"
                                + "de disco y se perderán los cambios no guardados. \n");
                    }
                    String confirmar;
                    confirmar = ES.leeRespuesta("¿Desea continuar con la carga y restaurar los datos del archivo? (S/N)");
                    if (confirmar.equalsIgnoreCase("s")) {
                        //Recuperamos los datos que tenga guardados el fichero productos.dat
                        Main.recuperaDatos("productos.dat");

                    }
                    break;

                case 6:
                    Main.escribirListaTxt(arrayProductos);
                    break;

                case 0:
                    String confirmacion = "";
                    if (cambiosEnArray == true) {
                        confirmacion = ES.leeCadena("Ha realizado cambios que no ha guardado en disco. ¿Desea guardarlos antes de salir?(S/N)");
                    }
                    switch (confirmacion) {

                        case "S":
                        case "s":
                            Main.guardaDatos();
                            cambiosEnArray = false;
                            break;

                    }

                    System.out.println("Programa finalizado");
                    break;
            }

        } while (opcion != 0);

    }

    /**
     * Método que permite añadir productos Fruta o Pan al arraylist de productos
     *
     * @return Arraylist de productos
     */
    private static void altaProducto() {
        int codigo = 0;
        String nombre = "";
        int cantidad = 0;
        String temporada;
        String gluten;

        System.out.println("Introduzca los datos del producto:");
        String confirmacion;

        confirmacion = ES.leeRespuesta("Si es pan escriba S, si es fruta escriba N (S/N)");

        if (confirmacion.equalsIgnoreCase("S")) {
            boolean valido = false;
            while (!valido) {

                nombre = ES.leeCadena("Nombre del producto:");
                if (!nombre.equals("")) {
                    valido = true;

                } else {
                    System.out.println("El nombre no puede ser vacío");
                }

            }

            gluten = ES.leeRespuesta("¿Es sin gluten? (S/N)");

            if (gluten.equalsIgnoreCase("s")) {
                gluten = "Sin gluten";
            } else {
                gluten = "Ojo alérgenos, contiene gluten";
            }
            cantidad = ES.leeEntero("Cantidad: ", 1);

            System.out.println("Se ha creado el nuevo producto.");
            //Añadimos al arraylist
            arrayProductos.add(new Pan(codigo, nombre, cantidad, gluten));
            cambiosEnArray = true;
        }

        if (confirmacion.equalsIgnoreCase("N")) {
            boolean valido = false;
            while (!valido) {
                nombre = ES.leeCadena("Nombre del producto:");
                if (!nombre.equals("")) {
                    valido = true;

                } else {
                    System.out.println("El nombre no puede ser vacío");
                }
            }
            temporada = ES.leeRespuesta("¿Es fruta de temporada? (S/N)");
            if (temporada.equalsIgnoreCase("s")) {
                temporada = "Sí";
            } else {
                temporada = "No";
            }
            cantidad = ES.leeEntero("Cantidad: ", 1);
            System.out.println("Se ha creado el nuevo producto.");
            //Añadimos al arraylist
            arrayProductos.add(new Fruta(codigo, nombre, cantidad, temporada));
            cambiosEnArray = true;
        }

    }

    /**
     * Método que listará los productos que hay actualmente en el arraylist, así
     * como el total de productos
     *
     * @param producto Arraylist con los productos almacenados
     */
    private static void listarProductos() {
        System.out.println(" Lista de productos de la tienda.\n"
                + "--------------------------------------------------------");
        for (Producto p : arrayProductos) {
            System.out.println(p.toString());
        }

        System.out.println(" Total de productos = " + Producto.totalProductos);

    }

    /**
     * Método que permite borrar un producto del arraylist de productos
     * indicando su código
     *
     * @param producto Arraylist de productos
     */
    private static void borrarProducto(ArrayList<Producto> arrayProductos) {
        Integer codigo;

        //Preguntamos el código del producto a borrar
        codigo = ES.leeEntero("Introduzca el código del producto a borrar: ");

        // Buscar si existe un producto con ese código
        int posicion = buscarProducto(arrayProductos, codigo);

        // Si se encontró un producto con ese código
        if (posicion != -1) {

            System.out.println("Se va a proceder a borrar de la lista: \n" + arrayProductos.get(posicion).toString());
            // Pedir confirmación de borrado al usuario
            String respuesta = ES.leeRespuesta("¿Desea continuar con el borrado? (S/N):");
            if (respuesta.equalsIgnoreCase("S")) {
                //Eliminamos el objeto
                for (int i = 0; i < arrayProductos.size(); i++) {
                    if (arrayProductos.get(i).getCodigo().equals(arrayProductos.get(posicion).getCodigo())) {
                        arrayProductos.remove(i);
                    }
                }
                Producto.totalProductos--;

                ES.msgln("Se borró el producto.");
                cambiosEnArray = true;
            } else {
                ES.msgln("No se eliminó nada.");

            }
        } else {
            ES.msgln("No se encuentra un producto con ese código.");

        }

    }

    /**
     * Método que permite buscar el código de un producto, comparándolo con el
     * pasado por parámetro
     *
     * @param producto Arraylist de productos donde buscar el código
     * @param codigo Código del producto
     * @return -1 si no se encuentra el código o un valor positivo con el lugar
     * en el arraylist que ocupa el producto buscado.
     */
    private static int buscarProducto(ArrayList<Producto> arrayProductos, Integer codigo) {
        boolean encontrado = false;
        int posicion = -1;

        // Buscar un objeto por su código
        int contador = 0;
        while (contador < arrayProductos.size() && !encontrado) {

            if (arrayProductos.get(contador) != null) {
                if ((arrayProductos.get(contador).getCodigo().equals(codigo))) {
                    encontrado = true;
                    posicion = contador;
                }
            }

            contador++;
        }
        return posicion;
    }

    /**
     * Método que permite guardar los datos del arraylist de productos en el
     * fichero productos.dat
     *
     * @param producto Arraylist de productos
     */
    private static void guardaDatos() {
        try {
            //Abrimos el fichero para escribir y guardar en él
            ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream("productos.dat"));
            //Esribimos el número total de productos
            escribiendoFichero.writeInt(Producto.totalProductos);
            //Escribimos los objetos del arraylist en el fichero
            escribiendoFichero.writeObject(arrayProductos);
            //Cerramos el fichero
            escribiendoFichero.close();

            System.out.println("Los datos se han guardado correctamente en el fichero productos.dat");
        } catch (IOException e) {
            System.out.println("Error, no se han guardado los datos");
        }

    }

    /**
     * Método que permite cargar los datos almacenados en el fichero
     * productos.dat en el arraylist de productos
     *
     * @param archivo nombre del archivo del cual se van a recuperar los datos
     * que tenga grabados
     * @return ArrayList de productos
     */
    public static ArrayList<Producto> recuperaDatos(String archivo) {

        FileInputStream fis;
        try {
            // Abrimos el fichero para lectura
            fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            /*Leemos y recuperamos los datos en el mismo orden que se guardaron, primero
            el total de productos y después los objetos*/
            Producto.totalProductos = ois.readInt();
            arrayProductos = (ArrayList<Producto>) ois.readObject();
            //Cerramos el fichero
            ois.close();

            cambiosEnArray = false;

            System.out.println("Se han cargado los datos en la aplicación:");
            System.out.println("Había almacenados un total de " + Producto.totalProductos + " productos\n");

            int ultimoCodigo = 0;
            /*Recorremos el arraylist y almacenamos el último código de producto
              que tengamos guardado*/
            for (int i = 0; i < arrayProductos.size(); i++) {
                ultimoCodigo = arrayProductos.get(i).getCodigo();
            }
            /*Le asignamos su valor + 1 a la variable codigoSiguiente para que siga asignando
            a los productos un código a partir del último que hubiera guardado y no empiece a
            asignarse los códigos a partir de 1 nuevamente*/
            Producto.codigoSiguiente = ultimoCodigo + 1;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("No se ha encontrado el fichero productos.dat. No existen datos previos que cargar");
        }
        return arrayProductos;
    }

    /**
     * Método que permite escribir la lista de productos del arraylist de
     * productos en el fichero productos.txt
     *
     * @param producto Arraylist de productos
     */
    private static void escribirListaTxt(ArrayList<Producto> arrayProductos) {

        try {
            //Abrimos el fichero para escribir en él
            FileWriter salida = new FileWriter("productos.txt");
            //Envolvemos en un buffer
            BufferedWriter bw = new BufferedWriter(salida);
            //Escribimos en el fichero de texto los datos de los objetos de nuestro arraylist de productos
            PrintWriter pw = new PrintWriter(bw);
            for (int j = 0; j < arrayProductos.size(); j++) {
                pw.println(arrayProductos.get(j));

            }
            //Cerramos el fichero
            pw.close();
        } catch (IOException ex) {
            System.out.println("No se ha escrito nada");
        }
    }

}
