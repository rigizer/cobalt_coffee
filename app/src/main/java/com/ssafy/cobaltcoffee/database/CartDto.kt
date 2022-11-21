package com.ssafy.cobaltcoffee.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartDto(
    var userId : String = "userId",
    var productId: Int = 0,
    var quantity: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
//    @NonNull
    var id: Long = 0

    constructor(id:Long, userId:String, productId:Int, quantity: Int): this(userId, productId, quantity){
        this.id = id;
    }
}