package co.edu.ufps.CrudJPA.DAO;

import java.util.List;

import co.edu.ufps.CrudJPA.Conexion.Conexion;
import co.edu.ufps.CrudJPA.modelo.User;


public class UserDAO  {
	
	private Conexion<User> conexion= new Conexion();

	public UserDAO() {
		this.conexion= new Conexion(User.class);
		// TODO Auto-generated constructor stub
	}
	
	public User select(int id) {
		return conexion.find(id);
		
	}
	
	public List<User> selectedAll(){
		return conexion.list();
	}
	public void insert(User user) {
		conexion.insert(user);		
	}
	
	public User buscarUser(Integer id) {
		User u1=new User();
		return u1=conexion.find(id);		
		
	}
	public void actualizarUser(User u) {		
		conexion.update(u);
		
	}
	public void borrarUser(User u) {
		conexion.delete(u);
		
	}
	

}
