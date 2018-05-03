package cn.maolin.myblog.service.impl;

import cn.maolin.myblog.entity.Attach;
import cn.maolin.myblog.mapper.AttachMapper;
import cn.maolin.myblog.service.AttachService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/5/3 10:49
 */
@Service
public class AttachServiceImpl implements AttachService {

    private final AttachMapper attachMapper;

    public AttachServiceImpl(AttachMapper attachMapper) {
        this.attachMapper = attachMapper;
    }

    /**
     * 保存附件信息
     *
     * @param attach
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save(Attach attach) {
        attachMapper.save(attach);
    }

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @Override
    public Attach findById(Integer id) {
        return attachMapper.selectById(id);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(Integer id) {
        attachMapper.delete(id);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 数量
     * @return List<Attach>
     */
    @Override
    public List<Attach> page(int page, int limit) {
        PageHelper.startPage(page, limit);
        return attachMapper.select();
    }
}
