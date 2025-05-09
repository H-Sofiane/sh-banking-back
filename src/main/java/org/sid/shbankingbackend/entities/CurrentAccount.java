package org.sid.shbankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("CA")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class CurrentAccount extends BankAccount{
    private double overDraft;
}
