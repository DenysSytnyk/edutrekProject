package telran.edutrek.tools.status.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.edutrek.tools.status.dto.StatusDto;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "edutrek_statuses")
public class StatusData 
{
	String id;
	String name;
	
	public StatusData(String name) {
		super();
		this.name = name;
	}
	
	public StatusDto build() 
	{
		return new StatusDto(id, name);
	}
}
