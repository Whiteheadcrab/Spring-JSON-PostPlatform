package whiteheadcrab.springframework.jsonConverter.services;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import whiteheadcrab.springframework.jsonConverter.model.Account;
import whiteheadcrab.springframework.jsonConverter.model.Parcel;
import whiteheadcrab.springframework.jsonConverter.repositories.ParcelRepositories;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Base64;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Base64.Encoder;





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

    @Override
    public Parcel findbyId(Long l)
    {
        Optional<Parcel> parcelOptional = parcelRepositories.findById(l);

        return parcelOptional.get();
    }

    @Override
    public String savePDF(Parcel parcel) throws IOException
    {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();

        //First line
        Integer ty = 700;
        Integer tx = 25;

        //Inputting first line
        String parcelInfo = "Etykieta Listu przewozowego dla Parcel Id "+parcel.getId();
        textAdd(ty,tx,parcelInfo,contentStream);

        //Inputting Parcel
        //Header of Parcel
        tx = 0;
        ty = -30;
        parcelInfo="Parcel";
        textAdd(ty,tx,parcelInfo,contentStream);

        //Body of Parcel
        ty = -15;
        parcelInfo="Data wydruku: "+parcel.getPrintDate();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Data nadania: "+parcel.getDeliveryDate();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Stacja nadania: "+parcel.getDeliveryAim();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Stacja docelowa: "+parcel.getStation().getName();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Rodzaj przesylki: "+parcel.getType();
        textAdd(ty,tx,parcelInfo,contentStream);

        //Inputting value from account
        //Header
        ty = -45;
        parcelInfo="Account";
        textAdd(ty,tx,parcelInfo,contentStream);
        //Body of Account
        ty = -15;
        parcelInfo="NIP: "+parcel.getAccount().getNip();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Nazwa Firmy: "+parcel.getAccount().getFirmName();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Imie: "+parcel.getAccount().getFirstName();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Nazwisko: "+parcel.getAccount().getLastName();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Ulica: "+parcel.getAccount().getStreetName();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Numer Domu: "+parcel.getAccount().getHouseNumber();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Numer Mieszkania: "+parcel.getAccount().getFlatNumber();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Kod pocztowy: "+parcel.getAccount().getPostCode();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Miasto: "+parcel.getAccount().getTown();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Telephon kontaktowy: "+parcel.getAccount().getTelephoneNumber();
        textAdd(ty,tx,parcelInfo,contentStream);

        //Inputting Receiver
        //Header
        ty = -45;
        parcelInfo="Odbiorca";
        textAdd(ty,tx,parcelInfo,contentStream);

        //Body of Receiver
        ty = -15;
        parcelInfo="Telephon kontaktowy: "+parcel.getReceiver().getTelephoneNumber();
        textAdd(ty,tx,parcelInfo,contentStream);
        parcelInfo="Email: "+parcel.getReceiver().getEmail();
        textAdd(ty,tx,parcelInfo,contentStream);

        //Encoding into Base64
        Encoder encoder = Base64.getEncoder();
        String collect  = collectJson(parcel);
        String encodedString = encoder.encodeToString(collect.getBytes());

        //Header of decoded Parcel
        ty = -45;
        parcelInfo="KOD2D";
        textAdd(ty,tx,parcelInfo,contentStream);

        //Body of encoded Parcel
        ty = -15;

        //Checking Length of encoded Parcel
        if (encodedString.length()>60) {
            String part;

                do
                    {
                    part = encodedString.substring(0,60);
                    textAdd(ty,tx,part,contentStream);
                    encodedString = encodedString.substring(60);
                    }
                while (encodedString.length() > 60);
        }
        else {
            textAdd(ty,tx,encodedString,contentStream);
        }

        contentStream.endText();
        contentStream.close();

        //Path for saved document
        String path;
        //Receive current time and date for file name
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        path = "C:/Users/Mykyta/Desktop/"+ formatter.format(date)+"-"+LocalDateTime.now().getHour()+"-"+LocalDateTime.now().getMinute()+ "-Parcel-"+parcel.getId()+".pdf";

        document.save(path);
        document.close();

        return path;
    }

    private void textAdd(Integer ty , Integer tx, String text , PDPageContentStream pdPageContentStream) throws IOException
    {
        pdPageContentStream.newLineAtOffset(tx, ty);
        pdPageContentStream.showText(text);
    }


    public String collectJson(Parcel parcel)
    {
        String gatheredString = parcel.getId().toString()+"KOD2D"+parcel.getPrintDate().toString()+"KOD2D"+
                parcel.getDeliveryDate().toString()+"KOD2D"+ parcel.getDeliveryAim()+"KOD2D"+
                parcel.getStation().getName()+"KOD2D"+parcel.getType().toString()+"KOD2D"+
                parcel.getAccount().getNip().toString()+"KOD2D"+parcel.getAccount().getFirmName()+"KOD2D"+
                parcel.getAccount().getFirstName()+"KOD2D"+parcel.getAccount().getLastName()+"KOD2D"+
                parcel.getAccount().getStreetName()+"KOD2D"+parcel.getAccount().getHouseNumber().toString()+"KOD2D"+
                parcel.getAccount().getFlatNumber().toString()+"KOD2D"+parcel.getAccount().getPostCode()+"KOD2D"+
                parcel.getAccount().getTown()+"KOD2D"+parcel.getAccount().getTelephoneNumber()+"KOD2D"+
                parcel.getReceiver().getTelephoneNumber()+"KOD2D"+parcel.getReceiver().getEmail()+"KOD2D";
        return  gatheredString;
    }
}
