package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void constructorVacio_deberiaCrearUsuario(){

        Usuario usuario = new Usuario();


        assertNotNull(usuario);

    }






    @Test
    void constructorConParametros_deberiaAsignarValores(){

        Usuario usuario =
                new Usuario(
                        "Guido",
                        "Winkler",
                        "guido123",
                        "123456"
                );



        assertEquals(
                "Guido",
                usuario.getNombre()
        );


        assertEquals(
                "Winkler",
                usuario.getApellido()
        );


        assertEquals(
                "guido123",
                usuario.getUsername()
        );


        assertEquals(
                "123456",
                usuario.getPassword()
        );

    }







    @Test
    void setIdUsuario_deberiaGuardarId(){

        Usuario usuario = new Usuario();


        usuario.setIdUsuario(1);



        assertEquals(
                1,
                usuario.getIdUsuario()
        );

    }







    @Test
    void setNombre_deberiaGuardarNombre(){

        Usuario usuario = new Usuario();


        usuario.setNombre("Juan");



        assertEquals(
                "Juan",
                usuario.getNombre()
        );

    }







    @Test
    void setApellido_deberiaGuardarApellido(){

        Usuario usuario = new Usuario();


        usuario.setApellido("Perez");



        assertEquals(
                "Perez",
                usuario.getApellido()
        );

    }







    @Test
    void setUsername_deberiaGuardarUsername(){

        Usuario usuario = new Usuario();


        usuario.setUsername("usuarioTest");



        assertEquals(
                "usuarioTest",
                usuario.getUsername()
        );

    }







    @Test
    void setPassword_deberiaGuardarPassword(){

        Usuario usuario = new Usuario();


        usuario.setPassword("password123");



        assertEquals(
                "password123",
                usuario.getPassword()
        );

    }







    @Test
    void modificarUsuario_deberiaActualizarTodosLosDatos(){

        Usuario usuario = new Usuario();



        usuario.setIdUsuario(10);
        usuario.setNombre("Nuevo Nombre");
        usuario.setApellido("Nuevo Apellido");
        usuario.setUsername("nuevoUser");
        usuario.setPassword("nuevaPass");



        assertEquals(
                10,
                usuario.getIdUsuario()
        );


        assertEquals(
                "Nuevo Nombre",
                usuario.getNombre()
        );


        assertEquals(
                "Nuevo Apellido",
                usuario.getApellido()
        );


        assertEquals(
                "nuevoUser",
                usuario.getUsername()
        );


        assertEquals(
                "nuevaPass",
                usuario.getPassword()
        );

    }

}