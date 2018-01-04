package servlet;

import dao.MessageDao;
import dao.MessageDaoJdbcImpl;
import domain.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/12/30.
 */
@WebServlet(name = "MessageServlet")
public class MessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        List<Message> messages = null;
        MessageDao messageDao = new MessageDaoJdbcImpl();
        if (action == null || action == "") {
            action = "readed";
        }
        if (action.equals("readed")) {
            messages = messageDao.readed(id);
        } else if (action.equals("sented")) {

            messages = messageDao.sented(id);
        } else if (action.equals("unsent")) {

            messages = messageDao.unsent(id);
        }
        request.setAttribute("id",id);
        request.setAttribute("action",action);
        request.setAttribute("messages",messages);
        request.getRequestDispatcher("message.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
