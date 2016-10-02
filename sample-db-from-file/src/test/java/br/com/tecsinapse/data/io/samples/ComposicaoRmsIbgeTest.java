package br.com.tecsinapse.data.io.samples;

import br.com.tecsinapse.exporter.importer.parser.SpreadsheetParser;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.File;
import java.util.List;

public class ComposicaoRmsIbgeTest {

    @Test
    public void importarTest() throws Exception {

        System.out.println("p1");
        File entrada = new File(ComposicaoRmsIbgeTest.class.getResource("/Composicao_RMs_RIDEs_AglomUrbanas_2010_07_31.xls").toURI());

        SpreadsheetParser<ComposicaoRmsIbge> parser = new SpreadsheetParser<>(ComposicaoRmsIbge.class, entrada);
        parser.setHeadersRows(1);

        EntityManager em = Persistence.createEntityManagerFactory("dataTestPU").createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();

        final List<ComposicaoRmsIbge> ibgeList = parser.parse();
        ibgeList.forEach(em::persist);

        em.flush();
        etx.commit();
   }

}