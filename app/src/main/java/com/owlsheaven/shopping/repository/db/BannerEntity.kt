package com.owlsheaven.shopping.repository.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.owlsheaven.shopping.repository.api.BannerDto

@Entity(tableName = "banners", indices = [Index("product_id")])
data class BannerEntity(
    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "id")
    var rowId: Long = 0L,

    @field:ColumnInfo(name = "product_id") val id:Long, // 상품 ID
    @field:ColumnInfo(name = "image") val imageUrl:String, // 이미지 링크
) {
    companion object {
        fun from (dto: BannerDto): BannerEntity {
            return BannerEntity(
                0L,
                dto.id,
                dto.imageUrl
            )
        }
    }
}