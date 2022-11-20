package apap.tugas.akhir.RumahSehat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.akhir.RumahSehat.model.ApotekerModel;
import apap.tugas.akhir.RumahSehat.model.DokterModel;
// import apap.tugas.akhir.RumahSehat.model.RoleModel;
import apap.tugas.akhir.RumahSehat.model.UserModel;
import apap.tugas.akhir.RumahSehat.service.DokterService;
import apap.tugas.akhir.RumahSehat.service.UserService;
import apap.tugas.akhir.RumahSehat.service.ApotekerService;
// import apap.tugas.akhir.RumahSehat.service.RoleService;
import apap.tugas.akhir.RumahSehat.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApotekerService apotekerService;

    @Autowired
    private DokterService dokterService;

    // @Autowired
    // private RoleService roleService;


    // @GetMapping("/add")
    // private String addUserFormPage(Model model) {
    //     UserModel user = new UserModel();
    //     List<RoleModel> listRole = roleService.findAll();
    //     model.addAttribute("user", user);
    //     model.addAttribute("listRole", listRole);
    //     return "form-add-user";
    // }

    // @PostMapping("/add") 
    // private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
    //     user.setIsSso(false);
    //     userService.addUser(user);
    //     model.addAttribute("user", user);
    //     return "redirect:/";
    // }

    // add dokter
    @GetMapping("/add-dokter")
    private String addDokterFormPage(Model model) {
        DokterModel dokter = new DokterModel();
        // List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("dokter", dokter);
        // model.addAttribute("listRole", listRole);
        return "form-add-dokter";
    }

    @PostMapping("/add-dokter") 
    private String addDokterSubmit(@ModelAttribute DokterModel dokter, Model model) {
        dokter.setIsSso(false);

        // userService.addUser(dokter);
        dokterService.addDokter(dokter);

        model.addAttribute("dokter", dokter);
        return "redirect:/";
    }
    
    // add apoteker
    @GetMapping("/add-apoteker")
    private String addApotekerFormPage(Model model) {
        ApotekerModel apoteker = new ApotekerModel();
        // List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("apoteker", apoteker);
        // model.addAttribute("listRole", listRole);
        return "form-add-apoteker";
    }

    @PostMapping("/add-apoteker") 
    private String addApotekerSubmit(@ModelAttribute ApotekerModel apoteker, Model model) {
        apoteker.setIsSso(false);

        // userService.addUser(apoteker);
        apotekerService.addApoteker(apoteker);

        model.addAttribute("user", apoteker);
        return "redirect:/";
    }
    
    // @GetMapping("/viewall")
    // public String listUser(Model model){
    //     List<UserModel> listPengguna = userService.getListUser();
    //     model.addAttribute("listPengguna", listPengguna);
    //     return "viewall-pengguna";
    // }

    @GetMapping("/viewall-dokter")
    public String listDokter(Model model){
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute("listDokter", listDokter);
        return "viewall-dokter";
    }

    @GetMapping("/viewall-apoteker")
    public String listApoteker(Model model){
        List<ApotekerModel> listApoteker = apotekerService.getListApoteker();
        model.addAttribute("listApoteker", listApoteker);
        return "viewall-apoteker";
    }

    // @GetMapping("/delete/{username}")
    // public String deleteUserFormPage(@PathVariable String username, Model model) {
    //     UserModel user = userService.getUserByUsername(username);
    //     userService.deleteUser(user);
    //     model.addAttribute("username", user.getUsername());
    //     return "delete-user";
    // }

    @GetMapping("/update")
    public String updatePasswordFormPage(Model model){
        return "form-update-password";
    }

    // @PostMapping("/update")
    // public String updatePasswordSubmitPage(@RequestParam String username, String oldPass, String newPass, String confirmPass, Model model){
    //     UserModel user = userService.getUserByUsername(username);

    //     if(userService.updatePassword(user, oldPass, newPass, confirmPass)){
    //         return "update-password";
    //     }

    //     model.addAttribute("nav", "Update Password");
    //     model.addAttribute("msg", "Konfirmasi password baru tidak sama dengan password baru");

    //     return "action-failed";
    // }
}
