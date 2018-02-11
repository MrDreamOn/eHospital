package com.hospital.register.controller.subscription;

import java.util.ArrayList;
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

import com.hospital.register.annotation.OperateLogs;
import com.hospital.register.bean.Schedule;
import com.hospital.register.bean.ScheduleExample;
import com.hospital.register.bean.ScheduleExample.Criteria;
import com.hospital.register.bean.Subscription;
import com.hospital.register.bean.SubscriptionExample;
import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.conditionVO.ScheduleVO;
import com.hospital.register.conditionVO.SubscriptionVO;
import com.hospital.register.conditionVO.UserVO;
import com.hospital.register.exception.EhospitalServiceException;
import com.hospital.register.service.ScheduleService;
import com.hospital.register.service.SubscriptionService;
import com.hospital.register.service.UserService;
import com.hospital.register.util.DateUtil;
import com.hospital.register.util.RestResponse;
import com.hospital.register.util.idCard.IdcardInfoExtractor;
import com.hospital.register.util.idCard.IdcardValidator;
import com.hospital.register.vo.SubscriptionView;

/**
 * 
 * 
 * @author caiwei
 * @version $Id: SubscriptionController.java, v 0.1 2017年12月10日 下午10:19:04
 *          caiwei Exp $
 */

@Controller
@RequestMapping(value = "/subscription")
public class SubscriptionController {

	private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/addSubcription", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse addSubcription(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String scheduleId = request.getParameter("scheduleId");
		String idCard = request.getParameter("idCard");
		String telPhone = request.getParameter("telPhone");
		String name = request.getParameter("name");
		UserExample ue = new UserExample();
		ue.createCriteria().andUserIdEqualTo(Integer.parseInt(userId));
		User user = userService.findUsersByCondition(ue).get(0);
		ScheduleExample se = new ScheduleExample();
		se.createCriteria().andScheduleIdEqualTo(Integer.parseInt(scheduleId));
		Schedule schedule = scheduleService.getScheduleInfo(se).get(0);
		logger.info("addSubcription,userId={}", userId);

		Subscription subVO = new Subscription();
		subVO.setScheduleId(schedule.getScheduleId());
		subVO.setUserId(Integer.parseInt(userId));
		subVO.setPatientName(name);
		IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(idCard);
		subVO.setPatientBirthday(idcardInfoExtractor.getBirthday());
		subVO.setPatientSex(Integer.parseInt(idcardInfoExtractor.getGender()));
		subVO.setPatientPhone(telPhone);
		subVO.setSubscriptionDate(schedule.getClinicDate());
		subVO.setSubscriptionTime(schedule.getClinicTime());
		subVO.setSubscriptionStatus(0);
		subVO.setSubscriptionFee(schedule.getClinicFee().longValue());
		subVO.setSubscriptionSource("wechat");
		subVO.setCreateTime(new Date());
		subVO.setUpdateTime(new Date());
		try {
			String id = subscriptionService.addSubscription(subVO, user.getOpenId());
		     return RestResponse.successResWithTokenData(id, "YGdykliy_+@124LK/");
		} catch (EhospitalServiceException eh) {
			logger.info("addSubcription error,message={}", eh.getMessage());
			return RestResponse.errorRes(eh.getMessage());
		} catch (Exception e) {
			logger.info("addSubcription error,message={}", e.getMessage());
			return RestResponse.errorRes("系统异常,请稍后再试");
		}
	}

	@RequestMapping(value = "/querySub", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse querySubscription(HttpServletRequest request) {
		ScheduleExample examples = new ScheduleExample();
		//根据doctorId获取排班信息
		String doctorId = request.getParameter("doctorId");
		//String doctorId = "1";
		Criteria criteria = examples.createCriteria();
		criteria.andDoctorIdEqualTo(StringUtils.isEmpty(doctorId)? 0 :Integer.parseInt(doctorId))
		.andClinicDateBetween(DateUtil.getNotToday(new Date(), 1), DateUtil.getNotToday(new Date(), 7))
		.andClinicStatusEqualTo(1);
		examples.setOrderByClause("clinic_date,clinic_time");
		List<Schedule> listSch = scheduleService.getScheduleInfo(examples);
		List<ScheduleVO> listVO = new ArrayList<ScheduleVO>();
		for (Schedule sch : listSch) {
			ScheduleVO vo = new ScheduleVO();
			vo.setScheduleId(sch.getScheduleId());
			vo.setClinicNo(sch.getClinicNo());
			vo.setClincTime(sch.getClinicTime());
			vo.setWeekStr(DateUtil.getWeekByDate(sch.getClinicWeek()));
			vo.setDateStr(DateUtil.parseDateToString(sch.getClinicDate()));
			listVO.add(vo);
		}
		return RestResponse.successResWithTokenData(listVO, "YGdykliy_+@124LK/");

	}

	@RequestMapping(value = "/queryUser", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse queryUser(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		//String userId = "31";
		UserExample example = new UserExample();
		example.createCriteria().andUserIdEqualTo(Integer.parseInt(userId));
		List<User> list = userService.findUsersByCondition(example);
		if (list.size() > 0) {
			return RestResponse.successResWithTokenData(list.get(0), "YGdykliy_+@124LK/");
		} else {
			return RestResponse.errorRes("系统数据异常，用户信息不存在");
		}
	}

	@RequestMapping(value = "/checkSubInfo", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse checkSubInfo(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		//String userId = "31";
		//String openId = "test_01_02";
		String name = request.getParameter("name");
		String idCard = request.getParameter("idCard");
		String telPhone = request.getParameter("telPhone");
		String scheduleId = request.getParameter("scheduleId");
		UserExample example = new UserExample();
		example.createCriteria().andUserIdEqualTo(Integer.parseInt(userId));
		List<User> list = userService.findUsersByCondition(example);
		User user2 = list.get(0);
		// if (!StringUtils.hasLength(user.getRealName())) {
		IdcardValidator idcardValidator = new IdcardValidator();
		if (!idcardValidator.isValidatedAllIdcard(idCard)) {
			return RestResponse.errorRes("身份证格式错误！");
		}
		UserVO user = new UserVO();
		IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(idCard);

		user.setRealName(name);
		user.setIdCard(idCard);
		user.setTelphone(telPhone);
		user.setSexStr(idcardInfoExtractor.getGender().equals("1") ? "男" : "女");
		user.setAge(idcardInfoExtractor.getAge() + "");
		// }
		SubscriptionExample exampleSub = new SubscriptionExample();
		exampleSub.createCriteria()
		.andScheduleIdEqualTo(Integer.parseInt(scheduleId))
		.andUserIdEqualTo(user2.getUserId())
		.andSubscriptionStatusNotEqualTo(4);
		List<Subscription> listsub = subscriptionService.querySubscription(exampleSub);
		if (listsub.isEmpty()) {
//	        user2.setRealName(user.getRealName());
//	        user2.setIdCard(user.getIdCard());
//	        user2.setTelephone(user.getTelphone());
//	        user2.setSex(Integer.parseInt(idcardInfoExtractor.getGender()));
//		    userService.updateByPrimaryKeySelective(user2);
			return RestResponse.successResWithData(user);
		}
		return RestResponse.errorRes("您已预约过该排班,请选择其他时间");
	}

	/**
	 * 用于展示确认预约页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryInfo", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse queryInfo(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String scheduleId = request.getParameter("scheduleId");
		UserExample ue = new UserExample();
		ue.createCriteria().andUserIdEqualTo(Integer.parseInt(userId));
		User user = userService.findUsersByCondition(ue).get(0);
		ScheduleExample se = new ScheduleExample();
		se.createCriteria().andScheduleIdEqualTo(Integer.parseInt(scheduleId));
		Schedule schedule = scheduleService.getScheduleInfo(se).get(0);
		SubscriptionVO vo = new SubscriptionVO();
		vo.setScheduleId(scheduleId);
		vo.setUserId(userId);
//		IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(user.getIdCard());
//		vo.setAge(idcardInfoExtractor.getAge() + "");
//		vo.setName(user.getRealName());
//		vo.setSex(idcardInfoExtractor.getGender().equals("1") ? "男" : "女");
//		vo.setTelphone(user.getTelephone());
		StringBuilder sb = new StringBuilder();
		sb.append(DateUtil.formatDate(schedule.getClinicDate()));
		sb.append(" ");
		sb.append(DateUtil.getWeekByDate(schedule.getClinicWeek()));
		sb.append(" ");
		sb.append(schedule.getClinicTime());
		vo.setDateString(sb.toString());
		return RestResponse.successResWithData(vo);

	}

	/**
	 * 取消预约
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cancleSubscription", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse cancleSubscription(HttpServletRequest request) {
		String subScriptionId = request.getParameter("subId");
		try {
			subscriptionService.cancleSubscription(subScriptionId);
		} catch (EhospitalServiceException ex) {
			return RestResponse.errorRes(ex.getMessage());
		} catch (Exception e) {
			return RestResponse.errorRes(e.getMessage());
		}
		return RestResponse.success();
	}

	/**
	 * 查询预约记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/querySubList", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse querySubscriptionList(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		List<SubscriptionVO> subVOList = new ArrayList<SubscriptionVO>();
		try {
			SubscriptionExample example = new SubscriptionExample();
			example.createCriteria().andUserIdEqualTo(Integer.parseInt(userId));
			example.setOrderByClause("subscription_date DESC ");
			List<Subscription> subList = subscriptionService.querySubscriptionByPage(example, 1, 50);
			for (Subscription sub : subList) {
				SubscriptionVO vo = new SubscriptionVO();
				vo.setName(sub.getPatientName());
				vo.setTelphone(sub.getPatientPhone());
				vo.setStatus(sub.getSubscriptionStatus().toString());
				StringBuilder sb = new StringBuilder();
				sb.append(DateUtil.formatDate(sub.getSubscriptionDate()));
				sb.append(" ");
				sb.append(DateUtil.getWeekByDate(DateUtil.dayForWeek(sub.getSubscriptionDate())));
				sb.append(" ");
				sb.append(sub.getSubscriptionTime());
				vo.setDateString(sb.toString());
				subVOList.add(vo);
			}
		} catch (EhospitalServiceException ex) {
			return RestResponse.errorRes(ex.getMessage());
		} catch (Exception e) {
			return RestResponse.errorRes(e.getMessage());
		}
		return RestResponse.successResWithData(subVOList);
	}

	@RequestMapping(value = "/querySubscription", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogs(operateInfo="查询预约信息")
	public RestResponse querySubscriptionNew(Integer userId, Integer currentPage, Integer pageSize) {
		logger.info("querySubscription,userId={},currentPage={},pageSize={}", userId, currentPage, pageSize);
		SubscriptionExample example = new SubscriptionExample();
		List<SubscriptionView> resultList = new ArrayList<SubscriptionView>();
		try {
			example.createCriteria().andUserIdEqualTo(userId);
			example.setOrderByClause("subscription_date DESC ");
			long count = subscriptionService.countSubsciption(example);
			if(count > 0) {
				List<Subscription> subList = subscriptionService.querySubscriptionByPage(example, currentPage, pageSize);
				for(Subscription item :subList) {
					SubscriptionView view= new SubscriptionView();
					view.setSubscriptionId(item.getSubscriptionId()+"");
					view.setCreateTime(item.getCreateTime());
					view.setSubscriptionStatus(item.getSubscriptionStatus());
					view.setSubscriptionSource(item.getSubscriptionSource());
					view.setSubscriptionFee(item.getSubscriptionFee());
					resultList.add(view);
				}
				
			}
			return RestResponse.successRes4Find(resultList, Integer.parseInt(count + ""));
		} catch (Exception e) {
			logger.error("查询用户信息出错,e={}", e);
			return RestResponse.errorRes("查询用户信息出错");
		}
	}

}
