package ro.sd.a2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.sd.a2.entity.User;


@Controller
public class FirstController {

    private static final Logger log = LoggerFactory.getLogger(FirstController.class);

    @GetMapping("/profile")
    public ModelAndView showProfile() {
        ModelAndView mav = new ModelAndView();
        User user = new User("Bubu");
        mav.addObject("userObj", user);
        mav.addObject("numeStudent", user.getName());
        mav.setViewName("profile");
        log.info("SUCCESS");
        return mav;
    }

}
