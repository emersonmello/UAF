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

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Emerson Ribeiro de Mello
 */
@Entity
@Table(name = "RRUser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RRUser.findAll", query = "SELECT r FROM RRUser r"),
    @NamedQuery(name = "RRUser.findByRriD", query = "SELECT r FROM RRUser r WHERE r.rriD = :rriD"),
    @NamedQuery(name = "RRUser.findByPublicKey", query = "SELECT r FROM RRUser r WHERE r.publicKey = :publicKey"),
    @NamedQuery(name = "RRUser.findBySignCounter", query = "SELECT r FROM RRUser r WHERE r.signCounter = :signCounter"),
    @NamedQuery(name = "RRUser.findByAuthenticatorVersion", query = "SELECT r FROM RRUser r WHERE r.authenticatorVersion = :authenticatorVersion"),
    @NamedQuery(name = "RRUser.findByTcDisplayPNGCharacteristics", query = "SELECT r FROM RRUser r WHERE r.tcDisplayPNGCharacteristics = :tcDisplayPNGCharacteristics"),
    @NamedQuery(name = "RRUser.findByUsername", query = "SELECT r FROM RRUser r WHERE r.username = :username"),
    @NamedQuery(name = "RRUser.findByUserId", query = "SELECT r FROM RRUser r WHERE r.userId = :userId"),
    @NamedQuery(name = "RRUser.findByDeviceId", query = "SELECT r FROM RRUser r WHERE r.deviceId = :deviceId"),
    @NamedQuery(name = "RRUser.findByTimeStamp", query = "SELECT r FROM RRUser r WHERE r.timeStamp = :timeStamp"),
    @NamedQuery(name = "RRUser.findByStatus", query = "SELECT r FROM RRUser r WHERE r.status = :status"),
    @NamedQuery(name = "RRUser.findByAttestCert", query = "SELECT r FROM RRUser r WHERE r.attestCert = :attestCert"),
    @NamedQuery(name = "RRUser.findByAttestDataToSign", query = "SELECT r FROM RRUser r WHERE r.attestDataToSign = :attestDataToSign"),
    @NamedQuery(name = "RRUser.findByAttestSignature", query = "SELECT r FROM RRUser r WHERE r.attestSignature = :attestSignature"),
    @NamedQuery(name = "RRUser.findByAttestVerifiedStatus", query = "SELECT r FROM RRUser r WHERE r.attestVerifiedStatus = :attestVerifiedStatus"),
    @NamedQuery(name = "RRUser.findByAraaid", query = "SELECT r FROM RRUser r WHERE r.araaid = :araaid"),
    @NamedQuery(name = "RRUser.findByARKeyID", query = "SELECT r FROM RRUser r WHERE r.aRKeyID = :aRKeyID"),
    @NamedQuery(name = "RRUser.findByARdeviceId", query = "SELECT r FROM RRUser r WHERE r.aRdeviceId = :aRdeviceId"),
    @NamedQuery(name = "RRUser.findByARusername", query = "SELECT r FROM RRUser r WHERE r.aRusername = :aRusername"),
    @NamedQuery(name = "RRUser.findByARstatus", query = "SELECT r FROM RRUser r WHERE r.aRstatus = :aRstatus")})
public class RRUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "rriD")
    private String rriD;
    @Column(name = "PublicKey")
    private String publicKey;
    @Column(name = "SignCounter")
    private String signCounter;
    @Column(name = "AuthenticatorVersion")
    private String authenticatorVersion;
    @Column(name = "tcDisplayPNGCharacteristics")
    private String tcDisplayPNGCharacteristics;
    @Column(name = "username")
    private String username;
    @Column(name = "userId")
    private String userId;
    @Column(name = "deviceId")
    private String deviceId;
    @Column(name = "timeStamp")
    private String timeStamp;
    @Column(name = "status")
    private String status;
    @Column(name = "attestCert")
    private String attestCert;
    @Column(name = "attestDataToSign")
    private String attestDataToSign;
    @Column(name = "attestSignature")
    private String attestSignature;
    @Column(name = "attestVerifiedStatus")
    private String attestVerifiedStatus;
    @Column(name = "ARAAID")
    private String araaid;
    @Column(name = "ARKeyID")
    private String aRKeyID;
    @Column(name = "ARdeviceId")
    private String aRdeviceId;
    @Column(name = "ARusername")
    private String aRusername;
    @Column(name = "ARstatus")
    private String aRstatus;

    public RRUser() {
    }

    public RRUser(String rriD) {
        this.rriD = rriD;
    }

    public String getRriD() {
        return rriD;
    }

    public void setRriD(String rriD) {
        this.rriD = rriD;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getSignCounter() {
        return signCounter;
    }

    public void setSignCounter(String signCounter) {
        this.signCounter = signCounter;
    }

    public String getAuthenticatorVersion() {
        return authenticatorVersion;
    }

    public void setAuthenticatorVersion(String authenticatorVersion) {
        this.authenticatorVersion = authenticatorVersion;
    }

    public String getTcDisplayPNGCharacteristics() {
        return tcDisplayPNGCharacteristics;
    }

    public void setTcDisplayPNGCharacteristics(String tcDisplayPNGCharacteristics) {
        this.tcDisplayPNGCharacteristics = tcDisplayPNGCharacteristics;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttestCert() {
        return attestCert;
    }

    public void setAttestCert(String attestCert) {
        this.attestCert = attestCert;
    }

    public String getAttestDataToSign() {
        return attestDataToSign;
    }

    public void setAttestDataToSign(String attestDataToSign) {
        this.attestDataToSign = attestDataToSign;
    }

    public String getAttestSignature() {
        return attestSignature;
    }

    public void setAttestSignature(String attestSignature) {
        this.attestSignature = attestSignature;
    }

    public String getAttestVerifiedStatus() {
        return attestVerifiedStatus;
    }

    public void setAttestVerifiedStatus(String attestVerifiedStatus) {
        this.attestVerifiedStatus = attestVerifiedStatus;
    }

    public String getAraaid() {
        return araaid;
    }

    public void setAraaid(String araaid) {
        this.araaid = araaid;
    }

    public String getARKeyID() {
        return aRKeyID;
    }

    public void setARKeyID(String aRKeyID) {
        this.aRKeyID = aRKeyID;
    }

    public String getARdeviceId() {
        return aRdeviceId;
    }

    public void setARdeviceId(String aRdeviceId) {
        this.aRdeviceId = aRdeviceId;
    }

    public String getARusername() {
        return aRusername;
    }

    public void setARusername(String aRusername) {
        this.aRusername = aRusername;
    }

    public String getARstatus() {
        return aRstatus;
    }

    public void setARstatus(String aRstatus) {
        this.aRstatus = aRstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rriD != null ? rriD.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RRUser)) {
            return false;
        }
        RRUser other = (RRUser) object;
        if ((this.rriD == null && other.rriD != null) || (this.rriD != null && !this.rriD.equals(other.rriD))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifsc.edu.mello.res.db.RRUser[ rriD=" + rriD + " ]";
    }
    
}
