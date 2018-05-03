package cn.maolin.myblog.service.impl;

import cn.maolin.myblog.entity.Contents;
import cn.maolin.myblog.exception.TipException;
import cn.maolin.myblog.mapper.CommentsMapper;
import cn.maolin.myblog.mapper.ContentsMapper;
import cn.maolin.myblog.mapper.RelationshipsMapper;
import cn.maolin.myblog.model.dto.Types;
import cn.maolin.myblog.service.ContentsService;
import cn.maolin.myblog.service.MetasService;
import cn.maolin.myblog.util.BlogConstant;
import cn.maolin.myblog.util.BlogUtil;
import cn.maolin.myblog.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author 张茂林
 * @since 2018/4/19 11:38
 */
@Service
public class ContentsServiceImpl implements ContentsService {

    private final ContentsMapper contentsMapper;
    private final MetasService metasService;
    private final RelationshipsMapper relationshipsMapper;
    private final CommentsMapper commentsMapper;

    public ContentsServiceImpl(ContentsMapper contentsMapper, MetasService metasService, RelationshipsMapper relationshipsMapper, CommentsMapper commentsMapper) {
        this.contentsMapper = contentsMapper;
        this.metasService = metasService;
        this.relationshipsMapper = relationshipsMapper;
        this.commentsMapper = commentsMapper;
    }

    /**
     * 发布文章
     *
     * @param contents 文章
     * @return 主键
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void publish(Contents contents) {
        if (null == contents) {
            throw new TipException("文章对象为空");
        }
        if (StringUtils.isEmpty(contents.getTitle())) {
            throw new TipException("文章标题不能为空");
        }
        if (contents.getTitle().length() > BlogConstant.MAX_TITLE_COUNT) {
            throw new TipException("文章标题最多可以输入" + BlogConstant.MAX_TITLE_COUNT + "个字符");
        }

        if (StringUtils.isEmpty(contents.getContent())) {
            throw new TipException("文章内容不能为空");
        }
        // 最多可以输入2w个字
        int len = contents.getContent().length();
        if (len > BlogConstant.MAX_TEXT_COUNT) {
            throw new TipException("文章内容最多可以输入" + BlogConstant.MAX_TEXT_COUNT + "个字符");
        }
        if (null == contents.getAuthorId()) {
            throw new TipException("请登录后发布文章");
        }

        if (!StringUtils.isEmpty(contents.getSlug())) {
            if (contents.getSlug().length() < 5) {
                throw new TipException("路径太短了");
            }
            if (!BlogUtil.isPath(contents.getSlug())) {
                throw new TipException("您输入的路径不合法");
            }

            long count = contentsMapper.countContentsBySlugAndType(contents.getSlug(), contents.getType());
            if (count > 0) {
                throw new TipException("该路径已经存在，请重新输入");
            }
        }

        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));

        contents.setHits(0);
        contents.setCommentsNum(0);
        int time = DateUtil.nowUnix();
        contents.setCreated(time);
        contents.setModified(time);

        String tags = contents.getTags();
        String categories = contents.getCategories();

        contentsMapper.insert(contents);

        metasService.insert(contents.getCid(), tags, Types.TAG);
        metasService.insert(contents.getCid(), categories, Types.CATEGORY);


    }

    /**
     * 更新文章
     *
     * @param contents
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateArticle(Contents contents) {
        contents.setModified(DateUtil.nowUnix());
        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));
        contents.setTags(contents.getTags() != null ? contents.getTags() : "");
        contents.setCategories(contents.getCategories() != null ? contents.getCategories() : "");

        String tags = contents.getTags();
        String categories = contents.getCategories();

        contentsMapper.update(contents);

        Integer cid = contents.getCid();

        if (null != contents.getType() && !contents.getType().equals(Types.PAGE)) {
            relationshipsMapper.delete(cid);
        }

        metasService.insert(cid, tags, Types.TAG);
        metasService.insert(cid, categories, Types.CATEGORY);
    }

    /**
     * 删除文章
     *
     * @param cid 主键id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delete(int cid) {
        contentsMapper.delete(cid);
        relationshipsMapper.delete(cid);
        //todo 删除评论
        commentsMapper.deleteByCid(cid);
    }

    /**
     * 分页查询列表
     *
     * @param page  当前页
     * @param limit 当前页条数
     * @return List<Contents>
     */
    @Override
    public List<Contents> selectContentsByPage(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Contents> list = contentsMapper.selectContentsByPage(Types.ARTICLE);

        return list;
    }

    /**
     * 根据主键或者slug查询文章
     *
     * @param cid 主键或者slug
     * @return Optional<Contents>
     */
    @Override
    public Optional<Contents> getContents(String cid) {
        if (!StringUtils.isEmpty(cid)) {
            if (BlogUtil.isNum(cid)) {
                return Optional.ofNullable(contentsMapper.selectContentsByCid(Integer.valueOf(cid)));
            } else {
                return Optional.ofNullable(contentsMapper.selectContentsBySlug(cid));
            }
        }
        return Optional.empty();
    }

    /**
     * 更新点击率
     *
     * @param temp
     */
    @Override
    public void updateArticleHits(Contents temp) {
        contentsMapper.updateHits(temp);
    }
}
