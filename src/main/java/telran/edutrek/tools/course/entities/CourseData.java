package telran.edutrek.tools.course.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.edutrek.tools.course.dto.CourseDto;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "edutrek_courses")
public class CourseData 
{
	String id;
	String name;
	
	public CourseData(String name) {
		super();
		this.name = name;
	}
	
	public CourseDto build() 
	{
			return new CourseDto(id, name);
	}
}
