package cn.keepting.family.server.dao;

import cn.keepting.family.server.dao.model.CityPo;
import cn.keepting.family.server.dao.model.UserPo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: create by fuhao.xu
 * @description: cn.keepting.family.server.dao
 * @date:2021/1/7
 **/
@Mapper
public interface CityDao extends BaseMapper<CityPo> {
}
