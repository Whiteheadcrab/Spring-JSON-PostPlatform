package whiteheadcrab.springframework.jsonConverter.services;

import whiteheadcrab.springframework.jsonConverter.model.Account;
import whiteheadcrab.springframework.jsonConverter.model.Parcel;

import java.util.Set;

public interface ParcelService
{
    Set<Parcel> getParcels();

    Set<Parcel> findParcelsbyAccount(Account account);

    Parcel findbyId(Long l);
}
