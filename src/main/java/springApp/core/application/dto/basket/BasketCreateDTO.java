package springApp.core.application.dto.basket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BasketCreateDTO implements Serializable {
    private UUID productId;
}
