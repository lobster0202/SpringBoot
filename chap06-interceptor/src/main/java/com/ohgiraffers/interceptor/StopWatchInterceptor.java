package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class StopWatchInterceptor implements HandlerInterceptor {

    /* 생성자 주입이 하나만 있을 때는 @Autowired가 생략됨*/

    private final MenuService menuService;

    //@Autowired 생략되고 있음
    public StopWatchInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }


    /* 전처리 메소드 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandler 호출함");

        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime",startTime);

        /* true를 반환하면 컨트롤러 메소드의 호출로 이어지고, false를 반환하면 호출하지 않음 */
        return true;
    }

    /* 후처리 메소드 */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("postHandler 호출함");

        long startTime = (Long) request.getAttribute("startTime");

        long endTime = System.currentTimeMillis();

        modelAndView.addObject("interval", endTime - startTime);
    }

    /* 뷰가 렌더링 된 이후 동작하는 메소드 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion 호출함");
        menuService.method();
    }
}
