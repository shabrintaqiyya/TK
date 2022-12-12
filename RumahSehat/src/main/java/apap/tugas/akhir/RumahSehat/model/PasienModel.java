package apap.tugas.akhir.RumahSehat.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pasien")
@JsonIgnoreProperties(value={"listAppointment", "id", "role", "isSso", "saldo"}, allowSetters = true)
public class PasienModel extends UserModel implements Serializable{
    
    @NotNull
    @Column(name = "saldo", nullable = false)
    private Integer saldo;

    @NotNull
    @Column(name = "umur", nullable = false)
    private Integer umur;

    // @NotNull
    // @Size(max = 50)
    // @Column(name = "nama", nullable = false)
    // private String nama;

    // @NotNull
    // @Size(max = 50)
    // @Column(name = "role", nullable = false)
    // private String role;

    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIgnore
    // private String role;

    // @NotNull
    // @Size(max = 50)
    // @Column(name = "username", nullable = false, unique = true)
    // private String username;

    // @NotNull
    // @Lob
    // @Column(name = "password", nullable = false)
    // private String password;

    // @NotNull
    // @Size(max = 50)
    // @Column(name = "email", nullable = false, unique = true)
    // private String email;

    // @NotNull
    // @Column(name = "is_Sso", nullable = false)
    // private Boolean isSso;

    @OneToMany(mappedBy = "pasien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<AppointmentModel> listAppointment;
}
