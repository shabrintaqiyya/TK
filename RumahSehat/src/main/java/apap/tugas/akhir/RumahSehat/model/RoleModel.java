// package apap.tugas.akhir.RumahSehat.model;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.sun.istack.NotNull;
// import org.hibernate.annotations.OnDelete;
// import org.hibernate.annotations.OnDeleteAction;

// import javax.persistence.*;
// import javax.validation.constraints.Size;
// import java.io.Serializable;
// import java.util.List;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// import javax.persistence.Entity;
// import javax.persistence.Table;

// @Setter
// @Getter
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// @Table(name = "role")
// public class RoleModel implements Serializable {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotNull
//     @Size(max = 50)
//     @Column(name = "role", nullable = false)
//     private String role;

//     @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
//     @OnDelete(action = OnDeleteAction.CASCADE)
//     @JsonIgnore
//     private List<UserModel> userRole;
// }
