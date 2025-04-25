package com.neu.csye6220.libseatmgmt.controller;


import com.neu.csye6220.libseatmgmt.model.Reservation;
import com.neu.csye6220.libseatmgmt.model.Seat;
import com.neu.csye6220.libseatmgmt.service.interfaces.ISeatReservationService;
import com.neu.csye6220.libseatmgmt.service.interfaces.ISeatService;
import com.neu.csye6220.libseatmgmt.service.interfaces.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/reservations/admin")
public class AdminController {

    @Autowired
    private ISeatReservationService seatReservationService;

    @Autowired
    private ISeatService seatService;

    @Autowired
    private IUserService userService;


    @GetMapping("")
    public String showReservationDashboard(HttpSession session, Model model) {
        System.out.println("AdminId : "+session.getAttribute("adminId"));
        System.out.println("UserId : "+session.getAttribute("userId"));

        if (session.getAttribute("adminId") == null)
            return "redirect:/login";

        List<Reservation> reservations = seatReservationService.getReservationsForTodayAndTomorrow();
        model.addAttribute("reservations", reservations);
        return "admin-manage-reservations";
    }


    @PostMapping("/filter")
    public String filterReservations(@RequestParam(required = false) String seatType,
                                     @RequestParam(required = false) Integer floorNumber,
                                     @RequestParam String startDateTime,
                                     @RequestParam String endDateTime,
                                     HttpSession session, Model model) {
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        Timestamp startTimestamp = Timestamp.valueOf(LocalDateTime.parse(startDateTime, formatter));
        Timestamp endTimestamp = Timestamp.valueOf(LocalDateTime.parse(endDateTime, formatter));

            List<Reservation> filtered = seatReservationService.filterReservations(seatType,floorNumber, startTimestamp, endTimestamp);
        model.addAttribute("reservations", filtered);
        return "admin-manage-reservations";
    }

    @GetMapping("/seats")
    public String showAllSeats(HttpSession session, Model model) {
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";
        List<Seat> seats = seatService.getAllSeats();
        model.addAttribute("seats", seats);
        return "admin-all-seats";
    }

    @GetMapping("/seat/view/{seatId}")
    public String getReservationsBySeat(@PathVariable Long seatId, HttpSession session, Model model) {
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";

        model.addAttribute("reservations", seatReservationService.getReservationsBySeatId(seatId));
        model.addAttribute("seat", seatService.getSeatById(seatId));
        return "admin-seat-detail-reservations";
    }

    @GetMapping("/users")
    public String showAllUsers(HttpSession session, Model model) {
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";
        model.addAttribute("users", userService.getAllUsers());
        return "admin-all-users";
    }

    @GetMapping("/user/view/{userId}")
    public String getReservationsByUser(@PathVariable Long userId, HttpSession session, Model model) {
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";
        model.addAttribute("reservations", seatReservationService.getReservationsByUserId(userId));
        model.addAttribute("user", userService.getUserById(userId));
        return "admin-user-detail-reservations";
    }

    @GetMapping("/cancel/{id}/user/{userId}")
    public String cancelUserReservation(@PathVariable Long id, @PathVariable Long userId, HttpSession session) {
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";
        seatReservationService.deleteReservation(id);
        return "redirect:/reservations/admin/user/view/" + userId;
    }

    @GetMapping("/cancel/{id}")
    public String cancelReservation(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";
        seatReservationService.deleteReservation(id);
        return "redirect:/reservations/admin";
    }


}
