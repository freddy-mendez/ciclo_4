package co.edu.unab.mercapp;

import java.util.ArrayList;

import co.edu.unab.mercapp.entity.Categoria;

public class DataTemp {
    public static final ArrayList<Categoria> CATEGORIAS = new ArrayList<>();

    public static final ArrayList<Categoria> CATEGORIAS_INTERES = new ArrayList<>();

    static final void cargarCategorias() {
        CATEGORIAS.add(new Categoria("Aseo Personal",
                "Elementos de aseo para el cuidad personal"));
        CATEGORIAS.add(new Categoria("Alimentos",
                "Gran variedad de productos de gran calidad"));
        CATEGORIAS.add(new Categoria("Bebidas",
                "Productos refrescantes para cualquier momento"));
        CATEGORIAS.add(new Categoria("Electrodomesticos",
                "Dispositivos electronicos para el entretenimiento "));
        CATEGORIAS.add(new Categoria("Lacteos",
                "Variedad de productos derivados de la Lecha "));
        CATEGORIAS.add(new Categoria("Cereales",
                "Gran variedad en productos de cereales"));
    }
}
