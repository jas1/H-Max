package sample1.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SuppressWarnings("serial")
@SessionScoped
@ManagedBean(name="sessionBean")
public class SessionBean extends AbstractSessionBean implements Serializable {

}
