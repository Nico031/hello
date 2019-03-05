package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Client;

/**
 * Servlet implementation class AffichageClient
 */
@WebServlet({ "/AffichageClient", "/affichage" })
public class AffichageClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffichageClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// on précise au navigateur qu'on lui renvoie un contenu de type html
		response.setContentType("text/html");

		/**
		 * Recuperation dans la session du bean client
		 */
		Client client = (Client) request.getSession().getAttribute("client");

		// Notre reponse: une page html (un formulaire qui est généré par le serveur
		// vers le navigateur)

		try (PrintWriter out = response.getWriter()) {

			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("		<head>");
			out.print("			<meta charset='utf-8'>");
			out.print("			<title>Bienvenue</title>");
			out.print("		</head>");

			out.print("		<body>");
			if (client!=null) {
				out.print("			<h1>Bonjour " + client.getNom() + " " + client.getPrenom() + " " + client.getAge() + "</h1><br>");
			} else {
				
				out.print("<p>Session vide</p>");
			}
			
			out.print("</body>");
			out.print("</html>");

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
