package edu.app.model.order;

import edu.app.model.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table (name = "orders")
@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Order implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @ManyToOne()
    private User user;

    @NotEmpty
    private Long price;

    private LocalDate createdOn;

    public Order() { }
}
