drop table if exists T_ROL;
create table T_ROL (
                            "rol_id" serial not null,
                            "rol_nombre" varchar(100) not null,
                            primary key ("rol_id")
);

drop table if exists T_TIPO_VACUNA;
create table T_TIPO_VACUNA (
                               "tipo_vac_id" serial not null,
                               "tipo_vac_descripcion" varchar(100) not null,
                               primary key ("tipo_vac_id")
);

drop table if exists T_USUARIO;
create table T_USUARIO (
                           "user_id" serial not null,
                           "user_username" varchar(150) not null,
                           "user_password" varchar(255) not null,
                           "user_rol_id" int not null,
                           primary key ("user_id"),
                           constraint "fk_usuario_rol"
                               foreign key ("user_rol_id")
                                   references T_ROL ("rol_id")
                                   on delete no action
                                   on update no action
);

drop table if exists T_EMPLEADO;
create table T_EMPLEADO(
                           "emp_id" serial not null,
                           "emp_cedula" varchar(10) not null,
                           "emp_nombre" varchar(150) not null,
                           "emp_apellido" varchar(150) not null,
                           "emp_correo" varchar(100) not null,
                           "emp_fecha_nac" timestamp null,
                           "emp_telefono" varchar(10) null,
                           "emp_direccion" varchar(255) null,
                           "emp_estado_vacuna" boolean null,
                           "emp_user_id" int not null,
                           primary key ("emp_id"),
                           unique("emp_cedula"),
                           unique("emp_correo"),
                           constraint "fk_empleado_usuario"
                               foreign key ("emp_user_id")
                                   references T_USUARIO ("user_id")
                                   on delete no action
                                   on update no action
);

drop table if exists T_VACUNA;
create table T_VACUNA(
                         "vac_id" serial not null,
                         "vac_tipo_vacuna_id" int not null,
                         "vac_fecha" timestamp not null,
                         "vac_numero_dosis" int not null,
                         "vac_emp_id" int not null,
                         primary key ("vac_id"),
                         constraint "fk_vacuna_empleado"
                             foreign key ("vac_emp_id")
                                 references T_EMPLEADO ("emp_id")
                                 on delete no action
                                 on update no action,
                         constraint "fk_vacuna_tipo_vacuna"
                             foreign key ("vac_tipo_vacuna_id")
                                 references T_TIPO_VACUNA ("tipo_vac_id")
                                 on delete no action
                                 on update no action
);

INSERT INTO public.t_rol (rol_id, rol_nombre) VALUES (1, 'ADMIN');
INSERT INTO public.t_rol (rol_id, rol_nombre) VALUES (2, 'USER');