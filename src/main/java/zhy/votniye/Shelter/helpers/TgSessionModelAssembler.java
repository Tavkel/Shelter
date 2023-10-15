package zhy.votniye.Shelter.helpers;

import zhy.votniye.Shelter.models.Contact;
import zhy.votniye.Shelter.models.Owner;

import java.util.Collection;
import java.util.Optional;

public class TgSessionModelAssembler {

    private Contact contact;

    public boolean updateOwner(String data, int step){
        //todo rework
        switch (step) {
            case 1:
                var owner = new Owner();
                contact = new Contact();
                contact.setOwner(owner);
                var fio = data.split(" ");
                owner.setFirstName(fio[0]);
                owner.setLastName(fio[1]);
                owner.setMiddleName(fio[2]);
                return false;
            case 2:
                contact.setPhone(Integer.parseInt(data.replaceAll("\\D", "")));
                return false;
            case 3:
                contact.setAddress(data);
                return false;
            case 4:
                contact.setEmail(data);
                return false;
            case 5:
                contact.setComment(data);
                return true;
            default:
                throw new IllegalStateException("Unexpected value: " + step);
        }
    }

    public Contact getContact() {
        return contact;
    }


}
