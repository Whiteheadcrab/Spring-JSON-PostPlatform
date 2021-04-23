package whiteheadcrab.springframework.jsonConverter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import whiteheadcrab.springframework.jsonConverter.model.Type;
import whiteheadcrab.springframework.jsonConverter.services.AccountService;
import whiteheadcrab.springframework.jsonConverter.services.ParcelService;

import java.io.IOException;


@Slf4j
@Controller
public class LoginController
{
    public LoginController(ParcelService parcelService, AccountService accountService) {
        this.parcelService = parcelService;
        this.accountService = accountService;
    }

    private final ParcelService parcelService;
    private final AccountService accountService;

    @RequestMapping("/parcel/{id}/show")
    public String loginProcess(Model model, @PathVariable String id)
    {
        //For now here will be direct link to the page on which will be parcel added in bootstrap
        //In future here will be account page which will shows all parcels for this account and only selecting this specific parcel it will open parcel page
        //Must be account/show
        model.addAttribute("parcel",parcelService.findbyId(new Long(id)));
        Type.values();
        return "parcel/show";
    }

    @RequestMapping("/parcel/{id}/save")
    public String saveProcess(Model model, @PathVariable String id) throws IOException {
        //For now here will be direct link to the page on which will be parcel added in bootstrap
        //In future here will be account page which will shows all parcels for this account and only selecting this specific parcel it will open parcel page
        //Must be account/show
        model.addAttribute("parcel",parcelService.findbyId(new Long(id)));
        Type.values();
        model.addAttribute("path",parcelService.savePDF(parcelService.findbyId(new Long(id))));
        return "parcel/save";
    }

}
