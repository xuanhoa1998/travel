package vn.nuce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.nuce.dto.UserDto;

import javax.servlet.http.HttpSession;

@RequestMapping("/home")
@Controller
public class HomeController {

    @GetMapping
    public String showPage(HttpSession session, ModelMap modelMap) {
        UserDto dto = null;
        if (session.getAttribute("user") != null) {
            dto = (UserDto) session.getAttribute("user");
        }
        if (dto != null) {
            modelMap.addAttribute("dto", dto);
        }
        return "home";
    }
}
