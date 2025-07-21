package com.example.shipmentapp.models

import com.example.shipmentapp.R

/**
 * This file contains the data model definitions and sample data for the Shipment Tracking application.
 *
 * It includes:
 * - [Shipment]: Data class representing a single shipment with various details like sender/receiver info,
 * status, tracking number, and financial details.
 * - [ShipmentStatus]: Enum class defining the possible states a shipment can be in (e.g., LOADING, IN_PROGRESS).
 * - [Vehicles]: Data class for vehicle types used in shipments, including an image resource.
 * - [User]: Data class for user information, including name, avatar, and location.
 * - [Order]: Data class for order details, used in the search functionality, containing item name,
 * order number, and pickup/drop-off locations.
 * - [TabItem]: Data class used for defining the tabs in the Shipment History screen, including title,
 * count of items in that category, and the associated [ShipmentStatus].
 *
 * It also provides sample data lists for:
 * - [sampleOrders]: A list of predefined [Order] objects for demonstration and testing the search feature.
 * - [tabs]: A list of [TabItem]s representing the available categories for shipment filtering.
 * - [sampleShipments]: A comprehensive list of predefined [Shipment] objects used for displaying
 * shipment history and testing filtering by status.
 * - [vehiclesList]: A list of [Vehicles] for demonstration.
 * - [sampleUser]: A single [User] object for demonstration.
 */
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
    LOADING, IN_PROGRESS, COMPLETED, PENDING, ALL, CANCELLED
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
    val count: Int,
    val status: ShipmentStatus
)

val sampleOrders = listOf(
    Order(
        itemName = "Mackbook pro M2",
        orderNumber = "#NEA43857340857904",
        pickupLocation = "Paris",
        dropOffLocation = "Morocco"
    ),
    Order(
        itemName = "Summer linen jacket",
        orderNumber = "#NEJ20089934122231",
        pickupLocation = "Barcelona",
        dropOffLocation = "Paris",
    ),
    Order(
        itemName = "Tapered-fit jeans AW",
        orderNumber = "#NEJ35870264978659",
        pickupLocation = "Columbia",
        dropOffLocation = "Paris"
    ),
    Order(
        itemName = "Slim fit jeans AW",
        orderNumber = "#NEJ35870264978659",
        pickupLocation = "Bogota",
        dropOffLocation = "Dhaka"
    ),
    Order(
        itemName = "Office setup desk",
        orderNumber = "#NEJ23481570754963",
        pickupLocation = "France",
        dropOffLocation = "German"
    )
)

val tabs = listOf(
    TabItem("All", 12, ShipmentStatus.ALL),
    TabItem("Completed", 5, ShipmentStatus.COMPLETED),
    TabItem("In progress", 3, ShipmentStatus.IN_PROGRESS),
    TabItem("Pending Order", 4, ShipmentStatus.PENDING),
    TabItem("Cancelled", 0, ShipmentStatus.CANCELLED)
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
        status = ShipmentStatus.COMPLETED,
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
    ),
    Shipment(
        status = ShipmentStatus.COMPLETED,
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
    ),
    Shipment(
        status = ShipmentStatus.COMPLETED,
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
    ),
    Shipment(
        status = ShipmentStatus.COMPLETED,
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
    ),
    Shipment(
        status = ShipmentStatus.COMPLETED,
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
)


val vehiclesList = listOf(
    Vehicles("Ocean freight", "International", R.drawable.boat),
    Vehicles("Cargo freight", "Reliable", R.drawable.truck),
    Vehicles("Road freight", "Local", R.drawable.truck)
)

val sampleUser = User(
    name = "John Doe",
    avatar = R.drawable.images,
    location = "Wertheimer, Illinois"
)