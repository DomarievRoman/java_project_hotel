package com.domariev.controller.command;

import com.domariev.controller.command.service.Command;
import com.domariev.controller.command.service.Url;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorCommand implements Command {
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return Url.ERROR_PAGE;
    }
}
