package com.works.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @NotBlank(message = "Detail can not be blank")
    private String productName;
    @NotBlank(message = "Detail can not be blank")
    private String productPrice;
    @NotBlank(message = "Detail can not be blank")
    private String productDetail;


    @ManyToOne
    @JoinColumn(name="categoryCıd",referencedColumnName = "cid")
    private Categories categories;

    @ManyToOne
    @JoinColumn(name="productImageiid",referencedColumnName = "iid")
    private ProductImage productImage;


}
