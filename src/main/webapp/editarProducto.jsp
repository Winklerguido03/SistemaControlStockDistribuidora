<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Editar Producto</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="css/styleLogin.css">

</head>


<body>


<header>


<nav class="navbar navbar-expand-lg bg-body-tertiary">

<div class="container-fluid">


<a class="navbar-brand" href="DashboardServlet">
DISTRIBUIDORA
</a>


<div class="collapse navbar-collapse">

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
Categorías
</a>
</li>


</ul>


</div>


</div>

</nav>


</header>





<main>

<div class="d-flex mb-3">

    <a href="ProductoServlet" class="btn btn-secondary ms-auto">
        <i class="bi bi-arrow-left"></i>
        Volver
    </a>

</div>


<div class="formulario">


<h2>Editar Producto</h2>



<form action="ProductoServlet" method="post">


<input type="hidden"
       name="operacion"
       value="actualizar">


<input type="hidden"
       name="idProducto"
       value="${producto.idProducto}">





<label>Nombre del Producto</label><br>

<input type="text"
       name="txtNombreProducto"
       value="${producto.nombre}"
       required>



<br><br>





<label>Categoría</label><br>


<select name="cmbCategoria" required>


<option value="">
Seleccione una categoría
</option>



<c:forEach var="categoria" items="${listaCategorias}">


<option value="${categoria.idCategoria}"

        ${producto.categoriaProducto.idCategoria == categoria.idCategoria ? 'selected' : ''}>

${categoria.nombre}

</option>


</c:forEach>


</select>



<br><br>






<label>Proveedor</label><br>


<select name="cmbProveedor" required>


<option value="">
Seleccione un proveedor
</option>




<c:forEach var="proveedor" items="${listaProveedores}">


<option value="${proveedor.idProveedor}"

        ${producto.proveedorProducto.idProveedor == proveedor.idProveedor ? 'selected' : ''}>


${proveedor.nombre}


</option>


</c:forEach>


</select>



<br><br>






<label>Precio de Compra</label><br>


<input type="number"

       name="txtPrecioCompra"

       value="${producto.precioCompra}"

       min="0"

       required>




<br><br>






<label>Precio de Venta</label><br>


<input type="number"

       name="txtPrecioVenta"

       value="${producto.precioVenta}"

       min="0"

       required>




<br><br>







<label>Stock</label><br>


<input type="number"

       name="txtCantidad"

       value="${producto.stock}"

       min="0"

       required>




<br><br>






<input class="btn btn-warning"

       type="submit"

       value="Guardar Cambios">



</form>



</div>



</main>



</body>


</html>