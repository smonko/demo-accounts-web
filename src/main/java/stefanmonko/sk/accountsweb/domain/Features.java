package stefanmonko.sk.accountsweb.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Features {

    private String name;
    private Boolean value;

    public Features(final String name, final Boolean value) {
        setName(name);
        setValue(this.value);
    }
    
}
