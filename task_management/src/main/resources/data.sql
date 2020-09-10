delete from  user_role;
delete from  roles;
delete from  users;

INSERT INTO roles (id, name) VALUES 
(1, 'ROLE_ADMIN'),
(2, 'AD_READ'),
(3, 'AD_WRITE'),
(4, 'IFS_READ'),
(5, 'IFS_WRITE'),
(6, 'PB_READ'),
(7, 'PB_WRITE'),
(8, 'REPORTING');

INSERT INTO users (id, user_name, password) VALUES 
(1, 'admin', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(2, 'user1', '$2a$10$T9iIeZDoPBM66NdlPz7vFeMZEY3su/Tg//dmBgdua90uBC8vkfV3a'),
(3, 'user2', '$2a$10$0EAfOC9ImarzQZLoDfh58.Aly379t6osmfzIGrUDoypx81h2oDFkO'),
(4, 'bhushan', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(5, 'sudhir', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(6, 'heta', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(7, 'pankaj', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(8, 'priyanka', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(9, 'manganna', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(10, 'karan', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(11, 'komal', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(12, 'rakesh', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(13, 'ad_read', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(14, 'ad_write', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(15, 'ifs_read', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(16, 'ifs_write', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(17, 'pb_read', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(18, 'pb_write', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(19, 'reporting', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q'),
(20, 'test', '$2a$10$DE00tu5tUyv7kqMyw.14L.oJNv6flYSjTGmmdYKeLB1I1Us7bAU6q');

insert into user_role(user_id, role_id) values
(1,1),
(2,1),
(2,2),
(3,1),
(3,2),
(4,1),
(5,1),
(6,1),
(7,1),
(8,1),
(9,1),
(10,1),
(11,1),
(12,1),
(13,2),
(14,3),
(15,4),
(16,5),
(17,6),
(18,7),
(19,8),
(20,3),
(20,8);