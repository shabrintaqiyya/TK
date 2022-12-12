package apap.tugas.akhir.RumahSehat.controller;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.model.UserModel;
import apap.tugas.akhir.RumahSehat.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class AppointmentController {
    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @RequestMapping("/appointment/{username}")
    public String jadwalAppointmentAdmin(
        @PathVariable(value = "username") String username, 
        Model model
    ) {
        UserModel user = userService.getUserByUsername(username);
        System.out.println(user.getUsername());
        if (user.getRole().equals("Dokter")) {
            DokterModel dokter = dokterService.getDokterByUsername(username);
            List<AppointmentModel> listAppointmentDokter = dokter.getListAppointment();
            model.addAttribute("listAppointmentDokter", listAppointmentDokter);
            return "viewall-appointment-dokter";
        }
        List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
        model.addAttribute("listAppointment", listAppointment);
        return "viewall-appointment-admin";
    }

    @GetMapping("/appointment/view/{kode}")
    public String viewDetailAppointmentPage(@PathVariable String kode, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kode);
        if (!appointment.getIsDone()) {
            model.addAttribute("appointment", appointment);
            model.addAttribute("resep", appointment.getResep());
            if (appointment.getResep() != null) {
                if (appointment.getResep().getIsDone()) { // Klo resep udh jadi
                    return "view-appointment-resep-done";
                }

                return "view-appointment-resep-undone";
            }
            return "view-appointment";
        }
        model.addAttribute("appointment", appointment);
        model.addAttribute("resep", appointment.getResep());
        return "view-appointment-done";
    }

    @GetMapping("/appointment/done/{kode}")
    public String updateDoneAppointment(@PathVariable String kode, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kode);
        if (appointment.getResep() == null || appointment.getResep().getIsDone()) {
            AppointmentModel appointmentUpdate =  appointmentService.setDoneAppointment(appointment);
            // model.addAttribute("kode", appointmentUpdate);
        }
        model.addAttribute("appointment", appointment);
        return "appointment-done";
    }
}
