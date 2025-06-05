package com.cantuaria.message.model;

public interface ConsolidateMessage {

    String DESTINATION_QUEUE = "consolidation-request";

    record RequestConsolidate(
            Long id
    ) {
    }

}
