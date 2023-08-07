package www.rahagloball.loginregkotapp.models.cashfrii

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymentSessionId {
    @SerializedName("cf_order_id")
    @Expose
    var cfOrderId: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("customer_details")
    @Expose
    var customerDetails: CustomerDetails? = null

    @SerializedName("entity")
    @Expose
    var entity: String? = null

    @SerializedName("order_amount")
    @Expose
    var orderAmount: String? = null

    @SerializedName("order_currency")
    @Expose
    var orderCurrency: String? = null

    @SerializedName("order_expiry_time")
    @Expose
    var orderExpiryTime: String? = null

    @SerializedName("order_id")
    @Expose
    var orderId: String? = null

    @SerializedName("order_meta")
    @Expose
    var orderMeta: OrderMeta? = null

    @SerializedName("order_note")
    @Expose
    var orderNote: String? = null

    @SerializedName("order_splits")
    @Expose
    var orderSplits: List<String>? = null

    @SerializedName("order_status")
    @Expose
    var orderStatus: String? = null

    @SerializedName("order_tags")
    @Expose
    var orderTags: String? = null

    @SerializedName("payment_session_id")
    @Expose
    var paymentSessionId: String? = null

    @SerializedName("payments")
    @Expose
    var payments: Payments? = null

    @SerializedName("refunds")
    @Expose
    var refunds: Refunds? = null

    @SerializedName("settlements")
    @Expose
    var settlements: Settlements? = null

    @SerializedName("terminal_data")
    @Expose
    var terminalData: String? = null
}