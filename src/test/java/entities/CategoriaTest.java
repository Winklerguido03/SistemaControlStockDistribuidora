package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    @Test
    void constructorVacio_deberiaCrearCategoria() {

        Categoria categoria = new Categoria();

        assertNotNull(categoria);

    }



    @Test
    void constructorConNombre_deberiaAsignarNombre() {

        Categoria categoria = new Categoria("Snacks");


        assertEquals(
                "Snacks",
                categoria.getNombre()
        );

    }



    @Test
    void setIdCategoria_deberiaGuardarId() {

        Categoria categoria = new Categoria();


        categoria.setIdCategoria(1);



        assertEquals(
                1,
                categoria.getIdCategoria()
        );

    }




    @Test
    void setNombre_deberiaGuardarNombre() {

        Categoria categoria = new Categoria();


        categoria.setNombre("Golosinas");



        assertEquals(
                "Golosinas",
                categoria.getNombre()
        );

    }



    @Test
    void modificarValores_deberiaActualizarDatos() {

        Categoria categoria = new Categoria("Inicial");


        categoria.setIdCategoria(5);
        categoria.setNombre("Nueva Categoria");



        assertEquals(
                5,
                categoria.getIdCategoria()
        );


        assertEquals(
                "Nueva Categoria",
                categoria.getNombre()
        );

    }

}