package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Client;

/**
 * Servlet implementation class MyFirstServlet
 */
@WebServlet(urlPatterns = { "/login" }, initParams = {
		@WebInitParam(name = "nom", value = "nico", description = "my first servlet") })

public class Form extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Form() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * methode pour repondre aux demandes de ressources html (afficher la page web
	 * demandée)
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			out.print("			<title>Formulaire</title>");
			out.print("		</head>");

			out.print("		<body>");
			
			out.print("			<h1>Veuillez vous identifier</h1><br>");

			// method 'post' = methode utilisée pour recupérer les données saisies
			out.print("			<form method='post' action='login'>");

			out.print("				<label for='nom'>Nom : </label>");
			if (client != null) {
				out.print("				<input name='nom' type='text' id='nom' value='" + client.getNom()
						+ "'/><br><br>");
			} else {
				out.print("			<input name='nom' type='text' id='nom'/><br><br>");
			}

			out.print("				<label for='prenom'>Prénom : </label>");
			if (client != null) {
				out.print("				<input name='prenom' type='text' id='prenom' value='" + client.getPrenom()
						+ "'/><br><br>");
			} else {
				out.print("				<input name='prenom' type='text' id='prenom'/><br><br>");

			}

			out.print("				<label for='age'>Age : </label>");
			if (client != null) {
				out.print("				<input name='age' type='number' id='age' value='" + client.getAge()
						+ "'/><br><br>");
			} else {
				out.print("				<input name='age' type='number' id='age'/><br><br>");

			}

			out.print("				<input name='boutonValider' type='submit'/>");

			out.print("			</form>");
			out.print("		</body>");

			out.print("</html>");

		}
	}

	/**
	 * C'est ici qu'on récupère les données saisie depuis le navigateur vers le
	 * serveur: méthode 'post"
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nom = request.getParameter("nom"); // on met le meme nom précisé dans name de l'input
		String prenom = request.getParameter("prenom");
		String age = request.getParameter("age");

		/**
		 * Vérification de la saisie + génération de la réponse html
		 */
		try (PrintWriter out = response.getWriter()) {

			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("		<head>");
			out.print("			<meta charset='utf-8'>");
			out.print("			<title>Bienvenue</title>");
			out.print("		</head>");

			out.print("		<body>");

			/*si les champs ne sont pas vides => on convertit le champs age (String) en int
			 + on affecte les saisies dans le bean client*/
			if (!nom.isEmpty() && !prenom.isEmpty() && !age.isEmpty()) {

				int intAge = 0;

				try {

					intAge = Integer.parseInt(age);

				} catch (Exception e) {
					// TODO: handle exception
				}
				Client client = new Client(nom, prenom, intAge);

				// on stocke le bean client dans la session
				request.getSession().setAttribute("client", client);
				// on affiche les infos clients en réponse
				out.print("			<h1>Bonjour " + nom + " " + prenom + " ." + "Votre age est " + age + "</h1><br>");

			} else {
				out.print("<p>Veuillez renseigner tous les champs</p>");

			}
			/*
			 * out.println("<b>Query String: </b>"+request.getQueryString()+"<br />");
			 * out.println("<table>"); Enumeration<?> parameterNames =
			 * request.getParameterNames(); while (parameterNames.hasMoreElements()) {
			 * String parameterName = (String) parameterNames.nextElement();
			 * out.println("<tr><td>" + parameterName + "</td>"); String paramValue =
			 * request.getParameter(parameterName); out.println("<td>" + paramValue +
			 * "</td>");
			 * 
			 * } out.println("</table>");
			 */

			out.print("		</body>");

			out.print("</html>");

			doGet(request, response);
		}
	}

}
