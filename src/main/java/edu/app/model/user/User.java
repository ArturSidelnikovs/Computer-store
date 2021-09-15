package edu.app.model.user;
import edu.app.model.role.RoleEnum;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;


@Entity
@Table (name = "users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User implements Serializable {


    @Id
    @GeneratedValue (generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Size (min = 4 , max = 36 , message = "Login cannot be empty")
    private String userName;

    @Size(min = 4, message = "Password cannot be empty")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private RoleEnum role = RoleEnum.USER;

    private String firstName;

    private String lastName;

    private Date createdOn;

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)

    @Lob
    private byte[] avatar;

    public String getAvatarData(byte[] byteDate) {
        return Base64.getMimeEncoder().encodeToString(byteDate);
    }

    public User(String userName, String password, long id) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public User() { }
}
