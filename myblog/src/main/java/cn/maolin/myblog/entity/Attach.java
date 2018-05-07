package cn.maolin.myblog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_attach
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class Attach implements Serializable {
    /**
     * Database Column Remarks:
     *   主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_attach.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   姓名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_attach.fname
     *
     * @mbg.generated
     */
    private String fname;

    /**
     * Database Column Remarks:
     *   类型
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_attach.ftype
     *
     * @mbg.generated
     */
    private String ftype;

    /**
     * Database Column Remarks:
     *   关键字
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_attach.fkey
     *
     * @mbg.generated
     */
    private String fkey;

    /**
     * Database Column Remarks:
     *   作者ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_attach.author_id
     *
     * @mbg.generated
     */
    private Integer authorId;

    /**
     * Database Column Remarks:
     *   创建日期
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_attach.created
     *
     * @mbg.generated
     */
    private Integer created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_attach
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

}