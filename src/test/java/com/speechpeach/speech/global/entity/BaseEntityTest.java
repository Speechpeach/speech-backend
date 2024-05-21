package com.speechpeach.speech.global.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BaseEntity 테스트")
class BaseEntityTest {

    @Test
    @DisplayName("생성일시, 수정일시, 데이터삭제여부 잘 들어가는지 확인")
    void baseEntitySetTest() throws NoSuchFieldException, IllegalAccessException{
        // given
        LocalDateTime testTime = LocalDateTime.now();
        BaseEntity baseEntity = new BaseEntity() {};

        Field createdDateField = BaseEntity.class.getDeclaredField("createdDate");
        createdDateField.setAccessible(true);
        createdDateField.set(baseEntity, testTime);

        Field modifiedDateField = BaseEntity.class.getDeclaredField("modifiedDate");
        modifiedDateField.setAccessible(true);
        modifiedDateField.set(baseEntity, testTime);

        Field isDeleteField = BaseEntity.class.getDeclaredField("isDelete");
        isDeleteField.setAccessible(true);
        isDeleteField.set(baseEntity, false);

        // when & then
        assertThat(createdDateField.get(baseEntity)).isEqualTo(testTime);
        assertThat(modifiedDateField.get(baseEntity)).isEqualTo(testTime);
        assertThat(isDeleteField.get(baseEntity)).isEqualTo(false);
    }


}