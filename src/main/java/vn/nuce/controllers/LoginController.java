package vn.nuce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.nuce.dto.UserDto;
import vn.nuce.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserServiceImpl service;

    @GetMapping("/login")
    public String showPageDefault(ModelMap modelMap) {
        modelMap.addAttribute("userDto", new UserDto());
        return "login";
    }

    @PostMapping("/login")
    public String checkLoginAccount(@ModelAttribute(name = "userDto") UserDto dto, HttpSession session, ModelMap modelMap) {
        Object[] objects = service.checkLogin(dto.getUsername(), dto.getPassword());
        boolean isSuccess = (boolean) objects[0];
        if (isSuccess) {
            session.setAttribute("user", objects[1]);
            return "redirect:/home";
        } else {
            modelMap.addAttribute("error", "Tên tài khoản hoặc mật khẩu không chính xác!");
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logoutAccount(HttpSession session) {
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return "redirect:/home";
    }
}
