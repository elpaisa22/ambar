/**
 * ambar-core-api [11/10/2011 01:56:56]
 */
package org.ambar.core.commons.filters.helpers;

import org.ambar.core.commons.filters.BinaryFilter;
import org.ambar.core.commons.filters.GroupFilter;
import org.ambar.core.commons.filters.MultipleFilter;
import org.ambar.core.commons.filters.NotGroupFilter;
import org.ambar.core.commons.filters.Pager;
import org.ambar.core.commons.filters.QueryPredicate;
import org.ambar.core.commons.filters.TernaryFilter;
import org.ambar.core.commons.filters.UnaryFilter;

/**
 * <p>
 * Interfaz con las operaciones para la construcción de expresiones
 * JPQL basadas en la jerarquía de clases cuya raíz es
 * {@link org.ambar.core.commons.filters.Filter}.
 * </p>
 *
 * @author Sebastian
 *
 */
public interface FilterExpressionBuilder {
    /**
     * Objeto que encapsula la expresión para el filtro.
     * @return {@link QueryPredicate}
     */
    QueryPredicate getPredicate();

    /**
     * Método que construye una expresión JPQL de tipo unaria.
     * @param pFilter Objeto que encapsula los parámetros de la expresión
     * @param pObjectClass Clase del objeto al cual se aplicaran los filtros
     */
    void buildExpression(UnaryFilter pFilter, Class<?> pObjectClass);

    /**
     * Método que construye una expresión JPQL de tipo binaria.
     * @param pFilter Objeto que encapsula los parámetros de la expresión
     * @param pObjectClass Clase del objeto al cual se aplicaran los filtros
     */
    void buildExpression(BinaryFilter pFilter, Class<?> pObjectClass);

    /**
     * Método que construye una expresión JPQL de tipo ternaria.
     * @param pFilter Objeto que encapsula los parámetros de la expresión
     * @param pObjectClass Clase del objeto al cual se aplicaran los filtros
     */
    void buildExpression(TernaryFilter pFilter, Class<?> pObjectClass);

    /**
     * Método que construye una expresión JPQL de mútliples valores.
     * @param pFilter Objeto que encapsula los parámetros de la expresión
     * @param pObjectClass Clase del objeto al cual se aplicaran los filtros
     */
    void buildExpression(MultipleFilter pFilter, Class<?> pObjectClass);

    /**
     * Método que construye una expresión JPQL que agrupa expresiones de distinto
     * tipo encadenadas por un operador lógico.
     * @param pFilter Objeto que encapsula los parámetros de la expresión
     * @param pObjectClass Clase del objeto al cual se aplicaran los filtros
     */
    void buildExpression(GroupFilter pFilter, Class<?> pObjectClass);

    /**
     * Método que construye una expresión JPQL que niega una agrupación de expresiones de distinto
     * tipo encadenadas por un operador lógico.
     * @param pFilter Objeto que encapsula los parámetros de la expresión
     * @param pObjectClass Clase del objeto al cual se aplicaran los filtros
     */
    void buildExpression(NotGroupFilter pFilter, Class<?> pObjectClass);

    /**
     * Método que genera la cláusula <i>ORDER BY</i> de la expresión
     * y establece los parámetros de paginado.
     * @param pPager Información de ordenamiento y paginación
     */
    void processOrderAndPagination(Pager pPager);
}
