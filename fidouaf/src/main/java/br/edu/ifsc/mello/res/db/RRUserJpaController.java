/*
 * Copyright 2016 Emerson Ribeiro de Mello 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.edu.ifsc.mello.res.db;

import br.edu.ifsc.mello.res.db.exceptions.NonexistentEntityException;
import br.edu.ifsc.mello.res.db.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Emerson Ribeiro de Mello
 */
public class RRUserJpaController implements Serializable {

    public RRUserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RRUser RRUser) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(RRUser);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRRUser(RRUser.getRriD()) != null) {
                throw new PreexistingEntityException("RRUser " + RRUser + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RRUser RRUser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RRUser = em.merge(RRUser);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = RRUser.getRriD();
                if (findRRUser(id) == null) {
                    throw new NonexistentEntityException("The rRUser with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RRUser RRUser;
            try {
                RRUser = em.getReference(RRUser.class, id);
                RRUser.getRriD();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The RRUser with id " + id + " no longer exists.", enfe);
            }
            em.remove(RRUser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RRUser> findRRUserEntities() {
        return findRRUserEntities(true, -1, -1);
    }

    public List<RRUser> findRRUserEntities(int maxResults, int firstResult) {
        return findRRUserEntities(false, maxResults, firstResult);
    }

    private List<RRUser> findRRUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RRUser.class));
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

    public RRUser findRRUser(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RRUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getRRUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RRUser> rt = cq.from(RRUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
