package apap.tugas.akhir.RumahSehat.service;

import java.util.List;

import apap.tugas.akhir.RumahSehat.model.UserModel;

public interface UserRestService {
    // CourseModel createCourse(CourseModel course);
    // List<CourseModel> retrieveListCourse();
    // CourseModel getCourseByCode(String code);
    // CourseModel updateCourse(String code, CourseModel courseUpdate);
    // void deleteCourse(String code);
    // Mono<String> getStatus(String code);
    // Mono<CourseDetail> postStatus();

    UserModel addUser(UserModel user);
    public String encrypt(String password);
    public UserModel getUserByUsername(String username);
    List<UserModel> getListUser();
    void deleteUser(UserModel user);
    boolean updatePassword(UserModel username, String oldPass, String newPass, String confirmPass);
}