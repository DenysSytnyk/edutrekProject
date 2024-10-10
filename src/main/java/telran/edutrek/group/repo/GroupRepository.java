package telran.edutrek.group.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.group.entities.GroupData;

public interface GroupRepository extends MongoRepository<GroupData, String>
{
	
}
