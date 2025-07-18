package com.example.shipmentapp.models

import com.example.shipmentapp.R

data class Shipment(
    val senderCity: String,
    val senderCode: String,
    val receiverCity: String,
    val receiverCode: String,
    val timeRange: String,
    val status: String,
    val shipmentNumber: String
)

data class Vehicles (
    val vehicleType: String,
    val desc: String,
    val image: Int
)

data class User(
    val name: String,
    val avatar: Int,
    val location: String
)

data class Order(
    val itemName: String,
    val trackingNumber: String,
    val pickupLocation: String,
    val dropOffLocation: String
)

val sampleOrders = listOf(
    Order(
        itemName = "Shirt",
        trackingNumber = "123456789",
        pickupLocation = "Atlanta",
        dropOffLocation = "Chicago"
    ),
    Order(
        itemName = "Shoes",
        trackingNumber = "987654321",
        pickupLocation = "New York",
        dropOffLocation = "Los Angeles",
        ),
    Order(
        itemName = "Jacket",
        trackingNumber = "456789123",
        pickupLocation = "San Francisco",
        dropOffLocation = "Miami"
    )
)

val sampleCard = Shipment(
    senderCity = "Atlanta",
    senderCode = "5243",
    receiverCity = "Chicago",
    receiverCode = "6342",
    timeRange = "2 day - 3 days",
    status = "Waiting to collect",
    shipmentNumber = "123456789"
)


val vehiclesList = listOf(
    Vehicles("Ocean freight", "International", R.drawable.truck_img),
    Vehicles("Air freight", "International", R.drawable.truck_img),
    Vehicles("Road freight", "Local", R.drawable.truck_img)
)

val sampleUser = User(
    name = "John Doe",
    avatar = R.drawable.images,
    location = "Wertheimer, Illinois"
)