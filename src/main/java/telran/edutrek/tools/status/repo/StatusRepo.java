package telran.edutrek.tools.status.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.tools.status.entities.StatusData;

public interface StatusRepo extends MongoRepository<StatusData, String>
{

	Optional<StatusData> findByName(String name);

}
