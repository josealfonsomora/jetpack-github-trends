package com.josealfonsomora.jetpackgithubtrends.commons

import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

fun String.toZonedDateTime(zone: ZoneId): ZonedDateTime =
    LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME).atZone(zone)

fun Int?.toReadableK(): String = this?.let { number ->
    when {
        number in 1_000..999_999 -> String.format("%.1fK", number / 1000.0)
        number > 999_999 -> String.format("%.1fM", number / 1000000.0)
        else -> number.toString()
    }
} ?: "0"
