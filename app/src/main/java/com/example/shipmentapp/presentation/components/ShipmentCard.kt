package com.example.shipmentapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shipmentapp.models.Shipment
import com.example.shipmentapp.models.ShipmentStatus
import com.example.shipmentapp.models.sampleShipments
import com.example.shipmentapp.ui.theme.ShipmentAppTheme

@Composable
fun ShipmentCard(
    modifier: Modifier = Modifier,
    shipment: Shipment
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Column(
            modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Header Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Shipment Number",
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = shipment.shipmentNumber,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Black,
                    )
                }


                // Package Icon
                Box(
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalShipping,
                        contentDescription = "Package",
                        modifier = Modifier.size(40.dp),
                        tint = Color(0xFFD97706)
                    )
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .offset(x = 8.dp, y = (-4).dp)
                            .background(
                                Color.Black,
                                RoundedCornerShape(4.dp)
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .background(
                                    Color(0xFFFBBF24),
                                    CircleShape
                                )
                                .align(Alignment.Center)
                        )
                    }
                }
            }

            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))

            // todo: Replace icons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                Color(0xFFFED7AA),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(
                                    Color(0xFFF97316),
                                    RoundedCornerShape(4.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Sender",
                                modifier = Modifier.size(16.dp),
                                tint = Color.White
                            )
                        }
                    }

                    Column {
                        Text(
                            text = "Sender",
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        )
                        Text(
                            text = "${shipment.senderCity}, ${shipment.senderCode}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Time",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(
                                    Color(0xFF10B981),
                                    CircleShape
                                )
                        )
                        Text(
                            text = shipment.timeRange,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

//            Spacer(modifier= Modifier.height(4.dp))

            // Receiver Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                Color(0xFFD1FAE5),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(
                                    Color(0xFF10B981),
                                    RoundedCornerShape(4.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Place,
                                contentDescription = "Receiver",
                                modifier = Modifier.size(16.dp),
                                tint = Color.White
                            )
                        }
                    }

                    Column {
                        Text(
                            text = "Receiver",
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        )
                        Text(
                            text = "${shipment.receiverCity}, ${shipment.receiverCode}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Status",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    )
                    Text(
                        text = if (shipment.status == ShipmentStatus.PENDING) "Waiting To Collect" else "In Transit",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Column {

                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))

                Spacer(modifier = Modifier.height(8.dp))

                // Add Stop Button
                Button(
                    onClick = { /* Handle add stop */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier.size(20.dp),
                        tint = Color(0xFFF97316)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Add Stop",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFF97316)
                        )
                    )
                }
            }
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun ShipmentCardPreview() {
    ShipmentAppTheme {
        ShipmentCard(
            shipment = sampleShipments[0]
        )
    }
}