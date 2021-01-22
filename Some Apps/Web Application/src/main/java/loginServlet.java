import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String loginName = "admin"; //username used to access this website
    private final String pass = "password"; //password used to access this website

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // get request parameters for userID and password
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");



        if(loginName.equals(userid) && pass.equals(password)){ //checks to see if the username and password entered are correct
            HttpSession session = request.getSession(); //this creates a new session as no previous session exists
            session.setAttribute("userid", userid); // sets the session attribute
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30*60);
            response.setContentType("text/html");


            response.sendRedirect("MainPage"); //Redirects to Servlet1.java


        }else{

            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            out.println("<a href='loginPage.xml'>Click here to login again</a>");
        }



    }


}