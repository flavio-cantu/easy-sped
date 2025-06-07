package com.cantuaria.bookkeeping.model;

import java.time.LocalDate;

public record SaveBookkeeping(
        Long clientId,
        Long companyId,
        Long accountantId,
        LocalDate start,
        LocalDate end,
        Long layoutVersionId,
        Integer purposeValue,
        String profileValue) {
}
