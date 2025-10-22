package rs.ac.bg.etf.dipl.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubErrorDto {

    private String object;
    private String field;
    private String message;
}
