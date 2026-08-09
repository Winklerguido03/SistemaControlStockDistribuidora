package dao;

import entities.Categoria;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaDAOTest {

    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Test
    void getAll() {

        List<Categoria> categorias = categoriaDAO.getAll();

        assertNotNull(categorias);

    }

    @Test
    void getById() {

        Categoria categoria = categoriaDAO.getById(14);

        assertNotNull(categoria);

    }

    @Test
    void existsById() {

        boolean existe = categoriaDAO.existsById(14);

        assertTrue(existe);

    }

    @Test
    void insert() {

        Categoria categoria = new Categoria();

        categoria.setNombre("Categoria Test");

        assertDoesNotThrow(() -> categoriaDAO.insert(categoria));

    }

    @Test
    void update() {

        Categoria categoria = categoriaDAO.getById(26);

        assertNotNull(categoria);

        String nombreOriginal = categoria.getNombre();

        categoria.setNombre("Categoria Actualizada Test");

        assertDoesNotThrow(() -> categoriaDAO.update(categoria));

        // Restaurar el nombre original
        categoria.setNombre(nombreOriginal);
        categoriaDAO.update(categoria);

    }

    @Test
    void delete() {

        Categoria categoria = new Categoria();

        categoria.setNombre("Categoria Eliminar");

        categoriaDAO.insert(categoria);

        List<Categoria> categorias = categoriaDAO.getAll();

        Categoria ultima = categorias.get(categorias.size() - 1);

        assertDoesNotThrow(() -> categoriaDAO.delete(ultima.getIdCategoria()));

    }

}
