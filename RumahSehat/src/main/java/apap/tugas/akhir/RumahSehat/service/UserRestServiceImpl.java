package apap.tugas.akhir.RumahSehat.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tugas.akhir.RumahSehat.model.UserModel;
import apap.tugas.akhir.RumahSehat.repository.UserDb;
import apap.tugas.akhir.RumahSehat.rest.Setting;
// import apap.tutorial.belajarbelajar.model.CourseModel;
// import apap.tutorial.belajarbelajar.repository.CourseDb;
// import apap.tutorial.belajarbelajar.rest.CourseDetail;
// import apap.tutorial.belajarbelajar.rest.Setting;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService {

    // @Autowired
    // private CourseDb courseDb;
    
    private WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.userUrl).build();
    }

    // @Override
    // public CourseModel createCourse(CourseModel course)  {
    //     return courseDb.save(course);
    // }

    // @Override
    // public List<CourseModel> retrieveListCourse() {
    //     return courseDb.findAll();
    // }

    // @Override
    // public CourseModel getCourseByCode(String code) {
    //     Optional<CourseModel> course = courseDb.findByCode(code);
    //     if (course.isPresent()) {
    //         return course.get();
    //     } else {
    //         throw new NoSuchElementException();
    //     }

    // }

    // @Override
    // public CourseModel updateCourse(String code, CourseModel courseUpdate) {
    //     CourseModel course = getCourseByCode(code);
    //     course.setNameCourse(courseUpdate.getNameCourse());
    //     course.setDescription(courseUpdate.getDescription());
    //     course.setJumlahSks(courseUpdate.getJumlahSks());
    //     course.setTanggalDimulai(courseUpdate.getTanggalDimulai());
    //     course.setTanggalBerakhir(courseUpdate.getTanggalBerakhir());
    //     return courseDb.save(course);
    // }

    // @Override
    // public void deleteCourse(String code) {
    //     CourseModel course = getCourseByCode(code);
    //     if(isClosed(course.getTanggalDimulai(), course.getTanggalBerakhir())) {
    //         courseDb.delete(course);
    //     } else {
    //         throw new UnsupportedOperationException();
    //     }
    // }

    // public boolean isClosed(LocalDateTime tanggalDimulai, LocalDateTime tanggalBerakhir) {
    //     LocalDateTime now = LocalDateTime.now();
    //     if (now.isBefore(tanggalDimulai)||now.isAfter(tanggalBerakhir)){
    //         return true;
    //     }
    //     return false;
    // }

    // @Override
    // public Mono<String> getStatus(String code) {
    //     return this.webClient.get().uri("/rest/course/" + code + "/status").retrieve().bodyToMono(String.class);
    // }

    // @Override
    // public Mono<CourseDetail> postStatus() {
    //     MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
    //     data.add("code" , "APAP");
    //     data.add("nameCourse", "Arsitektur PAP");
    //     return this.webClient.post().uri("/rest/course/full" )
    //     .syncBody(data)
    //     .retrieve()
    //     .bodyToMono(CourseDetail.class);
    // }

    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user){
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel getUserByUsername(String username){
        return userDb.findByUsername(username);
    }

    @Override
    public List<UserModel> getListUser(){
        return userDb.findAll();
    }

    @Override
    public void deleteUser(UserModel user){
        userDb.delete(user);
    }

    @Override
    public boolean updatePassword(UserModel username, String oldPass, String newPass, String confirmPass){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        if(passwordEncoder.matches(oldPass, username.getPassword()) && newPass.equals(confirmPass)){
            String pass = encrypt(newPass);
            username.setPassword(pass);
            userDb.save(username);
            return true;
        }
        
        return false;
    }
}