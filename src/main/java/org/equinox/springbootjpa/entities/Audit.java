package org.equinox.springbootjpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDate;

@Data
@Embeddable
public class Audit {
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @PrePersist
    public void prePersist() {
        this.setCreatedAt(LocalDate.now());
        this.setUpdatedAt(LocalDate.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedAt(LocalDate.now());
    }
}
