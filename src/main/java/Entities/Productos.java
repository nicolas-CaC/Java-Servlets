package Entities;

import java.util.ArrayList;
import java.util.List;

public class Productos {

    public static List<Productos> listado = new ArrayList<>();
    
    private String nombre;
    private String marca;
    private int precio;

    public Productos(String nombre, String marca, int precio) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
    }

    // GETTERS
    public static List<Productos> getListado() {
        return listado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public int getPrecio() {
        return precio;
    }
    
    public void addProduct(){
        Productos nuevo = new Productos(this.nombre, this.marca, this.precio);
        Productos.listado.add(nuevo);
    }
    
    public boolean updateProduct(){
        boolean encontrado = false;
        
        for(Productos producto: Productos.listado){
            if(producto.nombre.equalsIgnoreCase(this.nombre)){
                producto.marca = this.marca;
                producto.precio = this.precio;
                encontrado = true;
                return encontrado;
            }
        }
        
        return encontrado;
    }
    
    public static boolean deleteProduct(String nombre){
        boolean encontrado = false;
        
        for(Productos producto: Productos.listado){
            if(producto.nombre.equalsIgnoreCase(nombre)){
                Productos.listado.remove(producto);
                encontrado = true;
                return encontrado;
            }
        }
        
        return encontrado;
    }
    
}
