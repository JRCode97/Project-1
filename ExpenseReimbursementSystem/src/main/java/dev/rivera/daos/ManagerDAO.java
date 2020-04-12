package dev.rivera.daos;

import dev.rivera.entities.*;

public interface ManagerDAO {
	
	public Manager getManagerByID(int id);
	public Manager UpdateManager(Manager m);
	public boolean DeleteManager(Manager m);
}
