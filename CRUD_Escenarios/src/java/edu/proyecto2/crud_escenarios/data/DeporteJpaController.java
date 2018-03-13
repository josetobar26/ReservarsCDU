/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.data;

import edu.proyecto2.crud_escenarios.data.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jose
 */
public class DeporteJpaController implements Serializable {

    public DeporteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Deporte deporte) {
        if (deporte.getEspacioDeportivoList() == null) {
            deporte.setEspacioDeportivoList(new ArrayList<EspacioDeportivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EspacioDeportivo> attachedEspacioDeportivoList = new ArrayList<EspacioDeportivo>();
            for (EspacioDeportivo espacioDeportivoListEspacioDeportivoToAttach : deporte.getEspacioDeportivoList()) {
                espacioDeportivoListEspacioDeportivoToAttach = em.getReference(espacioDeportivoListEspacioDeportivoToAttach.getClass(), espacioDeportivoListEspacioDeportivoToAttach.getIdEspacio());
                attachedEspacioDeportivoList.add(espacioDeportivoListEspacioDeportivoToAttach);
            }
            deporte.setEspacioDeportivoList(attachedEspacioDeportivoList);
            em.persist(deporte);
            for (EspacioDeportivo espacioDeportivoListEspacioDeportivo : deporte.getEspacioDeportivoList()) {
                espacioDeportivoListEspacioDeportivo.getDeporteList().add(deporte);
                espacioDeportivoListEspacioDeportivo = em.merge(espacioDeportivoListEspacioDeportivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Deporte deporte) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Deporte persistentDeporte = em.find(Deporte.class, deporte.getIdDeporte());
            List<EspacioDeportivo> espacioDeportivoListOld = persistentDeporte.getEspacioDeportivoList();
            List<EspacioDeportivo> espacioDeportivoListNew = deporte.getEspacioDeportivoList();
            List<EspacioDeportivo> attachedEspacioDeportivoListNew = new ArrayList<EspacioDeportivo>();
            for (EspacioDeportivo espacioDeportivoListNewEspacioDeportivoToAttach : espacioDeportivoListNew) {
                espacioDeportivoListNewEspacioDeportivoToAttach = em.getReference(espacioDeportivoListNewEspacioDeportivoToAttach.getClass(), espacioDeportivoListNewEspacioDeportivoToAttach.getIdEspacio());
                attachedEspacioDeportivoListNew.add(espacioDeportivoListNewEspacioDeportivoToAttach);
            }
            espacioDeportivoListNew = attachedEspacioDeportivoListNew;
            deporte.setEspacioDeportivoList(espacioDeportivoListNew);
            deporte = em.merge(deporte);
            for (EspacioDeportivo espacioDeportivoListOldEspacioDeportivo : espacioDeportivoListOld) {
                if (!espacioDeportivoListNew.contains(espacioDeportivoListOldEspacioDeportivo)) {
                    espacioDeportivoListOldEspacioDeportivo.getDeporteList().remove(deporte);
                    espacioDeportivoListOldEspacioDeportivo = em.merge(espacioDeportivoListOldEspacioDeportivo);
                }
            }
            for (EspacioDeportivo espacioDeportivoListNewEspacioDeportivo : espacioDeportivoListNew) {
                if (!espacioDeportivoListOld.contains(espacioDeportivoListNewEspacioDeportivo)) {
                    espacioDeportivoListNewEspacioDeportivo.getDeporteList().add(deporte);
                    espacioDeportivoListNewEspacioDeportivo = em.merge(espacioDeportivoListNewEspacioDeportivo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = deporte.getIdDeporte();
                if (findDeporte(id) == null) {
                    throw new NonexistentEntityException("The deporte with id " + id + " no longer exists.");
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
            Deporte deporte;
            try {
                deporte = em.getReference(Deporte.class, id);
                deporte.getIdDeporte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The deporte with id " + id + " no longer exists.", enfe);
            }
            List<EspacioDeportivo> espacioDeportivoList = deporte.getEspacioDeportivoList();
            for (EspacioDeportivo espacioDeportivoListEspacioDeportivo : espacioDeportivoList) {
                espacioDeportivoListEspacioDeportivo.getDeporteList().remove(deporte);
                espacioDeportivoListEspacioDeportivo = em.merge(espacioDeportivoListEspacioDeportivo);
            }
            em.remove(deporte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Deporte> findDeporteEntities() {
        return findDeporteEntities(true, -1, -1);
    }

    public List<Deporte> findDeporteEntities(int maxResults, int firstResult) {
        return findDeporteEntities(false, maxResults, firstResult);
    }

    private List<Deporte> findDeporteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Deporte.class));
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

    public Deporte findDeporte(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Deporte.class, id);
        } finally {
            em.close();
        }
    }

    public int getDeporteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Deporte> rt = cq.from(Deporte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
