package com.aak.configuration;

import java.util.Collection;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

/**
 *
 * @author ahmed
 * @date 21.5.18
 */
public class SplitCollectionEditor extends CustomCollectionEditor {

  private final Class<? extends Collection> collectionType;
  private final String splitRegex;

  public SplitCollectionEditor(Class<? extends Collection> collectionType, String splitRegex) {
    super(collectionType, true);
    this.collectionType = collectionType;
    this.splitRegex = splitRegex;
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    if (text == null || text.isEmpty()) {
      super.setValue(super.createCollection(this.collectionType, 0));
    } else {
      super.setValue(text.split(splitRegex));
    }
  }
}
