package ${package.Controller};

import cn.hutool.log.LogFactory;
import com.joauth2.bean.${entity};
import ${package.Service}.${table.serviceName};
import com.joauth2.config.log.annotation.LogSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>

<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */

@Controller
@RequestMapping("${table.entityPath}")
@LogSubject("${table.comment}")
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass}<${entity}, ${table.serviceName}>  {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} service;

    @Override
    protected ${table.serviceName} getService() {
        return service;
    }

    @Override
    protected cn.hutool.log.Log getLog() {
        return LogFactory.get();
    }

    @GetMapping("/")
    public String list(){
      return "${table.entityPath}/list";
    }

}

