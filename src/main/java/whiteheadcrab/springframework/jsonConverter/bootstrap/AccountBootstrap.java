package whiteheadcrab.springframework.jsonConverter.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import whiteheadcrab.springframework.jsonConverter.model.*;
import whiteheadcrab.springframework.jsonConverter.repositories.AccountRepositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
    public AccountBootstrap(AccountRepositories accountRepositories) {
        this.accountRepositories = accountRepositories;
    }

    private final AccountRepositories accountRepositories;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        accountRepositories.saveAll(getAccount());
    }

    private List<Account> getAccount()
    {
        List<Account> accounts = new ArrayList<>(1);

        //Adding account
        Account account1 = new Account();
        account1.setFirmName("Norels Patrolum");
        account1.setFirstName("Jack");
        account1.setLastName("Sermius");
        account1.setStreetName("Alej Serkisus");
        account1.setHouseNumber((long) 3);
        account1.setFlatNumber((long) 13);
        account1.setPostCode("12-673");
        account1.setTown("Mexton");
        account1.setTelephoneNumber("111-222-333");

        //Creating Parcel
        Parcel parcel1 = new Parcel();
        parcel1.setDeliveryAim("Veni");
        parcel1.setStation(new Station("Stulak "));
        parcel1.setType(Type.LIGHT);
        parcel1.setPrintDate(LocalDate.ofEpochDay(18-04-2021));
        parcel1.setDeliveryDate(LocalDate.ofEpochDay(19-04-2021));


        //Creating Receiver
        Receiver receiver1 = new Receiver();
        receiver1.setTelephoneNumber("444-555-888");
        receiver1.setEmail("receiver@gmail.com");

        //Connected Parcel and Receiver
        parcel1.setReceiver(receiver1);

        //Adding Parcel to Account
        account1.addParcel(parcel1);


        //Adding created Account for Account List
        accounts.add(account1);

        //Return Account list
        return accounts;
    }
}
