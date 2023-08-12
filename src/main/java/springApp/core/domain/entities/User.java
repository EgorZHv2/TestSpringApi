package springApp.core.domain.entities;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import springApp.core.domain.enums.UserRole;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "`user`")
public class User extends AbstractPersistable<UUID> implements Serializable {

    @Column(name="name",nullable = false)
    private String name;
    @Column(name = "age",nullable = false)
    private Integer age;
    @Column(name = "`passwordHash`",nullable = false)
    private String passwordHash;
    @Column(name = "`phoneNumber`",nullable = false)
    private String phoneNumber;

    @Column(name = "`userRole`",nullable = false)
    private UserRole userRole = UserRole.User;
    public UserRole getUserRole() {
        return userRole;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    @OneToMany(mappedBy = "pk.user",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<ProductsInBaskets> productsInBasketsList;

    @OneToMany(mappedBy = "user", orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Order> orders;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public List<ProductsInBaskets> getBasketList() {
        return productsInBasketsList;
    }
    public void setBasketList(List<ProductsInBaskets> productsInBasketsList) {
        this.productsInBasketsList = productsInBasketsList;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public User(){}

}

