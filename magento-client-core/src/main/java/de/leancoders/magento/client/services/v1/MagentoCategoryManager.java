package de.leancoders.magento.client.services.v1;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import de.leancoders.magento.client.MagentoClient;
import de.leancoders.magento.common.model.category.Category;
import de.leancoders.magento.common.model.category.CategoryProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 */
public class MagentoCategoryManager extends MagentoHttpComponent {
    private static final String relativePath4Categories = "rest/V1/categories";
    private final MagentoClient client;

    public MagentoCategoryManager(MagentoClient client) {
        super(client.getHttpComponent());
        this.client = client;
    }

    public boolean deleteCategory(long categoryId) {
        String url = baseUri() + "/" + relativePath4Categories + "/" + categoryId;
        String json = deleteSecure(url);
        if (!validate(json)) {
            return false;
        }
        return json.equalsIgnoreCase("true");
    }

    public long addCategory(Category category) throws JsonProcessingException {
        Map<String, Object> cat = new HashMap<>();
        cat.put("id", category.getId());
        cat.put("parent_id", category.getParentId());
        cat.put("name", category.getName());
        cat.put("is_active", category.isActive());
        cat.put("position", category.getPosition());
        cat.put("level", category.getLevel());
        cat.put("children", "string");
        cat.put("include_in_menu", true);
        cat.put("available_sort_by", new ArrayList<>());
        cat.put("extension_attributes", new ArrayList<>());
        cat.put("custom_attributes", new ArrayList<>());
        Map<String, Object> req = new HashMap<>();
        req.put("category", cat);
        String url = baseUri() + "/" + relativePath4Categories;

        final String body = OBJECT_MAPPER.writeValueAsString(req);
        String json = postSecure(url, body);

        if (!validate(json)) {
            return -1;
        }
        return Long.parseLong(json);
    }

    public boolean updateCategory(Category category) throws JsonProcessingException {
        Map<String, Object> cat = new HashMap<>();
        cat.put("id", category.getId());
        cat.put("parent_id", category.getParentId());
        cat.put("name", category.getName());
        cat.put("is_active", category.isActive());
        cat.put("position", category.getPosition());
        cat.put("level", category.getLevel());
        cat.put("children", "string");
        cat.put("include_in_menu", true);
        cat.put("available_sort_by", new ArrayList<>());
        cat.put("extension_attributes", new ArrayList<>());
        cat.put("custom_attributes", new ArrayList<>());
        Map<String, Object> req = new HashMap<>();
        req.put("category", cat);
        String url = baseUri() + "/" + relativePath4Categories + "/" + category.getId();

        String body = OBJECT_MAPPER.writeValueAsString(req);
        String json = postSecure(url, body);

        if (!validate(json)) {
            return false;
        }
        return json.equalsIgnoreCase("true");
    }

    public Category all() throws JsonProcessingException {
        int pageIndex = 0;
        int pageSize = 1000;
        String uri = baseUri() + "/" + relativePath4Categories
            + "?searchCriteria[currentPage]=" + pageIndex
            + "&searchCriteria[pageSize]=" + pageSize;
        String json = getSecured(uri);
        if (!validate(json)) {
            return null;
        }

        return OBJECT_MAPPER.readValue(json, Category.class);
    }

    public Category getCategoryByIdClean(long id) throws JsonProcessingException {
        String uri = baseUri() + "/" + relativePath4Categories + "/" + id;
        return getCategoryByUrl(uri);
    }

    public Category getRootCategoryById(long id) throws JsonProcessingException {
        String uri = baseUri() + "/" + relativePath4Categories + "?rootCategoryId=" + id;
        return getCategoryByUrl(uri);
    }

    private Category getCategoryByUrl(String uri) throws JsonProcessingException {
        String json = getSecured(uri);
        if (!validate(json)) {
            return null;
        }
        return OBJECT_MAPPER.readValue(json, Category.class);
    }

    public Category getCategoryByIdWithChildren(long id) throws JsonProcessingException {
        Category all = all();
        return getCategoryById(all, id);
    }

    private Category getCategoryById(Category x, long id) {
        if (x.getId() == id) {
            return x;
        }
        for (Category child : x.getChildrenData()) {
            Category x_ = getCategoryById(child, id);
            if (x_ != null) {
                return x_;
            }
        }
        return null;
    }

    public List<CategoryProduct> getProductsInCategory(long id) throws JsonProcessingException {
        String uri = baseUri() + "/" + relativePath4Categories + "/" + id + "/products";
        String json = getSecured(uri);

        if (!validate(json)) {
            return null;
        }

        return OBJECT_MAPPER.readValue(json, new TypeReference<>() {
        });
    }

    public boolean addProductToCategory(long categoryId, String productSku, int position) throws JsonProcessingException {
        String uri = baseUri() + "/" + relativePath4Categories + "/" + categoryId + "/products";
        Map<String, Object> req = new HashMap<>();
        Map<String, Object> detail = new HashMap<>();
        detail.put("sku", productSku);
        detail.put("position", position);
        detail.put("category_id", categoryId);
        detail.put("extension_attributes", new HashMap<>());
        req.put("productLink", detail);
        String body = OBJECT_MAPPER.writeValueAsString(req);
        String json = putSecure(uri, body);

        return json.equals("true");
    }


    @Override
    public String token() {
        return client.token();
    }


    @Override
    public String baseUri() {
        return client.baseUri();
    }


    public boolean removeProductFromCategory(long categoryId, String productSku) {
        String uri = baseUri() + "/" + relativePath4Categories + "/" + categoryId + "/products/" + productSku;

        String json = deleteSecure(uri);
        return json.equals("true");
    }
}
