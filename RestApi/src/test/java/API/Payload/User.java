package API.Payload;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)

public class User {

	int id;
	String username;
	String firstname;
	String lastname;
	String email;
	String password;
	String phone;
	int userStatus;


}
