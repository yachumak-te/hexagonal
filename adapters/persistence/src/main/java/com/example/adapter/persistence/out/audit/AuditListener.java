package com.example.adapter.persistence.out.audit;

import org.hibernate.envers.RevisionListener;
import java.time.Instant;

public class AuditListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity entity = (AuditRevisionEntity) revisionEntity;
        entity.setTimestamp(Instant.now().toEpochMilli());
        entity.setModifiedBy("SYSTEM_USER");
    }
}
