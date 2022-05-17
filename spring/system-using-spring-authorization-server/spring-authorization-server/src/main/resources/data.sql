INSERT INTO USER_ENTITY (name, username, password, account_expired, account_locked, credentials_expired, enabled) 
	VALUES ('Admin', 'admin', 'adm1234', false, false, false, true);
INSERT INTO USER_ROLE_ENTITY (role, user_account_id) VALUES ('ADMIN', 1);