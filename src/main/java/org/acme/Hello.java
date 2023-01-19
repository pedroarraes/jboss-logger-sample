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
		
		LOGGER.debug("This is an example of a DEBUG log!");
		LOGGER.error("This is an example of an ERROR log!");
		LOGGER.info("This is an example of an INFO log!");
		LOGGER.warn("This is an example of a WARN log!");
		LOGGER.trace("This is an example of a TRACE log!");
				
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