package com.josealfonsomora.jetpackgithubtrends.commons

import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter

fun String.toZonedDateTime(zone: ZoneId): ZonedDateTime =
    LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME).atZone(zone)
