package com.lee.mybatis.plugins;


import com.lee.mybatis.utils.CamelNameUtils;
import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * @author bruce
 */
public class InnerClassKeyPlugin extends PluginAdapter {

  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  @Override
  public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    this.generateInnerKeyClass(introspectedTable, introspectedTable.getAllColumns(), topLevelClass);
    return true;
  }

  @Override
  public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    this.generateInnerKeyClass(introspectedTable, introspectedTable.getAllColumns(), topLevelClass);
    return true;
  }

  @Override
  public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    this.generateInnerKeyClass(introspectedTable, introspectedTable.getAllColumns(), topLevelClass);
    return true;
  }

  private void generateInnerKeyClass(IntrospectedTable introspectedTable, List<IntrospectedColumn> introspectedColumns, TopLevelClass topLevelClass) {
    String outerClassFullyQualifiedName = topLevelClass.getType().getFullyQualifiedName();

    String innerKeyClassFullyQualifiedName = outerClassFullyQualifiedName + "Key";

    FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType(innerKeyClassFullyQualifiedName);

    InnerClass innerClass = new InnerClass(fullyQualifiedJavaType);
    innerClass.setFinal(true);
    innerClass.setStatic(true);
    innerClass.setVisibility(JavaVisibility.PUBLIC);

    for (Iterator iterator = introspectedColumns.iterator(); iterator.hasNext(); ) {
      IntrospectedColumn introspectedColumn = (IntrospectedColumn) iterator.next();
      String fieldJavaProperty = introspectedColumn.getJavaProperty();

      Field field = new Field();
      field.setVisibility(JavaVisibility.PUBLIC);
      field.setStatic(true);
      field.setFinal(true);
      field.setName(CamelNameUtils.toUnderlineName(fieldJavaProperty));
      field.setType(FullyQualifiedJavaType.getStringInstance());
      field.setInitializationString("\"" + CamelNameUtils.toUnderlineName(fieldJavaProperty).toLowerCase() + "\"");

      field.getJavaDocLines().add("/**");
      field.getJavaDocLines().add(" * " + introspectedColumn.getRemarks());
      field.getJavaDocLines().add(" */");

      innerClass.addField(field);
    }

    topLevelClass.addInnerClass(innerClass);
  }


}
