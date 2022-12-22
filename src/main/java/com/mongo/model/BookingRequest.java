package com.mongo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookings")
public class BookingRequest {

    @Id
    //@Field("bookingId")
    private String bookingId;
    private String origin;
    private String destination;
    private String date;
    private String shipper;
    private String consignee;
}
