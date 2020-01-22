package com.ncarignan.songr;

import org.springframework.data.jpa.repository.JpaRepository;

//inside the diaomond we need to declare<TypeOfThingGoingIntoDb, TypeOfThatThing'sId>
public interface EmotionRepository  extends JpaRepository<Emotion, Long> {
}


// To get things working so far. We imported new dependencies jpa, postgres
// we told Emotion to be an @Entity
// we gave Emotion an @Id that was auto generated
// We set up our connection to the db, using application.properties, url, password username
// Created a JpaRepository called EmotionRepository
// we @Autowired an instance of the EmotionRepository (wherever we needed it, specifically in the controller)
// we retrieved some Emotions from the db, in place of our hard coded ones