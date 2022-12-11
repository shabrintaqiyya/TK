package apap.tugas.akhir.RumahSehat.restcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.tugas.akhir.RumahSehat.service.AppointmentRestService;
import apap.tugas.akhir.RumahSehat.service.DokterRestService;
import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.rest.AptDetail;

@RestController
@RequestMapping("/api/v1")
public class AppointmentRestController {
    @Autowired
    private AppointmentRestService appointmentRestService;
    
    @PostMapping(value = "appointment/add")
    private AppointmentModel createAppointment(@Valid @RequestBody AptDetail aptDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        }
        else {
            DokterModel dokter = appointmentRestService.makeDokter(aptDTO.getUsernameDokter());
            PasienModel pasien = appointmentRestService.makePasien(aptDTO.getUsernamePasien());
            AppointmentModel apt = appointmentRestService.createAppointment(dokter, pasien, aptDTO);
            if (appointmentRestService.cekJadwal(apt)) {
                return appointmentRestService.saveAppointment(apt);
            }
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Appointment bentrok dengan jadwal lain"
            );
        }
    }


    // @GetMapping(value = "appointment/{username}")
    // public List<AppointmentModel> retrieveListAppointment(@PathVariable("username") String username) {
    //     UserModel user = userRestService.getUserByUsername(username);
    //     if (user.getRole().equals("Dokter")) {
    //         DokterModel dokter = dokterRestService.getDokterByUsername(username);
    //         return dokterRestService.getListAppointmentInDokter(dokter);
    //     }
    //     return appointmentRestService.retrieveListAppointment();
    // }

    // // Retrieve
    // @GetMapping(value = "appointment/{kode}")
    // private AppointmentModel retrieveApt(@PathVariable("kode") String kode) {
    //     try {
    //         return appointmentRestService.getAppointmentByKode(kode);
    //     } catch (NoSuchElementException e) {
    //         throw new ResponseStatusException(
    //             HttpStatus.NOT_FOUND, "Appointment kode " + kode + " not found"
    //         );
    //     }
    // }
}
