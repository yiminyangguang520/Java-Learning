/**
 *
 */
package com.devglan.model;

import java.beans.PropertyEditorSupport;

/**
 * @author only2dhir
 */
public class QuestionCategoryConverter extends PropertyEditorSupport {

  @Override
  public void setAsText(final String text) throws IllegalArgumentException {
    setValue(QuestionCategory.fromValue(text));
  }

}
