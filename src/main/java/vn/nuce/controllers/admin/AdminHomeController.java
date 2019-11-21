package vn.nuce.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.nuce.dto.UserDto;
import vn.nuce.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;

@RequestMapping("/admin")
@Controller
public class AdminHomeController {
    @Autowired
    UserServiceImpl service;

    @GetMapping("/home")
    public String showPageDefault(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("user") != null) {
            UserDto dto = (UserDto) session.getAttribute("user");
            modelMap.addAttribute("dto", dto);
        }
        modelMap.addAttribute("users",service.findAllUsers());
        return "/admin/home";
    }
}
