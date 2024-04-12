package com.ohgiraffers.handlermethod;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
@RequestMapping("/first/*")
/* @SessionAttribute 를 하면 */
@SessionAttributes("id")
public class FirstController {

    /* 반환 값을 void로 설정하면 요청 주소가 view의 이름이 된다. */
    @GetMapping("regist")
    public void regist() {
    }

    /* (석현) WebRequest가 getParameter랑 같은 걸 가지고 있음 */
    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request) {

        /* (석현) 굳이 이렇게 적지 않아도 되는 어노테이션이 있음
        * 그게 바로 RequestParam임 */
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        String message = name + "을(를) 신규 메뉴 목록의" + categoryCode + "번 카테고리에"
                + price + "원을 등록 하셨습니다.";

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    @GetMapping("modify")
    public void modify() {

    }

    /* @RequestParam으로 요청 파라미터 전달 받기
    *  요청 파라미터를 매핑하여 호출 시 값을 넣어주는 어노테이션으로 매개 변수 앞에 작성한다.
    *  form의 name속성 값과 매개변수의 이름이 다른 경우 @RequestParam("name")을 설정하면 된다.
    *
    *  값이 넘어오지 않는 경우 defaultValue를 이용해 기본값을 설정해 줄 수 있다.                 */
    @PostMapping("modify")
    /*  (석현) @RequestParam을 지워도 애가 알잘딱하게 알아먹고 해결해줌
     *         그래도 명시적으로 작성해주는게 좋긴 함                     */
    /* defaultValue를 써주지 않은 상태에서 아무것도 입력하지 않고 버튼을 누르면 오류가 뜨는데
     * defaultValue를 설정해주면 아무것도 입력하지 않아도 0으로 입력됨                     */
    public String modifyMenuPrice(Model model, @RequestParam String modifyName,
                                  @RequestParam(defaultValue = "0") int modifyPrice) {
        String message = modifyName + "메뉴의 가격을 " + modifyPrice + "원으로 변경했습니다.";

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    /* 파라미터가 여러개 인 경우 맵으로 한번에 처리할 수 있다.
    * 이 때 맵의 키는 form의 name 속성 값이 된다.            */
    @PostMapping("modifyAll")
    public String modifyMenu(Model model, @RequestParam Map<String ,String > parameters) {
        String modifyMenu = parameters.get("modifyName2");
        int modifyPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = "메뉴의 이름을 " + modifyMenu + "으로, 가격을 " + modifyPrice + "원 으로 변경함";

        model.addAttribute("message", message);

        return "first/messagePrinter";
    }

    @GetMapping("search")
    public void search() {

    }

    /* @ModelAttribute 이용하는 방법
    *  DTO 같은 모델을 커맨트 객체를 생성항 매개변수로
    *  전달해 준 뒤 해당 인스턴스를 model에 담는다.
    *
    *  경우에 따라 폼에서 입력한 값을 다음 화면으로
    * 바로 전달해야 하는 경우 유용하게 사용할 수있다.
    *
    * @ModelAttribute("모델에 담을 key값")을 지정할 수 있으며,
    * 지정하지 않으면 타입의 앞 글자를 소문자로 한 네이밍 규칙을 따른다. menuDTO로 담긴다.  */
    @PostMapping("search")
    public String searchMenu(@ModelAttribute("menu") MenuDTO menu) {

        System.out.println(menu);

        return "first/searchResult";
    }

    @GetMapping("login")
    public void login() {}

    /* HttpSession을 매개변수로 선언하면 핸들러 메소드호출 시 세션 객체르 넣어서 호출한다.*/
    @PostMapping("login")
    public String sessionTest1(HttpSession session, @RequestParam String id) {
        session.setAttribute("id", id);

        return "first/loginResult";

    }

    @GetMapping("logout1")
    public String logoutTest1(HttpSession session) {

        session.invalidate();

        return "first/loginResult";
    }


    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id) {

        model.addAttribute("id", id);

        return "first/loginResult";

    }

    /* SessionAttributes로 등록된 값은 session의 상태를 관리하는
    *  SessionStatus의 setComplete() 메소드를 호출해야 사용이 만료된다.*/
    @GetMapping("logout2")
    public String logoutTest2(SessionStatus sessionStatus) {

        sessionStatus.setComplete();

        return "first/loginResult";
    }


}
