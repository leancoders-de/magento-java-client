package de.leancoders.magento.common.model.cart;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;


/**
 *
 */
@Getter
@Setter
public class GiftCardItemOption {
    @JsonProperty(value = "giftcard_amount")
    private String giftcardAmount;
    @JsonProperty(value = "custom_giftcard_amount")
    private BigDecimal customGiftcardAmount;
    @JsonProperty(value = "giftcard_sender_name")
    private String giftcardSenderName;
    @JsonProperty(value = "giftcard_recipient_name")
    private String giftcardRecipientName;
    @JsonProperty(value = "giftcard_sender_email")
    private String giftcardSenderEmail;
    @JsonProperty(value = "giftcard_recipient_email")
    private String giftcardRecipientEmail;
    @JsonProperty(value = "giftcard_message")
    private String giftcardMessage;
    @JsonProperty(value = "extension_attributes")
    private Map<String, Object> extensionAttributes;
}
