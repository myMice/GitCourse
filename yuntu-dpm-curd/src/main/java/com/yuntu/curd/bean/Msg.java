package com.yuntu.curd.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * ��������ͨ�õ�������
 * 
 * @author Administrator
 *
 */
public class Msg {
	// ״̬�� 200 �ɹ� 400 ʧ��
	private int code;
	// ��ʾ��Ϣ(�����ɹ�,����ʧ��)
	private String msg;
	// �û�Ҫ���ظ������������
	private Map<String, Object> extend = new HashMap<String, Object>();

	// �ɹ�,ͬʱ����msg����
	public static Msg success() {
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("�ɹ�");
		return result;
	}

	// ʧ��,ͬʱ����msg����
	public static Msg fail() {
		Msg result = new Msg();
		result.setCode(400);
		result.setMsg("ʧ��");
		return result;
	}

	// �����Ϣ��msg������,ͬʱ����msg����
	public Msg add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
}
