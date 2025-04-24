package com.neu.csye6220.libseatmgmt.controller;

import com.neu.csye6220.libseatmgmt.dto.ReservationDTO;
import com.neu.csye6220.libseatmgmt.model.Reservation;
import com.neu.csye6220.libseatmgmt.model.Seat;
import com.neu.csye6220.libseatmgmt.service.interfaces.ISeatReservationService;

import com.neu.csye6220.libseatmgmt.service.interfaces.ISeatService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/reservations/user")
public class SeatReservationController {

    @Autowired
    private ISeatReservationService seatReservationService;

    @Autowired
    private ISeatService seatService;

    @GetMapping("")
    public String showUserReservationDashboard(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null)
            return "redirect:/login";
        Integer userId = (Integer) session.getAttribute("userId");
        model.addAttribute("reservations", seatReservationService.getReservationsByUserId(Long.valueOf(userId)));
        return "user-manage-reservations";
    }

    @GetMapping("/search")
    public String showSeatSearchForm(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null)
            return "redirect:/login";
        return "user-search-seats";
    }

    @PostMapping("/seats-results")
    public String showAvailableSeats(  @RequestParam String seatType,
                                       @RequestParam String startDateTime,
                                       @RequestParam String endDateTime,
                                       HttpSession session, Model model) {
        if (session.getAttribute("userId") == null)
            return "redirect:/login";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        Timestamp startTimestamp = Timestamp.valueOf(LocalDateTime.parse(startDateTime, formatter));
        Timestamp endTimestamp = Timestamp.valueOf(LocalDateTime.parse(endDateTime, formatter));

        List<Seat> availableSeats = seatReservationService.getAvailableSeats(seatType, startTimestamp, endTimestamp);
        model.addAttribute("availableSeats", availableSeats);
        model.addAttribute("reservation", new ReservationDTO());
        return "user-available-seats";
    }

    @PostMapping("/seats-by-type")
    public String showSeatsByType(@RequestParam String seatType, HttpSession session, Model model) {
        if (session.getAttribute("userId") == null)
            return "redirect:/login";

        List<Seat> matchingSeats = seatService.getSeatsByType(seatType); ;
        model.addAttribute("availableSeats", matchingSeats);
        model.addAttribute("seatType", seatType);
        return "user-available-seats";
    }


    @GetMapping("/create/{seatId}")
    public String showCreateReservationForm(@PathVariable Long seatId, HttpSession session, Model model) {
        if (session.getAttribute("userId") == null)
            return "redirect:/login";

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setSid(seatId);
        model.addAttribute("reservation", reservationDTO);
        return "user-create-reservation";
    }

    @PostMapping("/save")
    public String createReservation(@Valid @ModelAttribute("reservation") ReservationDTO reservationDTO, HttpSession session, Model model) {
        System.out.println("Inside createReservation()");
        System.out.println("userId : " + session.getAttribute("userId"));
        if (session.getAttribute("userId") == null)
            return "redirect:/login";
        Integer userId = (Integer) session.getAttribute("userId");

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            Timestamp start = Timestamp.valueOf(LocalDateTime.parse(reservationDTO.getStartDateTime(), formatter));
            Timestamp end = Timestamp.valueOf(LocalDateTime.parse(reservationDTO.getEndDateTime(), formatter));

            if (!end.after(start)) {
                model.addAttribute("error", "End time must be after start time.");
                model.addAttribute("reservation", reservationDTO);
                return "user-create-reservation";
            }


            if (!seatReservationService.isSeatAvailable(reservationDTO.getSid(), start, end)) {
                model.addAttribute("error", "Seat is not available for the selected time.");
                model.addAttribute("reservation", reservationDTO);
                return "user-create-reservation";
            }

            boolean conflict = seatReservationService.getReservationsByUserId(Long.valueOf(userId)).stream()
                    .anyMatch(r -> r.getStartDateTime().before(end) && r.getEndDateTime().after(start));

            if (conflict) {
                model.addAttribute("error", "You have a conflicting reservation.");
                model.addAttribute("reservation", reservationDTO);
                return "user-create-reservation";
            }

            seatReservationService.createReservation(Long.valueOf(userId), reservationDTO.getSid(), start, end);
            return "redirect:/reservations/user";
        } catch (Exception e) {
            model.addAttribute("error", "Error creating reservation: " + e.getMessage());
            model.addAttribute("reservation", reservationDTO);
            return "user-create-reservation";
        }
    }

   @GetMapping("/edit/{id}")
    public String editReservation(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("userId") == null)
            return "redirect:/login";
        Integer userId = (Integer) session.getAttribute("userId");
        Reservation reservation = seatReservationService.getReservationById(id);

        if(!reservation.getUser().getId().equals(Long.valueOf(userId)))
            return "redirect:/access-denied";

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setSid(reservation.getSeat().getId());
        reservationDTO.setUid(userId);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        reservationDTO.setStartDateTime(reservation.getStartDateTime().toLocalDateTime().format(formatter));
        reservationDTO.setEndDateTime(reservation.getEndDateTime().toLocalDateTime().format(formatter));

        model.addAttribute("reservation", reservationDTO);
        model.addAttribute("reservationId", id);
        return "user-edit-reservation";
    }

    @PostMapping("/update/{id}")
    public String updateReservation(@PathVariable Long id, @Valid @ModelAttribute("reservation") ReservationDTO reservationDTO, HttpSession session, Model model) {
        if (session.getAttribute("userId") == null)
            return "redirect:/login";
        Integer userId = (Integer) session.getAttribute("userId");
        Reservation reservation = seatReservationService.getReservationById(id);

        if(!reservation.getUser().getId().equals(Long.valueOf(userId)))
            return "redirect:/access-denied";

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            Timestamp start = Timestamp.valueOf(LocalDateTime.parse(reservationDTO.getStartDateTime(), formatter));
            Timestamp end = Timestamp.valueOf(LocalDateTime.parse(reservationDTO.getEndDateTime(), formatter));

            if (!end.after(start)) {
                model.addAttribute("error", "End time must be after start time.");
                model.addAttribute("reservation", reservationDTO);
                model.addAttribute("reservationId", id);
                return "user-edit-reservation";
            }

            if (!seatReservationService.isSeatAvailableForUpdate(reservationDTO.getSid(), start, end,id)) {
                model.addAttribute("error", "Seat is not available for the selected time.");
                model.addAttribute("reservation", reservationDTO);
                model.addAttribute("reservationId", id);
                return "user-edit-reservation";
            }

            boolean conflict = seatReservationService.getReservationsByUserId(Long.valueOf(userId))
                    .stream()
                    .filter(r -> !r.getId().equals(id))
                    .anyMatch(r -> r.getStartDateTime().before(end) && r.getEndDateTime().after(start));

            if (conflict) {
                model.addAttribute("error", "You already have a reservation during this time.");
                model.addAttribute("reservation", reservationDTO);
                model.addAttribute("reservationId", id);
                return "user-edit-reservation";
            }

            reservation.setStartDateTime(start);
            reservation.setEndDateTime(end);
            seatReservationService.updateReservation(reservation);
            return "redirect:/reservations/user";
        } catch (Exception e) {
            model.addAttribute("error", "Error updating reservation: " + e.getMessage());
            model.addAttribute("reservation", reservationDTO);
            model.addAttribute("reservationId", id);
            return "user-edit-reservation";
        }
    }

    @GetMapping("/cancel/{id}")
    public String cancelReservation(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("userId") == null)
            return "redirect:/login";
        Integer userId = (Integer) session.getAttribute("userId");
        Reservation reservation = seatReservationService.getReservationById(id);

        if(!reservation.getUser().getId().equals(Long.valueOf(userId)))
            return "redirect:/access-denied";

        seatReservationService.deleteReservation(id);
        return "redirect:/reservations/user/my";
    }

    @PostMapping("/save-debug")
    public String testDebugPost(HttpServletRequest request) {
        System.out.println("DEBUG POST: Was able to hit /save-debug");
        return "redirect:/reservations/user";
    }


}

