/*
 */
package com.empresaurios.crud.bean;

import com.empresaurios.crud.dao.UsuarioDao;
import com.empresaurios.crud.model.Usuario;
import com.empresaurios.crud.util.CommonUtils;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UsuarioBean {
    
    private UsuarioDao usuarioDao;
    private Usuario usuario;
    
    public UsuarioBean() {
        usuarioDao = new UsuarioDao();
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String verificarDatos() {
        Usuario usuario;
        String resultado = "";
        try {
            usuario = usuarioDao.verificarDatos(this.usuario);
            if (usuario != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
                resultado = "autos";
            } else {
                resultado = "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public boolean verificarSesion() {
        boolean estado;

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") == null) {
            estado = false;
        } else {
            estado = true;
        }
        return estado;
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public void add() {
        usuarioDao.registry(this.usuario);
        this.usuario = new Usuario();
        util.redirectWithGet();
    }
    
    @ManagedProperty(value = "#{commonUtils}")
    private CommonUtils util;

    public void setUtil(CommonUtils util) {
        this.util = util;
    }
    
    public String navegaRegistro() {
        return "registro.xhtml";
    }
    
    public String navegaInicio() {
        return "index.xhtml";
    }
}
