package com.kaveesha.backend.model;

import lombok.Data;

// @Data is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the methods in this class.
@Data
public class Vendor {

    // Field to uniquely identify a vendor
    private Long vendorId;
}
