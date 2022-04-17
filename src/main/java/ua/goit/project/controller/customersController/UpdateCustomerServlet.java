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

@WebServlet(urlPatterns = "/updateCustomer")
public class UpdateCustomerServlet extends HttpServlet {

    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        customersService = new CustomersService(new CustomersConverter(), new CustomerRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        String customerBusiness = req.getParameter("customerBusiness");
        String customerId = req.getParameter("customerId");
        CustomersDto customersDto = new CustomersDto();
        customersDto.setName(customerName);
        customersDto.setBusiness(customerBusiness);
        try {
            customersService.find(Integer.parseInt(customerId));
        } catch (Exception ex) {
            req.setAttribute("exception", "Customer with id " + customerId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        customersDto.setId(Integer.parseInt(customerId));
        customersService.update(customersDto);
        req.getRequestDispatcher("/WEB-INF/html/customers/updatedCustomer.jsp").forward(req, resp);
    }
}
