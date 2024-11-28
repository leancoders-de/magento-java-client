package de.leancoders.magento.common.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
public class StockItem {

    @JsonProperty("item_id")
    private Integer itemId;
    @JsonProperty("product_id")
    private Integer productId;
    @JsonProperty("stock_id")
    private Integer stockId;

    @JsonProperty("backorders")
    private int backorders;
    @JsonProperty("enable_qty_increments")
    private boolean enableQtyIncrements;
    @JsonProperty("is_decimal_divided")
    private boolean flagDecimalDivided;
    @JsonProperty("is_in_stock")
    private boolean flagInStock;
    @JsonProperty("is_qty_decimal")
    private boolean flagQtyDecimal;
    @JsonProperty("low_stock_date")
    private LocalDate lowStockDate;

    @JsonProperty("manage_stock")
    private boolean flagManageStock;
    // Maximum Qty Allowed in Shopping Cart data wrapper
    @JsonProperty("max_sale_qty")
    private BigDecimal maxSaleQuantity;
    // Minimal quantity available for item status in stock
    @JsonProperty("min_qty")
    private BigDecimal minStockStateQuantity;
    // Minimum Qty Allowed in Shopping Cart or NULL when there is no limitation
    @JsonProperty("min_sale_qty")
    private BigDecimal minSaleQuantity;
    // Notify for Quantity Below data wrapper
    @JsonProperty("notify_stock_qty")
    private BigDecimal notifyStockQuantity;
    @JsonProperty("qty")
    private BigDecimal qty;
    // Quantity Increments data wrapper
    @JsonProperty("qty_increments")
    private BigDecimal qtyIncrements;
    //
    @JsonProperty("show_default_notification_message")
    private boolean flagShowDefaultNotificationMessage;

    //
    @JsonProperty("stock_status_changed_auto")
    private int stockStatusChangedAuto;

    @JsonProperty("use_config_backorders")
    private boolean flagUseConfigBackorders;
    @JsonProperty("use_config_manage_stock")
    private boolean flagUseConfigManageStock;
    @JsonProperty("use_config_max_sale_qty")
    private boolean flagUseConfigMaxSaleQty;
    @JsonProperty("use_config_min_qty")
    private boolean flagUseConfigMinQty;
    @JsonProperty("use_config_min_sale_qty")
    private int useConfigMinSaleQty;
    @JsonProperty("use_config_notify_stock_qty")
    private boolean flagUseConfigNotifyStockQty;
    @JsonProperty("use_config_qty_increments")
    private boolean flagUseConfigQtyIncrements;

    @JsonProperty("extension_attributes")
    private Map<String, Object> extensionAttributes;

}
