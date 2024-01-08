package com.learning.liquibase.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
/*@Builder
@Entity
*/
@Entity
@Table(name = "products")
@Setter
@Getter
public class Product {
    @Id
    private Integer id;
    @Column
    private String name;
    /*@Column
    private String description;*/
    /*@Column
    private String category;*/
    @Column
    private Integer price;

}
