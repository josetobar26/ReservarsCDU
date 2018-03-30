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
import edu.proyecto2.crud_escenarios.data.Deporte;
import edu.proyecto2.crud_escenarios.data.EspacioDeportivo;
import java.util.ArrayList;
import java.util.List;
import edu.proyecto2.crud_escenarios.data.ReservaEspacio;
import edu.proyecto2.crud_escenarios.jpa.exceptions.IllegalOrphanException;
import edu.proyecto2.crud_escenarios.jpa.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jose
 */
public class EspacioDeportivoJpaController implements Serializable {

    public EspacioDeportivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EspacioDeportivo espacioDeportivo) {
        if (espacioDeportivo.getDeporteList() == null) {
            espacioDeportivo.setDeporteList(new ArrayList<Deporte>());
        }
        if (espacioDeportivo.getReservaEspacioList() == null) {
            espacioDeportivo.setReservaEspacioList(new ArrayList<ReservaEspacio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Deporte> attachedDeporteList = new ArrayList<Deporte>();
            for (Deporte deporteListDeporteToAttach : espacioDeportivo.getDeporteList()) {
                deporteListDeporteToAttach = em.getReference(deporteListDeporteToAttach.getClass(), deporteListDeporteToAttach.getIdDeporte());
                attachedDeporteList.add(deporteListDeporteToAttach);
            }
            espacioDeportivo.setDeporteList(attachedDeporteList);
            List<ReservaEspacio> attachedReservaEspacioList = new ArrayList<ReservaEspacio>();
            for (ReservaEspacio reservaEspacioListReservaEspacioToAttach : espacioDeportivo.getReservaEspacioList()) {
                reservaEspacioListReservaEspacioToAttach = em.getReference(reservaEspacioListReservaEspacioToAttach.getClass(), reservaEspacioListReservaEspacioToAttach.getIdReserva());
                attachedReservaEspacioList.add(reservaEspacioListReservaEspacioToAttach);
            }
            espacioDeportivo.setReservaEspacioList(attachedReservaEspacioList);
            em.persist(espacioDeportivo);
            for (Deporte deporteListDeporte : espacioDeportivo.getDeporteList()) {
                deporteListDeporte.getEspacioDeportivoList().add(espacioDeportivo);
                deporteListDeporte = em.merge(deporteListDeporte);
            }
            for (ReservaEspacio reservaEspacioListReservaEspacio : espacioDeportivo.getReservaEspacioList()) {
                EspacioDeportivo oldIdEspacioOfReservaEspacioListReservaEspacio = reservaEspacioListReservaEspacio.getIdEspacio();
                reservaEspacioListReservaEspacio.setIdEspacio(espacioDeportivo);
                reservaEspacioListReservaEspacio = em.merge(reservaEspacioListReservaEspacio);
                if (oldIdEspacioOfReservaEspacioListReservaEspacio != null) {
                    oldIdEspacioOfReservaEspacioListReservaEspacio.getReservaEspacioList().remove(reservaEspacioListReservaEspacio);
                    oldIdEspacioOfReservaEspacioListReservaEspacio = em.merge(oldIdEspacioOfReservaEspacioListReservaEspacio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EspacioDeportivo espacioDeportivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EspacioDeportivo persistentEspacioDeportivo = em.find(EspacioDeportivo.class, espacioDeportivo.getIdEspacio());
            List<Deporte> deporteListOld = persistentEspacioDeportivo.getDeporteList();
            List<Deporte> deporteListNew = espacioDeportivo.getDeporteList();
            List<ReservaEspacio> reservaEspacioListOld = persistentEspacioDeportivo.getReservaEspacioList();
            List<ReservaEspacio> reservaEspacioListNew = espacioDeportivo.getReservaEspacioList();
            List<String> illegalOrphanMessages = null;
            for (ReservaEspacio reservaEspacioListOldReservaEspacio : reservaEspacioListOld) {
                if (!reservaEspacioListNew.contains(reservaEspacioListOldReservaEspacio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ReservaEspacio " + reservaEspacioListOldReservaEspacio + " since its idEspacio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Deporte> attachedDeporteListNew = new ArrayList<Deporte>();
            for (Deporte deporteListNewDeporteToAttach : deporteListNew) {
                deporteListNewDeporteToAttach = em.getReference(deporteListNewDeporteToAttach.getClass(), deporteListNewDeporteToAttach.getIdDeporte());
                attachedDeporteListNew.add(deporteListNewDeporteToAttach);
            }
            deporteListNew = attachedDeporteListNew;
            espacioDeportivo.setDeporteList(deporteListNew);
            List<ReservaEspacio> attachedReservaEspacioListNew = new ArrayList<ReservaEspacio>();
            for (ReservaEspacio reservaEspacioListNewReservaEspacioToAttach : reservaEspacioListNew) {
                reservaEspacioListNewReservaEspacioToAttach = em.getReference(reservaEspacioListNewReservaEspacioToAttach.getClass(), reservaEspacioListNewReservaEspacioToAttach.getIdReserva());
                attachedReservaEspacioListNew.add(reservaEspacioListNewReservaEspacioToAttach);
            }
            reservaEspacioListNew = attachedReservaEspacioListNew;
            espacioDeportivo.setReservaEspacioList(reservaEspacioListNew);
            espacioDeportivo = em.merge(espacioDeportivo);
            for (Deporte deporteListOldDeporte : deporteListOld) {
                if (!deporteListNew.contains(deporteListOldDeporte)) {
                    deporteListOldDeporte.getEspacioDeportivoList().remove(espacioDeportivo);
                    deporteListOldDeporte = em.merge(deporteListOldDeporte);
                }
            }
            for (Deporte deporteListNewDeporte : deporteListNew) {
                if (!deporteListOld.contains(deporteListNewDeporte)) {
                    deporteListNewDeporte.getEspacioDeportivoList().add(espacioDeportivo);
                    deporteListNewDeporte = em.merge(deporteListNewDeporte);
                }
            }
            for (ReservaEspacio reservaEspacioListNewReservaEspacio : reservaEspacioListNew) {
                if (!reservaEspacioListOld.contains(reservaEspacioListNewReservaEspacio)) {
                    EspacioDeportivo oldIdEspacioOfReservaEspacioListNewReservaEspacio = reservaEspacioListNewReservaEspacio.getIdEspacio();
                    reservaEspacioListNewReservaEspacio.setIdEspacio(espacioDeportivo);
                    reservaEspacioListNewReservaEspacio = em.merge(reservaEspacioListNewReservaEspacio);
                    if (oldIdEspacioOfReservaEspacioListNewReservaEspacio != null && !oldIdEspacioOfReservaEspacioListNewReservaEspacio.equals(espacioDeportivo)) {
                        oldIdEspacioOfReservaEspacioListNewReservaEspacio.getReservaEspacioList().remove(reservaEspacioListNewReservaEspacio);
                        oldIdEspacioOfReservaEspacioListNewReservaEspacio = em.merge(oldIdEspacioOfReservaEspacioListNewReservaEspacio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = espacioDeportivo.getIdEspacio();
                if (findEspacioDeportivo(id) == null) {
                    throw new NonexistentEntityException("The espacioDeportivo with id " + id + " no longer exists.");
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
            EspacioDeportivo espacioDeportivo;
            try {
                espacioDeportivo = em.getReference(EspacioDeportivo.class, id);
                espacioDeportivo.getIdEspacio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The espacioDeportivo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ReservaEspacio> reservaEspacioListOrphanCheck = espacioDeportivo.getReservaEspacioList();
            for (ReservaEspacio reservaEspacioListOrphanCheckReservaEspacio : reservaEspacioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EspacioDeportivo (" + espacioDeportivo + ") cannot be destroyed since the ReservaEspacio " + reservaEspacioListOrphanCheckReservaEspacio + " in its reservaEspacioList field has a non-nullable idEspacio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Deporte> deporteList = espacioDeportivo.getDeporteList();
            for (Deporte deporteListDeporte : deporteList) {
                deporteListDeporte.getEspacioDeportivoList().remove(espacioDeportivo);
                deporteListDeporte = em.merge(deporteListDeporte);
            }
            em.remove(espacioDeportivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EspacioDeportivo> findEspacioDeportivoEntities() {
        return findEspacioDeportivoEntities(true, -1, -1);
    }

    public List<EspacioDeportivo> findEspacioDeportivoEntities(int maxResults, int firstResult) {
        return findEspacioDeportivoEntities(false, maxResults, firstResult);
    }

    private List<EspacioDeportivo> findEspacioDeportivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EspacioDeportivo.class));
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

    public EspacioDeportivo findEspacioDeportivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EspacioDeportivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEspacioDeportivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EspacioDeportivo> rt = cq.from(EspacioDeportivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
