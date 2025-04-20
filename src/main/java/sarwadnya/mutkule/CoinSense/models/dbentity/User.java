package sarwadnya.mutkule.CoinSense.models.dbentity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @Getter @Setter
    @Column(unique = true, nullable = false)
    public String username;

    @Column(nullable = false)
    @Getter
    @Setter
    public String name;

    @Column(nullable = false)
    @Getter
    @Setter
    public String password;


}
