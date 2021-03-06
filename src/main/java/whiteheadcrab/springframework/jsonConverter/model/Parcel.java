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

   @OneToOne(cascade = CascadeType.ALL)
   private Station station;

   private String deliveryAim;

   @Enumerated(value = EnumType.STRING)
   private Type type;

   @OneToOne(cascade = CascadeType.ALL)
   private Receiver receiver;

   @ManyToOne
   private Account account;

}
