package whiteheadcrab.springframework.jsonConverter.repositories;

import org.springframework.data.repository.CrudRepository;
import whiteheadcrab.springframework.jsonConverter.model.Account;

public interface AccountRepositories extends CrudRepository<Account,Long>
{

}
