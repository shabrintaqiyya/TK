package apap.tugas.akhir.RumahSehat.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="admin")
public class AdminModel extends UserModel implements Serializable{
    
}
