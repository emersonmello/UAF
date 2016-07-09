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
package br.edu.ifsc.mello.res;

import br.edu.ifsc.mello.res.db.Facets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author Emerson Ribeiro de Mello
 */
public class FacetsList {

    @PersistenceUnit
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("fidoPU");

    public String[] getTrustedIds(){
        List<String> trustedIds = new ArrayList<String>();
        
        EntityManager em = emf.createEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        
        Map<String, FacetsList> m = new HashMap<String, FacetsList>();
        Query query = em.createNamedQuery("Facets.findAll");
        
        List<Facets> list = query.getResultList();
         
        for (Facets facet : list) {
            trustedIds.add(facet.getFDesc());
        }
        
        String[] res = trustedIds.toArray(new String[1]);
        
        return res;
    }
    
    
    
    
    
    
}
