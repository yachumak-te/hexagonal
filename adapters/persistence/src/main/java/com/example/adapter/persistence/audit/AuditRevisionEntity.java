package com.example.adapter.persistence.audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity(AuditListener.class)
@Getter
@Setter
@Table(name = "revinfo")
public class AuditRevisionEntity extends DefaultRevisionEntity {
    private String modifiedBy;
}
