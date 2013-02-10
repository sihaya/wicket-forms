/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.desertspring.grunt.domain;

import nl.desertspring.grunt.data.Monument;
import nl.desertspring.grunt.data.MonumentRepository;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author sihaya
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/nl/desertspring/wicketforms/wicket-forms.xml", "classpath:/nl/desertspring/grunt/data/grunt-context.xml"})
public class IntegrationIT
{

    @Autowired
    private MonumentRepository monumentRepository;

    @Test
    public void givenAMonumentItPersists()
    {
        Monument monument = new Monument("bla", "test", "teestt");

        monumentRepository.persist(monument);

        Monument monumentActual = monumentRepository.getById(monument.getMonumentId());

        assertEquals(monument.getName(), monumentActual.getName());
    }
}
