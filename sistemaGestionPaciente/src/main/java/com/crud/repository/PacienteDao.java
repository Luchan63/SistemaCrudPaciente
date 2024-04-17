package com.crud.repository;

import com.crud.modelo.Paciente;

// aaqui extiendo Dao para tener ya los metodos predefinidos y uutilice una ternica de jpa peroo aplicada a jdbc que
// me resulto una idea brillante porque evito definir muchos metodos y solo debo sobreEscribirlo.
public interface PacienteDao extends DAO<Paciente, Long>{
}
