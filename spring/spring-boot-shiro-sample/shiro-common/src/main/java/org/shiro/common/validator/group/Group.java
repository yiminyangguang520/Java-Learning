package org.shiro.common.validator.group;

import javax.validation.GroupSequence;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午9:46:07
 * 类说明：定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
