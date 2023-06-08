package com.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * 逻辑删除
 * Author: leell
 * Date: 2023/4/3 20:50:04
 */
public class LogicDeletePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 修改Mapper类
     */
    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
//        addLogicDeleteMethod(interfaze);
        return true;
    }

    private void addLogicDeleteMethod(Interface interfaze) {
        // 方法的返回值
        FullyQualifiedJavaType returnTypeInt = FullyQualifiedJavaType.getIntInstance();
        Method logicDeleteMethod = new Method("deleteLogicalByPrimaryKey");
        // 1.设置方法可见性
        logicDeleteMethod.setVisibility(JavaVisibility.PUBLIC);
        logicDeleteMethod.setAbstract(true);
        // 2.设置返回值类型 int类型
        logicDeleteMethod.setReturnType(returnTypeInt);
        // 3.设置方法名
//        logicDeleteMethod.setName("logicDelete");
        // 4.设置参数列表
        FullyQualifiedJavaType paramType = PrimitiveTypeWrapper.getLongInstance();
        logicDeleteMethod.addParameter(new Parameter(paramType, "id", "@Param(\"id\")"));
        interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        interfaze.addMethod(logicDeleteMethod);
    }

    /**
     * 修改Mapper.xml
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        addLogicDeleteXml(document, introspectedTable);
        return true;
    }

    private void addLogicDeleteXml(Document document, IntrospectedTable introspectedTable) {
        XmlElement logicDeleteElement = new XmlElement("update");
        logicDeleteElement.addAttribute(new Attribute("id", "deleteLogicalByPrimaryKey"));
        logicDeleteElement.addAttribute(new Attribute("parameterType", "java.lang.Long"));
        logicDeleteElement.addElement(new TextElement("update " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));
        logicDeleteElement.addElement(new TextElement("set deleted = 1 where id = #{id,jdbcType=BIGINT}"));
        document.getRootElement().addElement(logicDeleteElement);
    }
}