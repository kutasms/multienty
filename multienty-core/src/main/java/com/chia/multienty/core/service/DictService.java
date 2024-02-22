package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.DictDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.Dict;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.base.DictListGetParameter;
import com.chia.multienty.core.parameter.base.DictSaveParameter;
import com.chia.multienty.core.parameter.base.DictUpdateParameter;
import org.bouncycastle.crypto.CryptoException;

import java.util.List;

/**
 * <p>
 * 数据字典信息 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
public interface DictService extends KutaBaseService<Dict> {

    boolean deleteSafely(Long dictId) throws KutaRuntimeException;

    int save(DictSaveParameter parameter) throws CryptoException;

    int update(DictUpdateParameter parameter) throws CryptoException;

    IPage<DictDTO> getList(DictListGetParameter parameter);

    List<DictDTO> getChildren(String alias);

    //    @CachedMethod(timeout = 30 * 1000 * 60, cacheKeyPrefix = "DICT-LIST")
    List<DictDTO> getCachedChildren(String alias);

    List<DictDTO> getChildren(Long pid);

    DictDTO getValue(String alias);

    DictDTO getValue(String parentAlias, String childAlias) throws CryptoException;

    //    @CachedMethod(timeout = 30 * 1000 * 60, cacheKeyPrefix = "DICT")
    DictDTO getCachedValue(String parentAlias, String childAlias) throws KutaRuntimeException, CryptoException;

    //    @CachedMethod(timeout = 30 * 1000 * 60, cacheKeyPrefix = "DICT-SETTING-DURATION")
    Long getSettingCachingDuration() throws KutaRuntimeException, CryptoException;
}
