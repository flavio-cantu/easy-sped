package com.cantuaria.bookkeeping;

import com.cantuaria.sped.Bookkeeping;
import com.cantuaria.validation.BusinessValidation;
import org.springframework.stereotype.Component;


@Component
public class BookkeepingValidation {

    public void canConsolidate(Bookkeeping bookkeeping) {
        BusinessValidation businessValidation = new BusinessValidation();

        businessValidation.checkRule(
                bookkeeping.getStatus() != null,
                "A escrituração não está com situação que permite consolidar"
        );

        businessValidation.throwIfNotEmpty();
    }
}
