package br.edu.ifsp.spo.lp2a4.jdbc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.edu.ifsp.spo.lp2a4.jdbc.dao.ContatoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/atualizaContato")
public class AtualizaContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String dataEmTexto = request
				.getParameter("dataNascimento");
		Calendar dataNascimento = null;

		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy")
					.parse(dataEmTexto);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);
		} catch (ParseException e) {
			out.println("Erro de conversao da data");
			return;
		}

		try {
			ContatoDao dao = new ContatoDao();
			
			dao.altera(nome, email, endereco, dataNascimento, id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		out.println("<html>");
		out.println("<body>");
		out.println("<body>");
		out.println("Contato Atualizado com Sucesso! " + "<br/>");
		out.println("<br/>");
		out.println("<a href=\"http://localhost:8080/LP2-Maven-JDBC/Atualiza-contato.html\"><button>Voltar</button></a>");
		out.println("</body>");
		out.println("</html>");

	}
}
