package apap.tugas.akhir.RumahSehat.controller;

import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.model.ResepModel;
import apap.tugas.akhir.RumahSehat.model.TagihanModel;
import apap.tugas.akhir.RumahSehat.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class AppointmentController {
    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointment")
    public String jadwalAppointment(Model model) {
        List<AppointmentModel> listAppointment = appointmentService.getListAppointment();
        // List<AppointmentModel> listAppointmentDokter = ;
        // model.addAttribute("jadwalAppointment", jadwalAppointment);
        model.addAttribute("listAppointment", listAppointment);
        
        return "viewall-appointment";
    }

    @GetMapping("/appointment/{kode}")
    public String viewDetailAppointmentPage(@PathVariable String kode, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kode);
        // Counter jumlah resep yg belum selesai
        int counter = 0;
        for (ResepModel resep : appointment.getListResep()) {
            if (!resep.getIsDone()) {
                counter++;
            }
        }
        // System.out.printn("masukkk");
        // System.out.printlln(counter);
        // Artinya ada resep yg blm selesai shg gabisa selesaiin appointment
        // if (counter > 0) {
        // model.addAttribute("counter", counter);
        // }
        // Yg belum itu yg ada resepnya, tp udh selesai semua
        // tombol selesainya gk muncul
        if (!appointment.getIsDone()) {
            model.addAttribute("appointment", appointment);
            return "view-appointment";
        }
        model.addAttribute("appointment", appointment);
        model.addAttribute("counter", counter);
        return "view-appointment-done";
    }

    @GetMapping("/appointment/done/{kode}")
    public String updateDoneAppointment(@PathVariable String kode, Model model) {
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kode);
        appointment.setIsDone(true);
        appointmentService.setDoneAppointment(appointment);
        TagihanModel tagihan = appointmentService.setTagihan(appointment);
        model.addAttribute("kode", appointment.getKode());
        model.addAttribute("tagihan", tagihan);
        return "appointment-done";
    }
}
