package springApp.core.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "`order`")
@Getter
@Setter
public class Order extends AbstractPersistable<UUID> {

    @Column(name = "`userId`",insertable = false,updatable = false)
    private UUID userId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "`userId`")
    private User user;

    @Column(name = "`orderDate`")
    public LocalDateTime orderDate = LocalDateTime.now();
    @OneToMany(mappedBy = "pk.order",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<ProductsInOrders> productsInOrders = new ArrayList<>();


}
