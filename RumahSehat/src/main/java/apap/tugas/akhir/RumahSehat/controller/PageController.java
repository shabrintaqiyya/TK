package apap.tugas.akhir.RumahSehat.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import apap.tugas.akhir.RumahSehat.model.AdminModel;
import apap.tugas.akhir.RumahSehat.model.UserModel;
import apap.tugas.akhir.RumahSehat.security.xml.Attributes;
import apap.tugas.akhir.RumahSehat.security.xml.ServiceResponse;
import apap.tugas.akhir.RumahSehat.service.AdminService;
import apap.tugas.akhir.RumahSehat.service.UserService;
import apap.tugas.akhir.RumahSehat.setting.Setting;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class PageController {

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    private ArrayList<String> whiteList = new ArrayList<>(
        Arrays.asList(
            "shabrina.salsabila01",
            "magnolia.fayza01",
            "muhammad.raffy",
            "caryn.hanuga",
            "shabrina.taqiyya"
        )
    );

    @RequestMapping("/")
    private String home() {
        log.info("User mengakses homepage");
        return "home";
    }

    @RequestMapping("/login")
    private String login(Model model) {
        return "login";
    }

    private WebClient webClient = WebClient.builder().build();

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
        @RequestParam(value = "ticket", required = false) String ticket,
        HttpServletRequest request
    ){

        
        ServiceResponse serviceResponse = this.webClient.get().uri(
                String.format(
                        Setting.SERVER_VALIDATE_TICKET,
                        ticket,
                        Setting.CLIENT_LOGIN
                )
        ).retrieve().bodyToMono(ServiceResponse.class).block();

        Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();

        AdminModel admin = adminService.getUserByUsername(username);
        
        if (admin == null && whiteList.contains(username)) {
            admin = new AdminModel();
            admin.setEmail(username + "@ui.ac.id");
            admin.setNama(attributes.getNama());
            admin.setPassword("rumahsehat");
            admin.setUsername(username);
            admin.setIsSso(true);
            admin.setRole("Admin");
            adminService.addAdmin(admin);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "rumahsehat");
        
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        
        log.info(admin.getUsername() +" berhasil login");
        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "login-sso")
    public ModelAndView loginSSO() {
        System.out.println(Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
        log.info("Login dengan SSO");
        return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

    @GetMapping(value = "logout-sso")
    public ModelAndView logoutSSO(Principal principal) {
        UserModel user = userService.getUserByUsername(principal.getName());
        if (user.getIsSso() == false) {
            log.info("User berhasil logout");
            return new ModelAndView("redirect:/logout");
        }
        log.info("Admin berhasil logout dengan SSO");
        return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }

    // @GetMapping("/chart")
    // public String viewStatistics(Model model) {
    //     return "chart";
    // }
}