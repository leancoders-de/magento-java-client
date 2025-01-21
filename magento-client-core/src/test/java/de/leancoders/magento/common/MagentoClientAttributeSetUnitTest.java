package de.leancoders.magento.common;


import de.leancoders.magento.client.model.internal.MageConfig;
import de.leancoders.magento.client.services.AttributeSetClientService;
import de.leancoders.magento.client.services.MageClientService;
import de.leancoders.magento.common.model.attributeset.AttributeList;
import de.leancoders.magento.common.model.attributeset.AttributeSet;
import de.leancoders.magento.common.model.search.AttributeSetPage;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 *
 */
@Log4j2
public class MagentoClientAttributeSetUnitTest {

    @DisplayName("AttributeSet: Listing")
    @Test
    public void testAttributeSetListing() {
        final MageClientService clientService = new MageClientService(
            MageConfig.of(
                "https://staging.mr-hear.leancoders.de/",
                443
            )
        );
        clientService.loginAsAdmin("ralf", "admin12345");


        final AttributeSetClientService attributeSetClientService = clientService.attributeSets();

        final AttributeSetPage attributeSetPage = attributeSetClientService.attributeSets(0, 10);
        System.out.println("attributeSetPage = " + attributeSetPage);

        for (final AttributeSet item : attributeSetPage.getItems()) {
            final AttributeList attributesInSet = attributeSetClientService.attributesByAttributeSetId(item.getAttributeSetId());

            System.out.println("attributesInSet = " + attributesInSet);
        }
    }

}
