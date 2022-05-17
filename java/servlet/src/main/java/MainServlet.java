import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Meus livros");
		
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.print("Meus livros");
		
		writer.print("<ul>");

		writer.print("<li>"); writer.print("Livro de Java Web"); writer.print("</li>");

		writer.print("<li>"); writer.print("Livro de Android"); writer.print("</li>");

		writer.print("</ul>");
	}

}
