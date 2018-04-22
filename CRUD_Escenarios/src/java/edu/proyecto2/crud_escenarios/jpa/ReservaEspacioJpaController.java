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
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import edu.proyecto2.crud_escenarios.data.ReservaEspacio;
import edu.proyecto2.crud_escenarios.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jose
 */
public class ReservaEspacioJpaController implements Serializable {

    public ReservaEspacioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ReservaEspacio reservaEspacio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EspacioDeportivo idEspacio = reservaEspacio.getIdEspacio();
            if (idEspacio != null) {
                idEspacio = em.getReference(idEspacio.getClass(), idEspacio.getIdEspacio());
                reservaEspacio.setIdEspacio(idEspacio);
            }
            em.persist(reservaEspacio);
            if (idEspacio != null) {
                idEspacio.getReservaEspacioList().add(reservaEspacio);
                idEspacio = em.merge(idEspacio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReservaEspacio reservaEspacio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReservaEspacio persistentReservaEspacio = em.find(ReservaEspacio.class, reservaEspacio.getIdReserva());
            EspacioDeportivo idEspacioOld = persistentReservaEspacio.getIdEspacio();
            EspacioDeportivo idEspacioNew = reservaEspacio.getIdEspacio();
            if (idEspacioNew != null) {
                idEspacioNew = em.getReference(idEspacioNew.getClass(), idEspacioNew.getIdEspacio());
                reservaEspacio.setIdEspacio(idEspacioNew);
            }
            reservaEspacio = em.merge(reservaEspacio);
            if (idEspacioOld != null && !idEspacioOld.equals(idEspacioNew)) {
                idEspacioOld.getReservaEspacioList().remove(reservaEspacio);
                idEspacioOld = em.merge(idEspacioOld);
            }
            if (idEspacioNew != null && !idEspacioNew.equals(idEspacioOld)) {
                idEspacioNew.getReservaEspacioList().add(reservaEspacio);
                idEspacioNew = em.merge(idEspacioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reservaEspacio.getIdReserva();
                if (findReservaEspacio(id) == null) {
                    throw new NonexistentEntityException("The reservaEspacio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReservaEspacio reservaEspacio;
            try {
                reservaEspacio = em.getReference(ReservaEspacio.class, id);
                reservaEspacio.getIdReserva();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservaEspacio with id " + id + " no longer exists.", enfe);
            }
            EspacioDeportivo idEspacio = reservaEspacio.getIdEspacio();
            if (idEspacio != null) {
                idEspacio.getReservaEspacioList().remove(reservaEspacio);
                idEspacio = em.merge(idEspacio);
            }
            em.remove(reservaEspacio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ReservaEspacio> findReservaEspacioEntities() {
        return findReservaEspacioEntities(true, -1, -1);
    }

    public List<ReservaEspacio> findReservaEspacioEntities(int maxResults, int firstResult) {
        return findReservaEspacioEntities(false, maxResults, firstResult);
    }

    private List<ReservaEspacio> findReservaEspacioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReservaEspacio.class));
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

    public ReservaEspacio findReservaEspacio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ReservaEspacio.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservaEspacioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReservaEspacio> rt = cq.from(ReservaEspacio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
