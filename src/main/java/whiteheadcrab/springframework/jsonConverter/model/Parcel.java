package whiteheadcrab.springframework.jsonConverter.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"account"})
public class Parcel
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   // date data
   private LocalDate printDate;
   private LocalDate deliveryDate;

   @OneToMany(cascade = CascadeType.ALL )
   private Set<Station> Stations = new HashSet<>();

   private String deliveryAim;

   @Enumerated(value = EnumType.STRING)
   private Type type;

   @OneToOne(cascade = CascadeType.ALL)
   private Receiver Receiver;

   @ManyToOne
   private Account account;

   public Parcel addStation(Station station)
   {
      station.setParcel(this);
      this.Stations.add(station);
      return this;
   }
}
