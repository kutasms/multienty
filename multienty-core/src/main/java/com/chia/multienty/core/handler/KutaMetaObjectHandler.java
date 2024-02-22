package com.chia.multienty.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.chia.multienty.core.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class KutaMetaObjectHandler implements MetaObjectHandler {

    @Getter
    @AllArgsConstructor
    private enum FieldNames {
        CREATE_TIME("createTime"),
        UPDATE_TIME("updateTime"),
        VERSION("version"),
        DELETED("deleted"),
        STATUS("status");


        private String exp;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, FieldNames.VERSION.getExp(), Long.class, 1L);
        this.strictInsertFill(metaObject, FieldNames.CREATE_TIME.getExp(), LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, FieldNames.DELETED.getExp(), Boolean.class, false);
        this.strictInsertFill(metaObject, FieldNames.STATUS.getExp(), String.class, StatusEnum.NORMAL.getCode());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, FieldNames.UPDATE_TIME.getExp(), LocalDateTime.class, LocalDateTime.now());
    }
}
