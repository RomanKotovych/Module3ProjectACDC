package com.javarush.kotovych.mapping;

import com.javarush.kotovych.dto.AnswerTo;
import com.javarush.kotovych.dto.QuestTo;
import com.javarush.kotovych.dto.QuestionTo;
import com.javarush.kotovych.dto.UserTo;
import com.javarush.kotovych.entity.Answer;
import com.javarush.kotovych.entity.Quest;
import com.javarush.kotovych.entity.Question;
import com.javarush.kotovych.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {Dto.class})
public interface Dto {
    Dto MAPPER = Mappers.getMapper(Dto.class);


    User toEntity(UserTo userTo);

    UserTo toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserTo userTo, @MappingTarget User user);

    Answer toEntity(AnswerTo answerTo);

    AnswerTo toDto(Answer answer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Answer partialUpdate(AnswerTo answerTo, @MappingTarget Answer answer);

    Question toEntity(QuestionTo questionTo);

    QuestionTo toDto(Question question);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Question partialUpdate(QuestionTo questionTo, @MappingTarget Question question);

    Quest toEntity(QuestTo questTo);

    QuestTo toDto(Quest quest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Quest partialUpdate(QuestTo questTo, @MappingTarget Quest quest);
}
