package com.neu.csye6220.libseatmgmt.controller;

import com.neu.csye6220.libseatmgmt.service.interfaces.ISeatReservationService;
import com.neu.csye6220.libseatmgmt.service.interfaces.ISeatService;
import com.neu.csye6220.libseatmgmt.service.interfaces.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
public class SeatReservationController {

    @Autowired
    private ISeatReservationService seatReservationService;

    @Autowired
    private ISeatService seatService;

    @Autowired
    private IUserService userService;

    // For Admin -------------------------------------------------------

    @GetMapping("/admin/all")
    public String getAllReservations(HttpSession session, Model model) {
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";
        model.addAttribute("reservations", seatReservationService.getAllReservations());
        return "admin-view-reservations";
    }

    @GetMapping("/admin/cancel/{id}")
    public String cancelReservation(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";
        seatReservationService.deleteReservation(id);
        return "redirect:/reservations/admin/all";
    }

    @GetMapping("/admin/seat/{seatId}")
    public String getSeatReservations(@PathVariable Long seatId, HttpSession session, Model model) {
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";
        model.addAttribute("reservations", seatReservationService.getReservationsBySeatId(seatId));
        model.addAttribute("seat", seatService.getSeatById(seatId));
        return "admin-view-seat-reservations";
    }

    @GetMapping("/admin/seat/{userId}")
    public String getUserReservations(@PathVariable Long userId, HttpSession session, Model model) {
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";
        model.addAttribute("reservations", seatReservationService.getReservationsByUserId(userId));
        model.addAttribute("seat", userService.getUserById(userId));
        return "admin-view-user-seat-reservations";
    }


    // For User -------------------------------------------------------

    @GetMapping("/user/all")
    public String getUserReservations(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null)
            return "redirect:/login";
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("reservations", seatReservationService.getReservationsByUserId(userId));
        return "user-view-reservations";
    }


}
