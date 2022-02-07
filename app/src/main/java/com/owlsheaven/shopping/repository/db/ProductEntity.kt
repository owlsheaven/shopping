package com.owlsheaven.shopping.repository.db

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.owlsheaven.shopping.repository.api.ProductDto

@Entity(tableName = "products", indices = [Index("product_id"), Index("is_favorite")])
data class ProductEntity(
    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "id")
    var rowId: Long = 0L,

    @field:ColumnInfo(name = "is_favorite") var isFavorite:Boolean, // 좋아요 지정 여부

    @field:ColumnInfo(name = "product_id") val id:Long, // 상품 ID
    @field:ColumnInfo(name = "name") val name:String, // 상품명
    @field:ColumnInfo(name = "image") val imageUrl:String, // 이미지 링크
    @field:ColumnInfo(name = "is_new") val isNew:Boolean, // 신규 여부
    @field:ColumnInfo(name = "sell_count") val sellCount:Int, // 판매 갯수
    @field:ColumnInfo(name = "actual_price") val actualPrice:Int, // 상품 가격
    @field:ColumnInfo(name = "price") val price:Int, // 판매 가격 (할인 적용됨)
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductEntity>() {
            override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ProductEntity,
                newItem: ProductEntity
            ): Boolean {
                if (oldItem.isFavorite != newItem.isFavorite) return false
                if (!areContentsTheSameExceptFavorite(oldItem, newItem)) return false
                return true
            }

            private fun areContentsTheSameExceptFavorite(oldItem: ProductEntity,
                                                         newItem: ProductEntity
            ): Boolean {
                // changed by server
                if (oldItem.isNew != newItem.isNew) return false
                if (oldItem.price != newItem.price) return false
                if (oldItem.actualPrice != newItem.actualPrice) return false
                if (oldItem.name != newItem.name) return false
                if (oldItem.imageUrl != newItem.imageUrl) return false
                return true
            }
        }

        fun from(dto: ProductDto, isFavorite: Boolean): ProductEntity {
            return ProductEntity(
                0L,
                isFavorite,
                dto.id,
                dto.name,
                dto.imageUrl,
                dto.isNew,
                dto.sellCount,
                dto.actualPrice,
                dto.price
            )
        }
    }
}