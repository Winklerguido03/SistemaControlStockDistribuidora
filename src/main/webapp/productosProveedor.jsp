<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>DISTRIBUIDORA</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">

    <link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"/>

    <link rel="stylesheet" href="css/styleProductos.css" />

</head>

<body>


<header>

<nav class="navbar navbar-expand-lg bg-body-tertiary">

<div class="container-fluid">

<a class="navbar-brand" href="DashboardServlet">
DISTRIBUIDORA
</a>


<button class="navbar-toggler"
type="button"
data-bs-toggle="collapse"
data-bs-target="#navbarNav">

<span class="navbar-toggler-icon"></span>

</button>


<div class="collapse navbar-collapse" id="navbarNav">

<ul class="navbar-nav">

<li class="nav-item">
<a class="nav-link" href="ProductoServlet">
Productos
</a>
</li>


<li class="nav-item">
<a class="nav-link" href="ProveedorServlet">
Proveedores
</a>
</li>


<li class="nav-item">
<a class="nav-link" href="CategoriaServlet">
Categorias
</a>
</li>


<c:if test="${sessionScope.usuario != null}">

<a class="btnLogin" href="login.jsp">
CERRAR SESION
</a>

</c:if>


</ul>

</div>

</div>

</nav>

</header>



<main>


<div class="container mt-4">


<div class="d-flex justify-content-between align-items-center mb-3">


<h2>
Productos del proveedor: ${proveedor.nombre}
</h2>


<a href="ProveedorServlet" class="btn btn-secondary">

<i class="bi bi-arrow-left"></i>

Volver

</a>


</div>



<table class="table table-striped table-hover table-bordered align-middle">


<thead class="table-dark">


<tr>

<th>Nombre</th>

<th>Categoría</th>

<th>Proveedor</th>

<th>Precio Compra</th>

<th>Precio Venta</th>

<th>Stock</th>

</tr>


</thead>



<tbody>


<c:forEach var="producto" items="${listaProductos}">


<tr>


<td>
${producto.nombre}
</td>


<td>
${producto.categoriaProducto.nombre}
</td>


<td>
${producto.proveedorProducto.nombre}
</td>


<td>
${producto.precioCompra}
</td>


<td>
${producto.precioVenta}
</td>


<td>
${producto.stock}
</td>


</tr>


</c:forEach>



<c:if test="${empty listaProductos}">


<tr>

<td colspan="6" class="text-center">

No hay productos asociados a este proveedor.

</td>

</tr>


</c:if>


</tbody>


</table>


</div>


</main>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>


</body>

</html>