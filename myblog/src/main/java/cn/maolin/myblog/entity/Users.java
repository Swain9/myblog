package cn.maolin.myblog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_users
 *
 * @mbg.generated do_not_delete_during_merge
 */
@Data
public class Users implements Serializable {
    /**
     * Database Column Remarks:
     * 主键
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_users.uid
     *
     * @mbg.generated
     */
    private Integer uid;

    /**
     * Database Column Remarks:
     * 用户名
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_users.username
     *
     * @mbg.generated
     */
    private String username;

    /**
     * Database Column Remarks:
     * 密码
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_users.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     * Database Column Remarks:
     * 邮箱
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_users.email
     *
     * @mbg.generated
     */
    private String email;

    /**
     * Database Column Remarks:
     * 链接
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_users.home_url
     *
     * @mbg.generated
     */
    private String homeUrl;

    /**
     * Database Column Remarks:
     * 显示的名称
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_users.screen_name
     *
     * @mbg.generated
     */
    private String screenName;

    /**
     * Database Column Remarks:
     * 注册日期
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_users.created
     *
     * @mbg.generated
     */
    private Integer created;

    /**
     * Database Column Remarks:
     * 最后活动日期
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_users.activated
     *
     * @mbg.generated
     */
    private Integer activated;

    /**
     * Database Column Remarks:
     * 上次登陆日期
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_users.logged
     *
     * @mbg.generated
     */
    private Integer logged;

    /**
     * Database Column Remarks:
     * 用户组
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_users.group_name
     *
     * @mbg.generated
     */
    private String groupName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_users
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;


}