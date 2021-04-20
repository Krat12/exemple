package com.bank.example.listener;

import com.bank.example.model.DocumentScans;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class DocumentScansListener {

    @PrePersist
    public void prePersist(DocumentScans documentScans) {
        if (documentScans.getITN() == null && documentScans.getInsuranceNumber() == null) {
            throw new RuntimeException();
        }
    }

    @PreUpdate
    public void preUpdate(DocumentScans documentScans) {
        if (documentScans.getITN() == null && documentScans.getInsuranceNumber() == null) {
            throw new RuntimeException();
        }
    }


}
