package de.leancoders.magento.client.services.v1;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.client.utils.StringUtils;
import de.leancoders.magento.common.model.enums.EMimeType;
import de.leancoders.magento.common.model.product.ProductMedia;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;

import javax.annotation.Nonnull;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.leancoders.magento.client.services.MagePaths.PRODUCTS_V1;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;


/**
 *
 */
@Log4j2
public class MagentoProductMediaManager extends MagentoHttpComponent {

    private final MagentoClient client;

    public MagentoProductMediaManager(MagentoClient client) {
        super(client.getHttpComponent());
        this.client = client;
    }


    @Override
    public String token() {
        return client.token();
    }


    @Override
    public String baseUri() {
        return client.baseUri();
    }


    public long uploadProductImage(String sku, int position, String filename, byte[] imageBytes, String imageType, String imageFileName) {

        try {
            String base64EncodedData = new String(Base64.encodeBase64(imageBytes), "UTF-8");
            return uploadProductImage(sku, position, filename, base64EncodedData, imageType, imageFileName);
        }
        catch (UnsupportedEncodingException e) {
            log.error("Failed to covert image bytes to base64 string", e);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return -1L;
    }


    public boolean updateProductImage(String sku, long entryId, int position, String filename, byte[] imageBytes, String imageType, String imageFileName) {

        try {
            String base64EncodedData = new String(Base64.encodeBase64(imageBytes), "UTF-8");
            return updateProductImage(sku, entryId, position, filename, base64EncodedData, imageType, imageFileName);
        }
        catch (UnsupportedEncodingException e) {
            log.error("Failed to covert image bytes to base64 string", e);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    @Deprecated
    public boolean updateProductImage(String sku, long entryId, int position, String filename, String base64EncodedData, String imageType, String imageFileName) throws JsonProcessingException {
        final String uri = baseUri() + PRODUCTS_V1 + encode(sku) + "/media/" + entryId;

        final Map<String, Object> req = new HashMap<>();
        final Map<String, Object> entry = new HashMap<>();

        entry.put("id", entryId);
        entry.put("media_type", "image");
        entry.put("label", "Image");
        entry.put("position", position);
        entry.put("disabled", false);

        final List<String> types = Arrays.asList("image", "small_image", "thumbnail");
        entry.put("types", types);
        entry.put("file", filename);

        final Map<String, Object> content = new HashMap<>();
        content.put("base64_encoded_data", base64EncodedData);
        content.put("type", imageType);
        content.put("name", imageFileName);

        entry.put("content", content);

        req.put("entry", entry);

        final String body = OBJECT_MAPPER.writeValueAsString(req);
        final String json = putSecure(uri, body);

        if (!validate(json)) {
            return false;
        }
        return json.equalsIgnoreCase("true");
    }


    public long uploadProductImage(String sku, int position, String filename, String base64EncodedData, String imageType, String imageFileName) throws JsonProcessingException {
        final String uri = baseUri() + PRODUCTS_V1 + encode(sku) + "/media";

        final Map<String, Object> req = new HashMap<>();
        final Map<String, Object> entry = new HashMap<>();

        entry.put("media_type", "image");
        entry.put("label", "Image");
        entry.put("position", position);
        entry.put("disabled", false);

        final List<String> types = Arrays.asList("image", "small_image", "thumbnail");
        entry.put("types", types);
        entry.put("file", filename);

        final Map<String, Object> content = new HashMap<>();
        content.put("base64_encoded_data", base64EncodedData);
        content.put("type", imageType);
        content.put("name", imageFileName);

        entry.put("content", content);

        req.put("entry", entry);

        final String body = OBJECT_MAPPER.writeValueAsString(req);
        final String response = postSecure(uri, body);

        if (!validate(response)) {
            return -1L;
        }
        return Long.parseLong(StringUtils.stripQuotation(response));
    }


    @Nonnull
    public List<ProductMedia> getProductMediaList(@NonNull final String sku) throws JsonProcessingException {
        final String uri = baseUri() + PRODUCTS_V1 + encode(sku) + "/media";

        final String json = getSecured(uri);

        if (!validate(json)) {
            return null;
        }

        return OBJECT_MAPPER.readValue(json, new TypeReference<>() {
        });
    }


    @Deprecated
    @Nonnull
    public ProductMedia getProductMedia(@NonNull final String sku, final long entryId) throws JsonProcessingException {

        final String uri = baseUri() + PRODUCTS_V1 + encode(sku) + "/media/" + entryId;

        final String json = getSecured(uri);

        if (!validate(json)) {
            return null;
        }

        return OBJECT_MAPPER.readValue(json, ProductMedia.class);
    }


    @Deprecated
    public boolean deleteProductMedia(String sku, long entryId) {

        final String uri = baseUri() + PRODUCTS_V1 + encode(sku) + "/media/" + entryId;

        final String json = deleteSecure(uri);

        if (!validate(json)) {
            return false;
        }

        return json.equalsIgnoreCase("true");
    }


    @Deprecated
    public String getProductMediaAbsoluteUrl(@NonNull final String sku, final long entryId) throws JsonProcessingException {
        final ProductMedia media = getProductMedia(sku, entryId);
        final String filename = media.getFile();
        return baseUri() + "/pub/media/catalog/product/" + filename;
    }

    @Deprecated
    public String getProductMediaRelativeUrl(@NonNull final String sku, final long entryId) throws JsonProcessingException {
        ProductMedia media = getProductMedia(sku, entryId);
        String filename = media.getFile();
        return "/pub/media/catalog/product/" + filename;
    }


    @Nonnull
    public List<String> getProductMediaAbsoluteUrls(@NonNull final String sku) throws JsonProcessingException {
        final List<ProductMedia> mediaList = getProductMediaList(sku);
        final List<String> result = new ArrayList<>();
        for (final ProductMedia media : mediaList) {
            final String filename = media.getFile();
            final String uri = baseUri() + "/pub/media/catalog/product/" + filename;
            result.add(uri);
        }
        return result;
    }

    @Nonnull
    public List<String> getProductMediaRelativeUrls(@NonNull final String sku) throws JsonProcessingException {
        final List<ProductMedia> mediaList = getProductMediaList(sku);
        final List<String> result = new ArrayList<>();
        for (final ProductMedia media : mediaList) {
            final String filename = media.getFile();
            final String uri = "/pub/media/catalog/product/" + filename;
            result.add(uri);
        }
        return result;
    }

    public long uploadImage(@NonNull final String sku,
                            @NonNull final String imageFilePath,
                            final boolean forceOverwrite) throws JsonProcessingException {

        final EMimeType mimeType = org.apache.commons.lang3.StringUtils.endsWithIgnoreCase(imageFilePath, ".png") ? EMimeType.PNG : EMimeType.JPEG;

        final List<ProductMedia> mediaList = getProductMediaList(sku);

        if (forceOverwrite) {
            for (int k = 0; k < mediaList.size(); ++k) {
                deleteProductMedia(sku, mediaList.get(k).getId());
            }
        }
        else {
            if (mediaList.size() > 0) {
                return -1;
            }
        }


        final String filename = "/m/b/mb-" + StringUtils.cleanup(sku) + ".png";
        final int position = 1;
        final String type = mimeType.getMagentoRepresentation();

        final String imageName = fileName(imageFilePath);

        try (final InputStream inputStream = new FileInputStream(imageFilePath);
             final ByteArrayOutputStream baos = new ByteArrayOutputStream();) {

            int length;
            byte[] bytes = new byte[1024];
            while ((length = inputStream.read(bytes, 0, 1024)) > 0) {
                baos.write(bytes, 0, length);
            }
            bytes = baos.toByteArray();

            long uploadedId = client.media().uploadProductImage(sku, position, filename, bytes, type, imageName);

            log.info("uploaded {} for product {}: {}", imageFilePath, sku, uploadedId);

            return uploadedId;
        }
        catch (IOException exception) {
            log.error("Failed to upload as image " + imageFilePath + " is not available.", exception);
        }

        return -1;
    }

    public long uploadImage(@NonNull final String sku,
                            final byte[] bytes, @NonNull final EMimeType EMimeType,
                            final boolean forceOverwrite) throws JsonProcessingException {

        final List<ProductMedia> mediaList = getProductMediaList(sku);

        if (forceOverwrite) {
            for (int k = 0; k < mediaList.size(); ++k) {
                deleteProductMedia(sku, mediaList.get(k).getId());
            }
        }
        else {
            if (mediaList.size() > 0) {
                return -1;
            }
        }

        final String filename = "/m/b/mb-" + StringUtils.cleanup(sku) + ".png";
        final int position = 1;
        final String type = EMimeType.getMagentoRepresentation();
        final String imageName = fileName(filename);

        final long uploadedId = uploadProductImage(sku, position, filename, bytes, type, imageName);

        log.info("uploaded {} for product {}: {}", filename, sku, uploadedId);

        return uploadedId;
    }

    public boolean updateImage(String sku, long entryId, String imageFilePath) {

        final EMimeType mimeType = imageFilePath.toLowerCase().endsWith(".png") ? EMimeType.PNG : EMimeType.JPEG;


        final String filename = "/m/b/mb-" + StringUtils.cleanup(sku) + ".png";
        final int position = 1;
        final String type = mimeType.getMagentoRepresentation();

        final String imageName = fileName(imageFilePath);

        try (final InputStream inputStream = new FileInputStream(imageFilePath);
             final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            int length;
            byte[] bytes = new byte[1024];
            while ((length = inputStream.read(bytes, 0, 1024)) > 0) {
                baos.write(bytes, 0, length);
            }
            bytes = baos.toByteArray();

            boolean updated = updateProductImage(sku, entryId, position, filename, bytes, type, imageName);

            log.info("updating {} for product {}: {}", imageFilePath, sku, updated);

            return updated;
        }
        catch (IOException exception) {
            log.error("Failed to upload as image " + imageFilePath + " is not available.", exception);
        }

        return false;
    }

    public boolean updateImage(String sku, long entryId, byte[] bytes, EMimeType EMimeType) {

        final String filename = "/m/b/mb-" + StringUtils.cleanup(sku) + ".png";
        final int position = 1;
        final String type = EMimeType.getMagentoRepresentation();

        final String imageName = fileName(filename);

        boolean updated = updateProductImage(sku, entryId, position, filename, bytes, type, imageName);

        log.info("uploaded {} for product {}: {}", filename, sku, updated);

        return updated;
    }

    @Nonnull
    private static String fileName(@NonNull final String input) {
        if (input.contains(File.separator)) {
            return substringAfterLast(input, File.separator);
        }
        return input;
    }
}
