package API.Payload;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Store {

	Integer id;
	Integer petId;
	Integer quantity;
	String shipDate;
	String status;
	Boolean complete;

}
