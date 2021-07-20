package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;


@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public IndexServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        List<Task> tasksList = em.createNamedQuery("getAllTasks", Task.class).getResultList();

        Task[] tasksArray = tasksList.toArray(new Task[tasksList.size()]);

        String[] toDos = new String[tasksList.size()];
        Timestamp[] deadlines = new Timestamp[tasksList.size()];
        String[] timeLags = new String[tasksList.size()];
        int[] ids = new int[tasksList.size()];



        for(int i = 0; i < tasksList.size(); i++) {
            toDos[i] = tasksArray[i].getToDo();
            deadlines[i] = tasksArray[i].getDeadline();
            ids[i] = tasksArray[i].getId();
            timeLags[i] = timeLagCalculate(deadlines[i]);

        }




        em.close();


        request.setAttribute("ArraySize", tasksList.size());
        request.setAttribute("toDos", toDos);
        request.setAttribute("deadlines", deadlines);
        request.setAttribute("ids", ids);
        request.setAttribute("timeLags", timeLags);

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/tasks/index.jsp");
        rd.forward(request, response);


    }

    public static String timeLagCalculate (Timestamp time) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        long timeLag = time.getTime() - currentTime.getTime();

        if(timeLag < 0) {
            String response = "期限切れです";
            return response;
        }else if(timeLag < 60000) {
            //-1min
            int s = (int)timeLag / 1000;
            String response = "あと" + s + "秒";
            return response;
        }else if(timeLag < 3600000) {
            //-60min
            int min = (int)timeLag / 60000;
            String response ="あと" + min + "分";
            return response;
        }else if(timeLag < 86400000) {
            //-24h
            int h = (int)timeLag / 3600000;
            String response = "あと" + h + "時間";
            return response;
        }else {
            //1d-
            int d = (int)timeLag / 86400000;
            int h = (int)timeLag % 86400000 / 3600000;
            String response = "あと" + d + "日" + h + "時間";
            return response;
        }

    }

}
