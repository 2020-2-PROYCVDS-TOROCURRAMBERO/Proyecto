--Tipos
create domain TCorreo as VARCHAR(100) CHECK((value like '%@escuelaing.edu.co' or value like '%@mail.escuelaing.edu.co') and not(value like '@%') and not(value like '%@%@%'));
create domain TCATEGORIA as VARCHAR(100) check(VALUE='TORRE' or VALUE='PANTALLA' or VALUE='MOUSE' or VALUE='TECLADO');


--TABLAS
create table USUARIO(CARNET INTEGER not null,NOMBRE VARCHAR(50) not null, APELLIDO VARCHAR(50) not null,CORREO TCorreo not null, ROL VARCHAR(2),CONTRA VARCHAR(100) not null);
create table LABORATORIO(ID INTEGER not null,NOMBRE VARCHAR(50),CAPACIDAD INTEGER,FECHACIERRE DATE,FECHAAPERTURA DATE);
create table EQUIPO(ID INTEGER not null,FECHAINICIOACTIVIDAD DATE,FECHAFINACTIVIDAD DATE,FECHAADQUISICION DATE, LABORATORIO INTEGER);
create table ELEMENTO(ID INTEGER not null, CATEGORIA TCATEGORIA not NULL,FABRICANTE VARCHAR(100),REFERECIA VARCHAR(100),FECHAADQUISICION DATE not null,FECHAINICIOACTIVIDAD DATE, FECHAFINACTIVIDAD DATE,EQUIPO INTEGER);
create table NOVEDAD(ID INTEGER not null,FECHANOVEDAD DATE not null,DESCRIPCION VARCHAR(100),JUSTIFICACION VARCHAR(1000),EQUIPO INTEGER, ELEMENTO INTEGER,USUARIO INTEGER);



--PKS
alter table USUARIO add CONSTRAINT PK_USUARIO primary KEY(CARNET);
alter table LABORATORIO add constraint PK_LABORATORIO primary KEY(ID);
alter table EQUIPO add constraint PK_EQUIPO primary KEY(ID);
alter table ELEMENTO add constraint PK_ELEMENTO primary KEY(ID);
alter table NOVEDAD add constraint PK_NOVEDAD primary KEY(ID); 


--FKS
alter table EQUIPO add CONSTRAINT FK_EQUIPO_LABORATORIO foreign key(LABORATORIO) references LABORATORIO(ID);
alter table NOVEDAD add constraint FK_NOVEDAD_EQUIPO foreign KEY(EQUIPO) references EQUIPO(ID);
alter table NOVEDAD add constraint FK_NOVEDAD_ELEMNTOS foreign KEY(ELEMENTO) references ELEMENTO(ID);
alter table novedad add constraint fk_NOVEDAD_USUARIO foreign KEY(USUARIO) references usuario(CARNET);
alter table ELEMENTO add constraint FK_ELEMENTO_EQUIPO foreign KEY(EQUIPO) references EQUIPO(ID);


--UK
alter table USUARIO add constraint UK_USUARIO_CORREO UNIQUE(CORREO);


INSERT INTO usuario
(carnet, nombre, apellido, correo, rol, contra)
VALUES(1, 'juan','pal', 'juan@escuelaing.edu.co', 'ad', 'cl');

--Triggers
/*create function exception_asign_equipo() returns trigger as $test$
declare
	vali integer;
begin
	select count(*) into vali from laboratorio where id = new.laboratorio and fechacierre is not null;
	if(vali=1) then
		raise 'Laboratorio cerrado';
	end if;
	return new;
end;
$test$ language plpgsql;

create trigger no_AsignaEquiposALabsCerrados
before insert or update on EQUIPO
for each row
execute procedure exception_asign_equipo();



create function exception_elemento() returns trigger as $test2$
declare
vali integer;
begin
	select count(*) into vali from equipo where id = new.equipo and FECHAFINACTIVIDAD is not null;
	if(vali=1) then raise 'Equipo dado de baja';
	end if;
	return new;
end;
$test2$ language plpgsql; 

create trigger noAsignarElementosAEquiposDadosDeBaja
before insert or update on elemento
for each row
execute procedure exception_elemento();*/