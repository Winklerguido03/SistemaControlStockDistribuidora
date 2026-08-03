<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Ingreso</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/styleLogin.css">
</head>
<body>

<header>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">

        <a class="navbar-brand" href="DashboardServlet">DISTRIBUIDORA</a>

        <div class="collapse navbar-collapse">

            <ul class="navbar-nav">

                <li class="nav-item">
                    <a class="nav-link" href="ProductoServlet">Productos</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="ProveedorServlet">Proveedores</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="CategoriaServlet">Categorías</a>
                </li>

            </ul>

        </div>

    </div>
</nav>

</header>

<main>

<div class="formulario">

    <h2>Registrar Ingreso</h2>

    <form action="IngresoServlet" method="post">

        <input type="hidden" name="operacion" value="nuevo">

        <label>Nombre del Producto</label><br>
        <input type="text"
               name="txtNombreProducto"
               placeholder="Nombre del producto"
               required>

        <br><br>

        <label>Categoría</label><br>

        <select name="cmbCategoria" required>

            <option value="">Seleccione una categoría</option>

            <c:forEach var="categoria" items="${listaCategorias}">
                <option value="${categoria.idCategoria}">
                    ${categoria.nombre}
                </option>
            </c:forEach>

        </select>

        <br><br>

        <label>Proveedor</label><br>

        <select name="cmbProveedor" required>

            <option value="">Seleccione un proveedor</option>

            <c:forEach var="proveedor" items="${listaProveedores}">
                <option value="${proveedor.idProveedor}">
                    ${proveedor.nombre}
                </option>
            </c:forEach>

        </select>

        <br><br>

        <label>Precio de Compra</label><br>
        <input type="number"
               name="txtPrecioCompra"
               min="0"
               required>

        <br><br>

        <label>Precio de Venta</label><br>
        <input type="number"
               name="txtPrecioVenta"
               min="0"
               required>

        <br><br>

        <label>Cantidad</label><br>
        <input type="number"
               name="txtCantidad"
               min="1"
               required>

        <br><br>

        <input class="btn btn-success"
               type="submit"
               value="Registrar Ingreso">

    </form>

</div>

</main>

</body>
</html>