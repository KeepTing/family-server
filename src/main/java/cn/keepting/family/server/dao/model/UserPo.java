package cn.keepting.family.server.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.dao.model
 * @date:2021/1/7
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("family_user")
public class UserPo {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 加密后密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;
}
