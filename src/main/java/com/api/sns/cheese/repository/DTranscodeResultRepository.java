package com.api.sns.cheese.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.api.sns.cheese.domain.DTranscodeResult;

@EnableScan
public interface DTranscodeResultRepository extends CrudRepository<DTranscodeResult, String> {

	List<DTranscodeResult> findByFileKey(String fileKey);
}
