package com.example.schoolAccess.service;

import com.example.schoolAccess.dto.ContactDetailDTO;
import com.example.schoolAccess.exception.ValidationException;
import com.example.schoolAccess.model.ContactDetail;
import com.example.schoolAccess.model.User;
import com.example.schoolAccess.repository.ContactDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactDetailService {
    private final ContactDetailRepository contactDetailRepository;


    public ContactDetail createOrUpdate(ContactDetail contactDetail) {
        if (contactDetail.getPhoneNumber() == null) //TODO
        {
            throw new ValidationException("Phone number should be specified");
        }
        return contactDetailRepository.save(contactDetail);
    }


    public ContactDetail update(User user, ContactDetailDTO contactDetailDTO) {
        ContactDetail contactDetail = user.getContactDetail();
        contactDetail.setPhoneNumber(contactDetailDTO.getPhoneNumber());
        return createOrUpdate(contactDetail);
    }
}
