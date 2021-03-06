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
@WebFilter(filterName = "DeleteFilter")
public class DeleteFilter implements Filter, ControlWord {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //转换输入输出类型
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        httpReq.setCharacterEncoding("utf-8");
        httpResp.setContentType("text/html;charset=utf-8");

        //获取输入
        String id = httpReq.getParameter("stdNumber").trim();

        RequestDispatcher dispatcher = httpReq.getRequestDispatcher("/index.jsp");
        String msg = null;

        //判断输入
        if (!Pattern.matches("[1-9][0-9]{9}",id)) {
            msg = "学号格式错误";
            httpReq.setAttribute("status",CONTROL_DELETE);
            httpReq.setAttribute("msg",msg);
            dispatcher.forward(httpReq,httpResp);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
