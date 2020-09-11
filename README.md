# jetpack-github-trends

Android Jetpack example - List of trending Github repositories of kotlin

### Github API

This project will use `Search` Github API to request Kotlin repositories

(Search API)[https://docs.github.com/en/rest/reference/search#constructing-a-search-query]

Notes:
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
