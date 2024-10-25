package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private Integer categoryId;
    private String size;
    private Double price;
    private Integer qty;
    private String supperId;
}
