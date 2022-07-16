package com.automvc.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Kingstar
 * @since 1.0
 */
@FeignClient(value="ebanking",contextId="Service",path="account")
public interface Service extends CommOp{
	
}