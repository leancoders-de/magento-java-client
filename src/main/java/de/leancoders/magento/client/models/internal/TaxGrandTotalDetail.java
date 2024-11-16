package de.leancoders.magento.client.models.internal;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Getter
@Setter
public class TaxGrandTotalDetail {
    private double amount = 0;
    private List<TaxGrandTotalDetailRate> rates = new ArrayList<>();
    private long group_id = 0;
}
