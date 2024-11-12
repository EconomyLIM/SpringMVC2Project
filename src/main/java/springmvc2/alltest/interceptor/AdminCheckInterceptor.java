package springmvc2.alltest.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import springmvc2.alltest.member.domain.Member;
import springmvc2.alltest.session.SessionConst;

/**
 * date           : 2024-11-07
 * created by     : 임경재
 * description    :
 */
@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Member member = (Member)request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);
        if (member == null) {
            return false;
        }

        if (!member.getAccess().equals("admin")){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            response.sendRedirect("");
            return false;
        }

        return true;
    }
}
