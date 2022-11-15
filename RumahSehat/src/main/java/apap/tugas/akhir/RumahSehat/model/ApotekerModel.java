package apap.tugas.akhir.RumahSehat.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

// import lombok.AllArgsConstructor;
import lombok.Getter;
// import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="apoteker")
public class ApotekerModel extends UserModel implements Serializable{
    
    @OneToMany(mappedBy = "confirmerUuid", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ResepModel> listResep;
}
