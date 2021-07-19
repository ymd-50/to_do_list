package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");

        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            em.getTransaction().begin();

            Task t = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));


            String toDo = request.getParameter("toDo");
            t.setToDo(toDo);

            String detail = request.getParameter("detail");
            t.setDetail(detail);

            Timestamp update_at = new Timestamp(System.currentTimeMillis());
            t.setUpdate_at(update_at);

            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("deadline"));
                Timestamp deadline = new Timestamp(date.getTime());
                t.setDeadline(deadline);
            } catch (ParseException e) {
                e.printStackTrace();
                em.close();
            }


            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("task_id");

            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}
