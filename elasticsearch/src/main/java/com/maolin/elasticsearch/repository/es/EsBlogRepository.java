package com.maolin.elasticsearch.repository.es;

import com.maolin.elasticsearch.domain.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 45022
 * @since 2017/11/9 16:21
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {
    /**
     * 分页查询博客,去重
     *
     * @param title
     * @param summary
     * @param content
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content, Pageable pageable);
}
