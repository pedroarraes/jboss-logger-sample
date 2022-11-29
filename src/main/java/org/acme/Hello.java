package org.acme;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

@WebServlet(name = "hello", urlPatterns = { "/hello" })
public class Hello extends HttpServlet {

	private static final long serialVersionUID = -5032053177353366934L;
	
	private static final Logger LOGGER = Logger.getLogger(Hello.class);

	public Hello() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LOGGER.debug("Este é um exemplo de DEBUG");
		LOGGER.error("Este é um exemplo de ERROR");
		LOGGER.info("Este é um exemplo de INFO");
		LOGGER.warn("Este é um exemplo de WARN");
		LOGGER.trace("Este é um exemplo de TRACE");
				
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<h1>Hello World from Acme Organizations!</h1>");
		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}