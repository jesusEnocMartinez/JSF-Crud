/*
 */
package com.empresaurios.crud.dao;

import com.empresaurios.crud.model.Usuario;
import com.empresaurios.crud.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class UsuarioDao {
    
    private Session session;
    
    public Usuario verificarDatos(Usuario usuario) {
        Usuario usuarioVerificado = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Usuario WHERE nombre_usuario = '" + usuario.getNombreUsuario()
                    + "' AND clave = '" + usuario.getClave() + "'";
            Query query = session.createQuery(hql);
            
            if(!query.list().isEmpty()) {
                usuarioVerificado = (Usuario) query.list().get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return usuarioVerificado;
        }
    }
    
    public void registry(Usuario todo) {
        
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(todo);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
}
