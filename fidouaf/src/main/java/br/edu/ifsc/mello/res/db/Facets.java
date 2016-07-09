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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "facets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facets.findAll", query = "SELECT f FROM Facets f"),
    @NamedQuery(name = "Facets.findByFId", query = "SELECT f FROM Facets f WHERE f.fId = :fId"),
    @NamedQuery(name = "Facets.findByFDesc", query = "SELECT f FROM Facets f WHERE f.fDesc = :fDesc")})
public class Facets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fId")
    private Integer fId;
    @Basic(optional = false)
    @Column(name = "fDesc")
    private String fDesc;

    public Facets() {
    }

    public Facets(Integer fId) {
        this.fId = fId;
    }

    public Facets(Integer fId, String fDesc) {
        this.fId = fId;
        this.fDesc = fDesc;
    }

    public Integer getFId() {
        return fId;
    }

    public void setFId(Integer fId) {
        this.fId = fId;
    }

    public String getFDesc() {
        return fDesc;
    }

    public void setFDesc(String fDesc) {
        this.fDesc = fDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fId != null ? fId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facets)) {
            return false;
        }
        Facets other = (Facets) object;
        if ((this.fId == null && other.fId != null) || (this.fId != null && !this.fId.equals(other.fId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.ifsc.edu.mello.res.db.Facets[ fId=" + fId + " ]";
    }
    
}
