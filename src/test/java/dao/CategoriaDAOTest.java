package dao;

import entities.Categoria;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaDAOTest {

    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Test
    void getAll_deberiaDevolverLista() {

        List<Categoria> categorias = categoriaDAO.getAll();

        assertNotNull(categorias);
    }

    @Test
    void getById_deberiaDevolverCategoria() {

        Categoria categoria = categoriaDAO.getById(14);

        assertNotNull(categoria);
    }

    @Test
    void existsById_deberiaDevolverTrueSiExiste() {

        boolean existe = categoriaDAO.existsById(14);

        assertTrue(existe);
    }

    @Test
    void insert_deberiaRegistrarCategoria() {

        Categoria categoria = new Categoria();
        categoria.setNombre("Categoria Test");

        assertDoesNotThrow(() -> categoriaDAO.insert(categoria));
    }

    @Test
    void update_deberiaActualizarCategoria() {

        Categoria categoria = categoriaDAO.getById(72);

        assertNotNull(categoria);

        String nombreOriginal = categoria.getNombre();

        categoria.setNombre("Categoria Actualizada Test");

        assertDoesNotThrow(() -> categoriaDAO.update(categoria));

        // Restaurar el estado original
        categoria.setNombre(nombreOriginal);
        categoriaDAO.update(categoria);
    }

    @Test
    void delete_deberiaEliminarCategoria() {

        Categoria categoria = new Categoria();
        categoria.setNombre("Categoria Eliminar");

        categoriaDAO.insert(categoria);

        List<Categoria> categorias = categoriaDAO.getAll();

        Categoria ultima = categorias.get(categorias.size() - 1);

        assertDoesNotThrow(
                () -> categoriaDAO.delete(ultima.getIdCategoria())
        );
    }
}