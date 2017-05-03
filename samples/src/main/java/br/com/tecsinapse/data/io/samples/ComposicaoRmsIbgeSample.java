package br.com.tecsinapse.data.io.samples;

import br.com.tecsinapse.data.io.samples.model.ComposicaoRmsIbge;
import br.com.tecsinapse.exporter.importer.parser.SpreadsheetParser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.File;
import java.util.List;

public class ComposicaoRmsIbgeSample implements Sample {

    public static void main(String[] args) throws Exception {
        new ComposicaoRmsIbgeSample().sample();
    }

    @Override
    public void sample() throws Exception {
        File entrada = new File(ComposicaoRmsIbgeSample.class.getResource("/Composicao_RMs_RIDEs_AglomUrbanas_2010_07_31.xls").toURI());

        SpreadsheetParser<ComposicaoRmsIbge> parser = new SpreadsheetParser<>(ComposicaoRmsIbge.class, entrada);
        parser.setHeadersRows(1);
        final List<ComposicaoRmsIbge> ibgeList = parser.parse();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dataTestPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();

        etx.begin();
        ibgeList.forEach(em::persist);
        etx.commit();

        em.close();
        emf.close();
   }
}