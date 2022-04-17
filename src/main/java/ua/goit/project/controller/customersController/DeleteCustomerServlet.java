package ua.goit.project.controller.customersController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.PostgresProvider;
import ua.goit.project.config.PropertiesUtil;
import ua.goit.project.dataLayer.CustomerRepository;
import ua.goit.project.model.converter.CustomersConverter;
import ua.goit.project.service.CustomersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = ("/deleteCustomer"))
public class DeleteCustomerServlet extends HttpServlet {
    private CustomerRepository customerRepository;
    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        customerRepository = new CustomerRepository(dbConnector);
        customersService = new CustomersService(new CustomersConverter(), new CustomerRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        try {
            customersService.find(customerId);
        } catch (Exception ex) {
            req.setAttribute("exception", "Customer with id " + customerId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        customerRepository.delete(customerId);
        req.getRequestDispatcher("/WEB-INF/html/customers/deleteCustomer.jsp").forward(req, resp);
    }
}
