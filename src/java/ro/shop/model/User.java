package ro.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class User implements Comparable<User>{
    private int id;
    @EqualsAndHashCode.Exclude private String email;
    @EqualsAndHashCode.Exclude private String password;
    @EqualsAndHashCode.Exclude private String fullName;
    @EqualsAndHashCode.Exclude private String billAddress;

    public User(String email, String password, String fullName, String billAddress) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.billAddress = billAddress;
    }

    @Override
    public int compareTo(User user){
        if(user.id==this.id){
            return 0;
        }else if(user.id<this.id){
            return 1;
        }
        return -1;
    }
}
