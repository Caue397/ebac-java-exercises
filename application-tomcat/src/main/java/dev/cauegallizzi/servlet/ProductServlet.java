package dev.cauegallizzi.servlet;

import dev.cauegallizzi.dao.IProductDao;
import dev.cauegallizzi.dao.ProductDao;
import dev.cauegallizzi.domain.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private final IProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new" -> showForm(req, resp, null);
            case "edit" -> {
                UUID id = UUID.fromString(req.getParameter("id"));
                Product product = productDao.getById(id);
                showForm(req, resp, product);
            }
            default -> listProducts(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "create" -> createProduct(req, resp);
            case "update" -> updateProduct(req, resp);
            case "delete" -> deleteProduct(req, resp);
            default -> resp.sendRedirect(req.getContextPath() + "/products");
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> products = productDao.getAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp, Product product)
            throws ServletException, IOException {
        req.setAttribute("product", product);
        req.getRequestDispatcher("/WEB-INF/pages/product-form.jsp").forward(req, resp);
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Product product = new Product();
        product.setCode(req.getParameter("code"));
        product.setName(req.getParameter("name"));
        product.setDescription(req.getParameter("description"));
        product.setValue(new BigDecimal(req.getParameter("value")));
        productDao.save(product);
        resp.sendRedirect(req.getContextPath() + "/products");
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        Product product = productDao.getById(id);
        product.setCode(req.getParameter("code"));
        product.setName(req.getParameter("name"));
        product.setDescription(req.getParameter("description"));
        product.setValue(new BigDecimal(req.getParameter("value")));
        productDao.update(product);
        resp.sendRedirect(req.getContextPath() + "/products");
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        UUID id = UUID.fromString(req.getParameter("id"));
        Product product = productDao.getById(id);
        productDao.delete(product);
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
