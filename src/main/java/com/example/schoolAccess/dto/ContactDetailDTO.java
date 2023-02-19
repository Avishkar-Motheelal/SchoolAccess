package com.example.schoolAccess.dto;

import com.example.schoolAccess.model.ContactDetail;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactDetailDTO {
    @NotNull(message = "Phone number should be specified")
    private String phoneNumber;


    public ContactDetailDTO(ContactDetail contactDetail) {
        this.phoneNumber = contactDetail.getPhoneNumber();
    }


    public ContactDetail toContactDetail() {
        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setPhoneNumber(this.phoneNumber);
        return contactDetail;
    }
}