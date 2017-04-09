package sample1.common.web;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class FlashBean {

  private Map<Class<?>, Object> managedMap = new HashMap<Class<?>, Object>();

  public void putInManagedMap(Object object) {
    managedMap.put(object.getClass(), object);
  }

  /**
   * obtiene el managed bean del mapa.
   * @param clazz clase a buscar
   * @return la instancia de la clase 
   */
  @SuppressWarnings("unchecked")
  public <T> T getFromManagedMap(Class<T> clazz) {
    Object result = managedMap.get(clazz);
    managedMap.remove(clazz);
    return (T) result;
  }
}
