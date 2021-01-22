import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Servlet2
 *  The log out page. this page terminates the current session
 */
@WebServlet("/LogOutPage")
public class Servlet2 extends HttpServlet {
    //private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session=request.getSession(false);


        PrintWriter out = response.getWriter();

        if (session != null) {

            session.invalidate(); //terminates the current session

        }
        //begins creating a html page
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
            out.println("<title>Logged Out!</title>");
            out.println("</head>");
            //body section of document
            out.println("<body>");
            out.println("<h1>You are successfully logged out.</h1>");
            out.print("<a href='loginPage.xml'>Click here to login again</a>"); //Links to the login page
            out.println("</body>");

            //end XHTML document
            out.println("</html>");

        //close stream to complete the page
        out.close();


    }


}