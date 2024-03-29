package com.local.web;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.local.domain.User;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class UserControllerTest {
    @Test
    public void testhandle11(){

    }

    @Test
	public void testhandle41() {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("userName", "tom");
		form.add("password", "123456");
		form.add("age", "45");
		restTemplate.postForLocation(
				"http://localhost:8080/baobaotao/user/handle41.html",form);
	}
	
	@Test
	public void testhandle42() throws IOException{
		RestTemplate restTemplate = new RestTemplate();
		byte[] response = restTemplate.postForObject(
				"http://localhost:8080/baobaotao/user/handle42/{itemId}.html",null,byte[].class,"1233");
		Resource outFile = new FileSystemResource("d:/image_copy.jpg");
		FileCopyUtils.copy(response, outFile.getFile());
	}	

	@Test
	public void testhandle43() {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("userName", "tom");
		form.add("password", "123456");
		form.add("age", "45");
		restTemplate.postForLocation(
				"http://localhost:8080/baobaotao/user/handle43.html",form);
	}

    @Test
	public void testhandle44() throws IOException{
		RestTemplate restTemplate = new RestTemplate();
		byte[] response = restTemplate.postForObject(
				"http://localhost:8080/baobaotao/user/handle44/{itemId}.html",null,byte[].class,"1233");
		Resource outFile = new FileSystemResource("d:/image_copy.jpg");
		FileCopyUtils.copy(response, outFile.getFile());
	}
	
    @Test
	public void testhandle51WithXml(){	
		
		RestTemplate restTemplate = buildRestTemplate();
		
		User user = new User();
		user.setUserName("tom");
		user.setPassword("1234");
		user.setRealName("汤姆");	
		
		HttpHeaders entityHeaders = new HttpHeaders();
		entityHeaders.setContentType(MediaType.valueOf("application/xml;UTF-8"));
		entityHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		HttpEntity<User> requestEntity = new HttpEntity<User>(user,entityHeaders);

			
		ResponseEntity<User> responseEntity = restTemplate.exchange(
			   "http://localhost:8080/baobaotao/user/handle51.html",
			   HttpMethod.POST,requestEntity,User.class);
		
		User responseUser = responseEntity.getBody(); 
		Assert.assertNotNull(responseUser);
		Assert.assertEquals("1000", responseUser.getUserId());
		Assert.assertEquals("tom", responseUser.getUserName());
		Assert.assertEquals("汤姆", responseUser.getRealName());
	}
	
	private RestTemplate buildRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
        
		//①创建MarshallingHttpMessageConverter
		XStreamMarshaller xmlMarshaller = new XStreamMarshaller();
		xmlMarshaller.setStreamDriver(new StaxDriver());
		xmlMarshaller.setAnnotatedClasses(new Class[]{User.class});
		
		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter(); 
		xmlConverter.setMarshaller(xmlMarshaller);
		xmlConverter.setUnmarshaller(xmlMarshaller);
		restTemplate.getMessageConverters().add(xmlConverter);
		
		
		//②创建MappingJacksonHttpMessageConverter		
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();		
		restTemplate.getMessageConverters().add(jsonConverter);
		return restTemplate;
	}	

    @Test
	public void testhandle51WithJson(){
		
		RestTemplate restTemplate = buildRestTemplate();
		
		User user = new User();
		user.setUserName("tom");
		user.setPassword("1234");
		user.setRealName("tomson");	
		user.setBirthday(new Date());
		user.setSalary(10000L);
		
		HttpHeaders entityHeaders = new HttpHeaders();
		entityHeaders.setContentType(MediaType.valueOf("application/json;UTF-8"));
		entityHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<User> requestEntity = new HttpEntity<User>(user,entityHeaders);

			
		ResponseEntity<User> responseEntity = restTemplate.exchange(
			 "http://localhost:8080/baobaotao/user/handle51.html",
			 HttpMethod.POST,requestEntity,User.class);
		
		User responseUser = responseEntity.getBody(); 
		Assert.assertNotNull(responseUser);
		Assert.assertEquals("1000", responseUser.getUserId());
		Assert.assertEquals("tom", responseUser.getUserName());
	}

	@Test
	public void testhandle61() {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("userName", "tom");
		form.add("password", "123456");
		form.add("age", "45");
		String html = restTemplate.postForObject(
				"http://localhost:8080/baobaotao/user/handle61.html",form,String.class);
		Assert.assertNotNull(html);
		Assert.assertTrue(html.indexOf("1000")>-1);
	}
	
	@Test
	public void testhandle62() {
		RestTemplate restTemplate = new RestTemplate();
		String html = restTemplate.postForObject(
				"http://localhost:8080/baobaotao/user/handle62.html",null,String.class);
		Assert.assertNotNull(html);
		Assert.assertTrue(html.indexOf("1001")>-1);
	}	

	
	@Test
	public void testhandle63() {
		RestTemplate restTemplate = new RestTemplate();
		String html = restTemplate.postForObject(
				"http://localhost:8080/baobaotao/user/handle63.html",null,String.class);
		Assert.assertNotNull(html);
		Assert.assertTrue(html.indexOf("1001")>-1);
	}
	
	@Test
	public void testhandle71() {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("userName", "tom");
		form.add("password", "123456");
		form.add("age", "45");
		restTemplate.postForLocation("http://localhost:8080/baobaotao/user/handle71.html", form);
	}	
	
	@Test
	public void testhandle81() {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("user", "tom:123456:tomson");		
		String html = restTemplate.postForObject(
				"http://localhost:8080/baobaotao/user/handle81.html",form,String.class);
		Assert.assertNotNull(html);
		Assert.assertTrue(html.indexOf("tom")>-1);
	}
	
	@Test
	public void testhandle82() {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("userName", "tom");
		form.add("password", "123456");
		form.add("age", "45");
		form.add("birthday", "1980-01-01");
		form.add("salary", "4,500.00");
		String html = restTemplate.postForObject(
				"http://localhost:8080/baobaotao/user/handle82.html",form,String.class);
		Assert.assertNotNull(html);
		Assert.assertTrue(html.indexOf("tom")>-1);
	}
	
	@Test
	public void testhandle91() {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("userName", "tom");
		form.add("password", "12345");
		form.add("birthday", "1980-01-01");
		form.add("salary", "4,500.00");
		String html = restTemplate.postForObject(
				"http://localhost:8080/baobaotao/user/handle91.html",form,String.class);
		Assert.assertNotNull(html);
		Assert.assertTrue(html.indexOf("tom")>-1);
	}		
}
