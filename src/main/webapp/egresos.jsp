<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

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

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">DISTRIBUIDORA</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="productos.jsp">Productos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="proveedores.jsp">Proveedores</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="categorias.jsp">Categorias</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="ingresos.jsp">Ingresos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="egresos.jsp">Egresos</a>
        </li>
        <c:if test="${sessionScope.usuario != null}">
                <a class="btnLogin" href="login.jsp">CERRAR SESION</a>
                </c:if>
      </ul>
    </div>
  </div>
</nav>

</header>

<main>

    <div class="formulario">
        <h2>EGRESOS</h2>
    <form action="seLogin" method="POST">

    <label for="txtNombreProducto">Nombre Producto</label>
    <br>
    <input type="text" name="txtNombreProducto" id="txtNombreProducto" placeholder="Nombre Producto" required />
    <br>
    <label for="txtPrecioCompra">Precio Compra</label>
    <br>
    <input type="text" name="txtPrecioCompra" id="txtPrecioCompra" placeholder="Precio Compra" required />
    <br>
    <label for="txtPrecioVenta">Precio Venta</label>
    <br>
    <input type="text" name="txtPrecioVenta" id="txtPrecioVenta" placeholder="Precio Venta" required />
    <br>
    <label for="txtCantidad">Cantidad</label>
    <br>
    <input type="text" name="txtCantidad" id="txtCantidad" placeholder="Cantidad" required />

    <br>
    <input class="btn" type="submit" value="Enviar" />

    </form>
    </div>

</main>

</body>
</html>