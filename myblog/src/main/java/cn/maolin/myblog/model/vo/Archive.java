package cn.maolin.myblog.model.vo;

import cn.maolin.myblog.entity.Contents;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 文章归档
 */
@Data
public class Archive {

    private String date_str;
    private Date date;
    private String count;
    private List<Contents> articles;

}
