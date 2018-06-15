/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.proyecto2.crud_escenarios.data.ReservaEspacio;
import edu.proyecto2.crud_escenarios.data.Usuario;
import edu.proyecto2.crud_escenarios.jpa.exceptions.IllegalOrphanException;
import edu.proyecto2.crud_escenarios.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jose
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getReservaEspacioList() == null) {
            usuario.setReservaEspacioList(new ArrayList<ReservaEspacio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ReservaEspacio> attachedReservaEspacioList = new ArrayList<ReservaEspacio>();
            for (ReservaEspacio reservaEspacioListReservaEspacioToAttach : usuario.getReservaEspacioList()) {
                reservaEspacioListReservaEspacioToAttach = em.getReference(reservaEspacioListReservaEspacioToAttach.getClass(), reservaEspacioListReservaEspacioToAttach.getIdReserva());
                attachedReservaEspacioList.add(reservaEspacioListReservaEspacioToAttach);
            }
            usuario.setReservaEspacioList(attachedReservaEspacioList);
            em.persist(usuario);
            for (ReservaEspacio reservaEspacioListReservaEspacio : usuario.getReservaEspacioList()) {
                Usuario oldIdUsuarioOfReservaEspacioListReservaEspacio = reservaEspacioListReservaEspacio.getIdUsuario();
                reservaEspacioListReservaEspacio.setIdUsuario(usuario);
                reservaEspacioListReservaEspacio = em.merge(reservaEspacioListReservaEspacio);
                if (oldIdUsuarioOfReservaEspacioListReservaEspacio != null) {
                    oldIdUsuarioOfReservaEspacioListReservaEspacio.getReservaEspacioList().remove(reservaEspacioListReservaEspacio);
                    oldIdUsuarioOfReservaEspacioListReservaEspacio = em.merge(oldIdUsuarioOfReservaEspacioListReservaEspacio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            List<ReservaEspacio> reservaEspacioListOld = persistentUsuario.getReservaEspacioList();
            List<ReservaEspacio> reservaEspacioListNew = usuario.getReservaEspacioList();
            List<String> illegalOrphanMessages = null;
            for (ReservaEspacio reservaEspacioListOldReservaEspacio : reservaEspacioListOld) {
                if (!reservaEspacioListNew.contains(reservaEspacioListOldReservaEspacio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReservaEspacio " + reservaEspacioListOldReservaEspacio + " since its idUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ReservaEspacio> attachedReservaEspacioListNew = new ArrayList<ReservaEspacio>();
            for (ReservaEspacio reservaEspacioListNewReservaEspacioToAttach : reservaEspacioListNew) {
                reservaEspacioListNewReservaEspacioToAttach = em.getReference(reservaEspacioListNewReservaEspacioToAttach.getClass(), reservaEspacioListNewReservaEspacioToAttach.getIdReserva());
                attachedReservaEspacioListNew.add(reservaEspacioListNewReservaEspacioToAttach);
            }
            reservaEspacioListNew = attachedReservaEspacioListNew;
            usuario.setReservaEspacioList(reservaEspacioListNew);
            usuario = em.merge(usuario);
            for (ReservaEspacio reservaEspacioListNewReservaEspacio : reservaEspacioListNew) {
                if (!reservaEspacioListOld.contains(reservaEspacioListNewReservaEspacio)) {
                    Usuario oldIdUsuarioOfReservaEspacioListNewReservaEspacio = reservaEspacioListNewReservaEspacio.getIdUsuario();
                    reservaEspacioListNewReservaEspacio.setIdUsuario(usuario);
                    reservaEspacioListNewReservaEspacio = em.merge(reservaEspacioListNewReservaEspacio);
                    if (oldIdUsuarioOfReservaEspacioListNewReservaEspacio != null && !oldIdUsuarioOfReservaEspacioListNewReservaEspacio.equals(usuario)) {
                        oldIdUsuarioOfReservaEspacioListNewReservaEspacio.getReservaEspacioList().remove(reservaEspacioListNewReservaEspacio);
                        oldIdUsuarioOfReservaEspacioListNewReservaEspacio = em.merge(oldIdUsuarioOfReservaEspacioListNewReservaEspacio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ReservaEspacio> reservaEspacioListOrphanCheck = usuario.getReservaEspacioList();
            for (ReservaEspacio reservaEspacioListOrphanCheckReservaEspacio : reservaEspacioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the ReservaEspacio " + reservaEspacioListOrphanCheckReservaEspacio + " in its reservaEspacioList field has a non-nullable idUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
