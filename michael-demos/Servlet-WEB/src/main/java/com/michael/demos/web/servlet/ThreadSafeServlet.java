package com.michael.demos.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类功能描述:
 * <pre>
 *   线程安全的 Servlet
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/06 13:47
 */
public class ThreadSafeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init() -> " + this.getClass().getName());
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy()");
    }

    // @Override
    // protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //     // super.service(req, resp);
    //     System.out.println("service() -> " + this.getClass().getName());
    //     resp.getWriter().write(this.getClass().getName() + ":hello servlet");
    // }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
        System.out.println("doGet() -> " + this.getClass().getName());
        int i=1;
        i++;
        resp.getWriter().write(this.getClass().getName() + ":hello servlet -> " + i);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("doPost()");
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
        System.out.println("doHead()");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
        System.out.println("doPut()");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
        System.out.println("doDelete()");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        System.out.println("doOptions()");
    }
}
