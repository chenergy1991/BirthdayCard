package org.sang.component;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    /**
     *功能描述：这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了。
     * @author Chenergy
     * @date 2019/12/29
     * @param
     * @return
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每一个项目的登录实现逻辑都有所区别，这里使用最简单的Session提取loginUser来验证登陆。
        HttpSession session = request.getSession();
        //这里的loginUser是登录时放入Session的
        Object admin = (Object)session.getAttribute("loginUser");
        //如果session中没有admin，表示没登陆
        if (admin == null){
            //这个方法返回false表示忽略当前请求（如果一个用户调用了“需要登录才能使用的接口”，若该用户尚未登录，则该请求会被忽略掉）
            //也可以利用response给用户返回一些提示信息，告诉他尚未登录
            return false;
        }else {
            //如果session里有admin，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}