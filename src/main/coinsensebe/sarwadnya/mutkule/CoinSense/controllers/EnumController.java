package sarwadnya.mutkule.CoinSense.controllers;

import org.hibernate.engine.spi.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sarwadnya.mutkule.CoinSense.enums.ExpenseTypeEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EnumController {
    /*@GetMapping("/enums")
    public ResponseEntity<List<ExpenseTypeEnum>> getEnums(){
        return ResponseEntity.ok(Arrays.stream(ExpenseTypeEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList()));
    }*/
}
