package com.domariev.controller.servlet;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.CommandFactory;
import com.domariev.controller.command.service.Url;
import com.domariev.controller.dao.DBConnector;
import com.domariev.controller.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DBConnector init;

    private static final Logger log = LogManager.getLogger(MainServlet.class);


    public MainServlet() throws IOException, DAOException {
        super();
        init = new DBConnector();
        init.createTables();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DAOException e) {
            log.error("Cannot execute doPost method",e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DAOException e) {
            log.error("Cannot execute doGet method",e);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        CommandFactory client = new CommandFactory();
        Command command = client.defineCommand(request);
        String page = command.execute(request, response);
        log.debug("Command executed");
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
            log.debug("Request forward");
        } else {
            response.sendRedirect(Url.ERROR_PAGE);
            log.warn("Some error happening");
        }
    }
}
