package rs.ac.bg.etf.dipl.web.model.actor;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorRequest {

	@NotBlank
	private String name;
	private Date birthday;
	private String roles;
}
