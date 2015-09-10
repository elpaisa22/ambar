/**
 * ambar-core-impl [11/10/2011 16:33:56]
 */
package org.ambar.core.commons.mapping.configuration;

/**
 * <p>
 * Clase que encapsula información de los atributos mapeados.
 * </p>
 *
 * @author Sebastian
 *
 */
public class FieldMapping {

	private static final int HASH_VALUE_TRUE = 1231;
	private static final int HASH_VALUE_FALSE = 1237;

	private String fieldA;
	private String fieldB;
	private String fieldAParent;
	private boolean mapWithPersisted;
	private boolean detailCollection;
	private String detailParent;
	private boolean complexAssociation;
    private String queryAlias;
    private boolean caseInsensitive;
    private boolean addWildcards;

	/**
	 * Constructor por default.
	 */
	public FieldMapping() {
		super();
	}

	/**
	 * @return Retorna el valor del atributo fieldA.
	 */
	public String getFieldA() {
		return this.fieldA;
	}

	/**
	 * @param pFieldA Establece el valor del atributo fieldA.
	 */
	public void setFieldA(final String pFieldA) {
		this.fieldA = pFieldA;
	}

	/**
	 * @return Retorna el valor del atributo fieldB.
	 */
	public String getFieldB() {
		return this.fieldB;
	}

	/**
	 * @param pFieldB Establece el valor del atributo fieldB.
	 */
	public void setFieldB(final String pFieldB) {
		this.fieldB = pFieldB;
	}

	/**
	 * @return Retorna el valor del atributo fieldAParent.
	 */
	public String getFieldAParent() {
		return this.fieldAParent;
	}

	/**
	 * @param pFieldAParent Establece el valor del atributo fieldAParent.
	 */
	public void setFieldAParent(final String pFieldAParent) {
		this.fieldAParent = pFieldAParent;
	}

	/**
	 * @return Retorna el valor del atributo mapWithPersisted.
	 */
	public boolean isMapWithPersisted() {
		return this.mapWithPersisted;
	}

	/**
	 * @param pMapWithPersisted Establece el valor del atributo mapWithPersisted.
	 */
	public void setMapWithPersisted(final boolean pMapWithPersisted) {
		this.mapWithPersisted = pMapWithPersisted;
	}

	/**
	 * @return Retorna el valor del atributo detailCollection.
	 */
	public boolean isDetailCollection() {
		return this.detailCollection;
	}

	/**
	 * @param pDetailCollection Establece el valor del atributo detailCollection.
	 */
	public void setDetailCollection(boolean pDetailCollection) {
		this.detailCollection = pDetailCollection;
	}

	/**
	 * @return Retorna el valor del atributo detailParent.
	 */
	public String getDetailParent() {
		return this.detailParent;
	}

	/**
	 * @param pDetailParent Establece el valor del atributo detailParent.
	 */
	public void setDetailParent(String pDetailParent) {
		this.detailParent = pDetailParent;
	}

	/**
	 * @return Retorna el valor del atributo complexAssociation.
	 */
	public boolean isComplexAssociation() {
		return this.complexAssociation;
	}

	/**
	 * @param pComplexAssociation Establece el valor del atributo complexAssociation.
	 */
	public void setComplexAssociation(boolean pComplexAssociation) {
		this.complexAssociation = pComplexAssociation;
	}

    /**
     * @return Retorna el valor del atributo queryAlias.
     */
    public String getQueryAlias() {
        return queryAlias;
    }

    /**
     * @param pQueryAlias Establece el valor del atributo queryAlias.
     */
    public void setQueryAlias(final String pQueryAlias) {
        queryAlias = pQueryAlias;
    }

    /**
     * @return Retorna el valor del atributo caseInsensitive.
     */
    public boolean isCaseInsensitive() {
        return caseInsensitive;
    }

    /**
     * @param pCaseInsensitive Establece el valor del atributo caseInsensitive.
     */
    public void setCaseInsensitive(final boolean pCaseInsensitive) {
        caseInsensitive = pCaseInsensitive;
    }

    /**
     * @return Retorna el valor del atributo addWildcards.
     */
    public boolean isAddWildcards() {
        return addWildcards;
    }

    /**
     * @param pAddWildcards Establece el valor del atributo addWildcards.
     */
    public void setAddWildcards(final boolean pAddWildcards) {
        addWildcards = pAddWildcards;
    }

	/* (non-JSDoc)
      * @see java.lang.Object#hashCode()
      */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.fieldA == null) ? 0 : this.fieldA.hashCode());
		result = prime * result + ((this.fieldAParent == null) ? 0 : this.fieldAParent.hashCode());
		result = prime * result + ((this.fieldB == null) ? 0 : this.fieldB.hashCode());
		result = prime * result + (this.mapWithPersisted ? HASH_VALUE_TRUE : HASH_VALUE_FALSE);
		return result;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object pObj) {
		if (this == pObj) {
			return true;
		}
		if (pObj == null) {
			return false;
		}
		if (!(pObj instanceof FieldMapping)) {
			return false;
		}
		FieldMapping other = (FieldMapping) pObj;
		if (this.fieldA == null) {
			if (other.fieldA != null) {
				return false;
			}
		} else if (!this.fieldA.equals(other.fieldA)) {
			return false;
		}
		if (this.fieldAParent == null) {
			if (other.fieldAParent != null) {
				return false;
			}
		} else if (!this.fieldAParent.equals(other.fieldAParent)) {
			return false;
		}
		if (this.fieldB == null) {
			if (other.fieldB != null) {
				return false;
			}
		} else if (!this.fieldB.equals(other.fieldB)) {
			return false;
		}
		return (this.mapWithPersisted == other.mapWithPersisted);
	}

    /* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FieldMapping");
        sb.append("{fieldA='").append(fieldA).append('\'');
        sb.append(", fieldB='").append(fieldB).append('\'');
        sb.append(", fieldAParent='").append(fieldAParent).append('\'');
        sb.append(", mapWithPersisted=").append(mapWithPersisted);
        sb.append(", detailCollection=").append(detailCollection);
        sb.append(", detailParent='").append(detailParent).append('\'');
        sb.append(", complexAssociation=").append(complexAssociation);
        sb.append(", queryAlias='").append(queryAlias).append('\'');
        sb.append(", caseInsensitive=").append(caseInsensitive);
        sb.append(", addWildcards=").append(addWildcards);
        sb.append('}');
        return sb.toString();
    }
}

