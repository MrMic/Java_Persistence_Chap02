package fr.michaelchlon.javapersistence.ch02.repositories;

import fr.michaelchlon.javapersistence.ch02.Message;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
