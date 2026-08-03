<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Editar Proveedor</title>

    <!-- Bootstrap 5 -->
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        rel="stylesheet"/>

    <link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"/>

    <!-- CSS -->
    <link rel="stylesheet" href="css/styleLogin.css" />

</head>

<body>

<header>

<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <div class="container-fluid">

        <a class="navbar-brand" href="DashboardServlet">
            DISTRIBUIDORA
        </a>

    </div>

</nav>

</header>

<main>

<div class="formulario">

<h2>EDITAR PROVEEDOR</h2>

<form action="ProveedorServlet" method="POST">

    <input
        type="hidden"
        name="operacion"
        value="actualizar"/>

    <input
        type="hidden"
        name="txtId"
        value="${proveedor.idProveedor}"/>


    <label for="txtNombreProveedor">
        Nombre Proveedor
    </label>

    <br>

    <input
        type="text"
        name="txtNombreProveedor"
        id="txtNombreProveedor"
        value="${proveedor.nombre}"
        required/>

    <br>


    <label for="txtTelefono">
        Teléfono
    </label>

    <br>

    <input
        type="text"
        name="txtTelefono"
        id="txtTelefono"
        value="${proveedor.telefono}"
        required/>

    <br>


    <label for="txtEmail">
        Email
    </label>

    <br>

    <input
        type="email"
        name="txtEmail"
        id="txtEmail"
        value="${proveedor.email}"
        required/>

    <br>


    <label for="txtDireccion">
        Dirección
    </label>

    <br>

    <input
        type="text"
        name="txtDireccion"
        id="txtDireccion"
        value="${proveedor.direccion}"
        required/>

    <br><br>


    <input
        class="btn btn-warning"
        type="submit"
        value="Guardar Cambios"/>

</form>

</div>

</main>

</body>

</html>
```
