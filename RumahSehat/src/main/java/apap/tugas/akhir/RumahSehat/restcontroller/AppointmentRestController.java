package apap.tugas.akhir.RumahSehat.restcontroller;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.tugas.akhir.RumahSehat.service.AppointmentRestService;
import apap.tugas.akhir.RumahSehat.service.DokterRestService;
import apap.tugas.akhir.RumahSehat.service.UserService;
import apap.tugas.akhir.RumahSehat.model.AppointmentModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
import apap.tugas.akhir.RumahSehat.model.PasienModel;
import apap.tugas.akhir.RumahSehat.rest.AptDetail;

@RestController
@RequestMapping("/api/v1")
public class AppointmentRestController {
    @Autowired
    private AppointmentRestService appointmentRestService;

    @Autowired
    private UserService userService;

    
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
            if (!appointmentRestService.cekJadwal(apt)) {
                throw new ResponseStatusException(
                    HttpStatus.valueOf(400), "Waktu Appointment bertabrakan dengan jadwal appointment lain"
                );
            }
            return appointmentRestService.saveAppointment(apt);
        }
    }

    @GetMapping(value = "/appointment/list-appointment")
    private List<AptDetail> retrieveListAppointment(Principal principal) {
        PasienModel pasien = (PasienModel) userService.getUserByUsername(principal.getName());
        return appointmentRestService.retrieveListApt(pasien.getUsername());
    }

    @GetMapping(value = "/appointment/{kode}")
    private AptDetail retrieveAppointment(@PathVariable("kode") String kode) {
        try {
            System.out.println(kode);
            return appointmentRestService.getAppointmentByKode(kode);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Code Appointment " + kode + " not found"
            );
        }
    }
}
