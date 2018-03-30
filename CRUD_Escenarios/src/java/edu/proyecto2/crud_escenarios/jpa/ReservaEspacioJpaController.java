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
import edu.proyecto2.crud_escenarios.data.Usuario;
import edu.proyecto2.crud_escenarios.data.Horario;
import edu.proyecto2.crud_escenarios.data.ReservaEspacio;
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
            Usuario login = reservaEspacio.getLogin();
            if (login != null) {
                login = em.getReference(login.getClass(), login.getIdUsuario());
                reservaEspacio.setLogin(login);
            }
            Horario horario = reservaEspacio.getHorario();
            if (horario != null) {
                horario = em.getReference(horario.getClass(), horario.getIdHorario());
                reservaEspacio.setHorario(horario);
            }
            em.persist(reservaEspacio);
            if (idEspacio != null) {
                idEspacio.getReservaEspacioList().add(reservaEspacio);
                idEspacio = em.merge(idEspacio);
            }
            if (login != null) {
                login.getReservaEspacioList().add(reservaEspacio);
                login = em.merge(login);
            }
            if (horario != null) {
                ReservaEspacio oldIdReservaOfHorario = horario.getIdReserva();
                if (oldIdReservaOfHorario != null) {
                    oldIdReservaOfHorario.setHorario(null);
                    oldIdReservaOfHorario = em.merge(oldIdReservaOfHorario);
                }
                horario.setIdReserva(reservaEspacio);
                horario = em.merge(horario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ReservaEspacio reservaEspacio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ReservaEspacio persistentReservaEspacio = em.find(ReservaEspacio.class, reservaEspacio.getIdReserva());
            EspacioDeportivo idEspacioOld = persistentReservaEspacio.getIdEspacio();
            EspacioDeportivo idEspacioNew = reservaEspacio.getIdEspacio();
            Usuario loginOld = persistentReservaEspacio.getLogin();
            Usuario loginNew = reservaEspacio.getLogin();
            Horario horarioOld = persistentReservaEspacio.getHorario();
            Horario horarioNew = reservaEspacio.getHorario();
            List<String> illegalOrphanMessages = null;
            if (horarioOld != null && !horarioOld.equals(horarioNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Horario " + horarioOld + " since its idReserva field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEspacioNew != null) {
                idEspacioNew = em.getReference(idEspacioNew.getClass(), idEspacioNew.getIdEspacio());
                reservaEspacio.setIdEspacio(idEspacioNew);
            }
            if (loginNew != null) {
                loginNew = em.getReference(loginNew.getClass(), loginNew.getIdUsuario());
                reservaEspacio.setLogin(loginNew);
            }
            if (horarioNew != null) {
                horarioNew = em.getReference(horarioNew.getClass(), horarioNew.getIdHorario());
                reservaEspacio.setHorario(horarioNew);
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
            if (loginOld != null && !loginOld.equals(loginNew)) {
                loginOld.getReservaEspacioList().remove(reservaEspacio);
                loginOld = em.merge(loginOld);
            }
            if (loginNew != null && !loginNew.equals(loginOld)) {
                loginNew.getReservaEspacioList().add(reservaEspacio);
                loginNew = em.merge(loginNew);
            }
            if (horarioNew != null && !horarioNew.equals(horarioOld)) {
                ReservaEspacio oldIdReservaOfHorario = horarioNew.getIdReserva();
                if (oldIdReservaOfHorario != null) {
                    oldIdReservaOfHorario.setHorario(null);
                    oldIdReservaOfHorario = em.merge(oldIdReservaOfHorario);
                }
                horarioNew.setIdReserva(reservaEspacio);
                horarioNew = em.merge(horarioNew);
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Horario horarioOrphanCheck = reservaEspacio.getHorario();
            if (horarioOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ReservaEspacio (" + reservaEspacio + ") cannot be destroyed since the Horario " + horarioOrphanCheck + " in its horario field has a non-nullable idReserva field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            EspacioDeportivo idEspacio = reservaEspacio.getIdEspacio();
            if (idEspacio != null) {
                idEspacio.getReservaEspacioList().remove(reservaEspacio);
                idEspacio = em.merge(idEspacio);
            }
            Usuario login = reservaEspacio.getLogin();
            if (login != null) {
                login.getReservaEspacioList().remove(reservaEspacio);
                login = em.merge(login);
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
