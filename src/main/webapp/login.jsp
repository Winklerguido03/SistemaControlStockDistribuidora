<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<!doctype html>
<html lang="es">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>DISTRIBUIDORA</title>

    <!-- Bootstrap 5 -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link
      rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"/>

    <!-- CSS Personalizado -->
    <link rel="stylesheet" href="css/styleLogin.css" />
  </head>
<body>
<header>

 <nav class="navbar navbar-expand-lg navbar-light bg-light">
   <a class="navbar-brand" href="index.jsp">DISTRIBUIDORA</a>
   </div>
 </nav>

</header>

<main>

    <div class="formulario">
        <h2>INICIAR SESIÓN</h2>
    <form action="seLogin" method="POST">

    <label for="txtUser">CORREO</label>
    <br>
    <input type="text" name="txtCorreo" id="txtCorreo" placeholder="usuario@gmail.com" required />
    <br>
    <label for="txtPass">CONTRASEÑA</label>
    <br>
    <input type="password" name="txtPass" id="txtPass" placeholder="Contraseña" required />


    <br>
    <input class="btn" type="submit" value="Enviar" />

    </form>
    </div>

</main>

</body>
</html>
