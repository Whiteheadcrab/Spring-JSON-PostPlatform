package whiteheadcrab.springframework.jsonConverter.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"parcel"})
public class Station
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Parcel parcel;

    public Station()
    {}

    public Station(String name)
    {
        this.name = name;
    }

    public Station(String name , Parcel parcel)
    {
        this.name = name;
        this.parcel = parcel;
    }
}
