package www.rahagloball.loginregkotapp.models.crseinfonew

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class WhatlearnsCi : Serializable {
    @SerializedName("course_id")
    var courseId: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("detail")
    var detail: String? = null

    @SerializedName("status")
    var status: String? = null
}