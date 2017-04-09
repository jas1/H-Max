package sample1.common.web;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class FacesUtils {

  /**
   * get managed bean para facelets desde clase.
   * @param abeanClass clase del bean
   * @return el nombre en caso de estar definido en la annotation
   */
  public static String getManagedBeanNameForFacelets(Class<?> abeanClass) {

    if (abeanClass.getAnnotation(ManagedBean.class) == null) {
      throw new RuntimeException("Esto no es un Managed Bean");
    }

    String beanName = abeanClass.getSimpleName();

    // valor defautl
    beanName = Character.toLowerCase(beanName.charAt(0))
        + (beanName.length() > 1 ? beanName.substring(1) : "");

    try {
      // valor en caso de que se especifique un nombre
      beanName = abeanClass.getAnnotation(ManagedBean.class).name().isEmpty()
          ? beanName
          : abeanClass.getAnnotation(ManagedBean.class).name();
    } catch (Exception exc) {
      // aca no hacemos naranja, porque toma el valor default
    }

    return beanName;
  }

  /**
   * para guardar en el flash scope un objeto usa de key el cannonical name del objeto.
   * 
   * @param aobject objeto a guardar
   */

  public static void setFlashObject(Object aobject) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ((FlashBean) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(),
        null, "flashBean")).putInManagedMap(aobject);
  }

  public static void setFlashObject(String akey, Object aobject) {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    flash.put(akey, aobject);
  }

  /**
   * para obtener el objeto del flashscope con la key enviada
   * 
   * @param akey key del objeto
   * @return el objeto encontrado.
   */
  public static Object getFlashObject(String akey) {
    Flash lflash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    Object lret = lflash.get(akey);
    return lret;
  }

  /**
   * para obtener el objeto del flashscope con la key enviada.
   * 
   * @param akeyclass clase de la cual se va a utilizar el canonical name como key para buscar.
   * @return el objeto encontrado.
   */
  public static <T> T getFlashObject(Class<T> akeyclass) {
    return ((FlashBean) getManagedBean(FlashBean.class)).getFromManagedMap(akeyclass);
  }

  /**
   * Devuelve un 'managed bean' por nombre.
   * 
   * @param <T> bean class
   */

  @SuppressWarnings("unchecked")
  public static <T> T getManagedBean(Class<T> abeanClass) {
    FacesContext lcontext = FacesContext.getCurrentInstance();
    String lbeanName = getManagedBeanNameForFacelets(abeanClass);

    Object lobject = lcontext.getApplication().getELResolver().getValue(lcontext.getELContext(),
        null, lbeanName);
    return (T) lobject;
  }


  /**
   * Devuelve el objeto Application JSF.
   */
  public static Application getApplication() {
    return FacesContext.getCurrentInstance().getApplication();
  }


}
