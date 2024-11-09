package com.project.ims.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/*
CREATE TABLE Partner (
    partnerID INT PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(255),
    contactNumber VARCHAR(20)
);
 */

@Data
@Entity
@Table(name = "partner")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partnerID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "contactNumber", nullable = false)
    private String contactNumber;

    @OneToMany(mappedBy = "partner",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Export> exports;

}
