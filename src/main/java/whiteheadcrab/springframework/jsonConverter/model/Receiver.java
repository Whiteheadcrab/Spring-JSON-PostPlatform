package whiteheadcrab.springframework.jsonConverter.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Data
@Entity
@EqualsAndHashCode(exclude = {"parcel"})
public class Receiver
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String telephoneNumber;

    private String email;
}
