package ${BasePackageName}.controller;

import ${BasePackageName}.model.${ClassName};
import ${BasePackageName}.controller.base.BaseController;
import ${BasePackageName}.service.${ClassName}Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* ${ClassName}Controller
* Created on ${Date}
**/
@RestController
@RequestMapping(value = "/${ClassName?uncap_first}")
public class ${ClassName}Controller extends BaseController<${ClassName}> {

    @Autowired
    private ${ClassName}Service ${ClassName?uncap_first}Service;

}