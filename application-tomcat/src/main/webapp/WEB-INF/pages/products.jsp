<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produtos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Produtos</h2>
        <a href="${pageContext.request.contextPath}/products?action=new" class="btn btn-success">
            + Novo Produto
        </a>
    </div>

    <table class="table table-striped table-bordered align-middle">
        <thead class="table-dark">
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Valor</th>
                <th>Criado em</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.code}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>
                        <fmt:setLocale value="pt_BR"/>
                        <fmt:formatNumber value="${product.value}" type="currency"/>
                    </td>
                    <td>${product.createdAt}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}"
                           class="btn btn-sm btn-warning me-1">Editar</a>
                        <form action="${pageContext.request.contextPath}/products"
                              method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${product.id}">
                            <button type="submit" class="btn btn-sm btn-danger"
                                    onclick="return confirm('Deseja excluir o produto ${product.name}?')">
                                Excluir
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty products}">
                <tr>
                    <td colspan="6" class="text-center text-muted py-3">
                        Nenhum produto cadastrado.
                    </td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
