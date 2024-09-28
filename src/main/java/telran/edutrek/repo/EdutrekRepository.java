package telran.edutrek.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.accounting.entities.UserAccount;

public interface EdutrekRepository extends MongoRepository<UserAccount, String>
{

}
