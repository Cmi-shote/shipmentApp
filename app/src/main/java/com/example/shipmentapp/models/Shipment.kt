package com.example.shipmentapp.models

import com.example.shipmentapp.R

data class Shipment(
    val senderCity: String,
    val senderCode: String,
    val receiverCity: String,
    val receiverCode: String,
    val timeRange: String,
    val status: ShipmentStatus,
    val shipmentNumber: String,
    val date: String,
    val description: String,
    val trackingNumber: String,
    val origin: String,
    val amount: String,
    val title: String
)

enum class ShipmentStatus {
    LOADING, IN_PROGRESS, COMPLETED, PENDING
}

data class Vehicles(
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
    val orderNumber: String,
    val pickupLocation: String,
    val dropOffLocation: String
)

data class TabItem(
    val title: String,
    val count: Int
)

val sampleOrders = listOf(
    Order(
        itemName = "Shirt",
        orderNumber = "123456789",
        pickupLocation = "Atlanta",
        dropOffLocation = "Chicago"
    ),
    Order(
        itemName = "Shoes",
        orderNumber = "987654321",
        pickupLocation = "New York",
        dropOffLocation = "Los Angeles",
    ),
    Order(
        itemName = "Jacket",
        orderNumber = "456789123",
        pickupLocation = "San Francisco",
        dropOffLocation = "Miami"
    )
)

val sampleShipments = listOf(
    Shipment(
        status = ShipmentStatus.IN_PROGRESS,
        title = "Arriving today!",
        description = "Your delivery, #NEJ200899341222231\nfrom Atlanta, is arriving today!",
        amount = "\$1400 USD",
        date = "Sep 20, 2023",
        trackingNumber = "NEJ200899341222231",
        origin = "Atlanta",
        receiverCity = "Chicago",
        receiverCode = "6342",
        timeRange = "2 day - 3 days",
        senderCity = "Atlanta",
        senderCode = "5243",
        shipmentNumber = "NEJ200899341222231"
    ),
    Shipment(
        status = ShipmentStatus.PENDING,
        title = "Arriving today!",
        description = "Your delivery, #NEJ200899341222231\nfrom Atlanta, is arriving today!",
        amount = "\$650 USD",
        date = "Sep 20, 2023",
        trackingNumber = "NEJ200899341222231",
        origin = "Atlanta",
        receiverCity = "Chicago",
        receiverCode = "6342",
        timeRange = "2 day - 3 days",
        senderCity = "Atlanta",
        senderCode = "5243",
        shipmentNumber = "NEJ200899341222231"
    ),
    Shipment(
        status = ShipmentStatus.PENDING,
        title = "Arriving today!",
        description = "Your delivery, #NEJ200899341222231\nfrom Atlanta, is arriving today!",
        amount = "\$650 USD",
        date = "Sep 20, 2023",
        trackingNumber = "NEJ200899341222231",
        origin = "Atlanta",
        receiverCity = "Chicago",
        receiverCode = "6342",
        timeRange = "2 day - 3 days",
        senderCity = "Atlanta",
        senderCode = "5243",
        shipmentNumber = "NEJ200899341222231"
    ),
    Shipment(
        status = ShipmentStatus.LOADING,
        title = "Arriving today!",
        description = "Your delivery, #NEJ200899341222231\nfrom Atlanta, is arriving today!",
        amount = "\$650 USD",
        date = "Sep 20, 2023",
        trackingNumber = "NEJ200899341222231",
        origin = "Atlanta",
        receiverCity = "Chicago",
        receiverCode = "6342",
        timeRange = "2 day - 3 days",
        senderCity = "Atlanta",
        senderCode = "5243",
        shipmentNumber = "NEJ200899341222231"
    ),
    Shipment(
        status = ShipmentStatus.IN_PROGRESS,
        title = "Arriving today!",
        description = "Your delivery, #NEJ200899341222231\nfrom Atlanta, is arriving today!",
        amount = "\$1400 USD",
        date = "Sep 20, 2023",
        trackingNumber = "NEJ200899341222231",
        origin = "Atlanta",
        receiverCity = "Chicago",
        receiverCode = "6342",
        timeRange = "2 day - 3 days",
        senderCity = "Atlanta",
        senderCode = "5243",
        shipmentNumber = "NEJ200899341222231"
    ),
    Shipment(
        status = ShipmentStatus.IN_PROGRESS,
        title = "Arriving today!",
        description = "Your delivery, #NEJ200899341222231\nfrom Atlanta, is arriving today!",
        amount = "\$370 USD",
        date = "Sep 20, 2023",
        trackingNumber = "NEJ200899341222231",
        origin = "Atlanta",
        receiverCity = "Chicago",
        receiverCode = "6342",
        timeRange = "2 day - 3 days",
        senderCity = "Atlanta",
        senderCode = "5243",
        shipmentNumber = "NEJ200899341222231"
    ),
    Shipment(
        status = ShipmentStatus.IN_PROGRESS,
        title = "Arriving today!",
        description = "Your delivery, #NEJ200899341222231\nfrom Atlanta, is arriving today!",
        amount = "\$3570 USD",
        date = "Sep 20, 2023",
        trackingNumber = "NEJ200899341222231",
        origin = "Atlanta",
        receiverCity = "Chicago",
        receiverCode = "6342",
        timeRange = "2 day - 3 days",
        senderCity = "Atlanta",
        senderCode = "5243",
        shipmentNumber = "NEJ200899341222231"
    )
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