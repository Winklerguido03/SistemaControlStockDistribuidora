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

        <c:if test="${sessionScope.usuario != null}">
                <a class="btnLogin" href="login.jsp">CERRAR SESION</a>
                </c:if>
      </ul>
    </div>
  </div>
</nav>

</header>

    <main>

          <div class="container mt-4">

              <div class="d-flex justify-content-between align-items-center mb-3">

                  <h2>Productos</h2>

                  <a href="ingresos.jsp"
                     class="btn btn-success">

                      <i class="bi bi-plus-circle"></i>
                      Nuevo Ingreso

                  </a>
                  <a href="ProductoServlet?accion=nuevo"
                                       class="btn btn-success">

                                        <i class="bi bi-plus-circle"></i>
                                        Nuevo Egreso

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

                          <td>${producto.nombre}</td>

                          <td>${producto.categoria.nombre}</td>

                          <td>${producto.proveedor.nombre}</td>

                          <td>

                              <fmt:formatNumber
                                      value="${producto.precioCompra}"
                                      type="currency"/>

                          </td>

                          <td>

                              <fmt:formatNumber
                                      value="${producto.precioVenta}"
                                      type="currency"/>

                          </td>

                           <td>

                                                         <c:choose>

                                                             <c:when test="${producto.stock == 0}">

                                                                 <span class="badge bg-danger">
                                                                     ${producto.stock}
                                                                 </span>

                                                             </c:when>

                                                             <c:when test="${producto.stock <= producto.stockMinimo}">

                                                                 <span class="badge bg-warning text-dark">
                                                                     ${producto.stock}
                                                                 </span>

                                                             </c:when>

                                                             <c:otherwise>

                                                                 <span class="badge bg-success">
                                                                     ${producto.stock}
                                                                 </span>

                                                             </c:otherwise>

                                                         </c:choose>

                                                     </td>

                          <td>

                              <a href="ProductoServlet?accion=editar&id=${producto.id}"
                                 class="btn btn-warning btn-sm">

                                  Editar

                              </a>

                              <a href="ProductoServlet?accion=eliminar&id=${producto.id}"
                                 class="btn btn-danger btn-sm"
                                 onclick="return confirm('¿Está seguro de eliminar este producto?')">

                                  Eliminar

                              </a>

                          </td>

                      </tr>

                  </c:forEach>

                  <c:if test="${empty productos}">

                      <tr>

                          <td colspan="8" class="text-center">

                              No hay productos registrados.

                          </td>

                      </tr>

                  </c:if>

                  </tbody>

              </table>

          </div>

    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
