package cn.keepting.family.server.aspect;

import cn.keepting.family.server.dao.UserDao;
import cn.keepting.family.server.dao.model.UserPo;
import cn.keepting.family.server.exception.ResultCode;
import cn.keepting.family.server.exception.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: create by fuhao.xu
 * @description: com.chealile.inertcity.config
 * @date:2020/10/29
 **/
@Slf4j
@Aspect
@Component
public class CheckUserAspect {

    @Resource
    UserDao userDao;

    private static ThreadLocal<UserPo> contextUser = new ThreadLocal<>();

    @Pointcut("@annotation(cn.keepting.family.server.aspect.CheckUser)")
    public void ouAspect() {
    }

    @Before("ouAspect()")
    public void beforeMethod() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String phone = request.getParameter("phone");
//        Wrapper<UserInfo> userInfoWrapper = new EntityWrapper<>();
//        userInfoWrapper.eq("mobile", phone);
//        List<UserInfo> userInfos = userInfoDao.selectList(userInfoWrapper);
//        if (CollectionUtils.isEmpty(userInfos)) {
//            log.info("用户不存在：{}", phone);
//            throw new ServiceException("用户不存在");
//        }
        //查询司机表是否存在
        Wrapper<UserPo> driverInfoWrapper = new EntityWrapper<>();
        driverInfoWrapper.eq("mobile", phone);
        driverInfoWrapper.eq("status", 1);
        List<UserPo> driverInfos = userDao.selectList(driverInfoWrapper);
        if (CollectionUtils.isEmpty(driverInfos)) {
            log.info("用户不存在：{}", phone);
            throw new ServiceException(ResultCode.NOT_USER);
        }


        contextUser.set(driverInfos.get(0));
        log.info("用户信息放入cointext: {}", driverInfos.get(0));
    }

    @After("ouAspect()")
    public void afterMethod() {
        removeUser();
    }

    public static UserPo getDriver() {
        return contextUser.get();
    }

    public static void removeUser() {
        contextUser.remove();
    }
}
