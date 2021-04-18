package whiteheadcrab.springframework.jsonConverter.services;

import whiteheadcrab.springframework.jsonConverter.model.Account;

public interface AccountService
{
    Account findByNameAndPassword(String name , String pass);
}
