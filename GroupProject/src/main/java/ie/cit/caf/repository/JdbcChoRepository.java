package ie.cit.caf.repository;

import ie.cit.caf.domain.CHObject;
import ie.cit.caf.rowmapper.CHORowMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcChoRepository implements CHORepository{
	
	private JdbcTemplate jdbcTemplate; 
	
	@Autowired
	public JdbcChoRepository(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate; 
	}

	@Override
	public CHObject get(int id) {
		String sql = "SELECT * FROM chobjects WHERE id = ?";
		CHObject chobject = jdbcTemplate.queryForObject(sql, new Object[] { id }, 
				new CHORowMapper());
		return chobject; 	
	}

	@Override
	public void save(CHObject cho) {
		String sql = "INSERT INTO chobjects (id, title, date, medium, creditline, "
				+ "description, gallery_text) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)"; 
		jdbcTemplate.update(sql, 
				new Object [] {cho.getId(), cho.getTitle(), 
					cho.getDate(), cho.getMedium(), cho.getCreditline(), 
					cho.getDescription(), cho.getGallery_text()} ); 
		
	}

	@Override
	public void remove(CHObject chobject) {
		String sql = "DELETE FROM chobjects WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { chobject.getId() } );
	}

	@Override
	public List<CHObject> findAll() {
		
		String sql = "SELECT * FROM chobjects";
		return jdbcTemplate.query(sql, new CHORowMapper());
		
	}

}
