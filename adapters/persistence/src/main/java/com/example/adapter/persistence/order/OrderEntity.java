package com.example.adapter.persistence.order;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;



@Entity
@Table(name = "`order`")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class OrderEntity {

    @Column
    @Id
    private UUID id;

    @Column
    private OffsetDateTime placedAt;

    @Column
    private UUID userId;
}
