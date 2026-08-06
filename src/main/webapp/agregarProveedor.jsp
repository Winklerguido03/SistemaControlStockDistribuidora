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
   <a class="navbar-brand" href="DashboardServlet">DISTRIBUIDORA</a>
   </div>
 </nav>

</header>

<main>

<div class="d-flex mb-3">

    <a href="ProveedorServlet" class="btn btn-secondary ms-auto">
        <i class="bi bi-arrow-left"></i>
        Volver
    </a>

</div>

    <div class="formulario">
        <h2>AGREGAR PROVEEDOR</h2>
    <form action="ProveedorServlet" method="POST">

    <input type="hidden" name="txtId" id="txtId" value="-1" />
    <input type="hidden" name="operacion" id="operacion" value="nuevo" />

    <label for="txtNombreProveedor">Nombre Proveedor</label>
    <br>
    <input type="text" name="txtNombreProveedor" id="txtNombreProveedor" placeholder="Nombre Proveedor" required />
    <br>
    <label for="txtTelefono">Telefono</label>
    <br>
    <input type="text" name="txtTelefono" id="txtTelefono" placeholder="Telefono" required />
    <br>
    <label for="txtEmail">Email</label>
    <br>
    <input type="text" name="txtEmail" id="txtEmail" placeholder="proveedor@gmail.com" required />
    <br>
    <label for="txtDireccion">Dirección</label>
    <br>
    <input type="text" name="txtDireccion" id="txtDireccion" placeholder="Calle 55" required />

    <br>
    <input class="btn" type="submit" value="Enviar" />

    </form>
    </div>

</main>

</body>
</html>