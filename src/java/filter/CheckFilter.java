/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getServletPath();
        HttpSession session = httpRequest.getSession();
        boolean isAuth = true;
        if (session.getAttribute("role") == null) {
            if (!url.contains("error.jsp") && !url.contains("login")) {
                isAuth = false;
            }
        } else if (session.getAttribute ("role").equals("student")) {
            if (url.contains("Add") || url.contains("ViewMarkForTeacher") || url.contains("ViewMarkForAdmin") || url.contains("Update") || url.contains("Delete")) {
                isAuth = false;
            }
        } else if (session.getAttribute("role").equals("teacher") ) {
            if (url.contains("Add") || url.contains("ViewMarkForAdmin") || url.contains("Update") || url.contains("Delete")) {
                isAuth = false;
            }
        }
        if (!isAuth) {
            httpResponse.sendRedirect("jsp/error.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
