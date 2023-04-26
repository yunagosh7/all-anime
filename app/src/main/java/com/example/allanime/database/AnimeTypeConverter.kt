package com.example.allanime.database

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class AnimeTypeConverter {

    @TypeConverter
    fun fromAnyToString(attribute: Any?): String {
        return if (attribute == null) {
            ""
        } else attribute.toString()
    }

    @TypeConverter
    fun fromStringToAny(attribute: String?): Any {
        return if (attribute == null) {
            ""
        } else attribute as Any
    }


}