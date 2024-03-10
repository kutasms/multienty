package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.cache.redis.service.api.CommonRedisService;
import com.chia.multienty.core.domain.dto.DictDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.DictMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.Dict;
import com.chia.multienty.core.service.DictService;
import com.chia.multienty.core.util.SM4Util;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.base.DictListGetParameter;
import com.chia.multienty.core.parameter.base.DictSaveParameter;
import com.chia.multienty.core.parameter.base.DictUpdateParameter;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.crypto.CryptoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 数据字典信息 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Service
public class DictServiceImpl extends KutaBaseServiceImpl<DictMapper, Dict> implements DictService {
    @Autowired
    private YamlMultientyProperties properties;

    @Autowired
    private CommonRedisService commonRedisService;

    @Override
    public boolean deleteSafely(Long dictId) throws KutaRuntimeException {
        Dict orig = getBy(dictId,
                Dict::getVersion,
                Dict::getDeletable,
                Dict::getAlias);
        if(orig.getDeletable() != null && !orig.getDeletable()) {
            throw new KutaRuntimeException(HttpResultEnum.DATA_CANNOT_DELETE);
        }
        orig.setDictId(dictId);
        orig.setStatus(StatusEnum.DELETED.getCode());
        if(orig.getAlias() != null) {
            commonRedisService.delete(String.format("DICT-%s", orig.getAlias()));
        }
        return baseMapper.updateById(orig) == 1;
    }

    @Override
    public int save(DictSaveParameter parameter) throws CryptoException {
        Dict dict = new Dict();
        BeanUtils.copyProperties(parameter, dict);
        if(dict.getEncrypted() != null && dict.getEncrypted()) {
            dict.setValue(SM4Util.encrypt(properties.getSm4PrivateKey(), dict.getValue()));
        }
        dict.setCreateTime(LocalDateTime.now());
        dict.setVersion(1L);
        dict.setStatus(StatusEnum.NORMAL.getCode());
        int result = baseMapper.insert(dict);
        parameter.setDictId(dict.getDictId());
        return result;
    }

    @Override
    public int update(DictUpdateParameter parameter) throws CryptoException {
        Dict dict = new Dict();
        BeanUtils.copyProperties(parameter, dict);
        // 别名不允许修改
        dict.setAlias(null);
        if(dict.getEncrypted() != null && dict.getEncrypted()) {
            String privateKey = properties.getSm4PrivateKey();
            dict.setValue(SM4Util.encrypt(privateKey, dict.getValue()));
        }
        dict.setUpdateTime(LocalDateTime.now());
        if(parameter.getPid() != null && parameter.getPid() > 0) {
            Dict parentDict = getById(parameter.getPid());
            if(parentDict.getAlias() != null) {
                commonRedisService.delete(String.format("DICT-LIST-%s", parentDict.getAlias()));
            }
        }
        if(parameter.getAlias() != null) {
            commonRedisService.delete(String.format("DICT-%s", parameter.getAlias()));
        }
        return baseMapper.updateById(dict);
    }

    @Override
    public IPage<DictDTO> getList(DictListGetParameter parameter) {
        IPage<DictDTO> page = baseMapper.selectJoinPage(parameter.getPageObj(),
                DictDTO.class,
                new MTLambdaWrapper<Dict>()
                        .eq(parameter.getPid() != null, Dict::getPid, parameter.getPid())
                        .and(!StringUtils.isBlank(parameter.getKeywords()), ew -> {
                            ew.like(Dict::getLabel, parameter.getKeywords());
                        })
                        .notIn(Dict::getStatus, StatusEnum.DELETED.getCode())
        );
        return page;
    }

    @Override
    public List<DictDTO> getChildren(String alias) {
        List<DictDTO> list = baseMapper.selectJoinList(DictDTO.class,
                new MPJLambdaWrapper<Dict>()
                        .selectAll(Dict.class)
                        .inSql(Dict::getPid, String.format("select dict_id from mt_dict where alias = '%s'", alias)));
        if(list.size() > 0) {
            for (DictDTO record : list) {
                if(record.getEncrypted() != null && record.getEncrypted()) {
                    try {
                        record.setValue(SM4Util.decrypt(properties.getSm4PrivateKey(), record.getValue()));
                    } catch (CryptoException e) {
                        log.error(String.format("配置:{}解密失败", record.getLabel()), e);
                    }
                }
            }
        }
        return list;
    }

    @Override
//    @CachedMethod(timeout = 30 * 1000 * 60, cacheKeyPrefix = "DICT-LIST")
    public List<DictDTO> getCachedChildren(String alias) {
        List<DictDTO> list = baseMapper.selectJoinList(DictDTO.class,
                new MPJLambdaWrapper<Dict>()
                        .selectAll(Dict.class)
                        .inSql(Dict::getPid, String.format("select dict_id from mt_dict where alias = '%s'", alias)));
        if(list.size() > 0) {
            for (DictDTO record : list) {
                if(record.getEncrypted() != null && record.getEncrypted()) {
                    try {
                        record.setValue(SM4Util.decrypt(properties.getSm4PrivateKey(), record.getValue()));
                    } catch (CryptoException e) {
                        log.error(String.format("配置:{}解密失败", record.getLabel()), e);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public List<DictDTO> getChildren(Long pid) {
        List<DictDTO> list = baseMapper.selectJoinList(DictDTO.class,
                new MPJLambdaWrapper<Dict>()
                        .selectAll(Dict.class)
                        .inSql(Dict::getPid, String.format("select dict_id from mt_dict where pid = %s", pid)));
        if(list.size() > 0) {
            for (DictDTO record : list) {
                if(record.getEncrypted() != null && record.getEncrypted()) {
                    try {
                        record.setValue(SM4Util.decrypt(properties.getSm4PrivateKey(), record.getValue()));
                    } catch (CryptoException e) {
                        log.error(String.format("配置:{}解密失败", record.getLabel()), e);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public DictDTO getValue(String alias) {
        DictDTO record = baseMapper.selectJoinOne(DictDTO.class,
                new MPJLambdaWrapper<Dict>().eq(Dict::getAlias, alias));
        if(record.getEncrypted() != null && record.getEncrypted()) {
            try {
                record.setValue(SM4Util.decrypt(properties.getSm4PrivateKey(), record.getValue()));
            } catch (CryptoException e) {
                log.error(String.format("配置:{}解密失败", record.getLabel()), e);
            }
        }
        return record;
    }

    @Override
    public DictDTO getValue(String parentAlias, String childAlias) throws CryptoException {
        DictDTO record = baseMapper.selectJoinOne(DictDTO.class,
                new MPJLambdaWrapper<Dict>()
                        .selectAll(Dict.class)
                        .eq(Dict::getAlias, childAlias)
                        .inSql(Dict::getPid, String.format("select dict_id from mt_dict where alias = '%s'", parentAlias)));
        if(record.getEncrypted() != null && record.getEncrypted()) {
            try {
                record.setValue(SM4Util.decrypt(properties.getSm4PrivateKey(), record.getValue()));
            } catch (CryptoException e) {
                log.error(String.format("配置:{}解密失败", record.getLabel()), e);
                throw e;
            }
        }
        return record;
    }

    @Override
//    @CachedMethod(timeout = 30 * 1000 * 60, cacheKeyPrefix = "DICT")
    public DictDTO getCachedValue(String parentAlias, String childAlias) throws KutaRuntimeException, CryptoException {
        DictDTO record = getValue(parentAlias, childAlias);
        if(record == null) {
            throw new KutaRuntimeException(HttpResultEnum.SYSTEM_DICT_CONFIG_ERROR);
        }
        return record;
    }

    @Override
//    @CachedMethod(timeout = 30 * 1000 * 60, cacheKeyPrefix = "DICT-SETTING-DURATION")
    public Long getSettingCachingDuration() throws KutaRuntimeException, CryptoException {
        DictDTO dict = getValue("BASE-SETTING", "SETTING-CACHING-DURATION");
        if(dict != null) {
            return Long.parseLong(dict.getValue());
        }
        throw new KutaRuntimeException(HttpResultEnum.SYSTEM_DICT_CONFIG_ERROR);
    }
}
