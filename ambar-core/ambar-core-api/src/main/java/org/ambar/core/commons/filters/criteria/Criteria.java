/**
 * ambar-core-api [11/10/2011 01:42:06]
 */
package org.ambar.core.commons.filters.criteria;

/**
 * <p>
 * Clase que provee métodos para facilitar el acceso a la API de filtrado.
 * </p>
 *
 * @author Sebastian
 *
 */
public final class Criteria {

	/**
	 * Constructor por default.
	 */
	private Criteria() {
	}

	/**
	 * Método para crear una expresión filtro unaria vacía.
	 * @return {@link EmptyUnaryFilter}
	 */
	public static EmptyUnaryFilter createUnary() {
		return new EmptyUnaryFilter();
	}

	/**
	 * Método para crear una expresión de filtro binaria vacía.
	 * @return {@link EmptyBinaryFilter}
	 */
	public static EmptyBinaryFilter createBinary() {
		return new EmptyBinaryFilter();
	}

	/**
	 * Método para crear una expresión de filtro ternaria vacía.
	 * @return {@link EmptyTernanyFilter}
	 */
	public static EmptyTernanyFilter createTernary() {
		return new EmptyTernanyFilter();
	}

	/**
	 * Método para crear una expresión de filtro de múltiples
	 * operandos vacía.
	 * @return {@link EmptyMultipleFilter}
	 */
	public static EmptyMultipleFilter createMultiple() {
		return new EmptyMultipleFilter();
	}

	/**
	 * Método para crear una expresión de filtros agrupados vacía.
	 * @return {@link EmptyGroupFilter}
	 */
	public static EmptyGroupFilter createGroup() {
		return new EmptyGroupFilter();
	}

	/**
	 * Método para crear una expresión de filtros agrupados vacía precedida
	 * por una negación.
	 * @return {@link EmptyNotGroupFilter}
	 */
	public static EmptyNotGroupFilter createNotGroup() {
		return new EmptyNotGroupFilter();
	}
}

