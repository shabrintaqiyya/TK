package apap.tugas.akhir.RumahSehat.rest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetail {
    private String status;

    @JsonProperty("user-license" )
    private Integer userLicense;

    @JsonProperty("valid-until" )
    private Date validUntil;
}
