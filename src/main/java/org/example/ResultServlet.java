package org.example;

import waffle.windows.auth.IWindowsAccount;
import waffle.windows.auth.impl.WindowsAuthProviderImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( urlPatterns = {"/result"} )
public class ResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        String usernameForSearch = request.getParameter("usernameForSearch");

        WindowsAuthProviderImpl waffle = new WindowsAuthProviderImpl();
        IWindowsAccount iWindowsAccount;

        try {
            String currentUserName = System.getProperty("user.name");

            iWindowsAccount = waffle.lookupAccount(usernameForSearch);
            String login = iWindowsAccount.getName();

            if (currentUserName.equals(login)) throw new OnThisUserStartThisProgramException();

            if (login.trim().isEmpty()) {
                doHTMLRespone(response, usernameForSearch, false);
            }
            doHTMLRespone(response, usernameForSearch, true);
        } catch (Exception e) {
            doHTMLRespone(response, usernameForSearch, false);
        }
    }

    private void doHTMLRespone(HttpServletResponse response, String userName, boolean userNameExist) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
        out.println("<title>Результат поиска пользователя</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Результат проверки наличия пользователя Windows</h3>");
        if (userNameExist) {
            out.println("Пользователь "+ userName +" есть");
        } else {
            out.println("Пользователя "+ userName +" нет");
        }
        out.println("<form name=\"searchForm\" method=\"post\" action=\"/\">");
        out.println("<div><input type=\"submit\" value=\"Назад\"/></div>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    public static class OnThisUserStartThisProgramException extends Exception{}
}
