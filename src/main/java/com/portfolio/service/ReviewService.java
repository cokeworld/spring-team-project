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
import com.portfolio.mapper.MysqlMapper;
import com.portfolio.mapper.ReviewMapper;

import lombok.extern.java.Log;

@Log
@Transactional
@Service
public class ReviewService {
	
	@Autowired 
	private ReviewMapper reviewMapper;
	
	@Autowired
	private HostMapper hostMapper;
	
	@Autowired
	private ImagesMapper imagesMapper;
	
	@Autowired
	private MysqlMapper mysqlMapper;
	
	@Transactional
	public int addReview(ReviewVo reviewVo) {
		int num = mysqlMapper.getNextNum("airbnb_review");
		
		reviewMapper.addReview(reviewVo);
		
		return num;
	}
	
	public ReviewVo getReivewInfo(int num) {
		return reviewMapper.getReviewInfo(num);
	}
	
	@Transactional
	public Map<String, Object> getReviewContent(int num) {
		ReviewVo reviewVo = getReivewInfo(num);
		
		HostVo hostVo = hostMapper.getContentInfo(reviewVo.getNoNum());
		ImagesVo imagesVo = imagesMapper.getImageByNoNum(reviewVo.getNoNum());
		hostVo.setImageVo(imagesVo);
		
		int count = countReviewByNoNum(reviewVo.getNoNum());
		
		Map<String, Object> reviewContent = new HashMap<>();
		reviewContent.put("reviewVo", reviewVo);
		reviewContent.put("hostVo", hostVo);
		reviewContent.put("count", count);
		
		return reviewContent;
	}
	
	public List<ReviewVo> getReviewsByNoNum(int noNum) {
		return reviewMapper.getReviewsByNoNum(noNum);
	}
	
	public int countReviewByNoNum(int noNum) {
		return reviewMapper.countReviewByNoNum(noNum);
	}
	
	public int countReviewById(String id) {
		return reviewMapper.countReviewById(id);
	}
	
	public int updateReviewInfo(ReviewVo reviewVo) {
		reviewVo.setId("test");
		return reviewMapper.updateReviewInfo(reviewVo);
	}
	
	public int deleteReviewInfo(int num) {
		return reviewMapper.deleteReviewInfo(num);
	}
	
	@Transactional
	public Map<String, Object> getContentInfoForReview(int num) {
		HostVo hostVo = hostMapper.getContentInfo(num);
		ImagesVo imagesVo = imagesMapper.getImageByNoNum(num);
		hostVo.setImageVo(imagesVo);
		
		int count = countReviewByNoNum(num);
		
		Map<String, Object> reviewContent = new HashMap<>();
		reviewContent.put("hostVo", hostVo);
		reviewContent.put("count", count);
		
		return reviewContent;
	}
	
	@Transactional
	public List<ReviewVo> getContentForReviewList(String id, int startRow) {
		List<ReviewVo> reviewList = reviewMapper.getReviewsById(id, startRow);
		
		for(ReviewVo reviewVo: reviewList) {
			HostVo hostVo = hostMapper.getContentInfo(reviewVo.getNoNum());
			reviewVo.setHostVo(hostVo);
			
			ImagesVo imagesVo = imagesMapper.getImageByNoNum(reviewVo.getNoNum());
			reviewVo.setImagesVo(imagesVo);
			
			int count = countReviewByNoNum(reviewVo.getNoNum());
			reviewVo.setCount(count);
		}
		
		return reviewList;
	}
}
