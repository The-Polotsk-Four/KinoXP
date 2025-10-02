package dk.ek.backend.catalog.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SnackDto {
    private String name;
    private BigDecimal price;
    private int quantity;
    private LocalDate dateOfPurchase;

    public SnackDto() {
    }

    public SnackDto(String name, BigDecimal price, int quantity, LocalDate dateOfPurchase) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
