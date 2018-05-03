package cn.maolin.myblog.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

/**
 * @author 茂林
 * @since 2017/11/21 10:33
 */
public class ResultBean implements Serializable {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer code = 200;

    // 响应消息
    private String msg = "SUCCESS";

    // 响应中的数据
    private Object data = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultBean() {

    }

    public ResultBean(Object data) {
        this.data = data;
    }

    public ResultBean(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultBean ok() {
        return new ResultBean();
    }

    public static ResultBean ok(Object data) {
        return new ResultBean(data);
    }

    public static ResultBean error() {
        return build(HttpStatus.INTERNAL_SERVER_ERROR.value(), "FALSE", null);
    }

    public static ResultBean error(String msg) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }

    public static ResultBean error(Integer code, String msg) {
        return build(code, msg, null);
    }

    public static ResultBean error(Integer code, String msg, Object data) {
        return build(code, msg, data);
    }

    public static ResultBean build(Integer code, String msg) {
        return new ResultBean(code, msg, null);
    }

    public static ResultBean build(Integer code, String msg, Object data) {
        return new ResultBean(code, msg, data);
    }

    /**
     * 将json结果集转化为ResultBean对象
     *
     * @param jsonData json数据
     * @param clazz    ResultBean中的object类型
     * @return
     */
    public static ResultBean formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ResultBean.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static ResultBean format(String json) {
        try {
            return MAPPER.readValue(json, ResultBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz    集合中的类型
     * @return
     */
    public static ResultBean formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
