package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProveedorTest {

    @Test
    void constructorVacio_deberiaCrearProveedor(){

        Proveedor proveedor = new Proveedor();


        assertNotNull(proveedor);

    }





    @Test
    void constructorConParametros_deberiaAsignarValores(){

        Proveedor proveedor =
                new Proveedor(
                        "Distribuidora Test",
                        "3415555555",
                        "proveedor@test.com",
                        "Direccion Test"
                );



        assertEquals(
                "Distribuidora Test",
                proveedor.getNombre()
        );


        assertEquals(
                "3415555555",
                proveedor.getTelefono()
        );


        assertEquals(
                "proveedor@test.com",
                proveedor.getEmail()
        );


        assertEquals(
                "Direccion Test",
                proveedor.getDireccion()
        );

    }






    @Test
    void setIdProveedor_deberiaGuardarId(){

        Proveedor proveedor = new Proveedor();


        proveedor.setIdProveedor(1);



        assertEquals(
                1,
                proveedor.getIdProveedor()
        );

    }







    @Test
    void setNombre_deberiaGuardarNombre(){

        Proveedor proveedor = new Proveedor();


        proveedor.setNombre("Proveedor Test");



        assertEquals(
                "Proveedor Test",
                proveedor.getNombre()
        );

    }







    @Test
    void setTelefono_deberiaGuardarTelefono(){

        Proveedor proveedor = new Proveedor();


        proveedor.setTelefono("123456789");



        assertEquals(
                "123456789",
                proveedor.getTelefono()
        );

    }







    @Test
    void setEmail_deberiaGuardarEmail(){

        Proveedor proveedor = new Proveedor();


        proveedor.setEmail("test@email.com");



        assertEquals(
                "test@email.com",
                proveedor.getEmail()
        );

    }







    @Test
    void setDireccion_deberiaGuardarDireccion(){

        Proveedor proveedor = new Proveedor();


        proveedor.setDireccion("Calle Falsa 123");



        assertEquals(
                "Calle Falsa 123",
                proveedor.getDireccion()
        );

    }








    @Test
    void modificarProveedor_deberiaActualizarTodosLosDatos(){

        Proveedor proveedor = new Proveedor();



        proveedor.setIdProveedor(5);
        proveedor.setNombre("Nuevo Proveedor");
        proveedor.setTelefono("111222333");
        proveedor.setEmail("nuevo@test.com");
        proveedor.setDireccion("Nueva Direccion");



        assertEquals(
                5,
                proveedor.getIdProveedor()
        );


        assertEquals(
                "Nuevo Proveedor",
                proveedor.getNombre()
        );


        assertEquals(
                "111222333",
                proveedor.getTelefono()
        );


        assertEquals(
                "nuevo@test.com",
                proveedor.getEmail()
        );


        assertEquals(
                "Nueva Direccion",
                proveedor.getDireccion()
        );

    }

}