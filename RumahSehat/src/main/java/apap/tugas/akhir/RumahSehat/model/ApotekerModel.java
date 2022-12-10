package apap.tugas.akhir.RumahSehat.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.CascadeType;
import java.util.List;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="apoteker")
// @DiscriminatorValue("3")
public class ApotekerModel extends UserModel implements Serializable{
    
    @OneToMany(mappedBy = "confirmerUuid", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ResepModel> listResep;
}
