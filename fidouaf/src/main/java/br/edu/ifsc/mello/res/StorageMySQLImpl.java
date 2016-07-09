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

import br.edu.ifsc.mello.res.db.RRUser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.ebayopensource.fido.uaf.storage.AuthenticatorRecord;
import org.ebayopensource.fido.uaf.storage.DuplicateKeyException;
import org.ebayopensource.fido.uaf.storage.RegistrationRecord;
import org.ebayopensource.fido.uaf.storage.StorageInterface;
import org.ebayopensource.fido.uaf.storage.SystemErrorException;

/**
 *
 * @author Emerson Ribeiro de Mello
 */
public class StorageMySQLImpl implements StorageInterface {

    private static StorageMySQLImpl instance;

    @PersistenceUnit
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("fidoPU");

    public static StorageMySQLImpl getInstance() {
        if (instance == null) {
            instance = new StorageMySQLImpl();
        }
        return instance;
    }

    public void storeServerDataString(String username, String serverDataString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getUsername(String serverDataString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void store(RegistrationRecord[] records) throws DuplicateKeyException, SystemErrorException {
        System.out.println("Start -->");

        if (records != null && records.length > 0) {
            assert emf != null;
            EntityManager em = emf.createEntityManager();
            
            for (int i = 0; i < records.length; i++) {
                System.out.println("records[" + i + "]: " + records[i].authenticator.toString());
                RRUser found = em.find(RRUser.class, records[i].authenticator.toString());
               
                //Users found = em.find(Users.class, records[i].authenticator.toString());
                if (found != null) {
                    throw new DuplicateKeyException();
                }

                RRUser newUser = new RRUser(records[i].authenticator.toString());
                RegistrationRecord r = records[i];

                newUser.setAraaid(r.authenticator.AAID);
                newUser.setARKeyID(r.authenticator.KeyID);
                newUser.setARdeviceId(r.authenticator.deviceId);
                newUser.setARstatus(r.authenticator.status);
                newUser.setARusername(r.authenticator.username);

                newUser.setSignCounter(r.SignCounter);
                newUser.setAuthenticatorVersion(r.AuthenticatorVersion);
                newUser.setTcDisplayPNGCharacteristics(r.tcDisplayPNGCharacteristics);
                newUser.setUsername(r.username);
                newUser.setUserId(r.userId);
                newUser.setPublicKey(r.PublicKey);
                newUser.setDeviceId(r.deviceId);
                newUser.setTimeStamp(r.timeStamp);
                newUser.setStatus(r.status);
                newUser.setAttestCert(r.attestCert);
                newUser.setAttestDataToSign(r.attestDataToSign);
                newUser.setAttestSignature(r.attestSignature);
                newUser.setAttestVerifiedStatus(r.attestVerifiedStatus);

                em.getTransaction().begin();
                em.persist(newUser);
                em.getTransaction().commit();

            }
        }

        System.out.println("<-- End");

    }

    public RegistrationRecord readRegistrationRecord(String key) {
        RegistrationRecord rr = null;
        
        EntityManager em = emf.createEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        Query query = em.createNamedQuery("RRUser.findByRriD");
        query.setParameter("rriD", key);
        List<RRUser> list = query.getResultList();
        if (list.size() > 0) {
            RRUser user = list.get(0);
            RegistrationRecord r = new RegistrationRecord();
            AuthenticatorRecord aR = new AuthenticatorRecord();
            aR.AAID = user.getAraaid();
            aR.KeyID = user.getARKeyID();
            aR.deviceId = user.getARdeviceId();
            aR.status = user.getARstatus();
            aR.username = user.getARusername();

            r.AuthenticatorVersion = user.getAuthenticatorVersion();
            r.PublicKey = user.getPublicKey();
            r.SignCounter = user.getSignCounter();
            r.attestCert = user.getAttestCert();
            r.attestDataToSign = user.getAttestDataToSign();
            r.attestSignature = user.getAttestSignature();
            r.attestVerifiedStatus = user.getAttestVerifiedStatus();
            r.deviceId = user.getDeviceId();
            r.status = user.getStatus();
            r.tcDisplayPNGCharacteristics = user.getTcDisplayPNGCharacteristics();
            r.timeStamp = user.getTimeStamp();
            r.userId = user.getUserId();
            r.username = user.getUsername();

            r.authenticator = aR;

            rr = r;
        }

        return rr;

    }

    public void update(RegistrationRecord[] records) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Map<String, RegistrationRecord> dbDump() {
        assert emf != null;
        EntityManager em = emf.createEntityManager();
        Map<String, RegistrationRecord> m = new HashMap<String, RegistrationRecord>();

        Query query = em.createNamedQuery("RRUser.findAll");

        List<RRUser> list = query.getResultList();
        for (RRUser user : list) {
            RegistrationRecord r = new RegistrationRecord();
            AuthenticatorRecord aR = new AuthenticatorRecord();
            aR.AAID = user.getAraaid();
            aR.KeyID = user.getARKeyID();
            aR.deviceId = user.getARdeviceId();
            aR.status = user.getARstatus();
            aR.username = user.getARusername();

            String key = aR.toString();

            r.AuthenticatorVersion = user.getAuthenticatorVersion();
            r.PublicKey = user.getPublicKey();

            r.SignCounter = user.getSignCounter();
            r.attestCert = user.getAttestCert();
            r.attestDataToSign = user.getAttestDataToSign();
            r.attestSignature = user.getAttestSignature();
            r.attestVerifiedStatus = user.getAttestVerifiedStatus();
            r.deviceId = user.getDeviceId();
            r.status = user.getStatus();
            r.tcDisplayPNGCharacteristics = user.getTcDisplayPNGCharacteristics();
            r.timeStamp = user.getTimeStamp();
            r.userId = user.getUserId();
            r.username = user.getUsername();

            r.authenticator = aR;
            m.put(key, r);
        }

        return m;
    }

    public void deleteRegistrationRecord(String key) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        RRUser user = em.find(RRUser.class, key);
        em.remove(user);
        em.getTransaction().commit();

    }

}
