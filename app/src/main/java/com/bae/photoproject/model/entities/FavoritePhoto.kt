package com.bae.photoproject.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_photos_table")
data class FavoritePhoto(
    @ColumnInfo val previewPhoto: String,
    @ColumnInfo val originPhoto: String,
    @ColumnInfo val photographer: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
