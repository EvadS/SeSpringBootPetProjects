insert into usr (id, username, password, active)
values (1, 'admin', '$2y$08$rkRZYX.f8snJoLwK/gpA3.EpBhe0g4l31G6L9HvsJWXfSOhFwbza.', true);

insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');