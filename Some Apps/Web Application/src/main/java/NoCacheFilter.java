import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*

This page is meant to prevent to stop the collection of cache
 */

public class NoCacheFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); //prevents the collection of cache

        filterChain.doFilter(request, response);
    }
    @Override
    public void destroy() {
        //close any resources here


    }

}