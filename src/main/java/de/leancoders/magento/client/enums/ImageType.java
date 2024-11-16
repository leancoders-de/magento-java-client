package de.leancoders.magento.client.enums;


import lombok.Getter;

/**
 *
 */
@Getter
public enum ImageType {
    JPEG("image/png"),
    PNG("image/jpeg");

    private String contentType;
    ImageType(final String contentType) {
        this.contentType = contentType;
    }
}
