package com.example.proxyapp.dto;

public class ProductDto {

    public ProductDto(Long id, String name, Double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    private Long id;
    private String name;
    private Double cost;

    public Long getId() {
        return id;
    }

    public ProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public Double getCost() {
        return cost;
    }

    public ProductDto setCost(Double cost) {
        this.cost = cost;
        return this;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
