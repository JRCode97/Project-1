package dev.rivera.services;

import dev.rivera.entities.*;

public interface UserService {
	public Employee loginEmployee(String username,String password);
	public Manager loginManager(String username,String password);
}
