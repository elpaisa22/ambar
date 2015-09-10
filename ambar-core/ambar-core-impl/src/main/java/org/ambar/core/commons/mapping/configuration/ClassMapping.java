/**
 * ambar-core-impl [11/10/2011 16:38:26]
 */
package org.ambar.core.commons.mapping.configuration;

/**
 * <p>Información relativa a clases con mapeos configurados.</p>
 *
 * @author Sebastian
 *
 */
public class ClassMapping {
	private String classAName;
	private String classBName;

	/**
	 * Constructor por default.
	 */
	public ClassMapping() {
		super();
	}

	/**
	 * Constructor que recibe los nombres de las clases a
	 * encapsular por parámetro.
	 * @param pClassAName	Clase A
	 * @param pClassBName	Clase B
	 */
	public ClassMapping(String pClassAName, String pClassBName) {
		super();
		this.classAName = pClassAName;
		this.classBName = pClassBName;
	}

	/**
	 * @return Retorna el valor del atributo classAName.
	 */
	public String getClassAName() {
		return this.classAName;
	}

	/**
	 * @param pClassAName Establece el valor del atributo classAName.
	 */
	public void setClassAName(String pClassAName) {
		this.classAName = pClassAName;
	}

	/**
	 * @return Retorna el valor del atributo classBName.
	 */
	public String getClassBName() {
		return this.classBName;
	}

	/**
	 * @param pClassBName Establece el valor del atributo classBName.
	 */
	public void setClassBName(String pClassBName) {
		this.classBName = pClassBName;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.classAName == null) ? 0 : this.classAName.hashCode());
		result = prime * result + ((this.classBName == null) ? 0 : this.classBName.hashCode());
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
		if (!(pObj instanceof ClassMapping)) {
			return false;
		}
		ClassMapping other = (ClassMapping) pObj;
		if (this.classAName == null) {
			if (other.classAName != null) {
				return false;
			}
		} else if (!this.classAName.equals(other.classAName)) {
			return false;
		}
		if (this.classBName == null) {
			if (other.classBName != null) {
				return false;
			}
		} else if (!this.classBName.equals(other.classBName)) {
			return false;
		}
		return true;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassMapping [classAName=");
		builder.append(this.classAName);
		builder.append(", classBName=");
		builder.append(this.classBName);
		builder.append("]");
		return builder.toString();
	}
}

