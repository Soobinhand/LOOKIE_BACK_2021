package ac.kr.smu.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session  = request.getSession();
        log.info(request.getRequestURI());
        log.info("userSession is null? : "+(session.getAttribute("userSession")==null));
        boolean chk=session.getAttribute("userSession") != null;

        if(!chk)
            response.sendRedirect("/");

        return chk;
    }
}