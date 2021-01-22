import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Servlet1
 * This Page loads once the user is authenticated using loginServlet
 * It contains the links to the secured website
 */
@WebServlet("/MainPage")
public class Servlet1 extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            // TODO Auto-generated method stub
            HttpSession session = request.getSession(); //returns the session associated with the request

            //starts building a html document
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (session != null) { //starts the code below if a session exists

                    out.println("<?xml version = \"1.0\"?>");
                    out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD " +
                            "XHTML 1.0 Strict//EN\" \"http:www.w3.org" +
                            "/TR/xhtml11/DTD/xhtm11-strict.dtd\">"
                    );
                    out.println(
                            "<html xmlns = \"http://www.w3.org/1999/xhtml\">"
                    );

                    //head section of document
                    out.println("<head>");
                    out.println("<title>Welcome to the Website Page</title>");
                    out.println("</head>");
                    //body section of document
                    out.println("<body>");
                    out.println("<h1>Welcome to the admin </h1>");
                    out.println("<p>Click on any link to view content pages! </p>");
                    //these links create links to the xml page
                    out.print("<p><a href='ContentSheet1.xml'>Coronavirus</a></p>");
                    out.print("<p><a href='ContentSheet2.xml'>Joe Biden</a></p>");
                    out.print("<p><a href='ContentSheet3.xml'>2020 Formula One Championship</a></p>");
                    out.println("<form action=\"LogOutPage\" method=\"post\">"); //creates a post request
                    out.println("<input type=\"submit\" value=\"Log Out\"/>");
                    out.println("</form>");
                    out.println("</body>");



                    //end XHTML document
                    out.println("</html>");
                } else {
                    out.print("Please log in again!");
                    out.print("<br></br>");
                    out.print("<a href='loginPage.xml'>Click here to login again</a>");
                }

            //close stream to complete the page
            out.close();



        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // TODO Auto-generated method stub
        doGet(request, response);


    }
}
