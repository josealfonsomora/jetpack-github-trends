# Jetpack Github Trends

Android Jetpack example - List of trending Github repositories of kotlin

![Build unit tests](https://github.com/josealfonsomora/jetpack-github-trends/workflows/Build%20unit%20tests/badge.svg?branch=master&event=push)

### Github API

### Architecture and Dependencies

This project is built with Android recommended architecture plus clean architecture following the separation of concerns principle to 
make it more testable and maintainable.
If we were dealing with a bigger project we could think about separating layers (data, domain, ui) into different modules, for example, `Domain` layer
does not need android dependencies and we could make a reusable module extracting it into a kotlin module

#### Clean code architecture 

![Screenshot 2020-09-12 at 18 41 29](https://user-images.githubusercontent.com/793226/93000766-c7ef4600-f52a-11ea-80f6-ac1155747e3f.png)

#### MVVM - Databinding

[MVVM](https://developer.android.com/jetpack/guide)

[Data Binding](https://developer.android.com/jetpack/androidx/releases/databinding)

### Jetpack libraries used

This project is using latest Jetpack libraries even in alpha, like Paging 3 or DataStore. I consider that both libraries are mature enough and small 
enough to be released to production

[Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

[Navigation](https://developer.android.com/guide/navigation)
 
[Room](https://developer.android.com/topic/libraries/architecture/room?hl=en)

[DataStore](https://developer.android.com/jetpack/androidx/releases/datastore)

[Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)

[Arch Core](https://developer.android.com/jetpack/androidx/releases/arch-core)

#### External Dependencies

[Retrofit](https://square.github.io/retrofit/) 

[Okhttp](https://square.github.io/okhttp/)

[Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
 
[Glide](https://github.com/bumptech/glide)

[Glide Transformations](https://github.com/wasabeef/glide-transformations)

[Timber](https://github.com/JakeWharton/timber)

[LeakCanary](https://square.github.io/leakcanary/)

[ThreeTenABP](https://github.com/JakeWharton/ThreeTenABP)

#### Github API

This project will use `Search` Github API to request Kotlin repositories

[Search API](https://docs.github.com/en/rest/reference/search#constructing-a-search-query)

Notes to consider:
```
To access the API you must provide a custom media type in the Accept header:
   
   application/vnd.github.cloak-preview
   ☝️ This header is required.
```
```
The GitHub Search API provides up to 1,000 results for each search.
```
```
The Search API has a custom rate limit. For requests using Basic Authentication, OAuth, or client ID and secret, you can make up to 30 requests per minute. For unauthenticated requests, the rate limit allows you to make up to 10 requests per minute.
```

To get Trending repositories we are querying Github Rest API sorting by `stars` That give us most starred repositories from all the time. 
```
https://api.github.com/search/repositories?q=language:kotlin&sort=stars&per_page=10&page=1
```
