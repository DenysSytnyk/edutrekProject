package telran.edutrek.accounting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import telran.edutrek.accounting.entities.UserAccount;
import telran.edutrek.accounting.repo.EdutrekRepository;

@Configuration
public class AuthenticationConfiguration implements UserDetailsService
{

	@Autowired
	EdutrekRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		UserAccount user = repo.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
		
		String password = user.getHashCode();
		String[] roles = new String[]{"ROLE_" + user.getRoles()};
		return new User(username, password, AuthorityUtils.createAuthorityList(roles));
	}

}
