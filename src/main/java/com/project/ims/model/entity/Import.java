package com.project.ims.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "import")
public class Import {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int importID;

    @Column(name = "totalQuantity", nullable = false)
    private int totalQuantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "supplierID", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "importEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImport> productImports;


//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "import_product",
//            joinColumns = @JoinColumn(name = "importID"),
//            inverseJoinColumns = @JoinColumn(name = "productID")
//    )
//    private List<Product> products;

}
