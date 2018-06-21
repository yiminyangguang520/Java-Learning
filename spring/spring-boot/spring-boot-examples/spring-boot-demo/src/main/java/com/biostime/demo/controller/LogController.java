package com.biostime.demo.controller;

import com.biostime.autodoc.annotations.AutoDocMethod;
import com.biostime.autodoc.annotations.ModelType;
import com.biostime.demo.bean.LogBean;
import com.biostime.demo.bean.LogIdBean;
import com.biostime.demo.bean.base.BaseRequest;
import com.biostime.demo.bean.base.BaseResponse;
import com.biostime.demo.domain.Log;
import com.biostime.demo.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Function:Log控制层
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2016/11/11
 * Time: 14:20
 */
@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {

    @Autowired
    private LogService logService;
    /**
     * 根据id获取日志
     *
     * @param baseRequest
     * @return
     */
    @RequestMapping(value = "/getLog", method = {RequestMethod.POST})
    @AutoDocMethod(version = "v-0.0.1", name = "根据id获取日志", description = "根据id获取日志", author = "12360", model = ModelType.MKT, updateTime = "2016-11-11")
    public BaseResponse<LogBean> getLog(@RequestBody BaseRequest<LogIdBean> baseRequest) {
        BaseResponse<LogBean> baseResponse = new BaseResponse<>(baseRequest.getSeqNo());
        try {
            LogIdBean logIdBean = baseRequest.getRequest();
            LogBean logBean = new LogBean();
            Log log = logService.findById(logIdBean.getId());
            BeanUtils.copyProperties(log, logBean);
            baseResponse.setResponse(logBean);
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
            baseResponse = new BaseResponse(500, ex.getMessage());
        }
        return baseResponse;
    }
}
