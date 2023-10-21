package sia.tacocloud.tacos;
import lombok.Data;

@Data // use lombok to auto generate getter, setter, constructor during compile time
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    // defining ingredient types
    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
