package servlet;

import dao.MessageDao;
import dao.MessageDaoJdbcImpl;
import dao.UserDao;
import dao.UserDaoJdbcImpl;
import domain.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**用户的编辑信息和信息的删除功能
 * Created by Administrator on 2017/12/31.
 */
@WebServlet(name = "EditorServlet")
public class EditorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int selfId = Integer.parseInt(request.getParameter("id"));
//        int acceptID = Integer.parseInt(request.getParameter("acceptID"));
        String acceptID=request.getParameter("acceptID");
        String messid=request.getParameter("messid");

        MessageDao messageDao = new MessageDaoJdbcImpl();
        if (messid==null||messid=="") {
            UserDao userDao = new UserDaoJdbcImpl();
            int acceptid=Integer.parseInt(acceptID);
            if (userDao.getUser(acceptid) != null) {
                String sub = request.getParameter("sub");
                String datetime = request.getParameter("datetime");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date dt = new Date();
                try {
                    System.out.print(datetime);
                    dt = sdf.parse(datetime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.setSentID(selfId);
                message.setAcceptID(acceptid);
                message.setMess(sub);
                message.setRecordtime(dt);

                messageDao.addMess(message);
                request.setAttribute("id", selfId);
                request.getRequestDispatcher("MessageServlet").forward(request, response);
            } else {
                request.setAttribute("unexitMess", "ID为" + acceptID + "的收信人不存在");
                request.getRequestDispatcher("editor.jsp").forward(request, response);
            }
        }else{
            int id=Integer.parseInt(messid);
            messageDao.delete(id);
            request.setAttribute("id",selfId);
            request.getRequestDispatcher("MessageServlet").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}
