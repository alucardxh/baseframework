package com.baseframework.example.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baseframework.example.pojo.Student;
import com.baseframework.utils.SpringContextUtils;
import com.baseframework.utils.XStreamPlus;
import com.thoughtworks.xstream.XStream;

@Controller
public class DataConvert {
	
	/**
	 * json字符串对象互转
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/json")
	public Student getJSON() {
		Student student = new Student();
		student.setAge("1");
		student.setName("success");

		// 对象------>json字符串
		String jsonStudent = JSON.toJSONString(student);
		System.out.println(jsonStudent);

		// json字符串------>对象
		Student student1 = JSON.parseObject(jsonStudent, Student.class);
		System.out.println(student1);
		return student1;
	}

	/**
	 * xml字符串对象互转
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/xml")
	public String getJson(HttpServletRequest request, Model model) {
		Student student = new Student();
		student.setAge("1");
		student.setName("success");

		// 设置XStream对象
		XStream xStream = new XStream();
		xStream.ignoreUnknownElements();
		xStream.alias("xml", Student.class);

		// 对象------>xml字符串
		String xmlStudent = xStream.toXML(student);
		System.out.println(xmlStudent);

		// xml字符串------>对象
		Student student1 = (Student) xStream.fromXML(xmlStudent);
		System.out.println(student1);

		// 设置增强版XStream
		XStreamPlus<Student> xp = new XStreamPlus<Student>(String.class);
		xp.alias("xml", Student.class);

		// 对象------>xml字符串
		String s1 = xp.toXML(student);

		System.out.println(s1);
		// xml字符串------>对象
		Student s = xp.fromXML(s1);
		return s1;
	}
	
	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();
		
		Student student = new Student();
		student.setAge("1");
		student.setName("success");
		
		Student student2 = new Student();
		student2.setAge("2");
		student2.setName("success2");
		list.add(student);
		list.add(student2);
		
		String jsonStudent = JSON.toJSONString(list);
		System.out.println(jsonStudent);
		
		
		
		List<Student> lists = JSONArray.parseArray(jsonStudent, Student.class);
		
		
		
		
		
	}
}
