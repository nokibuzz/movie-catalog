package rs.ac.bg.etf.dipl.dto.actor;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateActorDto {

	private String name;
	private Date birthday;
	private String roles;
}
