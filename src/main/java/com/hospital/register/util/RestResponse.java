package com.hospital.register.util;

import java.util.HashMap;
/**
 * used to integrates with restful data stores.<br>
 * the data store use following format to verify the result:
 * 
 * <pre>
 *  "{"success":false,"data":"","message":"VERBOSE ERROR"}"
 * </pre>
 * 
 * @author paul.
 */
public class RestResponse extends HashMap<String, Object> {

	private static final long serialVersionUID = -2791356338016228077L;

	public RestResponse() {
		super();
	}

	public RestResponse(final boolean success) {
		super();
		put("success", success);
	}

	public static RestResponse success() {
		return new RestResponse(true);
	}

	public static RestResponse successRes4Find(final Object data, final Integer total) {
		final RestResponse response = new RestResponse(true);
		response.setData(data);
		response.put("total", total);
		return response;
	}

	public static RestResponse successResWithData(final Object data) {
		final RestResponse response = new RestResponse(true);
		response.setData(data);
		return response;
	}

	public static RestResponse errorRes(final String error) {
		final RestResponse response = new RestResponse(false);
		response.setErrorMsg(error);
		return response;
	}

	public void setData(final Object data) {
		put("data", data);
	}

	public Object getData() {
		return get("data");
	}

	public boolean isSuccess() {
		return (Boolean) get("success");
	}

	public void setSuccess(final boolean success) {
		put("success", success);
	}

	public void setErrorMsg(final String errorMsg) {
		put("error", errorMsg);
	}

	public String getErrorMsg() {
		return (String) get("error");
	}
}
