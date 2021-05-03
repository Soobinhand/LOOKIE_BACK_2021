package ac.kr.smu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class LoginController {
    @GetMapping
    public ModelAndView getLogin(){
        return new ModelAndView("login");
    }
}
