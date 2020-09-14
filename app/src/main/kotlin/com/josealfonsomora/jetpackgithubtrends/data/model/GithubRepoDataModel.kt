package com.josealfonsomora.jetpackgithubtrends.data.model

import com.google.gson.annotations.SerializedName
import com.josealfonsomora.jetpackgithubtrends.data.persistence.database.entity.GithubRepoEntity
import com.josealfonsomora.jetpackgithubtrends.domain.model.GithubRepo

data class GithubRepoDataModel(
	@SerializedName("id") val id: Int?,
	@SerializedName("node_id") val nodeId: String?,
	@SerializedName("name") val name: String?,
	@SerializedName("full_name") val fullName: String?,
	@SerializedName("private") val private: Boolean? = false,
	@SerializedName("owner") val owner: GithubOwnerDataModel?,
	@SerializedName("html_url") val htmlUrl: String?,
	@SerializedName("description") val description: String?,
	@SerializedName("fork") val fork: Boolean? = false,
	@SerializedName("url") val url: String?,
	@SerializedName("forks_url") val forksUrl: String?,
	@SerializedName("keys_url") val keysUrlsUrl: String?,
	@SerializedName("collaborators_url") val collaboratorsUrl: String?,
	@SerializedName("teams_url") val teamsUrl: String?,
	@SerializedName("hooks_url") val hooksUrl: String?,
	@SerializedName("issue_events_url") val issueEventsUrl: String?,
	@SerializedName("events_url") val eventsUrl: String?,
	@SerializedName("assignees_url") val assigneesUrl: String?,
	@SerializedName("branches_url") val branchesUrl: String?,
	@SerializedName("tags_url") val tagsUrl: String?,
	@SerializedName("blobs_url") val blobsUrl: String?,
	@SerializedName("git_tags_url") val gitTagsUrl: String?,
	@SerializedName("git_refs_url") val gitRefsUrl: String?,
	@SerializedName("trees_url") val treesUrl: String?,
	@SerializedName("statuses_url") val statusesUrl: String?,
	@SerializedName("languages_url") val languagesUrl: String?,
	@SerializedName("stargazers_url") val stargazersUrl: String?,
	@SerializedName("contributors_url") val contributorsUrl: String?,
	@SerializedName("subscribers_url") val subscribersUrl: String?,
	@SerializedName("subscription_url") val subscriptionUrl: String?,
	@SerializedName("commits_url") val commitsUrl: String?,
	@SerializedName("git_commits_url") val gitCommitsUrl: String?,
	@SerializedName("comments_url") val commentsUrl: String?,
	@SerializedName("issue_comment_url") val issueCommenturl: String?,
	@SerializedName("contents_url") val contentsUrl: String?,
	@SerializedName("compare_url") val compareUrl: String?,
	@SerializedName("merges_url") val mergesUrl: String?,
	@SerializedName("archive_url") val archiveUrl: String?,
	@SerializedName("downloads_url") val downloadsUrl: String?,
	@SerializedName("issues_url") val issuesUrl: String?,
	@SerializedName("pulls_url") val pullsUrl: String?,
	@SerializedName("milestones_url") val milestonesUrl: String?,
	@SerializedName("notifications_url") val notificationsUrl: String?,
	@SerializedName("labels_url") val labelsUrl: String?,
	@SerializedName("releases_url") val releasesUrl: String?,
	@SerializedName("deployments_url") val deploymentsUrl: String?,
	@SerializedName("created_at") val createdAt: String?,
	@SerializedName("updated_at") val updatedAt: String?,
	@SerializedName("pushed_at") val pushedAt: String?,
	@SerializedName("git_url") val gitUrl: String?,
	@SerializedName("ssh_url") val sshUrl: String?,
	@SerializedName("clone_url") val cloneUrl: String?,
	@SerializedName("svn_url") val svnUrl: String?,
	@SerializedName("homepage") val homepage: String?,
	@SerializedName("size") val size: Int?,
	@SerializedName("stargazers_count") val stargazersCount: Int?,
	@SerializedName("watchers_count") val watchersCount: Int?,
	@SerializedName("language") val language: String?,
	@SerializedName("has_issues") val hasIssues: Boolean? = false,
	@SerializedName("has_projects") val hasProjects: Boolean? = false,
	@SerializedName("has_downloads") val hasDownloads: Boolean? = false,
	@SerializedName("has_wiki") val hasWiki: Boolean? = false,
	@SerializedName("has_pages") val hasPages: Boolean? = false,
	@SerializedName("forks_count") val forksCount: Int?,
	@SerializedName("mirror_url") val mirrorUrl: String?,
	@SerializedName("archived") val archived: Boolean? = false,
	@SerializedName("disabled") val disabled: Boolean? = false,
	@SerializedName("open_issues_count") val openIssuesCount: Int?,
	@SerializedName("license") val license: GithubLicenseDataModel?,
	@SerializedName("forks") val forks: Int?,
	@SerializedName("open_issues") val openIssues: Int?,
	@SerializedName("watchers") val watchers: Int?,
	@SerializedName("default_branch") val defaultBranch: String?,
	@SerializedName("score") val score: Int?
)


fun GithubRepoDataModel.toDomainModel() = GithubRepo(
	id = this.id,
	nodeId = this.nodeId,
	name = this.name,
	fullName = this.fullName,
	private = this.private,
	owner = this.owner?.toDomainModel(),
	htmlUrl = this.htmlUrl,
	description = this.description,
	fork = this.fork,
	url = this.url,
	forksUrl = this.forksUrl,
	keysUrlsUrl = this.keysUrlsUrl,
	collaboratorsUrl = this.collaboratorsUrl,
	teamsUrl = this.teamsUrl,
	hooksUrl = this.hooksUrl,
	issueEventsUrl = this.issueEventsUrl,
	eventsUrl = this.eventsUrl,
	assigneesUrl = this.assigneesUrl,
	branchesUrl = this.branchesUrl,
	tagsUrl = this.tagsUrl,
	blobsUrl = this.blobsUrl,
	gitTagsUrl = this.gitTagsUrl,
	gitRefsUrl = this.gitRefsUrl,
	treesUrl = this.treesUrl,
	statusesUrl = this.statusesUrl,
	languagesUrl = this.languagesUrl,
	stargazersUrl = this.stargazersUrl,
	contributorsUrl = this.contributorsUrl,
	subscribersUrl = this.subscribersUrl,
	subscriptionUrl = this.subscriptionUrl,
	commitsUrl = this.commitsUrl,
	gitCommitsUrl = this.gitCommitsUrl,
	commentsUrl = this.commentsUrl,
	issueCommentUrl = this.issueCommenturl,
	contentsUrl = this.contentsUrl,
	compareUrl = this.compareUrl,
	mergesUrl = this.mergesUrl,
	archiveUrl = this.archiveUrl,
	downloadsUrl = this.downloadsUrl,
	issuesUrl = this.issuesUrl,
	pullsUrl = this.pullsUrl,
	milestonesUrl = this.milestonesUrl,
	notificationsUrl = this.notificationsUrl,
	labelsUrl = this.labelsUrl,
	releasesUrl = this.releasesUrl,
	deploymentsUrl = this.deploymentsUrl,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
	pushedAt = this.pushedAt,
	gitUrl = this.gitUrl,
	sshUrl = this.sshUrl,
	cloneUrl = this.cloneUrl,
	svnUrl = this.svnUrl,
	homepage = this.homepage,
	size = this.size,
	stargazersCount = this.stargazersCount,
	watchersCount = this.watchersCount,
	language = this.language,
	hasIssues = this.hasIssues,
	hasProjects = this.hasProjects,
	hasDownloads = this.hasDownloads,
	hasWiki = this.hasWiki,
	hasPages = this.hasPages,
	forksCount = this.forksCount,
	mirrorUrl = this.mirrorUrl,
	archived = this.archived,
	disabled = this.disabled,
	openIssuesCount = this.openIssuesCount,
	license = this.license.toDomainModel(),
	forks = this.forks,
	openIssues = this.openIssues,
	watchers = this.watchers,
	defaultBranch = this.defaultBranch,
	score = this.score
)

fun GithubRepoDataModel.toDatabaseEntity() = GithubRepoEntity(
	id = this.id!!,
	nodeId = this.nodeId,
	name = this.name,
	fullName = this.fullName,
	isPrivate = this.private ?: false,
	owner = this.owner?.id ?: 0,
	htmlUrl = this.htmlUrl,
	description = this.description,
	fork = this.fork ?: false,
	url = this.url,
	forksUrl = this.forksUrl,
	keysUrlsUrl = this.keysUrlsUrl,
	collaboratorsUrl = this.collaboratorsUrl,
	teamsUrl = this.teamsUrl,
	hooksUrl = this.hooksUrl,
	repoIssueEventsUrl = this.issueEventsUrl,
	eventsUrl = this.eventsUrl,
	assigneesUrl = this.assigneesUrl,
	branchesUrl = this.branchesUrl,
	tagsUrl = this.tagsUrl,
	blobsUrl = this.blobsUrl,
	gitTagsUrl = this.gitTagsUrl,
	gitRefsUrl = this.gitRefsUrl,
	treesUrl = this.treesUrl,
	statusesUrl = this.statusesUrl,
	languagesUrl = this.languagesUrl,
	stargazersUrl = this.stargazersUrl,
	contributorsUrl = this.contributorsUrl,
	subscribersUrl = this.subscribersUrl,
	subscriptionUrl = this.subscriptionUrl,
	commitsUrl = this.commitsUrl,
	gitCommitsUrl = this.gitCommitsUrl,
	commentsUrl = this.commentsUrl,
	repoIssueCommentUrl = this.issueEventsUrl,
	contentsUrl = this.contentsUrl,
	compareUrl = this.compareUrl,
	mergesUrl = this.mergesUrl,
	archiveUrl = this.archiveUrl,
	downloadsUrl = this.downloadsUrl,
	repoIssuesUrl = this.issuesUrl,
	pullsUrl = this.pullsUrl,
	milestonesUrl = this.milestonesUrl,
	notificationsUrl = this.notificationsUrl,
	labelsUrl = this.labelsUrl,
	releasesUrl = this.releasesUrl,
	deploymentsUrl = this.deploymentsUrl,
	createdAt = this.createdAt,
	updatedAt = this.updatedAt,
	pushedAt = this.pushedAt,
	gitUrl = this.gitUrl,
	sshUrl = this.sshUrl,
	cloneUrl = this.cloneUrl,
	svnUrl = this.svnUrl,
	homepage = this.homepage,
	size = this.size ?: 0,
	stargazersCount = this.stargazersCount ?: 0,
	watchersCount = this.watchersCount ?: 0,
	language = this.language,
	hasIssues = this.hasIssues ?: false,
	hasProjects = this.hasProjects ?: false,
	hasDownloads = this.hasDownloads ?: false,
	hasWiki = this.hasWiki ?: false,
	hasPages = this.hasPages ?: false,
	forksCount = this.forksCount ?: 0,
	mirrorUrl = this.mirrorUrl,
	archived = this.archived ?: false,
	disabled = this.disabled ?: false,
	openIssuesCount = this.openIssuesCount ?: 0,
	license = this.license?.key,
	forks = this.forks ?: 0,
	openIssues = this.openIssues ?: 0,
	watchers = this.watchers ?: 0,
	defaultBranch = this.defaultBranch,
	score = this.score ?: 0,
)