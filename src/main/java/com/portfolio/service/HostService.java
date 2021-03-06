package com.portfolio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.domain.HostVo;
import com.portfolio.domain.ImagesVo;
import com.portfolio.domain.ReviewVo;
import com.portfolio.mapper.HostMapper;
import com.portfolio.mapper.ImagesMapper;
import com.portfolio.mapper.ReviewMapper;

import lombok.extern.java.Log;

@Service
@Log
@Transactional
public class HostService {
	
	@Autowired
	private HostMapper hostMapper;
	
	@Autowired
	private ImagesMapper imagesMapper;
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	public void addContent(HostVo hostVo) {
		hostMapper.addContent(hostVo);
	}
	
	public void addContentAndImages(HostVo hostVo, List<ImagesVo> imagesList) {
		hostMapper.addContent(hostVo);
		
		for(ImagesVo imagesVo : imagesList) {
			log.info(imagesVo.toString());
			imagesMapper.addImage(imagesVo);
		}
	}
	
	public HostVo getHostVo(int num) {
		return hostMapper.getContentInfo(num);
	}
	
	@Transactional
	public Map<String, Object> getContentInfo(int num) {
		Map<String, Object> contentInfo = new HashMap<>();
		
		contentInfo.put("hostVo", hostMapper.getContentInfo(num));
		contentInfo.put("imageList", imagesMapper.getImagesByNum(num));
		contentInfo.put("reviewList", reviewMapper.getReviewsByNoNum(num));
		contentInfo.put("reviewListFour", reviewMapper.getReviewsByNoNumFour(num));
		contentInfo.put("count", reviewMapper.countReviewByNoNum(num));
		List<ReviewVo> reviewList = reviewMapper.getReviewsByNoNum(num);
		
		Double sum = 0.0;
		for(ReviewVo reviewVo : reviewList) {
			sum += Double.valueOf(reviewVo.getScore());
		}
		
		contentInfo.put("score", sum/reviewList.size());
		
		return contentInfo;
	}
	
	@Transactional
	public int updateContentInfo(HostVo hostVo, String type) {
		if(type.equals("houseType")) {
			String classification = hostVo.getClassification();
			String houseType = hostVo.getHouseType();
			String stayType = hostVo.getStayType();
			
			hostVo = hostMapper.getContentInfo(hostVo.getNum());
			hostVo.setClassification(classification);
			hostVo.setHouseType(houseType);
			hostVo.setStayType(stayType);
			
			return hostMapper.updateContentInfo(hostVo);
		} else if(type.equals("personAndBed")) {
			int countOfPerson = hostVo.getCountOfPerson();
			int countOfBedroom = hostVo.getCountOfBedroom();
			int countOfBed = hostVo.getCountOfBed();
			int countOfSofeBed = hostVo.getCountOfSofeBed();
			int countOfSofe = hostVo.getCountOfSofe();
			int countOfBlanket = hostVo.getCountOfBlanket();
			
			hostVo = hostMapper.getContentInfo(hostVo.getNum());
			hostVo.setCountOfPerson(countOfPerson);
			hostVo.setCountOfBedroom(countOfBedroom);
			hostVo.setCountOfBed(countOfBed);
			hostVo.setCountOfSofeBed(countOfSofeBed);
			hostVo.setCountOfSofe(countOfSofe);
			hostVo.setCountOfBlanket(countOfBlanket);
			
			return hostMapper.updateContentInfo(hostVo);
		} else if(type.equals("bathroom")) {
			int countOfBathroom = hostVo.getCountOfBathroom();
			
			hostVo = hostMapper.getContentInfo(hostVo.getNum());
			hostVo.setCountOfBathroom(countOfBathroom);
			
			return hostMapper.updateContentInfo(hostVo);
		} else if(type.equals("address")) {
			String postcode = hostVo.getPostcode();
			String address1 = hostVo.getAddress1();
			String address2 = hostVo.getAddress2();
			
			hostVo = hostMapper.getContentInfo(hostVo.getNum());
			hostVo.setPostcode(postcode);
			hostVo.setAddress1(address1);
			hostVo.setAddress2(address2);
			
			return hostMapper.updateContentInfo(hostVo);
		} else if(type.equals("amenities")) {
			String amenities = hostVo.getAmenities();
			
			hostVo = hostMapper.getContentInfo(hostVo.getNum());
			hostVo.setAmenities(amenities);
			
			return hostMapper.updateContentInfo(hostVo);
		} else if(type.equals("safety")) {
			String safety = hostVo.getSafety();
			
			hostVo = hostMapper.getContentInfo(hostVo.getNum());
			hostVo.setSafety(safety);
			
			return hostMapper.updateContentInfo(hostVo);
		} else if(type.equals("usefull")) {
			String usefull = hostVo.getUsefull();
			
			hostVo = hostMapper.getContentInfo(hostVo.getNum());
			hostVo.setUsefull(usefull);
			
			return hostMapper.updateContentInfo(hostVo);
		} else if(type.equals("hostComment")) {
			String hostComment = hostVo.getHostComment();
			
			hostVo = hostMapper.getContentInfo(hostVo.getNum());
			hostVo.setHostComment(hostComment);
			
			return hostMapper.updateContentInfo(hostVo);
		} else if(type.equals("cost")) {
			int cost = hostVo.getCost();
			
			hostVo = hostMapper.getContentInfo(hostVo.getNum());
			hostVo.setCost(cost);
			
			return hostMapper.updateContentInfo(hostVo);
		} else if(type.equals("title")) {
			String title = hostVo.getTitle();
			
			hostVo = hostMapper.getContentInfo(hostVo.getNum());
			hostVo.setTitle(title);
			
			return hostMapper.updateContentInfo(hostVo);
		}
		return -1;
	}
	
	@Transactional
	public void updateAddImagesAndDelImages(
		List<ImagesVo> imagesList, List<Integer> delFileNums) {
		for(ImagesVo imagesVo: imagesList) {
			imagesMapper.addImage(imagesVo);
		}
		
		if(delFileNums != null)
			imagesMapper.deleteImagesByNums(delFileNums);
	}
	
	public void deleteContentAndImages(int num) {
		hostMapper.deleteContentInfo(num);
		
		imagesMapper.deleteImagesByNoNum(num);
	}
}
