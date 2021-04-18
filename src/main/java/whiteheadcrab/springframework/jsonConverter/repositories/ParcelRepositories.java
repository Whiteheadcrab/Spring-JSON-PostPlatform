package whiteheadcrab.springframework.jsonConverter.repositories;

import org.springframework.data.repository.CrudRepository;
import whiteheadcrab.springframework.jsonConverter.model.Parcel;

import java.util.Optional;

public interface ParcelRepositories extends CrudRepository<Parcel,Long>
{

}
