package ie.cit.caf.jparepo;

import java.util.List;

import ie.cit.caf.entity.CHObject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoJpaRepo extends CrudRepository <CHObject, Integer>{
	
	public List<CHObject> findByMediumContains(String mediumName);

}
