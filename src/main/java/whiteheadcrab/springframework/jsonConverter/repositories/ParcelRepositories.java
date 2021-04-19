package whiteheadcrab.springframework.jsonConverter.repositories;

import org.springframework.data.repository.CrudRepository;
import whiteheadcrab.springframework.jsonConverter.model.Parcel;


public interface ParcelRepositories extends CrudRepository<Parcel,Long>
{

}
