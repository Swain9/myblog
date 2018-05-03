package cn.maolin.myblog.service.impl;

import cn.maolin.myblog.entity.Contents;
import cn.maolin.myblog.entity.Metas;
import cn.maolin.myblog.entity.Relationships;
import cn.maolin.myblog.exception.TipException;
import cn.maolin.myblog.mapper.ContentsMapper;
import cn.maolin.myblog.mapper.MetasMapper;
import cn.maolin.myblog.mapper.RelationshipsMapper;
import cn.maolin.myblog.model.dto.Types;
import cn.maolin.myblog.service.MetasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author 张茂林
 * @since 2018/4/18 18:21
 */
@Service
public class MetasServiceImpl implements MetasService {


    private final MetasMapper metasMapper;
    private final RelationshipsMapper relationshipsMapper;
    private final ContentsMapper contentsMapper;

    public MetasServiceImpl(MetasMapper metasMapper, RelationshipsMapper relationshipsMapper, ContentsMapper contentsMapper) {
        this.metasMapper = metasMapper;
        this.relationshipsMapper = relationshipsMapper;
        this.contentsMapper = contentsMapper;
    }

    /**
     * 获取元数据
     *
     * @param type 类型
     * @return List<Metas>
     */
    @Override
    public List<Metas> getMetas(String type) {
        return metasMapper.selectMetasByType(type);
    }

    /**
     * 保存多个项目
     *
     * @param cid   文章id
     * @param names 类型名称列表
     * @param type  类型，tag or category
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(Integer cid, String names, String type) {
        if (null == cid) {
            throw new TipException("项目关联id不能为空");
        }
        if (!StringUtils.isEmpty(names) && !StringUtils.isEmpty(type)) {
            String[] nameArr = names.split(",");
            for (String name : nameArr) {
                this.saveOrUpdate(cid, name, type);
            }
        }
    }

    /**
     * 保存一个元数据
     *
     * @param type  类型
     * @param cname 名称
     * @param mid   主键
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveMeta(String type, String cname, Integer mid) {
        if (!StringUtils.isEmpty(type) && !StringUtils.isEmpty(cname)) {
            Metas metas = metasMapper.selectMetasByTypeAndName(cname, type);
            if (null != metas) {
                throw new TipException("已经存在该项");
            } else {
                if (null != mid) {
                    metas = new Metas();
                    metas.setMid(mid);
                    metas.setName(cname);
                    metasMapper.update(metas);
                } else {
                    metas = new Metas();
                    metas.setType(type);
                    metas.setName(cname);
                    metasMapper.insert(metas);
                }
            }
        }
    }

    /**
     * 删除一个元数据
     *
     * @param mid 主键
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(int mid) {
        Metas metas = metasMapper.selectMetasByMid(mid);
        if (null == metas) {
            return;
        }

        String type = metas.getType();
        String name = metas.getName();
        metasMapper.deleteByMid(mid);

        List<Relationships> relationships = relationshipsMapper.selectAllByMid(mid);
        if (null != relationships) {
            relationships.stream()
                    .map(r -> contentsMapper.selectContentsByCid(r.getCid()))
                    .filter(Objects::nonNull)
                    .forEach(contents -> {
                        Integer cid = contents.getCid();
                        boolean isUpdate = false;
                        Contents temp = new Contents();
                        if (type.equals(Types.CATEGORY)) {
                            temp.setCategories(reMeta(name, contents.getCategories()));
                            isUpdate = true;
                        }
                        if (type.equals(Types.TAG)) {
                            temp.setTags(reMeta(name, contents.getTags()));
                            isUpdate = true;
                        }
                        if (isUpdate) {
                            temp.setCid(cid);
                            contentsMapper.update(temp);
                        }
                    });
        }
        relationshipsMapper.deleteByMid(mid);
    }

    private String reMeta(String name, String metas) {
        String[] ms = metas.split(",");
        StringBuilder sba = new StringBuilder();
        for (String m : ms) {
            if (!name.equals(m)) {
                sba.append(",").append(m);
            }
        }
        if (sba.length() > 0) {
            return sba.substring(1);
        }
        return "";
    }

    private void saveOrUpdate(Integer cid, String name, String type) {
        Metas metas = metasMapper.selectMetasByTypeAndName(name, type);

        Integer mid;
        if (null != metas) {
            mid = metas.getMid();
        } else {
            metas = new Metas();
            metas.setSlug(name);
            metas.setName(name);
            metas.setType(type);

            metasMapper.insert(metas);

            mid = metas.getMid();
        }
        if (mid != 0) {
            long count = relationshipsMapper.count(cid, mid);

            if (count == 0) {
                Relationships relationships = new Relationships();
                relationships.setCid(cid);
                relationships.setMid(mid);

                relationshipsMapper.insert(relationships);
            }
        }
    }
}
