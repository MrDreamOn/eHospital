package com.hospital.register.controller.person;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.bean.UserExample.Criteria;
import com.hospital.register.conditionVO.UserVO;
import com.hospital.register.service.UserService;
import com.hospital.register.util.DateUtil;
import com.hospital.register.util.RestResponse;
import com.hospital.register.util.idCard.IdcardInfoExtractor;

@Controller
@RequestMapping(value = "/queryPerson")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private UserService         userService;
    
    
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse queryPerson(HttpServletRequest request) {
        logger.info("查询患者信息...");
        String userId = request.getParameter("userId");
        UserExample ue = new UserExample();
        ue.createCriteria().andUserIdEqualTo(Integer.parseInt(userId));
        User user = userService.findUsersByCondition(ue).get(0);
        UserVO vo = new UserVO();
        if(StringUtils.hasText(user.getRealName())){
            vo.setRealName(user.getRealName());
            String idCard = user.getIdCard();
            vo.setIdCard(idCard);
            IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(idCard);
            vo.setBrithdayStr(DateUtil.formatDate(idcardInfoExtractor.getBirthday()));
            vo.setSexStr(idcardInfoExtractor.getGender().equals("1") ? "男" : "女");
            vo.setTelphone(user.getTelephone());
            vo.setAge(idcardInfoExtractor.getAge()+"");
            vo.setUserId(user.getUserId());
        }
            
        return RestResponse.successResWithData(vo);
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updatePerson(HttpServletRequest request) {
      String userId =   request.getParameter("userId");
      String realName =   request.getParameter("name");
      String idCard =   request.getParameter("idCard");
      String telPhone =   request.getParameter("telphone");
      if(!StringUtils.hasText(realName)){
          return RestResponse.errorRes("姓名不能为空");
      }
      
      if(!StringUtils.hasText(idCard)){
          return RestResponse.errorRes("身份证不能为空");
      }
      
      if(!StringUtils.hasText(telPhone)){
          return RestResponse.errorRes("手机号不能为空");
      }
      
      UserExample ue = new UserExample();
      ue.createCriteria().andUserIdEqualTo(Integer.parseInt(userId));
      User user = userService.findUsersByCondition(ue).get(0);
      if(!telPhone.equals(user.getTelephone())){
          UserExample ue2 = new UserExample();
          Criteria cri = ue2.createCriteria();
          cri.andTelephoneEqualTo(telPhone);
          cri.andUserIdNotEqualTo(user.getUserId());
          List<User> userList = userService.findUsersByCondition(ue2);
          if(userList.size() >0){
              return RestResponse.errorRes("该手机号已被注册");
          }

      }
      user.setRealName(realName);
      user.setIdCard(idCard);
      IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(idCard);
      user.setBirthday(idcardInfoExtractor.getBirthday());
      user.setSex(Integer.parseInt(idcardInfoExtractor.getGender()));
      user.setUpdateTime(new Date());
      userService.updateByPrimaryKeySelective(user);
      return RestResponse.success();
    }
}
