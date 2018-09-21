package com.clientui.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.clientui.beans.ExpeditionBean;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-expedition")
public interface MicroserviceExpeditionProxy {

    @PostMapping(value = "/microservice-expedition/expeditions")
    ExpeditionBean ajouterExpedition(@RequestBody ExpeditionBean expedition);
    
    @GetMapping( value = "/microservice-expedition/expeditions/{id}")
    ExpeditionBean recupererUneExpedition(@PathVariable("id") int id);
}
