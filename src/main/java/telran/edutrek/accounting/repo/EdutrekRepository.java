package telran.edutrek.accounting.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.accounting.entities.UserAccount;

public interface EdutrekRepository extends MongoRepository<UserAccount, String>
{
	Optional<UserAccount> findByLogin(String login);
}
