package www.rahagloball.loginregkotapp.models.mysinglecrs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MySingleCourse {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("course_id")
    @Expose
    var courseId: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("instructor_id")
    @Expose
    var instructorId: String? = null

    @SerializedName("order_id")
    @Expose
    var orderId: Any? = null

    @SerializedName("transaction_id")
    @Expose
    var transactionId: String? = null

    @SerializedName("payment_method")
    @Expose
    var paymentMethod: String? = null

    @SerializedName("total_amount")
    @Expose
    var totalAmount: String? = null

    @SerializedName("coupon_discount")
    @Expose
    var couponDiscount: Any? = null

    @SerializedName("currency")
    @Expose
    var currency: String? = null

    @SerializedName("currency_icon")
    @Expose
    var currencyIcon: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("duration")
    @Expose
    var duration: Any? = null

    @SerializedName("enroll_start")
    @Expose
    var enrollStart: Any? = null

    @SerializedName("enroll_expire")
    @Expose
    var enrollExpire: Any? = null

    @SerializedName("instructor_revenue")
    @Expose
    var instructorRevenue: Any? = null

    @SerializedName("bundle_id")
    @Expose
    var bundleId: Any? = null

    @SerializedName("bundle_course_id")
    @Expose
    var bundleCourseId: Any? = null

    @SerializedName("proof")
    @Expose
    var proof: Any? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("sale_id")
    @Expose
    var saleId: Any? = null

    @SerializedName("price_id")
    @Expose
    var priceId: Any? = null

    @SerializedName("subscription_id")
    @Expose
    var subscriptionId: Any? = null

    @SerializedName("customer_id")
    @Expose
    var customerId: Any? = null

    @SerializedName("subscription_status")
    @Expose
    var subscriptionStatus: Any? = null

    @SerializedName("refunded")
    @Expose
    var refunded: String? = null

    @SerializedName("courses")
    @Expose
    var courses: MyoneCourses? = null
}