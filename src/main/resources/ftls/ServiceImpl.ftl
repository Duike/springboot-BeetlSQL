package ${BasePackageName}.service;

import ${BasePackageName}.mapper.${ClassName}Dao;
import ${BasePackageName}.model.${ClassName};
import ${BasePackageName}.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*
* ${ClassName}Service
* Created on ${Date}
**/
@Service
public class ${ClassName}Service extends BaseServiceImpl<${ClassName}> {

    @Autowired
    private ${ClassName}Dao ${ClassName?uncap_first}Dao;

}