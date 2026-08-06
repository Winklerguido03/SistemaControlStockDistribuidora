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
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link
      rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"/>

    <!-- CSS Personalizado -->
    <link rel="stylesheet" href="css/style.css" />
  </head>
<body>
<header>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="DashboardServlet">DISTRIBUIDORA</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
      <div class="nav1">
      </div>
        <li class="nav-item">
          <a class="nav-link" href="ProductoServlet">PRODUCTOS</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="ProveedorServlet">PROVEEDORES</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="CategoriaServlet">CATEGORIAS</a>
        </li>
      </ul>
      <c:if test="${sessionScope.usuario != null}">
                         <div class="ms-lg-auto mt-3 mt-lg-0">
                                     <a class="btnLogin" href="login.jsp">
                                         CERRAR SESION
                                     </a>
                                 </div>
          </c:if>
    </div>

  </div>
</nav>

</header>
    <main>
          <body class="bg-light">

          <div class="container mt-5">

              <h2 class="mb-4 text-center">Dashboard - Sistema de Gestión de Stock</h2>

              <div class="row g-4">

                  <div class="col-md-3">
                      <div class="card text-center shadow">
                          <div class="card-body">
                              <h5 class="card-title">Productos</h5>
                              <h2>${totalProductos}</h2>
                          </div>
                      </div>
                  </div>

                  <div class="col-md-3">
                      <div class="card text-center shadow">
                          <div class="card-body">
                              <h5 class="card-title">Categorías</h5>
                              <h2>${totalCategorias}</h2>
                          </div>
                      </div>
                  </div>

                  <div class="col-md-3">
                      <div class="card text-center shadow">
                          <div class="card-body">
                              <h5 class="card-title">Proveedores</h5>
                              <h2>${totalProveedores}</h2>
                          </div>
                      </div>
                  </div>

                  <div class="col-md-3">
                      <div class="card text-center shadow">
                          <div class="card-body">
                              <h5 class="card-title">Stock Total</h5>
                              <h2>${stockTotal}</h2>
                          </div>
                      </div>
                  </div>

              </div>

              <div class="row mt-4 g-4">

                  <div class="col-md-4">
                      <div class="card shadow">
                          <div class="card-header">
                              Productos sin stock
                          </div>
                          <div class="card-body">
                              <h3>${productosSinStock}</h3>
                          </div>
                      </div>
                  </div>

                  <div class="col-md-4">
                      <div class="card shadow">
                          <div class="card-header">
                              Productos con stock
                          </div>
                          <div class="card-body">
                              <h3>${productosConStock}</h3>
                          </div>
                      </div>
                  </div>

                  <div class="col-md-4">
                      <div class="card shadow">
                          <div class="card-header">
                              Producto con mayor stock
                          </div>
                          <div class="card-body">
                              <strong>${productoMayorStock.nombre}</strong><br>
                              Stock: ${productoMayorStock.stock}
                          </div>
                      </div>
                  </div>

              </div>

          </div>

          </body>
    </main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
