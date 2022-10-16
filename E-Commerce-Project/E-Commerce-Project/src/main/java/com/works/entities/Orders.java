package com.works.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;


    @NotBlank(message = "Order name name can not be blank")
    private String orderName;


    @ManyToOne
    @JsonIgnore
    private User user;





}
