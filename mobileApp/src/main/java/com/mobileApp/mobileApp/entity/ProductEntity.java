//package com.mobileApp.mobileApp.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//public class ProductEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
//    @Column(name = "image_url")
//    private List<String> image = new ArrayList<>();
//
//    private Double price;
//
//    @Column(name = "no_of_units_in_box")
//    private Integer noOfUnitsInBox;
//
//    private String brand;
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "product_description", joinColumns = @JoinColumn(name = "product_id"))
//    @MapKeyColumn(name = "attribute_key")
//    @Column(name = "attribute_value")
//    private Map<String, String> description = new HashMap<>();
//
//    @OneToMany
//    private List<ProductDetails> productDetails;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<String> getImage() {
//        return image;
//    }
//
//    public void setImage(List<String> image) {
//        this.image = image;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public Integer getNoOfUnitsInBox() {
//        return noOfUnitsInBox;
//    }
//
//    public void setNoOfUnitsInBox(Integer noOfUnitsInBox) {
//        this.noOfUnitsInBox = noOfUnitsInBox;
//    }
//
//    public String getBrand() {
//        return brand;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }
//
//    public Map<String, String> getDescription() {
//        return description;
//    }
//
//    public void setDescription(Map<String, String> description) {
//        this.description = description;
//    }
//
//    public Long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    //    public CategoryEntity getCategory() {
////        return category;
////    }
////
////    public void setCategory(CategoryEntity category) {
////        this.category = category;
////    }
////
////    @ManyToOne
////    @JoinColumn(name = "category_id", nullable = false)  // Correct field for category
////    private CategoryEntity category;
//    private Long categoryId;
//
//}
//

package com.mobileApp.mobileApp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> image = new ArrayList<>();

    @Column(name = "no_of_units_in_box")
    private Integer noOfUnitsInBox;

    private String brand;

    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public Integer getNoOfUnitsInBox() {
        return noOfUnitsInBox;
    }

    public void setNoOfUnitsInBox(Integer noOfUnitsInBox) {
        this.noOfUnitsInBox = noOfUnitsInBox;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductDetails> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductDetails> productDetails = new ArrayList<>();
}
