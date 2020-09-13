package com.josealfonsomora.jetpackgithubtrends.data.model

import com.google.gson.annotations.SerializedName
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubOwner

data class GithubOwnerDataModel(
	@SerializedName("id") val id: Int,
	@SerializedName("login") val login: String,
	@SerializedName("node_id") val nodeId: String,
	@SerializedName("avatar_url") val avatarUrl: String,
	@SerializedName("gravatar_id") val gravatarId: String,
	@SerializedName("url") val url: String,
	@SerializedName("html_url") val htmlUrl: String,
	@SerializedName("followers_url") val followersUrl: String,
	@SerializedName("following_url") val followingUrl: String,
	@SerializedName("gists_url") val gistsUrl: String,
	@SerializedName("starred_url") val starredUrl: String,
	@SerializedName("subscriptions_url") val subscriptionsUrl: String,
	@SerializedName("organizations_url") val organizationsUrl: String,
	@SerializedName("repos_url") val reposUrl: String,
	@SerializedName("events_url") val eventsUrl: String,
	@SerializedName("received_events_url") val receivedEventsUrl: String,
	@SerializedName("type") val type: String,
	@SerializedName("site_admin") val siteAdmin: Boolean
)

fun GithubOwnerDataModel.toDomainModel() = GithubOwner(
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
