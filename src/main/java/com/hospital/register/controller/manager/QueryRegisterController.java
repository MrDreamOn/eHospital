package com.hospital.register.controller.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.hospital.register.annotation.TokenAccess;
import com.hospital.register.bean.Bonus;
import com.hospital.register.bean.BonusDetail;
import com.hospital.register.bean.Doctor;
import com.hospital.register.bean.Schedule;
import com.hospital.register.bean.ScheduleExample;
import com.hospital.register.bean.Subscription;
import com.hospital.register.bean.SubscriptionExample;
import com.hospital.register.bean.SubscriptionExample.Criteria;
import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.conditionVO.RegisterVO;
import com.hospital.register.conditionVO.UserVO;
import com.hospital.register.exception.EhospitalServiceException;
import com.hospital.register.service.BonusService;
import com.hospital.register.service.DoctorService;
import com.hospital.register.service.ScheduleService;
import com.hospital.register.service.SubscriptionService;
import com.hospital.register.service.UserService;
import com.hospital.register.util.DateUtil;
import com.hospital.register.util.RestResponse;
import com.hospital.register.util.idCard.IdcardInfoExtractor;
import com.hospital.register.util.idCard.IdcardValidator;
import com.hospital.register.vo.SubscriptionView;

@Controller
@RequestMapping(value = "/queryRegister")
public class QueryRegisterController {

	private static final Logger logger = LoggerFactory.getLogger(QueryRegisterController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private BonusService bonusService;
	
	@Autowired
	private DoctorService doctorService;

	/**
	 * 根据手机号判断是否是会员
	 * 
	 * @param telPhone
	 * @return
	 */

	@RequestMapping(value = "/queryTelphone", method = RequestMethod.POST)
	@ResponseBody
	@TokenAccess
	@OperateLogs(operateInfo = "查询预约信息")
	public RestResponse queryTelphone(HttpServletRequest request) {
		try {
			String telPhone = request.getParameter("telPhone");
			logger.info("queryUser,telPhone={}", telPhone);
			RegisterVO vo = new RegisterVO();
			UserExample example = new UserExample();
			example.createCriteria().andTelephoneEqualTo(telPhone);
			List<User> userList = userService.findUsersByCondition(example);
			if (userList.size() == 0) {
				vo.setIsRegister("1");
				return RestResponse.successResWithTokenData(vo, "YGdykliy_+@124LK/");
			}
			User user = userList.get(0);
			SubscriptionExample subExample = new SubscriptionExample();
			Criteria criteria = subExample.createCriteria();
			criteria.andSubscriptionDateEqualTo(DateUtil.formatDateToDate(new Date()));
			criteria.andUserIdEqualTo(user.getUserId());
			criteria.andSubscriptionStatusEqualTo(0);
			List<Subscription> sublist = subscriptionService.querySubscription(subExample);
			IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(user.getIdCard());
			if (sublist.size() == 0) {
				SubscriptionExample subExample2 = new SubscriptionExample();
				Criteria criteria2 = subExample2.createCriteria();
				criteria2.andSubscriptionStatusEqualTo(1);
				criteria2.andSubscriptionDateEqualTo(DateUtil.formatDateToDate(new Date()));
				criteria2.andUserIdEqualTo(user.getUserId());
				List<Subscription> sublist2 = subscriptionService.querySubscription(subExample2);
				if (sublist2.size() == 0) {
					vo.setIsRegister("2");
					vo.setUser(user);
					vo.setAge(idcardInfoExtractor.getAge());
					return RestResponse.successResWithTokenData(vo, "YGdykliy_+@124LK/");
				} else {
					vo.setIsRegister("4");
					vo.setSubscription(sublist2.get(0));
					ScheduleExample schExample = new ScheduleExample();
					schExample.createCriteria().andScheduleIdEqualTo(sublist2.get(0).getScheduleId());
					Schedule sch = scheduleService.getScheduleInfo(schExample).get(0);
					vo.setClinicTime(DateUtil.formatDate(sch.getClinicDate()) + " "
							+ DateUtil.getWeekByDate(sch.getClinicWeek()) + " 上午 10:00-12:00");
					vo.setCreateTime(DateUtil.formatDateTime(sublist2.get(0).getUpdateTime()));
					vo.setSubscription(sublist2.get(0));
					Doctor doctor = doctorService.getDoctorByPrimaryKey(sch.getDoctorId());
					vo.setDoctorName(doctor.getDoctorName());
					return RestResponse.successResWithTokenData(vo, "YGdykliy_+@124LK/");

				}
			}
			vo.setIsRegister("3");
			vo.setSubscription(sublist.get(0));
			ScheduleExample schExample = new ScheduleExample();
			schExample.createCriteria().andScheduleIdEqualTo(sublist.get(0).getScheduleId());
			Schedule sch = scheduleService.getScheduleInfo(schExample).get(0);
			vo.setClinicTime(DateUtil.formatDate(sch.getClinicDate()) + " "
					+ DateUtil.getWeekByDate(sch.getClinicWeek()) + " 上午 10:00-12:00");
			vo.setCreateTime(DateUtil.formatDateTime(sublist.get(0).getCreateTime()));
			Doctor doctor = doctorService.getDoctorByPrimaryKey(sch.getDoctorId());
			vo.setDoctorName(doctor.getDoctorName());
			return RestResponse.successResWithTokenData(vo, "YGdykliy_+@124LK/");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return RestResponse.errorRes(e.getMessage());
		}
	}

	/**
	 * 根据输入信息新增挂号
	 * 
	 * @param userVO
	 * @return
	 */

	@RequestMapping(value = "/addRegister", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogs(operateInfo = "新增挂号")
	public RestResponse addRegister(String telPhone, String idCard, String name, String status, int userId, int subId,String doctorId) {
		try {
			UserVO userVO = new UserVO();
			IdcardValidator idcardValidator = new IdcardValidator();
			if (!"3".equals(status)) {
				if (!idcardValidator.isValidatedAllIdcard(idCard)) {
					return RestResponse.errorRes("身份证格式错误！");

				}
				IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(idCard);
				userVO.setTelphone(telPhone);
				userVO.setIdCard(idCard);
				userVO.setRealName(name);
				userVO.setSex(Integer.parseInt(idcardInfoExtractor.getGender()));
				userVO.setBrithday(idcardInfoExtractor.getBirthday());
				userVO.setUserId(userId);
			}
			if ("1".equals(status)) {
				if(StringUtils.isEmpty(doctorId)) {
					return RestResponse.errorRes("请选择坐诊医生");
				}
				subscriptionService.addUserAndSubscription(userVO,doctorId);
			}

			if ("2".equals(status)) {
				if(StringUtils.isEmpty(doctorId)) {
					return RestResponse.errorRes("请选择坐诊医生");
				}
				subscriptionService.userAddSubscription(userVO,doctorId);
			}

			if ("3".equals(status)) {
				userVO.setSubscriptionId(subId);
				subscriptionService.updateSubscription(userVO);
			}

			return RestResponse.success();
		} catch (EhospitalServiceException eh) {
			logger.error(eh.getMessage());
			return RestResponse.errorRes(eh.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return RestResponse.errorRes(e.getMessage());
		}
	}

	/**
	 * 查询预约记录
	 * 
	 * @param telPhone
	 * @param startDate
	 * @param endDate
	 * @param clincSartDate
	 * @param clincEndDate
	 * @param currentPage
	 * @param pageSize
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/querySubscription", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse querySubscriptionList(String telPhone, String doctorId, String startDate, String endDate,
			String clincStartDate, String clincEndDate, int currentPage, int pageSize, int status) {
		logger.info("doctorId:{}",doctorId);
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		List<SubscriptionView> resultList = new ArrayList<SubscriptionView>();
		paramsMap.put("currentPage",currentPage);
		paramsMap.put("pageSize",pageSize);
		
		if (StringUtils.hasText(doctorId)) {
			paramsMap.put("doctorId",doctorId);
		}
		
		if (StringUtils.hasText(telPhone)) {
			paramsMap.put("telPhone",telPhone);
		}
		if (StringUtils.hasText(startDate)) {
			String[] dateArr = startDate.split(",");
			String startCreateDate = DateUtil.parseGMTDate(dateArr[0]);
			String endCreateDate = DateUtil.parseGMTDate(dateArr[1]);
			endCreateDate = endCreateDate+ " 23:59:59";
			paramsMap.put("startCreateDate",DateUtil.parseDateString(startCreateDate, "yyyy-MM-dd"));
			paramsMap.put("endCreateDate",DateUtil.parseDateString(endCreateDate, "yyyy-MM-dd HH:mm:ss"));
		}
		
		if (StringUtils.hasText(clincStartDate)) {
			String[] dateArr = clincStartDate.split(",");
			String startSubscriptionDate = DateUtil.parseGMTDate(dateArr[0]);
			String endSubscriptionDate = DateUtil.parseGMTDate(dateArr[1]);
			paramsMap.put("startSubscriptionDate",DateUtil.parseDateString(startSubscriptionDate, "yyyy-MM-dd"));
			paramsMap.put("endSubscriptionDate",DateUtil.parseDateString(endSubscriptionDate, "yyyy-MM-dd"));
		}
		
		if (-1 == status) {
			paramsMap.put("subscriptionStatus",0);
		} else if (0 == status) {
			paramsMap.put("subscriptionStatus",0);
			paramsMap.put("startSubscriptionDate",DateUtil.formatDateToDate(new Date()));
			paramsMap.put("endSubscriptionDate",DateUtil.formatDateToDate(new Date()));
		} else {
			paramsMap.put("subscriptionStatus",status);
		}
		
		long count = subscriptionService.countSubsciptionByMap(paramsMap);
		if(count > 0) {
			List<Map<String,Object>> mapList = subscriptionService.querySubscriptionByMap(paramsMap);
			for (Map<String,Object> map : mapList) {
				SubscriptionView view = new SubscriptionView();
				view.setSubscriptionId((int) map.get("subscription_id")+"");
				view.setCreateTime((Date) map.get("create_time"));
				view.setDoctorName((String) map.get("doctor_name"));
				view.setPatientName((String) map.get("patient_name"));
				view.setPatientPhone((String) map.get("patient_phone"));
				view.setSubscriptionDate((Date) map.get("subscription_date"));
				view.setSettleAmount(map.get("settle_amount") == null ? BigDecimal.ZERO : (BigDecimal) map.get("settle_amount"));
				resultList.add(view);
			}
		}
		return RestResponse.successRes4Find(resultList, Integer.parseInt(count + ""));
	}

	@RequestMapping(value = "/updateSubStatus", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogs(operateInfo = "更新预约状态")
	public RestResponse updateSubStatus(HttpServletRequest request) {
		String subid = request.getParameter("subid");
		String status = request.getParameter("status");
		String score = request.getParameter("score");
		String amount = request.getParameter("amount");
		Subscription sub = new Subscription();
		sub.setSubscriptionId(Integer.parseInt(subid));
		sub.setSubscriptionStatus(Integer.parseInt(status));
		sub.setUpdateTime(new Date());
		SubscriptionExample ex = new SubscriptionExample();
		ex.createCriteria().andSubscriptionIdEqualTo(sub.getSubscriptionId());
		Subscription subVO = subscriptionService.querySubscription(ex).get(0);
		if ("6".equals(status)) {
			if (StringUtils.hasText(score) && StringUtils.hasText(amount)) {
				Bonus bonusVO = new Bonus();
				bonusVO.setUserId(subVO.getUserId());
				List<Bonus> bList = bonusService.queryBonusByCondition(bonusVO);
				if (bList.size() == 0) {
					Bonus bonus = new Bonus();
					bonus.setBonusPoints(Integer.parseInt(score));
					bonus.setCreateTime(new Date());
					bonus.setUpdateTime(new Date());
					bonus.setUserId(subVO.getUserId());
					bonusService.addBonus(bonus);
					BonusDetail detail = new BonusDetail();
					detail.setBonusId(bonus.getBonusId());
					detail.setBonusPoints(Integer.parseInt(score));
					detail.setCompleteTime(new Date());
					detail.setSettleAmount(Long.parseLong(amount));
					detail.setSettleMode(1);
					detail.setSubscriptionId(subVO.getSubscriptionId());
					bonusService.addBonusDetail(detail);
				} else {
					Bonus b = bList.get(0);
					BonusDetail detail = new BonusDetail();
					detail.setBonusId(b.getBonusId());
					detail.setBonusPoints(Integer.parseInt(score));
					detail.setCompleteTime(new Date());
					detail.setSettleAmount(Long.parseLong(amount));
					detail.setSettleMode(1);
					detail.setSubscriptionId(subVO.getSubscriptionId());
					bonusService.addBonusDetail(detail);
					b.setBonusPoints(b.getBonusPoints() + detail.getBonusPoints());
					b.setUpdateTime(new Date());
					bonusService.updateBonus(b);
				}

			} else {
				return RestResponse.errorRes("请输入结算金额和积分");
			}

		}
		subscriptionService.updateSubStatus(sub);
		return RestResponse.successResWithTokenData(1, "YGdykliy_+@124LK/");
	}

	@RequestMapping(value = "/querySubscriptionDetail", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogs(operateInfo = "查询预约详情")
	public RestResponse querySubscriptionDetail(int subid) {
		SubscriptionExample subExample = new SubscriptionExample();
		Criteria criteria = subExample.createCriteria();
		criteria.andSubscriptionIdEqualTo(subid);
		List<Subscription> sublist = subscriptionService.querySubscription(subExample);
		if (sublist.size() > 0) {
			RegisterVO vo = new RegisterVO();
			vo.setSubscription(sublist.get(0));
			vo.setClinicTime(DateUtil.formatDate(sublist.get(0).getSubscriptionDate()) + " "
					+ sublist.get(0).getSubscriptionTime());
			vo.setCreateTime(DateUtil.formatDateTime(sublist.get(0).getUpdateTime()));
			ScheduleExample schExample = new ScheduleExample();
			schExample.createCriteria().andScheduleIdEqualTo(sublist.get(0).getScheduleId());
			Schedule sch = scheduleService.getScheduleInfo(schExample).get(0);
			Doctor doctor = doctorService.getDoctorByPrimaryKey(sch.getDoctorId());
			vo.setDoctorName(doctor.getDoctorName());
			return RestResponse.successResWithTokenData(vo, "YGdykliy_+@124LK/");
		}
		return RestResponse.successResWithTokenData(new RegisterVO(), "YGdykliy_+@124LK/");
	}
}
