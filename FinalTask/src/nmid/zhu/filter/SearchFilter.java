package nmid.zhu.filter;

import nmid.zhu.service.ControlWord;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by Lawrence on 2017/9/2.
 */
@WebFilter(filterName = "SearchFilter")
public class SearchFilter implements Filter, ControlWord {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //转换输入输出类型
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        httpReq.setCharacterEncoding("utf-8");
        httpResp.setContentType("text/html;charset=utf-8");

        //获取输入输出
        String name = httpReq.getParameter("name").trim();
        String id = httpReq.getParameter("stdNumber").trim();
        RequestDispatcher dispatcher = httpReq.getRequestDispatcher("/index.jsp");
        httpReq.setAttribute("status",CONTROL_SEARCH);
        //对输入进行校验
        if (id.equals("") && name.equals("")) {
            String msg = "请至少输入一项";
            httpReq.setAttribute("msg",msg);
            dispatcher.forward(httpReq,httpResp);
            return;
        } else if (!Pattern.matches("[1-9][0-9]{9}",id) && !Pattern.matches("[\\u4e00-\\u9fa5]{2,4}",name)) {
            String msg = "请正确输入";
            httpReq.setAttribute("msg",msg);
            dispatcher.forward(httpReq,httpResp);
            return;
        }

        chain.doFilter(req, resp);

        /*if (httpReq.getAttribute("students") == null) {
            String msg = "找不到该学生";
            httpReq.setAttribute("msg",msg);
            dispatcher.forward(httpReq,httpResp);
        }*/

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
