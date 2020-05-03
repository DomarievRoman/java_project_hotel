package com.domariev.controller.command.service;

import com.domariev.controller.command.ErrorCommand;
import com.domariev.controller.servlet.MainServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private static final Logger log = LogManager.getLogger(CommandFactory.class);

    public Command defineCommand(HttpServletRequest request) {
        Command current = new ErrorCommand();
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCommand();
            log.debug("Command defined");
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action);
            log.error("Wrong action", e);
        }
        return current;
    }
}
