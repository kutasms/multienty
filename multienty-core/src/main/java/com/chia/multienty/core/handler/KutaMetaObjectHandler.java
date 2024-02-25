package com.chia.multienty.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.chia.multienty.core.domain.enums.AutoFillFieldNames;
import com.chia.multienty.core.domain.enums.StatusEnum;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class KutaMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, AutoFillFieldNames.VERSION.getExp(), Long.class, 1L);
        this.strictInsertFill(metaObject, AutoFillFieldNames.CREATE_TIME.getExp(), LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, AutoFillFieldNames.DELETED.getExp(), Boolean.class, false);
        this.strictInsertFill(metaObject, AutoFillFieldNames.STATUS.getExp(), String.class, StatusEnum.NORMAL.getCode());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, AutoFillFieldNames.UPDATE_TIME.getExp(), LocalDateTime.class, LocalDateTime.now());
    }
}
