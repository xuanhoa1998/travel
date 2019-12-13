package vn.nuce.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import vn.nuce.Utils;
import vn.nuce.dto.UserDto;
import vn.nuce.entity.UserEntity;
import vn.nuce.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List<UserDto> users = service.findAllUsers();
        modelMap.addAttribute("users", users);
        if (session.getAttribute("status") != null) {
            modelMap.addAttribute("status",session.getAttribute("status"));
            session.removeAttribute("status");
        }
        return "/admin/home";
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserDto getUserDto(@PathVariable Long id) {
        return service.findOneUser(id);
    }

    @GetMapping("/user")
    public String updateUser(@RequestParam(name = "action") String action,
                             @RequestParam(name = "id") Long userId,
                             HttpSession session) {
        if (action.equals("delete")) {
            List<Long> ids = new ArrayList<>();
            ids.add(userId);
            service.deleteUser(ids);
            session.setAttribute("status","success");
        }
        return "redirect:/admin/home";
    }

    @PostMapping("/user/{action}")
    public String saveUser(@PathVariable String action,
                           @RequestParam(name = "userId") Long userId,
                           @RequestParam(name = "fullName") String fullName,
                           @RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password,
                           @RequestParam(name = "role") String role,
                           HttpSession session) {
        UserDto userDto = new UserDto();
        userDto.setFullName(fullName);
        userDto.setUsername(username);
        userDto.setRole(role);
        switch (action) {
            case "create":
                String encodePassword = Utils.encodePasswordMD5(password);
                userDto.setPassword(encodePassword);
                service.saveUser(userDto);
                session.setAttribute("status","success");
                break;
            case "update":
                userDto.setUserId(userId);
                userDto.setPassword(password);
                service.updateUser(userDto);
                session.setAttribute("status","success");
                break;
        }
        return "redirect:/admin/home";
    }
}
