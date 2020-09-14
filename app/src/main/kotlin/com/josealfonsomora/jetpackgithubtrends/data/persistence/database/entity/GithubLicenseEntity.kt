package com.josealfonsomora.jetpackgithubtrends.data.persistence.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubLicense

@Entity(tableName = "license")
data class GithubLicenseEntity(
    @PrimaryKey var key: String,
    var name: String?,
    var spdxId: String?,
    var url: String?,
    var nodeId: String?
)

fun GithubLicenseEntity.toDomainModel() = GithubLicense(
    key = this.key,
    name = this.name,
    spdxId = this.spdxId,
    url = this.url,
    nodeId = this.nodeId
)
