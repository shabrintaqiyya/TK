package apap.tugas.akhir.RumahSehat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
// @MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)

// @DiscriminatorColumn(name="role_type", discriminatorType = DiscriminatorType.INTEGER)
// @Entity
// @Table(name="user_model")
// @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama", nullable = false)
    private String nama;
    
    // @NotNull
    // @Size(max = 50)
    // @Column(name = "role", nullable = false)
    // private String role;

    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    private String role;

    @NotNull
    @Size(max = 50)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Lob
    @Column(name = "password", nullable = false)
    private String password;
    
    @NotNull
    @Size(max = 50)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "is_Sso", nullable = false)
    private Boolean isSso;
}
