package ie.cit.caf.jparepo;

import ie.cit.caf.entity.CHObject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoJpaRepo extends CrudRepository <CHObject, Integer>{
	
	

}
