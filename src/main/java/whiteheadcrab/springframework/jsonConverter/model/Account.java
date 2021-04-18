package whiteheadcrab.springframework.jsonConverter.model;

import lombok.Data;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nip;

    //Data of account
    private String firmName;
    private String firstname;
    private String lastname;
    private String streetName;
    private Long houseNumber;
    private Long flatNumber;
    private String postCode;
    private String town;
    private String telephoneNumber;

    //Date to login
    private String login;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Parcel> Parcels = new HashSet<>();

    public Account addParcel(Parcel parcel)
    {
        parcel.setAccount(this);
        this.Parcels.add(parcel);
        return this;
    }

}
