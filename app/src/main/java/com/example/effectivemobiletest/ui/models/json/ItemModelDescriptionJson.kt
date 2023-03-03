package com.example.effectivemobiletest.ui.models.json

data class ItemModelDescriptionJson(
    var name: String? = null,
    var description: String? = null,
    var rating: Double? = null,
    var numberOfReviews: Int? = null,
    var price: Int? = null,
    var colors: ArrayList<String> = arrayListOf(),
    var imageUrls: ArrayList<String> = arrayListOf()
)
