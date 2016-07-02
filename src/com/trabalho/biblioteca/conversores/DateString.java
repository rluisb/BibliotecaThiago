
package com.trabalho.biblioteca.conversores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="localDate")
public class DateString implements Converter {

 @Override
 public Object getAsObject(FacesContext arg0, UIComponent arg1, String dataString) {
  return LocalDateTime.parse(dataString);
 }

 @Override
 public String getAsString(FacesContext arg0, UIComponent arg1, Object localDateUnformated) {
  LocalDateTime Date = (LocalDateTime) localDateUnformated;
  return Date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
 }
}