package com.josealfonsomora.jetpackgithubtrends.data.persistence.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubOwner

@Entity(tableName = "owner")
data class GithubOwnerEntity(
    @PrimaryKey var id: Int,
    var login: String,
    var nodeId: String,
    var avatarUrl: String,
    var gravatarId: String,
    var url: String,
    var htmlUrl: String,
    var followersUrl: String,
    var followingUrl: String,
    var gistsUrl: String,
    var starredUrl: String,
    var subscriptionsUrl: String,
    var organizationsUrl: String,
    var reposUrl: String,
    var eventsUrl: String,
    var receivedEventsUrl: String,
    var type: String,
    var siteAdmin: Boolean
)

fun GithubOwnerEntity.toDomainModel() = GithubOwner(
    id = this.id,
    login = this.login,
    nodeId = this.nodeId,
    avatarUrl = this.avatarUrl,
    gravatarId = this.gravatarId,
    url = this.url,
    htmlUrl = this.htmlUrl,
    followersUrl = this.followersUrl,
    followingUrl = this.followingUrl,
    gistsUrl = this.gistsUrl,
    starredUrl = this.starredUrl,
    subscriptionsUrl = this.subscriptionsUrl,
    organizationsUrl = this.organizationsUrl,
    reposUrl = this.reposUrl,
    eventsUrl = this.eventsUrl,
    receivedEventsUrl = this.receivedEventsUrl,
    type = this.type,
    siteAdmin = this.siteAdmin
)
