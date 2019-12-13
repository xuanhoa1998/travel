package vn.nuce.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import vn.nuce.Utils;
import vn.nuce.dto.TourDto;
import vn.nuce.dto.UserDto;
import vn.nuce.service.impl.TourServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminTourController {

    @Autowired
    TourServiceImpl service;

    @GetMapping("/tours")
    public String showPage(HttpSession session, ModelMap modelMap) {
        setUser(session, modelMap);
        List<TourDto> tours = new ArrayList<>();
        try {
            tours = service.findAllTours();
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("tours", tours);
        if (session.getAttribute("status") != null) {
            modelMap.addAttribute("status", session.getAttribute("status"));
            session.removeAttribute("status");
        }
        return "/admin/tour";
    }

    @GetMapping("/tour/{id}")
    public String showTourById(@PathVariable Long id, HttpSession session, ModelMap modelMap) {
        setUser(session, modelMap);

        TourDto dto = service.findOneTour(id);

        modelMap.addAttribute("tour", dto);

        return "/admin/tour_info";
    }

    @GetMapping("/tour/info")
    public String insertTourForm(HttpSession session, ModelMap modelMap) {
        setUser(session, modelMap);

        return "/admin/tour_info";
    }

    @PostMapping("/tour/info")
    public String insertTour(@RequestParam(name = "action") String action,
                             @RequestParam(name = "tour_description") String description,
                             @RequestParam(name = "tour_name") String name,
                             @RequestParam(name = "breakfast") Long breakfast,
                             @RequestParam(name = "price") Long price,
                             @RequestParam(name = "address") String address,
                             HttpSession session, HttpServletRequest request) {
        TourDto dto = new TourDto();
        dto.setTourName(name);
        dto.setTourPrice(price);
        dto.setTourBreakFast(breakfast);
        dto.setTourAddress(address);
        dto.setTourDescription(description);
        switch (action) {
            case "create":
                try {
                    service.saveTour(dto);
                    session.setAttribute("status", "success");
                } catch (Exception e) {
                    session.setAttribute("status", "fail");
                    e.printStackTrace();
                }
                break;
            case "update":
                try {
                    Long tourId = Long.valueOf(request.getParameter("tourId"));
                    Timestamp createdDate = Timestamp.valueOf(request.getParameter("createdDate"));
                    dto.setTourId(tourId);
                    dto.setCreatedDate(createdDate);
                    service.updateTour(dto);
                    session.setAttribute("status", "success");
                } catch (Exception e) {
                    session.setAttribute("status", "fail");
                    e.printStackTrace();
                }
                break;
            default:
        }
        return "redirect:/admin/tours";
    }

    private void setUser(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("user") != null) {
            UserDto dto = (UserDto) session.getAttribute("user");
            modelMap.addAttribute("dto", dto);
        }
    }

}
