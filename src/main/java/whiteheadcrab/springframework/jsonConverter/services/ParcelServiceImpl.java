package whiteheadcrab.springframework.jsonConverter.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import whiteheadcrab.springframework.jsonConverter.model.Account;
import whiteheadcrab.springframework.jsonConverter.model.Parcel;
import whiteheadcrab.springframework.jsonConverter.repositories.ParcelRepositories;

import java.util.HashSet;

import java.util.Set;

@Slf4j
@Service
public class ParcelServiceImpl implements ParcelService
{
    private final ParcelRepositories parcelRepositories;

    public ParcelServiceImpl(ParcelRepositories parcelRepositories)
    {
        this.parcelRepositories = parcelRepositories;
    }

    @Override
    public Set<Parcel> getParcels()
    {
        log.debug("Working normal");
        Set<Parcel> parcelSet = new HashSet<>();
        parcelRepositories.findAll().iterator().forEachRemaining(parcelSet::add);
        return parcelSet;
    }

    //Implement when required part will be implemented
    @Override
    public Set<Parcel> findParcelsbyAccount(Account account)
    {
        Long accountid = account.getNip();

        return null;
    }
}
