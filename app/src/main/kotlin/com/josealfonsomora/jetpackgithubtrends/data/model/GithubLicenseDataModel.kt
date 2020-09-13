package com.josealfonsomora.jetpackgithubtrends.data.model

import com.google.gson.annotations.SerializedName
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubLicense

data class GithubLicenseDataModel(
    @SerializedName("key") val key: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("spdx_id") val spdxId: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("node_id") val nodeId: String?
)

fun GithubLicenseDataModel?.toDomainModel() = this?.let {
    GithubLicense(
        key = this.key,
        name = this.name,
        spdxId = this.spdxId,
        url = this.url,
        nodeId = this.nodeId
    )
}
