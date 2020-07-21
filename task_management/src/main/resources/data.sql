delete from  user_role;
delete from  roles;
delete from  users;

INSERT INTO roles (id, name) VALUES 
(1, 'ROLE_ADMIN'),
(2, 'ROLE_STUDENT');

INSERT INTO users (id, user_name, password) VALUES 
(1, 'admin', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(2, 'user1', '$2a$10$T9iIeZDoPBM66NdlPz7vFeMZEY3su/Tg//dmBgdua90uBC8vkfV3a'),
(3, 'user2', '$2a$10$0EAfOC9ImarzQZLoDfh58.Aly379t6osmfzIGrUDoypx81h2oDFkO');

insert into user_role(user_id, role_id) values
(1,1),
(1,2),
(2,2),
(3,2);
