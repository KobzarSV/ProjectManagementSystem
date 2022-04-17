package ua.goit.project.controller.customersController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.PostgresProvider;
import ua.goit.project.config.PropertiesUtil;
import ua.goit.project.dataLayer.CustomerRepository;
import ua.goit.project.model.converter.CustomersConverter;
import ua.goit.project.model.dto.CustomersDto;
import ua.goit.project.service.CustomersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/viewAllCustomers")
public class ViewAllCustomersServlet extends HttpServlet {

    private CustomersService service;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        service = new CustomersService(new CustomersConverter(), new CustomerRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CustomersDto> customersList = service.find();
        req.setAttribute("customers", customersList);
        req.getRequestDispatcher("/WEB-INF/html/customers/viewAllCustomers.jsp").forward(req, resp);
    }
}
