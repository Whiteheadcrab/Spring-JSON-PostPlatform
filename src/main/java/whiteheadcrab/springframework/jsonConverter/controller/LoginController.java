package whiteheadcrab.springframework.jsonConverter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import whiteheadcrab.springframework.jsonConverter.model.Account;
import whiteheadcrab.springframework.jsonConverter.model.Type;
import whiteheadcrab.springframework.jsonConverter.services.AccountService;

import javax.naming.Name;

@Slf4j
@Controller
public class LoginController
{
    private final AccountService accountService;

    public LoginController(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @RequestMapping("/parcel/{id}/show")
    public String loginProcess(@PathVariable String login , String password , Model model)
    {
        Account account;
        model.addAttribute("account" ,accountService.findByNameAndPassword(new String(login),new String(password)));
        Type.values();
        //For now here will be direct link to the page on which will be parcel added in bootstrap
        //In future here will be account page which will shows all parcels for this account and only selecting this specific parcel it will open parcel page
        //Must be account/show
        return "parcel/show";
    }
}
