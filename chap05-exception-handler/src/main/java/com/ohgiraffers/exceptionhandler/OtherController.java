package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    @GetMapping("other-controller-null")
    public String otherNullPointerExceptionTest() {

        String str = null;
        System.out.println(str.charAt(0));

        return "/";
    }

    @GetMapping("other-controller-user")
    public String otherUserExceptiontest() throws MemberRegistException {
        boolean check = true;
        if (check) {
            throw new MemberRegistException("당신은 회원가입을 할 수 없어요!");
        }
        return "/";
    }

    /* ArrayIndexOutOfBoundsException 발생 */
    @GetMapping("other-controller-array")
    public String otherArrayExceptionTest() {

        double[] array = new double[0];
        System.out.println(array[0]);

        return "/";
    }

}
