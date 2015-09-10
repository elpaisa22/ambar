/**
 * ambar-core-dictionary [11/03/2012 19:12:41]
 */
package org.ambar.core.dictionary.configuration;

import java.util.List;

import javax.annotation.Resource;

import org.ambar.core.dictionary.domain.entities.Entity;
import org.ambar.core.dictionary.domain.lookups.LookupMetaData;
import org.ambar.core.dictionary.domain.navigation.EntityInfo;
import org.ambar.core.dictionary.domain.navigation.Item;
import org.ambar.core.dictionary.domain.navigation.Module;
import org.ambar.core.dictionary.services.DictionaryServices;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * Test de los servicio de diccionario.
 * </p>
 *
 * @author Sebastian
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test_context.xml" })
public class ConfigurationReaderTest {

    @Resource(name = "dictionaryServices")
    private DictionaryServices dictionaryServices;

    /**
     * Test de la metadata del Cliente.
     * */
    @Test
    public void testConfigurationDictionary() {
    	Entity clienteMetaData = dictionaryServices.getEntityMetaDataById("Cliente");
    	Assert.assertNotNull(clienteMetaData);
    	Assert.assertEquals("El width de razonSocial del Cliente NO ES 300",
    		    "200",
    			dictionaryServices.getAttributeForEntity("Cliente", "razonsocial").getWidth().toString());


    	Module module = dictionaryServices.getModuleById("Principal");
    	Assert.assertEquals(module.getText(), "Modulo de Clientes");

    	List<Item> items = dictionaryServices.getItemsForModule("Principal");
    	Item item = items.get(0);
    	Assert.assertEquals(item.getText(), "Mis Clientes");

    	 EntityInfo clienteNavigation = dictionaryServices.getNavigationEntityInfoById("Cliente");
    	 Assert.assertEquals("Las subentidades del Cliente deben ser 1", clienteNavigation.getSubentities().size(), 1);

    	 LookupMetaData lkpCliente = dictionaryServices.getLookupById("lkp_cliente");
    	 Assert.assertEquals("El lookup debe tener 2 atributos", lkpCliente.getDisplayAttributes().size(), 2);
    }
}
