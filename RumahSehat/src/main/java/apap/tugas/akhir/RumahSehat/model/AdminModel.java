package apap.tugas.akhir.RumahSehat.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

// import lombok.AllArgsConstructor;
import lombok.Getter;
// import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
// @AllArgsConstructor
// @NoArgsConstructor
@Entity
@Table(name="admin")
public class AdminModel extends UserModel implements Serializable{
    
}
