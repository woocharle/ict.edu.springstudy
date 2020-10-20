package com.ict.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;




//자바 1.8이상 
//일반 컨트롤러와 달리 ModelAndView를 반환하기 않음.
//단순 문자열(String = text), json, xml 중 하나로 반환

@RestController
public class Ajax_Controller {
	// 단순문자인 경우 문서의 타입은 contentType="text/html" 타입으로 
	// 알아서 처리한다. 내용을 http://localhost:8090/XXXX.do로 볼 수 있다.
	
	@RequestMapping("text.do")
	@ResponseBody
	public String Hello() {
		return "<h2>Hello</h2>";
	}
	
	@RequestMapping(value="xmltest.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String XML_Test() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<products>");
		sb.append("<product>");
		sb.append("<name>딸기우유</name>");
		sb.append("<price>1000</price>");
		sb.append("</product>");
		sb.append("<product>");
		sb.append("<name>초코우유</name>");
		sb.append("<price>1000</price>");
		sb.append("</product>");
		sb.append("<product>");
		sb.append("<name>커피우유</name>");
		sb.append("<price>1000</price>");
		sb.append("</product>");
		sb.append("<product>");
		sb.append("<name>흰우유</name>");
		sb.append("<price>900</price>");
		sb.append("</product>");
		sb.append("</products>");
		
		return sb.toString();
	}
	
	@RequestMapping(value="xmltest2.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String XML_Test1() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<products>");
		sb.append("<product price=\"1000\" name=\"딸기우유\"/>");
		sb.append("<product price=\"1000\" name=\"초코우유\"/>");
		sb.append("<product price=\"1000\" name=\"커피우유\"/>");
		sb.append("<product price=\"900\" name=\"그냥우유\"/>");
		sb.append("</products>");
		
		return sb.toString();
	}
	
	@RequestMapping(value="xmltest3.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String XML_Test3() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<products>");
		sb.append("<product price=\"1000\">딸기우유</product>");
		sb.append("<product price=\"1000\">초코우유</product>");
		sb.append("<product price=\"1000\">커피우유</product>");
		sb.append("<product price=\"900\">그냥우유</product>");
		sb.append("</products>");
		
		return sb.toString();
	}
	
	//외부에서 xml파일을 읽어서 파싱하기 \
	//DOM 방식
	@RequestMapping(value="xmltest4.do", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String XML_Test4() {
		StringBuffer sb = new StringBuffer();
		sb.append("<table><thead><th>지역</th><th>온도</th><th>상태</th><th>아이콘</th></thead>");
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			URL url = new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml"); 
			InputStream is = url.openStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			Document document = builder.parse(bis);
			
			NodeList local = document.getElementsByTagName("local");
			for (int i = 0; i < local.getLength(); i++) {
				sb.append("<tr>");
				sb.append("<td>"+local.item(i).getFirstChild().getNodeValue()+"</td>");
				sb.append("<td>"+((Element)(local.item(i))).getAttribute("ta")+"</td>");
				sb.append("<td>"+((Element)(local.item(i))).getAttribute("desc")+"</td>");
				sb.append("<td> <img src='http://www.kma.go.kr/images/icon/NW/NB"
						+((Element)(local.item(i))).getAttribute("icon")+".png'>");
				sb.append("</tr>");
				
			}
			sb.append("</tbody></table>");
			
		}catch (Exception e) {
			
		}
		
		return sb.toString();
	}
	
	@RequestMapping(value="xmltest5.do", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String XML_Test5() {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml"); 
			URLConnection con = url.openConnection();
			BufferedReader br = new BufferedReader(
									new InputStreamReader(con.getInputStream(), "utf-8"));
			
			String msg = "";
			while((msg= br.readLine()) != null) {
				sb.append(msg);
			}
	
		}catch (Exception e) {
			
		}
		
		return sb.toString();
	}
	
	//자동으로 json생성
	@RequestMapping(value="jsontest.do", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String json_Test1() {
		StringBuffer sb = new StringBuffer();
		try {
			sb.append("[");
			sb.append("{\"name\":\"호두우유\", \"price\":\"12000\"},");
			sb.append("{\"name\":\"딸기우유\", \"price\":\"10000\"},");
			sb.append("{\"name\":\"메론우유\", \"price\":\"11000\"},");
			sb.append("{\"name\":\"망고우유\", \"price\":\"12000\"}");
			sb.append("]");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return sb.toString();

	}
	
	@RequestMapping(value="jsontest2.do", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String json_Test2() {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL("http://openapi.seoul.go.kr:8088/sample/json/SeoulLibraryTime/1/5/"); 
			URLConnection con = url.openConnection();
			BufferedReader br = new BufferedReader(
									new InputStreamReader(con.getInputStream(), "utf-8"));
			
			String msg = "";
			while((msg= br.readLine()) != null) {
				sb.append(msg);
			}
	
		}catch (Exception e) {
			
		}
		
		return sb.toString();
		
	}
	
}
