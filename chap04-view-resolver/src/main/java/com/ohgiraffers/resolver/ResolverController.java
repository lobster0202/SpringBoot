package com.ohgiraffers.resolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResolverController {
    @GetMapping("/string")
    /* Model : View에서 표현 돼야 하는 동적인 데이터를 담는 용도로 사용하는 객체 */
    public String stringReturn(Model model) {

        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환함");

        /* (석현) resources/templates/result.html 물리적인 뷰
        prefix(접두사)가 resources/templates/이고 suffix(접미사)가 .html이다
        * 아래는 논리적인 뷰 */
        /* 문자열로 뷰 이름을 반환한다는 것은 반환 후
         * TymeleafViewResolver에게 prefix/suffix를 붙여 응답 뷰로 설정하라는 의미  */
        return "result";
    }

    @GetMapping("/string-redirect")
    public String stringRedirect() {

        /* (석현) 리다이렉트는 잠깐 있다가 없어지는 역할 이라고 생각하면 좋음*/
        /* (석현) 접미사를 활용하여 어디 페이지에 연결할건지 활용 */
        /* 접두사로 redirect: 를 하면 forward가 아닌 redirect 시킨다. */
        return "redirect:/";
    }

    @GetMapping("/string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr) {

        rttr.addFlashAttribute("flashMessage", "리다이렉트 arrt 사용하여 redirect");


        /* 리다이렉트 처리 시 request scope의 데이터는 공유 되지 않는다.
        *  session scope에 너무 많은 데이터를 저장하는 것은 서버 성능에 영향을 준다.
        *  redirect 처리 시 전달 하여 잠깐 사용하고 싶은 데이터가 있다면 (ex. 저장 완료 alert 메세지 등)
        *  RedirectAttributes 객체를 통해 잠시 세션에 저장 했다가 redirect 후 세션에서 제거되게 할 수 있다. */
        return "redirect:/";
    }

    @GetMapping("/modelandview")
    public ModelAndView modelAndViewReturn(ModelAndView mv) {

        /* 모델 객체에 attribute 저장 */
        mv.addObject("forwardMessage", "ModelAndView를 이용한 모델과 뷰 반환");

        /* View 객체에 논리적 뷰 이름 설정 */
        mv.setViewName("result");

        return mv;
    }


}
