package com.experis.mefit.security.roles;

public interface RoleService {

	void addRole(String uid, String role) throws Exception;

	void removeRole(String uid, String role);

}
