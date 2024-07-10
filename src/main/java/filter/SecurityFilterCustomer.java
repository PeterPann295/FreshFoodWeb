package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;

/**
 * Servlet Filter implementation class SecurityFilterCustomer
 */
public class SecurityFilterCustomer extends HttpFilter implements Filter {

    /**
     * @see HttpFilter#HttpFilter()
     */
    public SecurityFilterCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        Customer customer = (session != null) ? (Customer) session.getAttribute("customer_login") : null;
        boolean isLoggedIn = (session != null && customer != null);

        String loginURI = httpRequest.getContextPath() + "/dangNhap.jsp";

        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("dangNhap.jsp");

        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            // Nếu đã đăng nhập và đang truy cập trang đăng nhập, chuyển hướng đến trang chính.
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/trangChu.jsp");
        } else if (!isLoggedIn && !isLoginPage) {
            // Nếu chưa đăng nhập và không phải trang đăng nhập, chuyển hướng đến trang đăng nhập.
            httpResponse.sendRedirect(loginURI);
        } else {
            // Cho phép tiếp tục chuyển đến servlet hoặc JSP.
            chain.doFilter(request, response);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}