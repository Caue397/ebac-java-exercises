<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${empty product ? 'Novo Produto' : 'Editar Produto'}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="mb-4">${empty product ? 'Novo Produto' : 'Editar Produto'}</h2>

            <form action="${pageContext.request.contextPath}/products" method="post">
                <input type="hidden" name="action" value="${empty product ? 'create' : 'update'}">
                <c:if test="${not empty product}">
                    <input type="hidden" name="id" value="${product.id}">
                </c:if>

                <div class="mb-3">
                    <label for="code" class="form-label">Código <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="code" name="code"
                           value="${product.code}" required>
                </div>

                <div class="mb-3">
                    <label for="name" class="form-label">Nome <span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="name" name="name"
                           value="${product.name}" required>
                </div>

                <div class="mb-3">
                    <label for="description" class="form-label">Descrição</label>
                    <textarea class="form-control" id="description" name="description"
                              rows="3">${product.description}</textarea>
                </div>

                <div class="mb-4">
                    <label for="value" class="form-label">Valor (R$) <span class="text-danger">*</span></label>
                    <input type="number" class="form-control" id="value" name="value"
                           step="0.01" min="0" value="${product.value}" required>
                </div>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary">Salvar</button>
                    <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
