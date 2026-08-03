package utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PasswordUtilTest {

    @Test
    public void testHashPassword_DiferentesHashesPorSalting() {
        String plainPassword = "MiPasswordSeguro123";

        String hash1 = PasswordUtil.hashPassword(plainPassword);
        String hash2 = PasswordUtil.hashPassword(plainPassword);

        // Verificamos que no devuelva null o vacío
        assertNotNull(hash1);
        assertFalse(hash1.isEmpty());

        // BCrypt genera una "salt" aleatoria cada vez.
        // Por lo tanto, el mismo password hasheado dos veces NO debe dar el mismo string.
        assertNotEquals(hash1, hash2, "BCrypt debería generar hashes distintos por el salting interno");

        // Verificamos que tenga el formato de BCrypt (empieza con $2a$, $2b$, o $2y$)
        assertTrue(hash1.startsWith("$2a$") || hash1.startsWith("$2b$") || hash1.startsWith("$2y$"));
    }

    @Test
    public void testVerifyPassword_Correcta() {
        String plainPassword = "PasswordDePrueba";
        String hashedPassword = PasswordUtil.hashPassword(plainPassword);

        // Debe verificar correctamente la contraseña original contra el hash
        boolean isMatch = PasswordUtil.verifyPassword(plainPassword, hashedPassword);
        assertTrue(isMatch, "La verificación debería ser exitosa con la contraseña correcta");
    }

    @Test
    public void testVerifyPassword_Incorrecta() {
        String plainPassword = "PasswordCorrecto";
        String wrongPassword = "PasswordIncorrecto";
        String hashedPassword = PasswordUtil.hashPassword(plainPassword);

        // Debe fallar al intentar verificar una contraseña diferente contra el hash
        boolean isMatch = PasswordUtil.verifyPassword(wrongPassword, hashedPassword);
        assertFalse(isMatch, "La verificación debería fallar con una contraseña incorrecta");
    }

    @Test
    public void testVerifyPassword_HashNuloOInvalido() {
        String plainPassword = "123";

        // Spring Security's BCryptPasswordEncoder arroja IllegalArgumentException
        // o devuelve false si el formato del hash no es de BCrypt (dependiendo de la versión).
        // Generalmente, un string aleatorio devuelve false.
        boolean result = PasswordUtil.verifyPassword(plainPassword, "hash_invalido_cualquiera");
        assertFalse(result);
    }

    @Test
    public void testConstructor() {
        // Al instanciarlo cubrimos el constructor por defecto (implicito)
        // evaluado en el porcentaje de Jacoco de la clase PasswordUtil
        PasswordUtil util = new PasswordUtil();
        assertNotNull(util);
    }
}