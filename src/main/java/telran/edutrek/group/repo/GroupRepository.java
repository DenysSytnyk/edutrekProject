package telran.edutrek.group.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.group.dto.GroupDto;
import telran.edutrek.group.entities.GroupData;

public interface GroupRepository extends MongoRepository<GroupData, String>
{

	Optional<GroupData> findByName(String name);
	
}
