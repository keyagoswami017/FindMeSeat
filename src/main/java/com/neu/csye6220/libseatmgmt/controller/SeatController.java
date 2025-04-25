package com.neu.csye6220.libseatmgmt.controller;

import com.neu.csye6220.libseatmgmt.dto.SeatDTO;
import com.neu.csye6220.libseatmgmt.model.Seat;
import com.neu.csye6220.libseatmgmt.service.interfaces.ISeatService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private ISeatService seatService;

    // For Admin -------------------------------------------------------

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";
        return "seats";
    }

    @GetMapping("/admin/add")
    public String addSeatForAdmin(HttpSession session, Model model){
        if(session.getAttribute("adminId") == null)
            return "redirect:/login";
        model.addAttribute("seat", new Seat());
        return "admin-add-seat";
    }

    @PostMapping("/admin/create")
    public String createSeat(@Valid @ModelAttribute("seat") SeatDTO seatDTO, BindingResult result, HttpSession session, Model model){
        if(session.getAttribute("adminId") == null)
            return "redirect:/login";

        if(seatService.seatExists(seatDTO.getSeatType(),seatDTO.getSeatNumber(), seatDTO.getFloorNumber())) {
            model.addAttribute("error", "Seat with this number already exists on the same floor.");
            model.addAttribute("seat", seatDTO);
            return "admin-add-seat";
        }
        if(result.hasErrors()){
            model.addAttribute("seat", seatDTO);
            return "admin-add-seat";
        }
        Seat seat = new Seat();
        seat.setSeatNumber(seatDTO.getSeatNumber());
        seat.setSeatType(seatDTO.getSeatType());
        seat.setFloorNumber(seatDTO.getFloorNumber());
        seat.setAvailable(seatDTO.isAvailable());
        seatService.createSeat(seat);
        return "redirect:/seat/admin/all";
    }

    @GetMapping("/admin/edit/{id}")
    public String editSeat(@PathVariable Long id,HttpSession session,Model model){
        if (session.getAttribute("adminId") == null)
            return "redirect:/login";
        model.addAttribute("seat", seatService.getSeatById(id));
        return "admin-edit-seat";
    }

    @PostMapping("/admin/update")
    public String updateSeat(@ModelAttribute Seat seat, HttpSession session){
        if(session.getAttribute("adminId") == null)
            return "redirect:/login";
        seatService.updateSeat(seat);
        return "redirect:/seat/admin/all";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteSeat(@PathVariable Long id, HttpSession session){
        if(session.getAttribute("adminId") == null)
            return "redirect:/login";
        seatService.deleteSeat(id);
        return "redirect:/seat/admin/all";
    }

    @GetMapping("/admin/all")
    public String getAllSeatsForAdmin(HttpSession session, Model model){
        if(session.getAttribute("adminId") == null)
            return "redirect:/login";
        model.addAttribute("seats", seatService.getAllSeats());
        return "admin-view-seats";
    }


 }
