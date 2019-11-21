package vn.nuce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import vn.nuce.dto.UserDto;
import vn.nuce.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserServiceImpl service;

    @GetMapping("/login")
    public String showPageDefault(ModelMap modelMap) {
        return "login";
    }

    @PostMapping("/login")
    public String checkLoginAccount(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password
            , HttpSession session, ModelMap modelMap) {
        Object[] objects = service.checkLogin(username, password);
        boolean isSuccess = (boolean) objects[0];
        if (isSuccess) {
            UserDto dto = (UserDto) objects[1];
            session.setAttribute("user", dto);
            return checkRoleAccount(dto.getRole());
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
        return "redirect:/login";
    }

    private String checkRoleAccount(String role) {
        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin/home";
        } else if (role.equals("ROLE_USER")) {
            return "redirect:/home";
        }
        return "redirect:/home";
    }
}
